package com.hand.movie.service;

import com.hand.movie.entity.Address;

import java.util.List;

/**
 * @author Fan Yang
 * @since 2017/8/26
 */
public interface AddressService {

    /**
     * 获取所以地址 本项目限定为6条
     * @return 包含Address对象的List
     */
    List<Address> getAll();
}
