package cn.xsshome.mvcdo.controller.rest;

import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDICRDishDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.po.BDICRDishBean;
import cn.xsshome.mvcdo.service.ai.baidu.BDICRDetectService;
import cn.xsshome.mvcdo.service.ai.baidu.BDICRService;
import cn.xsshome.mvcdo.util.BASE64;
import cn.xsshome.mvcdo.util.MultipartFileToFile;
import cn.xsshome.mvcdo.util.PictureUtil;
import cn.xsshome.mvcdo.vo.BDConstant;
import cn.xsshome.mvcdo.vo.BDDishResponse;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.xsshome.mvcdo.pojo.ai.baidu.po.BDICRDishBean.*;
import static cn.xsshome.mvcdo.util.JsonListUtil.jsonToList;

@Controller
@RequestMapping(value="rest/dishicr")
@Scope("prototype")
public class DishICRControllerFe {
    private static Logger logger = LoggerFactory.getLogger(BDOCRControllerFe.class);
    @Autowired
    private BDICRService bdicrService;
    @Autowired
    private BDICRDetectService bdicrDetectService;
    BDICRDishBean bdDishJson = new BDICRDishBean();
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
        String httpUrl = "https://aip.baidubce.com/rest/2.0/image-classify/v2/dish?access_token=" + bdicrService.getAuth();
        String httpArg = "image=" + imageBase;
        logger.info("bdicrService.getAuth()"+bdicrService.getAuth());
        String result = PictureUtil.request(httpUrl, httpArg);
        logger.info("=====dishICR接口返回的内容:"+result);
        JSONObject jsonResult = JSON.parseObject(result);
        BDICRDishBean bdDishJson = com.alibaba.fastjson.JSONObject.parseObject(jsonResult.toString(), BDICRDishBean.class);
        logger.info("jsonResult======="+jsonResult);
        Result result1 = new Result();
        List<Result> list = new ArrayList<Result>();
        JSONArray majar = jsonResult.getJSONArray("result");
//        JSONObject jo=JSON.parseObject((String) majar.get(0));
//        result1.setProbability(jo.get(probability));
        JSONObject jsonResult1= (JSONObject) majar.get(0);
        result1.setProbability((String) jsonResult1.get("probability"));
        result1.setCalorie((String) jsonResult1.get("calorie"));
        result1.setName((String) jsonResult1.get("name"));
        result1.setHas_calorie((boolean)jsonResult1.get("has_calorie"));
        list.add(result1);
        logger.info("jsonResult1.get(\"probability\")"+jsonResult1.get("probability"));
        logger.info("result1.name==============="+result1.getName());
        logger.info("listsoze-----------------"+list.size());
        logger.info("bdDishJson-----------------"+bdDishJson.toString());
        bdDishJson.setResult(list);
        saveResultDishData(bdDishJson);
        return jsonResult;
    }

    private void saveResultDishData(BDICRDishBean bdDishJson) {
            String resultData="";
            BDICRDishDO bdicrDishDO = new BDICRDishDO();
            bdicrDishDO.setOpenId("web");
//            bdicrDishDO.setNikeName(nickName);
            bdicrDishDO.setLogId(String.valueOf(bdDishJson.getLog_id()));
            bdicrDishDO.setResultNum(bdDishJson.getResult_num());
            bdicrDishDO.setCalorie(bdDishJson.getResult().get(0).getCalorie());
            bdicrDishDO.setHasCalorie(String.valueOf(bdDishJson.getResult().get(0).isHas_calorie()));
            bdicrDishDO.setDishName(bdDishJson.getResult().get(0).getName());
            bdicrDishDO.setProbability(bdDishJson.getResult().get(0).getProbability());
//            bdicrDishDO.setImagePath(dbPath);
//            bdicrDishDO.setEnterType(clientType);
//            bdicrDishDO.setBaikeUrl(bdDishJson.getResult().get(0).getBaike_info().getBaike_url());
//            bdicrDishDO.setImageUrl(bdDishJson.getResult().get(0).getBaike_info().getImage_url());
//            bdicrDishDO.setDescription(bdDishJson.getResult().get(0).getBaike_info().getDescription());
            int result = bdicrDetectService.saveDish(bdicrDishDO);
            logger.info("====保存成功了："+result);
    }
}
