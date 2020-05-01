package cn.xsshome.mvcdo.controller.rest;

import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDICRFuseDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.po.BDICRFuseBean;
import cn.xsshome.mvcdo.service.ai.baidu.BDICRDetectService;
import cn.xsshome.mvcdo.service.ai.baidu.BDICRService;
import cn.xsshome.mvcdo.util.BASE64;
import cn.xsshome.mvcdo.util.MultipartFileToFile;
import cn.xsshome.mvcdo.util.PictureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="rest/moneyicr")
@Scope("prototype")
public class MoneyICRControllerFe {
    private static Logger logger = LoggerFactory.getLogger(BDOCRControllerFe.class);
    @Autowired
    private BDICRService bdicrService;
    @Autowired
    private BDICRDetectService bdicrDetectService;
    /**
     * Car页面
     * @param request request对象
     * @param response response对象
     * @return 页面
     */
    @RequestMapping(value = "/detect",method = {RequestMethod.POST})
    @ResponseBody
    public JSONObject uploadImageClassify(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 进行BASE64位编码
        File newFile = MultipartFileToFile.multipartFileToFile(file);
        String imageBase = BASE64.encodeImgageToBase64(newFile);
        imageBase = imageBase.replaceAll("\r\n", "");
        imageBase = imageBase.replaceAll("\\+", "%2B");
        // 百度云的文字识别接口,后面参数为获取到的token
        String httpUrl = "https://aip.baidubce.com/rest/2.0/image-classify/v1/currency?access_token=" + bdicrService.getAuth();
        String httpArg = "image=" + imageBase;
        logger.info("bdicrService.getAuth()"+bdicrService.getAuth());
        String result = PictureUtil.request(httpUrl, httpArg);
        logger.info("=====MoneyICR接口返回的内容:"+result);
        JSONObject jsonResult = JSON.parseObject(result);

        BDICRFuseBean bdICRFuseBean = com.alibaba.fastjson.JSONObject.parseObject(jsonResult.toString(), BDICRFuseBean.class);
        logger.info("jsonResult======="+jsonResult);
        BDICRFuseBean.Result result1 = new BDICRFuseBean.Result();
        List<BDICRFuseBean.Result> list = new ArrayList<BDICRFuseBean.Result>();
        result1.setName(jsonResult.getJSONObject("result").get("currencyName")+"");
        result1.setYear(jsonResult.getJSONObject("result").get("year")+"");
        result1.setLogoType(jsonResult.getJSONObject("result").get("currencyCode")+""+jsonResult.getJSONObject("result").get("currencyDenomination"));
        list.add(result1);
        logger.info("result1.name==============="+result1.getName());
        logger.info("listsoze-----------------"+list.size());
        logger.info("bdDishJson-----------------"+bdICRFuseBean.toString());
        bdICRFuseBean.setResult(list);
        saveFuse(bdICRFuseBean);

        return jsonResult;

    }

    private void saveFuse(BDICRFuseBean bdicrFuseBean) {
        String resultData="";
        BDICRFuseDO bdicrFuseDO= new BDICRFuseDO();
//            bdicrDishDO.setNikeName(nickName);
        bdicrFuseDO.setLogId(bdicrFuseBean.getLog_id()+"");
        bdicrFuseDO.setIcrName(bdicrFuseBean.getResult().get(0).getName());
        bdicrFuseDO.setOpenId("web");
        bdicrFuseDO.setApiType("MoneyICR");
        bdicrFuseDO.setPyear(bdicrFuseBean.getResult().get(0).getYear());
        bdicrFuseDO.setDescription(bdicrFuseBean.getResult().get(0).getLogoType());
        int result = bdicrDetectService.saveFuse(bdicrFuseDO);
        logger.info("====保存成功了:"+result);
    }

}
