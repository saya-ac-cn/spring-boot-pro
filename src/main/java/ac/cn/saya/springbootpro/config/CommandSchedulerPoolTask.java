package ac.cn.saya.springbootpro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 定时任务
 * @Title: CommandSchedulerPoolTask
 * @ProjectName spring-boot-pro
 * @Author saya
 * @Date: 2021/12/4 17:26
 * @Description: TODO
 * https://blog.csdn.net/szzssz/article/details/81202363
 */
@Configuration
public class CommandSchedulerPoolTask {

    public static final Map<String, ScheduledFuture<?>> SCHEDUL_MAP = new HashMap<>();

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

}
