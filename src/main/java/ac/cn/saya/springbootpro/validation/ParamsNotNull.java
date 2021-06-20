package ac.cn.saya.springbootpro.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 表示该注解是加载参数上的; @Target： 定义放在什么位置，这个是放在参数中
@Target(ElementType.PARAMETER)
// 注解保留到运行阶段; @Retention： 定义了该Annotation被保留的时间长短，有些只在源码中保留，有时需要编译成的class中保留，有些需要程序运行时候保留。即描述注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamsNotNull {
    /**
     * 校验场景
     * @return
     */
    String scenes() default "insert";
}
