package ac.cn.saya.springbootpro.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Title: WebMvcConfig
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 6/20/21 23:40
 * @Description:配置拦截器，增加校验拦截器
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private CheckParamsInterceptor checkSourceInterceptor;

    /**
     * 增加校验拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 这个地方可以定义拦截器的具体的路径,启动项目时就会执行此方法
        registry.addInterceptor(checkSourceInterceptor).addPathPatterns("/**");
    }
}
