package cn.xsshome.mvcdo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Description wx.login
 * @author 小帅丶
 * @className WxLoginResponseBean
 * @Date 2019/9/19-14:17
 **/
public class WxLoginResponseBean extends BaseBean {
    private static final long serialVersionUID = 1L;
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Data{
        private String openid;
        private String session_key;

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getSession_key() {
            return session_key;
        }

        public void setSession_key(String session_key) {
            this.session_key = session_key;
        }
    }
}