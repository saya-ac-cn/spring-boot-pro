package ac.cn.saya.springbootpro.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
/**
 * @Title: ValidatorConfig
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 6/27/21 10:22
 * @Description:
 * 校验模式配置
 * 系统有两种校验模式（https://www.cnblogs.com/mooba/p/11276062.html）
 * 普通模式（默认是这个模式）: 会校验完所有的属性，然后返回所有的验证失败信息
 * 快速失败模式: 只要有一个验证失败，则返回
 */

@Configuration
public class ValidatorConfig {

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .failFast( true )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }

}
