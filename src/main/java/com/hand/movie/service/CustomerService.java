package com.hand.movie.service;

import com.hand.movie.entity.Customer;

import java.util.List;

/**
 * @author Fan Yang
 * @since 2017/8/26
 */
public interface CustomerService {

    /**
     * 获取所有客户信息
     */
    List<Customer> getAllWithAddress();

    /**
     * 插入新客户
     *
     * @param customer 待插入的新用户对象
     * @return 1 插入成功
     */
    int insertCustomer(Customer customer);

    /**
     * 更新客户信息
     *
     * @param customer 待更新的用户对象
     * @return 1 更新成功
     */
    int updateCustomer(Customer customer);

    /**
     * 删除客户
     *
     * @param customerId 带删除用户ID
     * @return 1 删除成功
     */
    int deleteCustomer(Integer customerId);
}
