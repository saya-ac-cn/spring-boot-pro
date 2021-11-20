package ac.cn.saya.springbootpro.entity;

import lombok.*;

import java.util.Date;

/**
 * 主场景实体
 *
 * @author lnk
 * @version 0.0.1
 * @date 2021/8/16 11:04
 * @description
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SceneRecordEntity {

    private Long uuid;

    /**
     * 场景id
     */
    private String cbSceneId;

    /**
     * 场景名，用户上传时指定的场景名
     */
    private String name;

    /**
     * 默认的场景名，也就是scene.json中的文件名
     */
    private String defaultName;

    /**
     * 主场景版本号
     */
    private Integer version;

    /**
     * 归属项目id
     */
    private Integer projectId;

    /**
     * 场景code
     */
    private String sceneCode;

    /**
     * 默认视角
     */
    private String defaultCamInfo;

    /**
     * 用户设置视角
     */
    private String configCamInfo;

    /**
     * 1->使用中，2->未使用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 地图位置信息
     */
    private String mapInfo;

    /**
     * 子场景归属主场景uuid，主场景数据时本栏为空
     */
    private Long parentUuid;

}
