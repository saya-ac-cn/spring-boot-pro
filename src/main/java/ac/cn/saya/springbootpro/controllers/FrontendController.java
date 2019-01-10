package ac.cn.saya.springbootpro.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: FrontendController
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author Saya
 * @Date: 2019/1/10 22:06
 * @Description:
 * 前端视图控制器
 */
@Controller
public class FrontendController {

    @RequestMapping(value = {"/","index","/home"})
    public String home(){
        return "/home";
    }

}
