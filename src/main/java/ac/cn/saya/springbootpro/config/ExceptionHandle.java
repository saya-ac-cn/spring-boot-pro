package ac.cn.saya.springbootpro.config;

import ac.cn.saya.springbootpro.tools.Result;
import ac.cn.saya.springbootpro.tools.ResultUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @Title: ExceptionHandle
 * @ProjectName mqtt-middle
 * @Author shmily
 * @Date: 2020/11/8 22:16
 * @Description: 全局控制的拦截器，主要用于对异常的处理-除了在此要配置外，还要在dispatcher-servlet中配置，让它能正常扫描到。
 */

@RestControllerAdvice
public class ExceptionHandle {


    @ExceptionHandler(value = Exception.class)
    public Result<Object> handle(Exception e) {
        if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            BindingResult bindingResult = bindException.getBindingResult();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            if (CollectionUtils.isEmpty(fieldErrors)) {
                return ResultUtil.error(-999, "未知的参数校验错误");
            }
            StringBuffer errorStr = new StringBuffer("参数错误：");
            for (FieldError error : fieldErrors) {
                errorStr.append(error.getDefaultMessage()).append(";");
            }
            return ResultUtil.error(400, errorStr.toString());
        } else {
            //不在定义范围内的异常错误
            return ResultUtil.error(-1, "未知错误");
        }
    }

}