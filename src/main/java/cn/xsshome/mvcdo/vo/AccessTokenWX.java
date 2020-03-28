package cn.xsshome.mvcdo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Description AccessTokenWX
 * @author 小帅丶
 * @className AccessTokenWX
 * @Date 2019/9/29-10:22
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccessTokenWX {
    private String access_token;
    private Integer expires_in;
    private String errcode;
    private String errmsg;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
