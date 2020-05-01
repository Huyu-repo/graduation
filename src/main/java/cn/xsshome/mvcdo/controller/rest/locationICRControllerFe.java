package cn.xsshome.mvcdo.controller.rest;

import cn.xsshome.mvcdo.pojo.ai.baidu.po.BDICRLandMarkRedWineBean;
import cn.xsshome.mvcdo.service.ai.baidu.BDICRService;
import cn.xsshome.mvcdo.util.BASE64;
import cn.xsshome.mvcdo.util.MultipartFileToFile;
import cn.xsshome.mvcdo.util.PictureUtil;
import com.alibaba.fastjson.JSON;
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

@Controller
@RequestMapping(value="rest/locationicr")
@Scope("prototype")
public class locationICRControllerFe {
    private static Logger logger = LoggerFactory.getLogger(BDOCRControllerFe.class);
    @Autowired
    private BDICRService bdicrService;

    /**
     * location页面
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
        String httpUrl = " https://aip.baidubce.com/rest/2.0/image-classify/v1/landmark?access_token=" + bdicrService.getAuth();
        String httpArg = "image=" + imageBase;
        logger.info("bdicrService.getAuth()"+bdicrService.getAuth());
        String result = PictureUtil.request(httpUrl, httpArg);
        logger.info("=====locationICR接口返回的内容:"+result);
        JSONObject jsonResult = JSON.parseObject(result);


        return jsonResult;

    }
}
