package cn.xsshome.mvcdo.util;

import cn.xsshome.mvcdo.controller.rest.BDOCRControllerFe;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {
    /**
     * JSON 转 POJO
     */
    private static Logger logger = LoggerFactory.getLogger(BDOCRControllerFe.class);
    public static <T> T getObject(String pojo, Class<T> tclass) {
        try {
            return JSONObject.parseObject(pojo, tclass);
        } catch (Exception e) {
            logger.error(tclass + "转 JSON 失败");
        }
        return null;
    }

    /**
     * POJO 转 JSON
     */
    public static <T> String getJson(T tResponse){
        String pojo = JSONObject.toJSONString(tResponse);
        return pojo;
    }

}