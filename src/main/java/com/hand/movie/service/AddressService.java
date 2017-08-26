package com.hand.movie.service;

import com.hand.movie.entity.Address;

import java.util.List;

/**
 * @author Fan Yang
 * @since 2017/8/26
 */
public interface AddressService {

    /**
     * 获取所有地址
     */
    public List<Address> getAll();
}
