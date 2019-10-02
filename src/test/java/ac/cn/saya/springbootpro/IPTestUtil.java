package ac.cn.saya.springbootpro;

import ac.cn.saya.springbootpro.tools.HttpRequestUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * IP测试单元
 * @Title: IPTestUtil
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-10-01 22:52
 * @Description:
 */

public class IPTestUtil {

    @Test
    public void textNewsList(){
        JSONObject jsonObject = HttpRequestUtil.httpUrlConnetionGet("https://saya.ac.cn/frontend/Pandora/news", "nowPage=1", "pageSize=10");
        System.out.println(jsonObject);
    }

}
