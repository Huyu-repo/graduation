package cn.xsshome.mvcdo.controller.rest;

import cn.xsshome.mvcdo.common.AIConstant;
import cn.xsshome.mvcdo.interceptor.GetAccessTokenJob;
import cn.xsshome.mvcdo.util.QQSendEmailUtil;
import cn.xsshome.mvcdo.vo.AccessTokenWX;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @Description 获取token
 * @author 小帅丶
 * @className AccessTokenRestController
 * @Date 2019/9/29-10:45
 **/
@Controller
@RequestMapping(value="rest/token")
@Scope("prototype")
public class AccessTokenRestController {
    private static Logger logger = LoggerFactory.getLogger(AccessTokenRestController.class);
    private String IMG_CHECK_URL="https://api.weixin.qq.com/wxa/img_sec_check?access_token=ACCESS_TOKEN";
    @Autowired
    private GetAccessTokenJob getAccessTokenJob;
    /**
     * 获取token
     * @param code 唯一标识
     * @param request request对象
     * @param response response对象
     * @return 页面
     */
    @RequestMapping(value="/get_token/{code}",method = {RequestMethod.GET})
    @ResponseBody
    public AccessTokenWX index(@PathVariable String code, HttpServletRequest request, HttpServletResponse response) throws Exception{
        logger.info("获取token======"+code + "访问的ip"+request.getRemoteAddr());
        String openId = ServletRequestUtils.getStringParameter(request, "openId","");
        logger.info("=======访问的openId"+openId);
        AccessTokenWX accessTokenWX = new AccessTokenWX();
        Map<String,String> map = GetAccessTokenJob.tokenMap;
        if(code.equals("YDXS")||code.equals("XSYD")){
            if(map.isEmpty()){
                getAccessTokenJob.getAccessToken();
                String token = map.get(code);
                accessTokenWX.setAccess_token(token);
                return accessTokenWX;
            }else{
                String token = map.get(code);
                accessTokenWX.setAccess_token(token);
                return accessTokenWX;
            }
        }else{
            accessTokenWX.setErrcode("505");
            accessTokenWX.setErrmsg("code错误");
            return accessTokenWX;
        }
    }
    /**
     * 图片过滤检测
     * @param file 要校验的图片
     * @param access_token 接口调用凭证
     * @return
     */
    @RequestMapping(value = "/imgcheck/general", method = {RequestMethod.POST})
    @ResponseBody
    public AccessTokenWX checkPicgeneral(@RequestParam(value = "file") MultipartFile file, String access_token,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        logger.info("获取access_token======" + access_token + "访问的ip" + httpServletRequest.getRemoteAddr());
        AccessTokenWX accessTokenWX = new AccessTokenWX();
        try {
            String url = "https://api.weixin.qq.com/wxa/img_sec_check?access_token=" + access_token;
            String result = uploadFile(url, file);
            accessTokenWX = JSON.parseObject(result, AccessTokenWX.class);
            System.out.println("图片检测结果 = " + result);
            return accessTokenWX;
        } catch (Exception e) {
            System.out.println("----------------调用腾讯内容过滤系统出错------------------" + e.getMessage());
            accessTokenWX.setErrcode("500");
            accessTokenWX.setErrmsg("system错误");
            return accessTokenWX;
        }
    }
    /**
     * 图片过滤检测
     * @param file
     * @return
     */
    @RequestMapping(value = "/imgcheck", method = {RequestMethod.POST})
    @ResponseBody
    public AccessTokenWX checkPic(@RequestParam(value = "file") MultipartFile file, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String code = ServletRequestUtils.getStringParameter(httpServletRequest, "code", "");
        logger.info("获取code======" + code + "访问的ip" + httpServletRequest.getRemoteAddr());
        String openId = ServletRequestUtils.getStringParameter(httpServletRequest, "openId", "");
        logger.info("=======访问的openId" + openId);
        String token = "";
        AccessTokenWX accessTokenWX = new AccessTokenWX();
        try {
            Map<String, String> map = GetAccessTokenJob.tokenMap;
            if (code.equals("YDXS") || code.equals("XSYD")) {
                if (map.isEmpty()) {
                    getAccessTokenJob.getAccessToken();
                    token = map.get(code);
                } else {
                    token = map.get(code);
                }
                String url = "https://api.weixin.qq.com/wxa/img_sec_check?access_token=" + token;
                String result = uploadFile(url, file);
                accessTokenWX = JSON.parseObject(result, AccessTokenWX.class);
                if(!accessTokenWX.getErrcode().equals("0")){
                    QQSendEmailUtil.send_email("图片审核警报", "图片审核接口返回内容：\n"+result+"\n页面接口返回的内容:"+result, AIConstant.EMAIL_ADDRESS);
                    if(accessTokenWX.getErrcode().equals("87015")){
                       accessTokenWX.setErrcode("0");
                       accessTokenWX.setErrmsg("ok");
                   }
                }
                System.out.println("图片检测结果 = " + result);
                return accessTokenWX;
            } else {
                accessTokenWX.setErrcode("505");
                accessTokenWX.setErrmsg("code错误");
                return accessTokenWX;
            }
        } catch (Exception e) {
            System.out.println("----------------调用腾讯内容过滤系统出错------------------" + e.getMessage());
            accessTokenWX.setErrcode("500");
            accessTokenWX.setErrmsg("system错误");
            return accessTokenWX;
        }
    }
    /**
     * 上传二进制文件
     * @param graphurl 接口地址
     * @param file 图片文件
     * @return
     */
    public static String uploadFile(String graphurl,MultipartFile file) {
        String line = null;//接口返回的结果
        try {
            // 换行符
            final String newLine = "\r\n";
            final String boundaryPrefix = "--";
            // 定义数据分隔线
            String BOUNDARY = "========7d4a6d158c9";
            // 服务器的域名
            URL url = new URL(graphurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置为POST情
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求头参数
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);
            conn.setRequestProperty("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
            OutputStream out = new DataOutputStream(conn.getOutputStream());

            // 上传文件
            StringBuilder sb = new StringBuilder();
            sb.append(boundaryPrefix);
            sb.append(BOUNDARY);
            sb.append(newLine);
            // 文件参数,photo参数名可以随意修改
            sb.append("Content-Disposition: form-data;name=\"image\";filename=\""
                    + "https://api.weixin.qq.com" + "\"" + newLine);
            sb.append("Content-Type:application/octet-stream");
            // 参数头设置完以后需要两个换行，然后才是参数内容
            sb.append(newLine);
            sb.append(newLine);

            // 将参数头的数据写入到输出流中
            out.write(sb.toString().getBytes());

            // 读取文件数据
            out.write(file.getBytes());
            // 最后添加换行
            out.write(newLine.getBytes());

            // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
            byte[] end_data = (newLine + boundaryPrefix + BOUNDARY
                    + boundaryPrefix + newLine).getBytes();
            // 写上结尾标识
            out.write(end_data);
            out.flush();
            out.close();
            // 定义BufferedReader输入流来读取URL的响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                return line;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
        }
        return line;
    }
}
