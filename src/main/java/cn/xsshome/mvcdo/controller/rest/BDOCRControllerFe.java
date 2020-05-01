package cn.xsshome.mvcdo.controller.rest;

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDICRDishDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.po.BDICRDishBean;
import cn.xsshome.mvcdo.pojo.ai.baidu.po.BDOCRGeneralBean;
import cn.xsshome.mvcdo.service.ai.baidu.BDOCRService;
import cn.xsshome.mvcdo.util.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDOCRBankCardDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDOCRGeneralDO;
import cn.xsshome.mvcdo.pojo.ai.baidu.dbo.BDOCRIdCardDO;
import cn.xsshome.mvcdo.service.ai.baidu.BDOCRDetectService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 糊鱼
 * <p>Description: 文字识别管理模块</p>
 */
@Controller
@RequestMapping(value="rest/bdocr")
@Scope("prototype")
public class BDOCRControllerFe {
	private static Logger logger = LoggerFactory.getLogger(BDOCRControllerFe.class);
	@Autowired
	private BDOCRDetectService bdocrDetectService;
	@Autowired
	private BDOCRService bdocrService;
	/**
	 * Ocr页面
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
		String httpUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic?access_token=" + bdocrService.getAuth();
		String httpArg = "detect_direction=false&image=" + imageBase;
		logger.info("imageBase"+imageBase);
		logger.info("bdocrService.getAuth()"+bdocrService.getAuth());
		String result = PictureUtil.request(httpUrl, httpArg);
		logger.info("=====接口返回的内容:"+result);
		JSONObject jsonResult = JSON.parseObject(result);
		BDOCRGeneralBean bdocrGeneralBean = com.alibaba.fastjson.JSONObject.parseObject(jsonResult.toString(), BDOCRGeneralBean.class);
		logger.info("jsonResult======="+jsonResult);
		BDOCRGeneralBean.WordsResult result1 = new BDOCRGeneralBean.WordsResult();
		List<BDOCRGeneralBean.WordsResult> list = new ArrayList<BDOCRGeneralBean.WordsResult>();
		JSONArray majar = jsonResult.getJSONArray("words_result");
		String words="";
		for(int i=0;i<majar.size();i++){
			words += majar.getJSONObject(i).get("words");
		}
		result1.setWords(words);
		list.add(result1);
		bdocrGeneralBean.setWords_result(list);
		saveResultDishData(bdocrGeneralBean);
		return jsonResult;
	}


	private void saveResultDishData(BDOCRGeneralBean bdocrGeneralBean) {
		String resultData="";
		BDOCRGeneralDO bdocrGeneralDO= new BDOCRGeneralDO();
		bdocrGeneralDO.setOpenId("web");
//            bdicrDishDO.setNikeName(nickName);
		bdocrGeneralDO.setLogId(bdocrGeneralBean.getLog_id());
		bdocrGeneralDO.setWordsResultNum(bdocrGeneralBean.getWords_result_num());
		bdocrGeneralDO.setApiType("generalOCR");
		String words="";
		for (int i=0;i<bdocrGeneralBean.getWords_result().size();i++){
			words+=bdocrGeneralBean.getWords_result().get(i).getWords();
		}
		bdocrGeneralDO.setWords(words);
		bdocrGeneralDO.setOpenId("web");
		int result = bdocrDetectService.saveOcrGeneral(bdocrGeneralDO);
		logger.info("====保存成功了："+result);
	}


	/**
	 * 跳转百度文字识别管理页面 
	 * @param request request对象
	 * @param response response对象
	 * @return 页面
	 */
	@RequestMapping(value="/indexOcrGeneral")
	public String indexOcrGeneral(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转文字识别管理页面");
		return "ai/baidu/ocr";
	}
	/**
	 * 加载百度文字识别数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/listOcrGeneral")
	public PageUtils listOcrGeneral(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BDOCRGeneralDO> detectDOs = bdocrDetectService.listOcrGeneral(query);
		int total = bdocrDetectService.countOcrGeneral(query);
		PageUtils pageUtils = new PageUtils(detectDOs, total);
		return pageUtils;
	}
	/**
	 * 删除
	 */
	@PostMapping("/removeOcrGeneral")
	@ResponseBody
	public WholeResponse removeOcrGeneral(Long id,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if (bdocrDetectService.removeOcrGeneral(id) > 0) {
				return WholeResponse.successResponse("删除成功");
			}
		} catch (Exception e) {
			logger.error("remove博文出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
		return null;
	}
	/**
	 * 批量删除
	 */
	@PostMapping("/batchRemoveOcrGeneral")
	@ResponseBody
	public WholeResponse batchRemoveOcrGeneral(@RequestParam("ids[]") Long[] ocrId,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			bdocrDetectService.batchRemoveOcrGeneral(ocrId);
			return WholeResponse.successResponse("批量删除成功");
		} catch (Exception e) {
			logger.error("批量删除博文出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	/**
	 * 跳转百度文字idcard识别管理页面 
	 * @param request request对象
	 * @param response response对象
	 * @return 页面
	 */
	@RequestMapping(value="/indexOcrIdCard")
	public String indexOcrIdCard(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转文字idcard识别管理页面");
		return "ai/baidu/ocridcard";
	}
	/**
	 * 加载百度idcard文字识别数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/listOcrIdCard")
	public PageUtils listOcrIdCard(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BDOCRIdCardDO> detectDOs = bdocrDetectService.listOcrIdCard(query);
		int total = bdocrDetectService.countOcrIdCard(query);
		PageUtils pageUtils = new PageUtils(detectDOs, total);
		return pageUtils;
	}
	/**
	 * 删除
	 */
	@PostMapping("/removeOcrIdCard")
	@ResponseBody
	public WholeResponse removeOcrIdCard(Long id,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if (bdocrDetectService.removeOcrIdCard(id) > 0) {
				return WholeResponse.successResponse("删除成功");
			}
		} catch (Exception e) {
			logger.error("remove博文出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
		return null;
	}
	/**
	 * 批量删除
	 */
	@PostMapping("/batchRemoveOcrIdCard")
	@ResponseBody
	public WholeResponse batchRemoveOcrIdCard(@RequestParam("ids[]") Long[] ocrId,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			bdocrDetectService.batchRemoveOcrIdCard(ocrId);
			return WholeResponse.successResponse("批量删除成功");
		} catch (Exception e) {
			logger.error("批量删除博文出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
	
	/**
	 * 跳转百度文字bankcard识别管理页面 
	 * @param request request对象
	 * @param response response对象
	 * @return 页面
	 */
	@RequestMapping(value="/indexOcrBankCard")
	public String indexOcrBankCard(HttpServletRequest request,HttpServletResponse response){
		logger.info("index跳转文字bankcard识别管理页面");
		return "ai/baidu/ocrbankcard";
	}
	/**
	 * 加载百度bankcard文字识别数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/listOcrBankCard")
	public PageUtils listOcrBankCard(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BDOCRBankCardDO> detectDOs = bdocrDetectService.listOcrBankCard(query);
		int total = bdocrDetectService.countOcrIdCard(query);
		PageUtils pageUtils = new PageUtils(detectDOs, total);
		return pageUtils;
	}
	/**
	 * 删除bankcard
	 */
	@PostMapping("/removeOcrBankCard")
	@ResponseBody
	public WholeResponse removeOcrBankCard(Long id,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			if (bdocrDetectService.removeOcrBankCard(id) > 0) {
				return WholeResponse.successResponse("删除成功");
			}
		} catch (Exception e) {
			logger.error("remove出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
		return null;
	}
	/**
	 * 批量删除bankcard
	 */
	@PostMapping("/batchRemoveOcrBankCard")
	@ResponseBody
	public WholeResponse batchRemoveOcrBankCard(@RequestParam("ids[]") Long[] ocrId,HttpServletRequest request,HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (AIConstant.DEMO_ACCOUNT.equals(session.getAttribute("username"))) {
				return WholeResponse.errorResponse("1", "测试账户不允许添加数据");
			}
			bdocrDetectService.batchRemoveBankIdCard(ocrId);
			return WholeResponse.successResponse("批量删除成功");
		} catch (Exception e) {
			logger.error("批量删除出错"+e.getMessage());
			return WholeResponse.errorResponse("500", "系统异常");
		}
	}
}
