package ac.cn.saya.springbootpro.dao;

import ac.cn.saya.springbootpro.entity.NewsEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: NewsDAOTest
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-11-15 22:22
 * @Description:
 * NewsDAO 单元测试
 */

@SpringBootTest
public class NewsDAOTest {

    @Resource
    private NewsDAO newsDAO;

    /**
     * 使用mybatis plus自带的查询
     */
    @Test
    public void insertNews(){
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setContent("测试发布内容2");
        newsEntity.setLabel("mybatis-plus2");
        newsEntity.setSource("Pandora");
        newsEntity.setTopic("Mybatis-plus2");
        newsDAO.insert(newsEntity);
        // 直接从实体类中获取，无须其它繁琐的配置
        System.out.println("主键回填后的值："+newsEntity.getId());
    }


    /**
     * 使用自己写的sql
     */
    @Test
    public void getOneNews(){
        NewsEntity news = newsDAO.getOneNews(null);
        System.out.println(news);
    }

    /**
     * 查询label中包含'Golang'并且id小于4
     * WHERE label LIKE Golang% AND id < 4
     */
    @Test
    public void selectByWrapper(){
        QueryWrapper<NewsEntity> condition = new QueryWrapper<>();
        condition.likeRight("label","Golang").lt("id",4);
        List<NewsEntity> newsList = newsDAO.selectList(condition);
        newsList.forEach(System.out::println);
    }

}
