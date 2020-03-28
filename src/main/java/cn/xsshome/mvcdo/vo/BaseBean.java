package cn.xsshome.mvcdo.vo;

/**
 * @author 小帅丶
 * @className BaseBean
 * @Description //TODO
 * @Date 2019/9/19-14:16
 **/
public class BaseBean {
    private static final long serialVersionUID = 1L;
    //应答码
    private Integer code;
    //应答码描述
    private String msg;
    //应答码描述中文
    private String msg_zh;
    //作者
    private String author= "小帅丶";
    //时间戳
    private long time_stamp = System.currentTimeMillis();

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(long time_stamp) {
        this.time_stamp = time_stamp;
    }
}
