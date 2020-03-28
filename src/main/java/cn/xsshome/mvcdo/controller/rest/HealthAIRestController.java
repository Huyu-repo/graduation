package cn.xsshome.mvcdo.controller.rest;

import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.healthai.HealthAiClient;
import cn.xsshome.mvcdo.healthai.response.Physiognomy;
import cn.xsshome.mvcdo.healthai.util.JsonUtil;
import cn.xsshome.mvcdo.interceptor.BDFactory;
import cn.xsshome.mvcdo.util.PrintUtil;
import cn.xsshome.mvcdo.util.QQSendEmailUtil;
import cn.xsshome.mvcdo.vo.BDConstant;
import cn.xsshome.mvcdo.vo.BDDishResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.util.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 面相识别
 * @author 小帅丶
 * @className HealthAIRestController
 * @Date 2019/9/19-16:47
 **/
@Controller
@RequestMapping(value="rest/physiognomy")
@Scope("prototype")
public class HealthAIRestController {
    private static Logger logger = LoggerFactory.getLogger(HealthAIRestController.class);
    HealthAiClient healthAiClient = BDFactory.getHealthAiClient();
    String url = "https://api2.jiankangyouyi.com/v2/face/face_shape/physiognomy";
    /**
     * 面相分析接口
     * @param file
     * @param request
     * @param response
     */
    @RequestMapping(value="/face",method={RequestMethod.POST})
    public void uoloadFaceMerge(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        String resultData = "";
        String clientType = request.getParameter("clientType");
        String openId = ServletRequestUtils.getStringParameter(request, "openId","");
        logger.info("=======访问的openId"+openId);
        logger.info("=======访问的IP"+request.getRemoteAddr()+"======访问的User-Agent:"+request.getHeader("User-Agent"));
        try {
            if(!clientType.equals("wsc")){
                BDDishResponse bdDishResponse = new BDDishResponse();
                bdDishResponse.setCode(BDConstant.BD_403.getCode().toString());
                bdDishResponse.setMsg("缺少必要参数");
                resultData = JSON.toJSONString(bdDishResponse);
                logger.info("=====接口返回的内容:"+resultData);
                PrintUtil.printJson(response,resultData);
            }else{
                String authCode = request.getParameter("authCode");
                if(null==authCode||!authCode.equals(AIConstant.AUTH_CODE)){
                    BDDishResponse bdDishResponse = new BDDishResponse();
                    bdDishResponse.setCode(BDConstant.BD_NOTFUND.getCode().toString());
                    bdDishResponse.setMsg(BDConstant.BD_NOTFUND.getMsg());
                    resultData = JSON.toJSONString(bdDishResponse);
                    logger.info("=====接口返回的内容:"+resultData);
                    PrintUtil.printJson(response,resultData);
                }else{
                    JSONObject object = faceContent(file, healthAiClient);
                    if(object.containsKey("resCode")){
                        QQSendEmailUtil.send_email("面相接口错误通知", "接口返回内容：\n"+object.toJSONString()+"\n访问接口的ip："+request.getRemoteAddr()+"\n访问接口的openid："+openId, AIConstant.EMAIL_ADDRESS);
                    }
                    Physiognomy.ResData resData = JSON.parseObject(object.getString("resData"), Physiognomy.ResData.class);
                    if(resData.getRetCode().equals("SYS0046")){
                        QQSendEmailUtil.send_email("面相接口重新加载通知", "接口返回内容：\n"+resultData+"\n访问接口的ip："+request.getRemoteAddr()+"\n访问接口的openid："+openId, AIConstant.EMAIL_ADDRESS);
                        HealthAiClient healthAiClient = BDFactory.getHealthAiClientNew();
                        object = faceContent(file,healthAiClient);
                        resData = JSON.parseObject(object.getString("resData"), Physiognomy.ResData.class);
                        resultData = JSON.toJSONString(resData);
                        PrintUtil.printJson(response, resultData);
                    }else{
                        resultData = JSON.toJSONString(resData);
                        PrintUtil.printJson(response, resultData);
                    }
                    if(!resData.getRetCode().equals("SUCCESS")){
                        System.out.println("面相接口错误警报接口返回内容：\n"+resultData+"\n访问接口的ip："+request.getRemoteAddr()+"\n访问接口的openid："+openId);
                        QQSendEmailUtil.send_email("面相接口错误警报", "接口返回内容：\n"+resultData+"\n访问接口的ip："+request.getRemoteAddr()+"\n访问接口的openid："+openId, AIConstant.EMAIL_ADDRESS);
                    }
                    if(resData.getRetCode().equals("SUCCESS")&&resData.getRecognitionResult().getStatus()!=0){
                        System.out.println("面相接口识别异常警报接口返回内容：\n"+resultData+"\n访问接口的ip："+request.getRemoteAddr()+"\n访问接口的openid："+openId);
                        QQSendEmailUtil.send_email("面相接口识别异常警报", "接口返回内容：\n"+resultData+"\n访问接口的ip："+request.getRemoteAddr()+"\n访问接口的openid："+openId, AIConstant.EMAIL_ADDRESS);
                    }
                }
            }
        } catch (Exception e) {
            logger.info("面相接口出错了"+e.getMessage());
            BDDishResponse bdDishResponse = new BDDishResponse();
            bdDishResponse.setCode(BDConstant.BD_ERROR.getCode().toString());
            bdDishResponse.setMsg(BDConstant.BD_ERROR.getMsg());
            resultData = JSON.toJSONString(bdDishResponse);
            System.out.println(resultData);
            PrintUtil.printJson(response,resultData);
        }
    }
    /**
     * @Description 对数据单独处理
     * @Author 小帅丶
     * @Date  2019/11/15 10:46
     * @param file 图片文件
     * @param healthAiClient 面相服务
     * @return com.alibaba.fastjson.JSONObject
     **/
    private JSONObject faceContent(MultipartFile file, HealthAiClient healthAiClient) throws Exception{
        String resultData = "";
        byte[] image = file.getBytes();
        Map reqData = new HashMap();
        String imageBase64 = Base64Util.encode(image);
        reqData.put("imageFile", imageBase64);
        resultData = healthAiClient.execute(JsonUtil.toJson(reqData), url);
        System.out.println("resultData = " + resultData);
        JSONObject object = JSON.parseObject(resultData);
        return object;
    }
}
