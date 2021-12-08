package ac.cn.saya.springbootpro.controllers;

import ac.cn.saya.springbootpro.service.AsyncTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * @Title: AsyncController
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 11/9/21 11:03
 * @Description:
 */
@RestController
@RequestMapping(value = "async")
public class AsyncController {

    @Autowired
    private AsyncTaskService asyncTaskService;

    @Autowired
    private Executor taskExecutor2;

    @Autowired
    private Executor requestExecutor;

    @GetMapping("task1")
    public String doTaskOne() throws Exception {
        long start = System.currentTimeMillis();

        // 线程池1
        CompletableFuture<String> task1 = asyncTaskService.doTaskOne("1");
        CompletableFuture<String> task2 = asyncTaskService.doTaskOne("2");
        CompletableFuture<String> task3 = asyncTaskService.doTaskOne("3");

        // 一起执行
        CompletableFuture.allOf(task1, task2, task3).join();

        long end = System.currentTimeMillis();

        return ("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

    @GetMapping("task2")
    public String doTaskTwo() throws Exception {
        new Thread(()->{
            long start = System.currentTimeMillis();
            try {
                // 线程池2
                CompletableFuture<String> task1 = asyncTaskService.doTaskTwo("1");
                CompletableFuture<String> task2 = asyncTaskService.doTaskTwo("2");
                CompletableFuture<String> task3 = asyncTaskService.doTaskTwo("3");

                // 一起执行
                CompletableFuture.allOf(task1, task2, task3).join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");

        }).start();
        return "执行完毕";
    }

    @GetMapping("task3")
    public String doTaskThree() throws Exception {
        new Thread(()->{
            long start = System.currentTimeMillis();
            try {
                List<String> task = Arrays.asList("1", "2", "3");
                task.stream().map(e -> CompletableFuture.runAsync(() -> asyncTaskService.doTaskThree(e), taskExecutor2)).collect(Collectors.toList()).stream().map(CompletableFuture::join).collect(Collectors.toList());
            } catch (Exception e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");

        }).start();
        return "执行完毕";
    }

    @GetMapping("task4")
    public String doTaskFour() {
        requestExecutor.execute(()->{
            long start = System.currentTimeMillis();
            try {
                List<String> task = Arrays.asList("1", "2", "3");
                task.stream().map(e -> CompletableFuture.runAsync(() -> asyncTaskService.doTaskThree(e), taskExecutor2)).collect(Collectors.toList()).stream().map(CompletableFuture::join).collect(Collectors.toList());
            } catch (Exception e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
        });
        return "执行完毕";
    }

    @GetMapping("task5")
    public String doTaskFive() {
        requestExecutor.execute(()->{
            long start = System.currentTimeMillis();
            try {
                CompletableFuture.runAsync(()->asyncTaskService.addNews()).join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
        });
        return "执行完毕";
    }

}
