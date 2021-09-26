package ac.cn.saya.springbootpro.tools;

import ac.cn.saya.springbootpro.entity.SceneDataEntity;
import ac.cn.saya.springbootpro.entity.SceneRecordEntity;
import ac.cn.saya.springbootpro.service.SceneTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Title: BuildSceneTreeUtil
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 9/26/21 13:44
 * @Description:
 */

public class BuildSceneTreeUtil {

    private final long uuid;

    private final boolean flag;

    private final List<SceneRecordEntity> allSceneRecord;

    private final List<SceneDataEntity> allSceneData;

    /**
     * 主场景
     */
    private SceneRecordEntity mainSceneRecord;

    public BuildSceneTreeUtil(SceneRecordEntity mainSceneRecord,boolean flag,List<SceneRecordEntity> allSceneRecord, List<SceneDataEntity> allSceneData) {
        if (Objects.isNull(mainSceneRecord)||CollectionUtils.isEmpty(allSceneRecord) || CollectionUtils.isEmpty(allSceneData)){
            throw new NullPointerException("场景或者场景数据为空");
        }
        this.mainSceneRecord = mainSceneRecord;
        this.uuid = mainSceneRecord.getUuid();
        this.flag = flag;
        this.allSceneRecord = allSceneRecord;
        this.allSceneData = allSceneData;
    }


    /**
     * 构造场景一级
     * @return
     */
    public JSONObject generateScene(){
        JSONObject sceneJSON = (JSONObject) JSON.toJSON(mainSceneRecord);
        JSONArray buildAndPark = generateBuilding(sceneJSON);
        sceneJSON.put("children", buildAndPark);
        sceneJSON.put("dataType", "scene");
        sceneJSON.put("parentSceneUUID", uuid);
        sceneJSON.put("url", mainSceneRecord.getSceneCode() + "/" + mainSceneRecord.getVersion());
        return sceneJSON;
    }

    /**
     * 构造建筑园区二级
     * @return
     */
    public JSONArray generateBuilding(JSONObject sceneJSON){
        // 查询该场景(主+子)下的所有建筑及室外信息
        List<SceneDataEntity> buildingAndParkDatas = allSceneData.stream().filter(e -> ("0".equals(e.getParentCBID()))).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(buildingAndParkDatas)) {
            return null;
        }
        // 逐一处理每个建筑或者室外(单个)
        JSONArray buildingsJSON = new JSONArray();
        for (SceneDataEntity dataItem : buildingAndParkDatas) {
            // 这里需要考虑有子场景的情况
            if (Objects.isNull(dataItem.getChildrenSceneUUID())) {
                // 无子场景
                generateThings(dataItem, uuid, null, sceneJSON, buildingsJSON);
            } else {
                // 有子场景，通过childrenSceneUUID在scene_record唯一且为启用的条件查询一条场景
                Optional<SceneRecordEntity> childSceneDataOp = allSceneRecord.stream().filter(e -> 1 == e.getStatus() && Objects.equals(e.getUuid(), dataItem.getChildrenSceneUUID())).findFirst();
                // 关联的场景为空，或者未启用，则这颗树不予展示
                if (!childSceneDataOp.isPresent()) {
                    continue;
                }
                SceneRecordEntity childSceneRecord = childSceneDataOp.get();
                // 然后通过场景的uuid，在scene_data查询，条件是parent_scene_record_uuid = uuid，为建筑的数据。注意：子场景的下只有一个建筑
                Optional<SceneDataEntity> childBuildingAndParkOp = allSceneData.stream().filter(e -> ("0".equals(e.getParentCBID()) && Objects.equals(dataItem.getChildrenSceneUUID(), e.getParentSceneUUID()))).findFirst();

                if (!childBuildingAndParkOp.isPresent()) {
                    continue;
                }
                generateThings(childBuildingAndParkOp.get(),dataItem.getChildrenSceneUUID(), childSceneRecord, sceneJSON, buildingsJSON);
            }
        }
        // TODO 这里的户外建筑信息来源于下一级的数据挂载
        if (sceneJSON.containsKey(SceneTypeEnum.OUTDOOR.getVal())) {
            // 将园区的层级保持与建筑物是同一级
            buildingsJSON.add(sceneJSON.getJSONObject(SceneTypeEnum.OUTDOOR.getVal()));
            sceneJSON.remove(SceneTypeEnum.OUTDOOR.getVal());
        }
        return buildingsJSON;
    }


    /**
     * 构建本建筑下的所有楼层及物体信息
     *
     * @param buildInfoItem          本建筑基本信息
     * @param sceneUuid        物体归属的场景uuid
     * @param childSceneRecord 子场景数据信息
     * @param sceneJSON        主场景信息
     * @param buildingsJSON    用于装数据返回的结果集
     */
    private void generateThings(SceneDataEntity buildInfoItem,Long sceneUuid, SceneRecordEntity childSceneRecord, JSONObject sceneJSON, JSONArray buildingsJSON) {
        // 查询本建筑下的楼层或者室外数据
        List<SceneDataEntity> plansAndOutDoor = allSceneData.stream().filter(e->Objects.equals(e.getParentSceneUUID(),sceneUuid)&&Objects.equals(e.getParentCBID(),buildInfoItem.getCampusBuilderId())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(plansAndOutDoor)) {
            return;
        }
        // 处理楼栋数据
        if ((SceneTypeEnum.BUILDING.getVal()).equals(buildInfoItem.getDataType())) {
            // 每一栋下的数据
            JSONObject buildingJSON = (JSONObject) JSON.toJSON(buildInfoItem);
            buildingJSON.put("createTime", buildInfoItem.getCreateTime());
            //buildingJSON.put("createTime", DateFormatUtils.format(buildInfoItem.getCreateTime(), DateUtils.YYYY_MM_DD_HH_MM_SS));
            JSONArray plansJson = new JSONArray();
            // 楼层数据
            for (SceneDataEntity planItem : plansAndOutDoor) {
                JSONObject planJSON = (JSONObject) JSON.toJSON(planItem);
                JSONArray planJSONS = new JSONArray();
                List<SceneDataEntity> basic = null;
                // 根据flag控制物体是否显示
                if (flag) {
                    basic = allSceneData.stream().filter(e->Objects.equals(e.getParentSceneUUID(),sceneUuid)&&Objects.equals(e.getParentCBID(),planItem.getCampusBuilderId())).collect(Collectors.toList());
                }else{
                    // 只查询最底层房间数据
                    basic = allSceneData.stream().filter(e->(SceneTypeEnum.ROOM.getVal()).equals(e.getDataType())&&Objects.equals(e.getParentSceneUUID(),sceneUuid)&&Objects.equals(e.getParentCBID(),planItem.getCampusBuilderId())).collect(Collectors.toList());
                }
                if (CollectionUtils.isEmpty(basic)) {
                    plansJson.add(planJSON);
                    continue;
                }
                planJSONS.addAll(basic);
                planJSON.put("children", planJSONS);
                // 每一个楼层的数据
                plansJson.add(planJSON);
            }
            // 本栋楼下的场景
            buildingJSON.put("children", plansJson);
            if (Objects.isNull(childSceneRecord)) {
                // 本建筑附属于哪个主场景节点
                buildingJSON.put("underSceneId", buildInfoItem.getParentSceneUUID());
            } else {
                // 对于子场景的情况进行特殊处理
                buildingJSON.put("childrenSceneUUID", buildInfoItem.getParentSceneUUID());
                buildingJSON.put("version", childSceneRecord.getVersion());
                buildingJSON.put("url", childSceneRecord.getSceneCode() + "/" + childSceneRecord.getVersion());
                buildingJSON.put("underSceneId", sceneJSON.getLong("uuid"));
            }
            // 处理完毕每栋建筑
            buildingsJSON.add(buildingJSON);
        }
        // 处理园区数据
        if ("park".equals(buildInfoItem.getDataType())) {
            JSONObject outDoorJson = (JSONObject) JSON.toJSON(buildInfoItem);
            JSONArray parkJSONS = new JSONArray();
            parkJSONS.addAll(plansAndOutDoor);
            // flag控制底层房间物体显示
            if (flag) {
                outDoorJson.put("children", parkJSONS);
            } else {
                // 园区一级下面只显示房间
                List<SceneDataEntity> onlyDisplayRooms = plansAndOutDoor.stream().filter(e -> (SceneTypeEnum.ROOM.getVal()).equals(e.getDataType())).collect(Collectors.toList());
                outDoorJson.put("children", onlyDisplayRooms);
            }
            outDoorJson.remove("createTime");
            // TODO 将场景下的户外信息挂载到场景下，作用：在这里挂载后，调用者可以方便设置它与建筑同级
            sceneJSON.put(SceneTypeEnum.OUTDOOR.getVal(), outDoorJson);
        }
    }

}
