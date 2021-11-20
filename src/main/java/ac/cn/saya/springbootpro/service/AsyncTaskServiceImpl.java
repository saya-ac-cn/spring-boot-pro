package ac.cn.saya.springbootpro.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
