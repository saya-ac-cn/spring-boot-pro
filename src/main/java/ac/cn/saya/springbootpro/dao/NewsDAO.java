package ac.cn.saya.springbootpro.dao;

import ac.cn.saya.springbootpro.entity.NewsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Title: NewsDAO
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-11-15 22:04
 * @Description:
 * BaseMapper 中封装了很多关于增删该查的方法，后期自动生成，直接调用接口中的相关方法即可完成相应的操作
 */
@Mapper
@Repository("newsDAO")
public interface NewsDAO extends BaseMapper<NewsEntity> {

    public NewsEntity getOneNews(NewsEntity entity);

}
