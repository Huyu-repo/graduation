package cn.xsshome.mvcdo.controller.rest;

import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.interceptor.BDFactory;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDFaceDetectDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.po.FaceV3DetectBean;
import cn.xsshome.mvcdo.util.FileUtil;
import cn.xsshome.mvcdo.util.PrintUtil;
import cn.xsshome.mvcdo.vo.BDConstant;
import cn.xsshome.mvcdo.vo.BdFaceResponse;
import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;
import org.json.JSONObject;
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
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author 小帅丶
 * @date 2019年9月27日
 * <p>Description: 人脸融合rest服务</p>
 */
@Controller
@RequestMapping(value="rest/facemerge")
@Scope("prototype")
public class FaceMergeRestController {
    AipFace aipFace = BDFactory.getAipFace();
    private static Logger logger = LoggerFactory.getLogger(BDFaceRestController.class);
    /**
     * 人脸融合
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/detect",method = {RequestMethod.POST})
    public String uploadDetectFace(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        String mergeTemplate = request.getParameter("face_template");
        return null;
    }
}
