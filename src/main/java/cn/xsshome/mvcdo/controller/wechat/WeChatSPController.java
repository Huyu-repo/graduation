package cn.xsshome.mvcdo.controller.wechat;

import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xsshome.mvcdo.common.AnswerCode;
import cn.xsshome.mvcdo.util.*;
import cn.xsshome.mvcdo.vo.ApiWxJSCODE2SESSIONResponseBean;
import cn.xsshome.mvcdo.vo.WxLoginResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.pojo.system.WeChatUserInfoDO;
import cn.xsshome.mvcdo.service.system.WechatUserInfoService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微信小程序code换取微信用户信息
 * @author 小帅丶
 *
 */
@Controller
@RequestMapping(value="wcsp")
public class WeChatSPController {
	private static Logger logger = LoggerFactory.getLogger(WeChatSPController.class);
	//登录凭证校验接口地址
	private String JSCODE2SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
	//小程序 appId
	private String APPID = "wx4405b7c1adf65acc";
	//小程序 appSecret
	private String SECRET = "aea145020ef9fd77b23eca23903fc02d";
	final HttpHeaders httpHeaders= new HttpHeaders();
	@Autowired
	private WechatUserInfoService wechatUserInfoService;
	/**
	 * 获取微信小程序用户openid等信息
	 * @param encryptedData 加密数据
	 * @param iv 加密算法初始向量
	 * @param code 微信小程序code码
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/oauth")
	public void wxOauth(String encryptedData,String iv,String code,HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			logger.info("请求的参数有:\n加密数据="+encryptedData+"\n加密算法初始向量="+iv+"\n微信小程序code="+code);
			//1.拼接code等参数换取私钥值
			String param = "appid="+WeChatConstant.WCSP_APPID+"&secret="+WeChatConstant.WCSP_APPSECRET+"&grant_type="+WeChatConstant.GRANT_TYPE+"&js_code="+code;
			String result = cn.xsshome.mvcdo.util.HttpUtil.post(WeChatConstant.JSCODE2SESSION_URL, param);
			logger.info("=======接口返回的数据:"+result);
			JSONObject jsonObject = JSON.parseObject(result);
			String session_key = jsonObject.get("session_key").toString();
			logger.info("session_key私钥值===="+session_key);
			//2.使用私钥值 和 算法向量值 加密的数据进行解密
			String userInfo = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
			logger.info("解密后返回页面的数据==="+userInfo);
			PrintUtil.printJson(response, userInfo);
			WeChatUserInfoDO userInfoDO = JSONObject.toJavaObject(JSON.parseObject(userInfo), WeChatUserInfoDO.class);
			if(null!=userInfoDO){
				WeChatUserInfoDO dbUserInfoDO = wechatUserInfoService.get(userInfoDO.getOpenId());
				if(null==dbUserInfoDO){
					String nickNameEncode = URLEncoder.encode(userInfoDO.getNickName(),"UTF-8");
					userInfoDO.setNickName(nickNameEncode);
					wechatUserInfoService.save(userInfoDO);
				}else{
					String nickNameEncode = URLEncoder.encode(userInfoDO.getNickName(),"UTF-8");
					userInfoDO.setNickName(nickNameEncode);
					if(!userInfoDO.equals(dbUserInfoDO)){
						wechatUserInfoService.updateWechatUserInfo(userInfoDO);
					}
				}
			}else{
				QQSendEmailUtil.send_email("小程序获取用户信息失败", ""+userInfo, AIConstant.EMAIL_ADDRESS);
			}
		} catch (Exception e) {
			QQSendEmailUtil.send_email("小程序获取用户信息异常", ""+e.getMessage(), AIConstant.EMAIL_ADDRESS);
			logger.error("oauth===出错了"+e.getMessage());
		}
	}
	/**
	 * @Description 获取openid给小程序
	 * @Author 小帅丶
	 * @Date  2019/8/5 16:12
	 * @param code wx.login的code值
	 * @return org.springframework.http.ResponseEntity<java.lang.Object>
	 **/
	@RequestMapping(method = {RequestMethod.GET},value = "/login")
	public ResponseEntity<Object> wxLogin(@RequestParam(name = "code",defaultValue = "",required = true) String code){
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		WxLoginResponseBean bean = new WxLoginResponseBean();
		try {
			if(!"".equals(code)){
				String url = JSCODE2SESSION_URL.replace("APPID", APPID).replace("SECRET", SECRET).replace("JSCODE", code);
				String result = HttpUtil.sendGet(url, "");
				logger.info("接口返回的内容{}",result);
				ApiWxJSCODE2SESSIONResponseBean apiBean = JSON.parseObject(result,ApiWxJSCODE2SESSIONResponseBean.class);
				WxLoginResponseBean.Data data = new WxLoginResponseBean.Data();
				if(apiBean.getErrcode()==0){
					bean.setCode(AnswerCode.OK.getCode());
					bean.setMsg(AnswerCode.OK.getMsg());
					bean.setMsg(AnswerCode.OK.getMsg_zh());
					data.setOpenid(apiBean.getOpenid());
					data.setSession_key(apiBean.getSession_key());
					bean.setData(data);
					WeChatUserInfoDO userInfoDB = wechatUserInfoService.get(apiBean.getOpenid());
					if(null==userInfoDB){
						//保存到数据库
						WeChatUserInfoDO userInfo = new WeChatUserInfoDO();
						userInfo.setOpenId(apiBean.getOpenid());
						wechatUserInfoService.save(userInfo);
					}
				}else{
					data.setOpenid("");
					bean.setData(data);
				}
			}
		} catch (Exception e) {
			logger.info("出错了异常信息{}", e.getMessage());
			bean.setCode(AnswerCode.FAIL.getCode());
			bean.setMsg(AnswerCode.FAIL.getMsg());
			bean.setMsg(AnswerCode.FAIL.getMsg_zh());
		}
		return new ResponseEntity<Object>(bean,httpHeaders, HttpStatus.OK);
	}
}
