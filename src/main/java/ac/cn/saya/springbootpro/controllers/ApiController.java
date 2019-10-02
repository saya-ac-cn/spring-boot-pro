package ac.cn.saya.springbootpro.controllers;

import ac.cn.saya.springbootpro.tools.AmapLocateUtils;
import ac.cn.saya.springbootpro.tools.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: ApiController
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-10-02 22:25
 * @Description:
 */

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private AmapLocateUtils amapLocateUtils;

    @GetMapping(value = "/ip")
    public String getIp(HttpServletRequest httpRequest){
        return amapLocateUtils.getCityByIp(httpRequest);
    }

}
