package com.hand.movie.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用信息传递对象
 * @author Fan Yang
 * @since 2017/8/26
 */
public class ResponseDto {
    //元数据
    private Meta meta;

    //携带数据
    private Map<String, Object> data = new HashMap<>();


    public ResponseDto() {

    }

    /**
     * 携带数据返回
     * @param meta 元数据
     * @param data 携带的数据
     */
    public ResponseDto(Meta meta, Map<String, Object> data) {
        this.meta = meta;
        this.data = data;
    }

    /**
     * 不携带数据返回
     * @param meta 元数据
     */
    public ResponseDto(Meta meta) {
        this.meta = meta;
    }

    /**
     * 为 ResponseDto 添加返回数据
     * @param key   数据键
     * @param value 数据值
     */
    public ResponseDto add(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}

