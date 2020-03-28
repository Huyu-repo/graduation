package cn.xsshome.mvcdo.healthai;


import cn.xsshome.mvcdo.healthai.enums.Version;
import cn.xsshome.mvcdo.healthai.request.ServiceRequest;
import cn.xsshome.mvcdo.healthai.util.HttpClientUtil;
import cn.xsshome.mvcdo.healthai.util.JsonUtil;
import cn.xsshome.mvcdo.healthai.util.UUIDUtil;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 使用RSA对称加密访问API
 *
 * @author yangsongbo
 */
public class DefaultHealthAiClient implements HealthAiClient {

    private String appId;
    private String privateKey;
    private String version;

    private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);

    public DefaultHealthAiClient(String appId, String privateKey, Version version) {
        this.appId = appId;
        this.privateKey = privateKey;
        this.version = version.getValue();
    }


    public String execute(String reqDataJson, String url) {

        String nonceStr = UUIDUtil.getJavaUUID();
        String timestamp = sdf.format(new Timestamp(System.currentTimeMillis()));

        ServiceRequest req = new ServiceRequest(appId, nonceStr,
                version, timestamp, reqDataJson);

        String sign = SignHelper.sign(new SignHelper.SignFieldBean(appId, nonceStr, timestamp, version), privateKey);

        req.setSign(sign);
        req.setReqData(reqDataJson);
        String reqMessage = JsonUtil.toJson(req);
        return HttpClientUtil.post(url, reqMessage, HttpClientUtil.CONTENT_TYPE_JSON);
    }


}
