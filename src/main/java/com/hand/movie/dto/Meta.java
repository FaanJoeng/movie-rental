package com.hand.movie.dto;

/**
 * @author Fan Yang
 * @since 2017/8/27
 */
public class Meta {
    //返回码
    private Integer code;

    //说明辅助信息
    private String msg;

    public Meta(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Meta(Integer code) {
        this.code = code;
        if (code >= 400) {
            this.msg = "server error";
        } else if (code >= 300 && code < 400) {
            this.msg = "failure";
        } else if (code >= 200 && code < 300) {
            this.msg = "success";
        }
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
}
