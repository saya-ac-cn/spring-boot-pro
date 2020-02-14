package ac.cn.saya.springbootpro.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Title: ControllerAcepect
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2020-02-14 12:24
 * @Description:
 */
@Component
@Aspect
public class ControllerAcepect {

    /**
     * 定义切入点，切入点为com.example.demo.aop.AopController中的所有函数
     *通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * ac.cn.saya.springbootpro.controllers..*.*(..))")
    public void BrokerAspect(){

    }

    /**
     * @description  在连接点执行之前执行的通知
     */
    @Before("BrokerAspect()")
    public void doBeforeGame(){
        System.out.println("请求Controller！");
    }

}
