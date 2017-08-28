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
        dto.setMeta(new Meta(200));
        return dto;
    }

    /**
     * 自定义返回码及返回信息
     *
     * @param meta 自定义元数据
     */
    public static ResponseDto success(Meta meta) {
        ResponseDto dto = new ResponseDto();
        dto.setMeta(meta);
        return dto;
    }


    /**
     * 处理失败默认返回
     */
    public static ResponseDto fail(){
        ResponseDto dto = new ResponseDto();
        dto.setMeta(new Meta(300));
        return dto;
    }

    /**
     * 自定义返回码及返回信息
     *
     * @param meta 自定义元数据
     */
    public static ResponseDto fail(Meta meta) {
        ResponseDto dto = new ResponseDto();
        dto.setMeta(meta);
        return dto;
    }

}
