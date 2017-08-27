package com.hand.movie.dao;

import com.hand.movie.entity.Address;

import java.util.List;

/**
 * @author Fan Yang
 * @since 2017/8/27
 */
public interface AddressDao {
    /**
     * 返回地址列表
     */
    List<Address> getAll();
}
