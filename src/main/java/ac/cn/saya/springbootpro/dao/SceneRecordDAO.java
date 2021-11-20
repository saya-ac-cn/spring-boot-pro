package ac.cn.saya.springbootpro.dao;


import ac.cn.saya.springbootpro.entity.SceneRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 主场景Mapper
 *
 * @author lnk
 * @version 0.0.1
 * @date 2021/8/16 14:14
 * @description
 */
@Mapper
public interface SceneRecordDAO {

    /**
     * 添加场景
     *
     * @param param
     */
    public void insertScene(SceneRecordEntity param);

    /**
     * 根据场景code查询最新的版本信息
     *
     * @param sceneCode 场景编码
     * @return 最新版本的主场景
     */
    public SceneRecordEntity queryLatestVersion(@Param("sceneCode") String sceneCode);

    /**
     * 查询主场景列表信息（如果状态status->2，则创建时间，修改时间为空）
     *
     * @param param 查询条件（场景名，场景id）
     * @return
     */
    public List<SceneRecordEntity> query(SceneRecordEntity param);

    public SceneRecordEntity queryOneByUuid(@Param("uuid") Long uuid);

    @Deprecated
    public SceneRecordEntity queryOneByCode(@Param("sceneCode") String sceneCode);

    public void deleteByUuid(@Param("uuid") Long uuid);

    public void resetVersion(@Param("sceneCode") String sceneCode);

    public void switchVersion(@Param("uuid") Long uuid);

    /**
     * 获取当前启用的场景版本数据
     *
     * @param sceneCode 场景code
     * @return 场景数据
     * @author lnk
     * @date 2021/9/16 13:43
     * @description
     */
    public SceneRecordEntity queryActiveVersion(@Param("sceneCode") String sceneCode);

}
