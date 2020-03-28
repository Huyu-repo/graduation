package cn.xsshome.mvcdo.healthai;


import cn.xsshome.mvcdo.healthai.enums.Version;
import cn.xsshome.mvcdo.healthai.exception.AuthException;
import cn.xsshome.mvcdo.healthai.request.ServiceRequest;
import cn.xsshome.mvcdo.healthai.util.HttpClientUtil;
import cn.xsshome.mvcdo.healthai.util.JsonUtil;
import cn.xsshome.mvcdo.healthai.util.UUIDUtil;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 使用token访问API
 *
 * @author yangsongbo
 */
public class TokenHealthAiClient implements HealthAiClient {

    private String appId;
    private String apiKey;
    private String version;

    /**
     * token有效期
     */
    private Calendar expire;

    private String token;

    private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);

    public TokenHealthAiClient(String appId, String apiKey, Version version) {
        this.appId = appId;
        this.apiKey = apiKey;
        this.version = version.getValue();
    }


    public String execute(String reqDataJson, String url) {

        if (needAuth()) {
            createToken();
        }

        ServiceRequest req = new ServiceRequest(appId, UUIDUtil.getJavaUUID(), version,
                sdf.format(new Timestamp(System.currentTimeMillis())), reqDataJson);

        String reqMessage = JsonUtil.toJson(req);
        System.out.println("reqMessage = " + reqMessage);
        Map<String, String> header = new HashMap<String, String>(1);
        header.put("token", this.token);
        System.out.println(header.toString());
        return HttpClientUtil.post(url,
                reqMessage, HttpClientUtil.CONTENT_TYPE_JSON, header);
    }


    private synchronized void createToken() {

        if (!needAuth()) {
            return;
        }

        AuthHelper.AuthResponse res = AuthHelper.auth(this.appId, this.apiKey);

        if (res == null) {
            throw new AuthException("获取token为空");
        }

        if (res.getResultCode() == null || res.getResultCode() != 0) {
            throw new AuthException("获取token返回错误，" +
                    "resultCode[" + res.getResultCode() + "],message[" + res.getMessage() + "]");
        }

        if (res.getData() == null || res.getData().getToken() == null
                || "".equals(res.getData().getToken().trim())) {
            throw new AuthException("AuthResponse 中的data为空，或token为空：" + res.getData());
        }

        this.token = res.getData().getToken();

        Long expireTime = res.getData().getExpireTime();

        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, expireTime == null ? 86400 : expireTime.intValue());

        this.expire = c;
    }


    private boolean needAuth() {

        boolean tokenBlank = (this.token == null || "".equals(token.trim()));
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY, 1);

        return tokenBlank || c.after(this.expire);
    }

}
