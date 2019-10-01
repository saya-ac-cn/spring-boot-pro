package ac.cn.saya.springbootpro.tools;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.Instant;

/**
 * 高德ip定位
 *
 * @Title: AmapLocateUtils
 * @ProjectName spring-boot-pro
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-10-01 22:21
 * @Description:
 * 参考官方文档：https://lbs.amap.com/api/webservice/guide/api/ipconfig
 * 实例：https://blog.csdn.net/weixin_34293902/article/details/93369596
 */

public class AmapLocateUtils {

    /**
     * web服务的key
     */
    private static final String KEY = "4ead553c1c6d1e6c71e9d9bfae77492d";

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = JSONObject.parseObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static String getCityCodeByIp(String ipAddress) {
        JSONObject json = null;
        String adCode = "";
        try {
            json = readJsonFromUrl("https://restapi.amap.com/v3/ip?ip=" + ipAddress + "&key=" + KEY + "");
            if ("0".equals(json.getString("status"))) {
                //调用异常时，抛出返回信息，以便分析（我会在错误层统一捕获并输出到error日志）
                //throw new FailException("调用高德返回异常: " + json.toJSONString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            //throw new FailException("高德获取城市编码：", e);
        }
        //获取ip定位的城市编码（高德返回json如下，需要其他也可自行获取）
        adCode = json.getString("adcode");
        /**
         * {
         * 	"province": "四川省",
         * 	"city": "自贡市",
         * 	"adcode": "510300",
         * 	"infocode": "10000",
         * 	"rectangle": "104.7035158,29.29494196;104.8472285,29.39984268",
         * 	"status": "1",
         * 	"info": "OK"
         * }
         */
        System.out.println(json);
        return adCode;
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
        String s = AmapLocateUtils.getCityCodeByIp("171.90.211.88");
        System.out.println(s);
        Instant end = Instant.now();
        System.out.println("耗费时间：" + Duration.between(start, end).toMillis());
    }

}

