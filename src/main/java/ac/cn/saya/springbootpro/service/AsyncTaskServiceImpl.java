package ac.cn.saya.springbootpro.service;

import ac.cn.saya.springbootpro.dao.NewsDAO;
import ac.cn.saya.springbootpro.entity.NewsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Title: AsyncTaskServiceImpl
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 11/9/21 11:03
 * @Description:
 */
@Service
public class AsyncTaskServiceImpl implements AsyncTaskService{

    private static Logger logger = LoggerFactory.getLogger(AsyncTaskServiceImpl.class);

    @Resource
    private NewsDAO newsDAO;

    @Async("taskExecutor1")
    @Override
    public CompletableFuture<String> doTaskOne(String taskNo) throws Exception {
        logger.warn("开始任务：{}", taskNo);
        long start = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(10);
        long end = System.currentTimeMillis();
        logger.warn("完成任务：{}，耗时：{} 毫秒", taskNo, end - start);
        return CompletableFuture.completedFuture("任务完成");
    }

    @Async("taskExecutor2")
    @Override
    public CompletableFuture<String> doTaskTwo(String taskNo) throws Exception {
        logger.warn("开始任务：{}", taskNo);
        long start = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(10);
        long end = System.currentTimeMillis();
        logger.warn("完成任务：{}，耗时：{} 毫秒", taskNo, end - start);
        return CompletableFuture.completedFuture("任务完成");
    }

    @Override
    public String doTaskThree(String taskNo) {
        try {
            logger.warn("开始任务：{}", taskNo);
            long start = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(10);
            long end = System.currentTimeMillis();
            logger.warn("完成任务：{}，耗时：{} 毫秒", taskNo, end - start);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "任务完成";
    }

    @Transactional(readOnly = false,rollbackFor = RuntimeException.class)
    @Override
    public String addNews(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setContent("测试异步接口事务问题");
        newsEntity.setLabel("Transactional");
        newsEntity.setSource("Pandora");
        newsEntity.setTopic("异步接口事务");
        newsDAO.insert(newsEntity);
        throw new RuntimeException("手动抛出异常");
    }

}
