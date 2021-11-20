package ac.cn.saya.springbootpro.controllers;

import ac.cn.saya.springbootpro.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: FrontendController
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author Saya
 * @Date: 2019/1/10 22:06
 * @Description:
 * 前端视图控制器
 */
@RestController
public class FrontendController {

    @GetMapping(value = {"/","index","/home"})
    public String home(){
        return "/home";
    }


    @GetMapping(value = "/register")
    public String register(@Validated({UserEntity.InsetScene.class}) UserEntity user){
        return "----";
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }


}
