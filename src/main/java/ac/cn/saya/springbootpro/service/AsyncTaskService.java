package ac.cn.saya.springbootpro.service;


import java.util.concurrent.CompletableFuture;

/**
 * @Title: AsyncTaskService
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 11/9/21 11:14
 * @Description:
 */

public interface AsyncTaskService {

    public CompletableFuture<String> doTaskOne(String taskNo) throws Exception;

    public CompletableFuture<String> doTaskTwo(String taskNo) throws Exception;

    public String doTaskThree(String taskNo);
}
