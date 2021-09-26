package ac.cn.saya.springbootpro.service;

import ac.cn.saya.springbootpro.dao.SceneDataDAO;
import ac.cn.saya.springbootpro.dao.SceneRecordDAO;
import ac.cn.saya.springbootpro.entity.SceneDataEntity;
import ac.cn.saya.springbootpro.entity.SceneRecordEntity;
import ac.cn.saya.springbootpro.tools.BuildSceneTreeUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Title: SceneServiceImpl
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 9/25/21 21:40
 * @Description:
 */
@Service
public class SceneServiceImpl implements SceneService{

    @Resource
    private SceneRecordDAO sceneRecordDAO;

    @Resource
    private SceneDataDAO sceneDataDAO;


    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<SceneRecordEntity> queryScenesList(Integer projectId) {
        // 查询每个项目下的场景
        SceneRecordEntity sceneRecordWhere = new SceneRecordEntity();
        // 由于只有主场景下才有项目id，子项目是没有项目id的，所以可以通过项目id查询出所有的主场景数据
        sceneRecordWhere.setProjectId(projectId);
        // 必须是已经启用的
        sceneRecordWhere.setStatus(1);
        // 查询本项目下的场景
        return sceneRecordDAO.query(sceneRecordWhere);
    }

    /**
     * 查询指定场景的树结构
     *
     * @param uuid 场景id
     * @return 场景树
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public JSONObject showScene(Long uuid, boolean flag) {
        // 前置条件，查询出当前的场景数据
        SceneRecordEntity sceneRecord = sceneRecordDAO.queryOneByUuid(uuid);
        if (Objects.isNull(sceneRecord)) {
            return null;
        }
        // 后置条件，查询本场景下已经上传过的子场景
        SceneRecordEntity sceneRecordWhere = new SceneRecordEntity();
        sceneRecordWhere.setParentUuid(uuid);
        List<SceneRecordEntity> allSceneRecord = sceneRecordDAO.query(sceneRecordWhere);
        // 把主场景和子场景混合在一起
        allSceneRecord.add(sceneRecord);

        // 所有场景的唯一标识
        List<Long> sceneIds = allSceneRecord.stream().map(SceneRecordEntity::getUuid).collect(Collectors.toList());
        // 通过场景id在场景数据表里面做一次全量查询
        List<SceneDataEntity> allSceneData = sceneDataDAO.quereByParenUuid(sceneIds);
        if (CollectionUtils.isEmpty(allSceneData)){
            return null;
        }

        BuildSceneTreeUtil tools = new BuildSceneTreeUtil(sceneRecord,flag,allSceneRecord, allSceneData);
        return tools.generateScene();
    }

}
