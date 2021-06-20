package ac.cn.saya.springbootpro.validation;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Title: CheckParamsInterceptor
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 6/20/21 23:34
 * @Description: 参数检查注解拦截器
 */
@Component
public class CheckParamsInterceptor extends HandlerInterceptorAdapter {

    public static final String LOGGER_NAME = "EXCEPTION";
    private final static Logger logger = LoggerFactory.getLogger(LOGGER_NAME);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            logger.info("UnSupport handler");
            return true;
        }
        List<String> list = getParamsName((HandlerMethod) handler);
        // 线程安全的字符串拼接
        StringBuffer stringBuffer = new StringBuffer();
        // 检查参数是否为空
        for (String s : list) {
            String parameter = request.getParameter(s);
            //这个地方是定义缺少参数或者参数为空的时候返回的数据
            if (StringUtils.isEmpty(parameter)){
                // 参数为空记录进字符串，并进行循环拼接
                stringBuffer.append("缺少必要参数"+s+"；");
            }
        }
        if (!StringUtils.isEmpty(stringBuffer)) {
            // 当stringBuffer为空的时候，表示没有参数为空
            HashMap<String, Object> result = new HashMap<>(8);
            result.put("code",-1);
            result.put("message",stringBuffer.toString());
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            //跨域
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.getWriter().write(JSONObject.toJSONString(result));
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取使用了该注解的参数名称
     */
    private List getParamsName(HandlerMethod handlerMethod) {
        // 利用java反射获取参数
        Parameter[] parameters = handlerMethod.getMethod().getParameters();
        List<String> list = new ArrayList<>();
        for (Parameter parameter : parameters) {
            //判断这个参数是否被加入了 ParamsNotNull. 的注解
            //.isAnnotationPresent() 判断是否加了这个注释
            if(parameter.isAnnotationPresent(ParamsNotNull.class)){
                // 拿到这个注解绑定的参数信息
                ParamsNotNull annotation = parameter.getAnnotation(ParamsNotNull.class);
                String scenes = annotation.scenes();
                Class<?> type = parameter.getClass();
                String name = parameter.getName();
                list.add(name);
            }
        }
        return list;
    }

}
