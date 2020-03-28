package cn.xsshome.mvcdo.interceptor;

import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.util.HttpUtil;
import cn.xsshome.mvcdo.util.QQSendEmailUtil;
import cn.xsshome.mvcdo.vo.AccessTokenWX;
import com.alibaba.fastjson.JSON;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 定时获取token
 * @author 小帅丶
 * @className GetAccessTokenJob
 * @Date 2019/9/29-9:28
 **/
@Component
public class GetAccessTokenJob {
    private String AT_URL= "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public  static Map<String,String> tokenMap = new HashMap<String,String>();
    private static String A_APPID = "";
    private static String A_SECRKET = "";

    private static String B_APPID = "";
    private static String B_SECRKET = "";
    /**
     * @Description 两小时执行一次
     * @Author 小帅丶
     * @Date  2019/9/29 11:04
     * @return void
     **/
    @Scheduled(cron="0 0 0/2 * * ?")
    public void getToken() throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(tokenMap.isEmpty()){
            System.out.println("第一次执行"+format.format(new Date()));
            getAccessToken();
        }else{
            System.out.println("非第一次执行"+format.format(new Date()));
            getAccessToken();
        }
    }

    public Map<String, String> getAccessToken() throws Exception {
        String A_URL = AT_URL.replace("APPID", A_APPID).replace("APPSECRET", A_SECRKET);
        String B_URL = AT_URL.replace("APPID", B_APPID).replace("APPSECRET", B_SECRKET);
        AccessTokenWX accessTokenWX_A = getTokenBean(A_URL,"A");
        AccessTokenWX accessTokenWX_B = getTokenBean(A_URL,"B");
        if (null != accessTokenWX_A.getAccess_token()) {
            tokenMap.put("A", accessTokenWX_A.getAccess_token());
        } else {
            tokenMap.put("B", "500");
        }
        if (null != accessTokenWX_B.getAccess_token()) {
            tokenMap.put("A", accessTokenWX_B.getAccess_token());
        } else {
            tokenMap.put("B", "500");
        }
        return tokenMap;
    }

    private AccessTokenWX getTokenBean(String url,String type) throws Exception{
        String result = HttpUtil.sendGet(url,null);
        AccessTokenWX accessTokenWX = JSON.parseObject(result,AccessTokenWX.class);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        QQSendEmailUtil.send_email("获取AccessToken通知", type+"接口返回内容：\n"+result+"\n 执行时间:"+format.format(new Date()), AIConstant.EMAIL_ADDRESS);
        return accessTokenWX;
    }
}
