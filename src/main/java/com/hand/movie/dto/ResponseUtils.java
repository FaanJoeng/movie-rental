package com.hand.movie.dto;

/**
 * @author Fan Yang
 * @since 2017/8/26
 */
public class ResponseUtils {
    /**
     * 处理成功默认返回
     */
    public static ResponseDto success() {
        ResponseDto dto = new ResponseDto();
        dto.setMeta(new Meta(200, "success"));
        return dto;
    }

    /**
     * 处理失败默认返回
     */
    public static ResponseDto fail() {
        ResponseDto dto = new ResponseDto();
        dto.setMeta(new Meta(300, "fail"));
        return dto;
    }

}
