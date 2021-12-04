package ac.cn.saya.springbootpro.controllers;

import ac.cn.saya.springbootpro.config.CommandSchedulerPoolTask;
import ac.cn.saya.springbootpro.config.SendCommandThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ScheduledFuture;

/**
 * 动态设置定时任务
 * @Title: ScheduledController
 * @ProjectName spring-boot-pro
 * @Author saya
 * @Date: 2021/12/4 17:33
 * @Description: TODO
 */
@RestController
@RequestMapping(value = "/scheduled")
public class ScheduledController {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @PostMapping(value = "cron")
    public String create(@RequestParam(value = "cron") String cron){
        String taskCode = UUID.randomUUID().toString();
        SendCommandThread task = new SendCommandThread(taskCode);
        try {
            ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(task, new CronTrigger(cron));
            CommandSchedulerPoolTask.SCHEDUL_MAP.put(taskCode,schedule);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskCode;
    }

    @DeleteMapping(value = "cron")
    public String delete(@RequestParam(value = "code") String code){
        ScheduledFuture<?> future = CommandSchedulerPoolTask.SCHEDUL_MAP.get(code);
        if (Objects.isNull(future)){
            return code+"定时任务不存在";
        }
        future.cancel(true);
        CommandSchedulerPoolTask.SCHEDUL_MAP.remove(code);
        return "定时任务已经暂停";
    }

    @PutMapping(value = "cron")
    public String update(@RequestParam(value = "code") String code,@RequestParam(value = "cron") String cron){
        // 先将定时任务进行暂停
        ScheduledFuture<?> existFuture = CommandSchedulerPoolTask.SCHEDUL_MAP.get(code);
        if (Objects.isNull(existFuture)){
            return code+"定时任务不存在";
        }
        existFuture.cancel(true);
        SendCommandThread task = new SendCommandThread(code);
        ScheduledFuture<?> future=threadPoolTaskScheduler.schedule(task, new CronTrigger(cron));
        CommandSchedulerPoolTask.SCHEDUL_MAP.put("task1",future);
        return "定时任务已修改";
    }

}
