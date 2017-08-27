package com.hand.movie.dao;

import com.hand.movie.entity.Customer;

import java.util.List;

/**
 * @author Fan Yang
 * @since 2017/8/27
 */
public interface CustomerDao {

    /**
     * @return 所有顾客列表
     */
    List<Customer> getAllWithAddress();

    /**
     * 根据客户ID删除客户信息
     *
     * @param customerId 要删除的客户ID
     * @return 1 删除成功
     */
    int deleteByPrimaryKey(Integer customerId);

    /**
     * 根据待更新用户对象更新用户信息
     *
     * @param customer 包含待更新信息的Customer 对象
     * @return 1 更新成功
     */
    int updateByPrimaryKeySelective(Customer customer);

    /**
     * 根据新用户对象插入用户信息
     *
     * @param customer 新用户对象
     * @return 1 插入成功
     */
    int insertSelective(Customer customer);
}
