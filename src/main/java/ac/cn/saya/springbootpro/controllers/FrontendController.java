package ac.cn.saya.springbootpro.controllers;

import ac.cn.saya.springbootpro.entity.UserEntity;
import ac.cn.saya.springbootpro.validation.ParamsNotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String register(@ParamsNotNull(scenes = "insert") UserEntity user){
        return "----";
    }


}
