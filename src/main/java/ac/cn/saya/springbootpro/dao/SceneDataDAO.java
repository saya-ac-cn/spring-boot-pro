package ac.cn.saya.springbootpro.dao;



import ac.cn.saya.springbootpro.entity.SceneDataEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author lnk
 * @version 0.0.1
 * @title 主场景Mapper
 * @date 2021/8/16 14:08
 * @description
 */
@Mapper
public interface SceneDataDAO {

    /**
     * 添加建筑
     *
     * @param list
     */
    public void batchInsertBuilding(List<SceneDataEntity> list);

    public List<SceneDataEntity> quereByParenUuid(List<Long> list);

    public List<SceneDataEntity> query(SceneDataEntity param);

    /**
     * 不带时间的返回
     * TODO 后期记得修改
     */
    public List<SceneDataEntity> queryWithoutDate(SceneDataEntity param);

    public SceneDataEntity selectOneByUuid(Long uuid);

    public void delete(SceneDataEntity param);

    public void deleteByUuid(Long uuid);

    public void deleteByParentSceneUUID(Long uuid);

}
