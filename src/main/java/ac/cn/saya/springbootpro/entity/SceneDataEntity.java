package ac.cn.saya.springbootpro.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 子场景（建筑）
 *
 * @author lnk
 * @version 0.0.1
 * @date 2021/8/16 11:30
 * @description
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SceneDataEntity {


    public Long uuid;

    /**
     * campusbuilder ID
     */
    public String campusBuilderId;

    /**
     * 建筑名或者物体名
     */
    public String name;

    /**
     * 用户设置的建筑名或者物体名
     */
    public String settingName;

    /**
     * 与模型建筑里面的字段保持一直，放弃驼峰命名
     */
    public String userid;

    /**
     * 所属场景UUID，外键
     */
    public Long parentSceneUUID;

    /**
     * 默认视角
     */
    public String defaultCamInfo;

    /**
     * 用户设置视角
     */
    public String configCamInfo;

    /**
     * 数据类型（建筑，楼层，房间，设备）
     */
    public String dataType;

    /**
     * 父级节点id
     */
    public String parentCBID;

    /**
     * 物体所属房间的campusbuilder ID
     */
    public String belongRoom;

    public String properties;

    /**
     * 子场景UUID，外键（在主分场景下，该值可以唯一确定子场景的版本信息）
     */
    public Long childrenSceneUUID;

    /**
     * 创建时间
     */
    private Date createTime;

}
