package cn.xsshome.mvcdo.common;

/**
 * @author 小帅丶
 * @className AnswerCode
 * @Date 2019/9/19-14:18
 **/
public enum AnswerCode {
    OK(200,"success","成功"),
    FAIL(319500,"fail","失败"),
    PARAM_EMPTY(319400,"param empty","参数为空");
    //应答码
    private Integer code;
    //应答码描述
    private String msg;
    //应答码描述中文
    private String msg_zh;

    AnswerCode(Integer code, String msg, String msg_zh) {
        this.code = code;
        this.msg = msg;
        this.msg_zh = msg_zh;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg_zh() {
        return msg_zh;
    }

    public void setMsg_zh(String msg_zh) {
        this.msg_zh = msg_zh;
    }
}