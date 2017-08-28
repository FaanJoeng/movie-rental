package com.hand.movie.dao;

import com.hand.movie.entity.Customer;

import java.util.List;

/**
 * @author Fan Yang
 * @since 2017/8/27
 */
public interface CustomerDao {

    /**
     * @return 所有客户信息列表
     */
    List<Customer> getAllWithAddress();


    /**
     * 根据客户ID删除客户信息
     * @param customerId 要删除的客户ID
     * @return 1 删除成功
     */
    int deleteByPrimaryKey(Integer customerId);


    /**
     * 根据待更新客户对象更新客户信息
     * @param customer 包含待更新信息的Customer对象
     * @return 1 更新成功
     */
    int updateByPrimaryKeySelective(Customer customer);


    /**
     * 根据新客户对象插入客户信息
     * @param customer 新客户对象
     * @return 1 插入成功
     */
    int insertSelective(Customer customer);


    /**
     * 根据FirstName查找客户信息
     *
     * @param name 客户firstName
     * @return null 不存在
     */
    Customer getCustomerByName(String name);


    /**
     * 根据ID查找客户信息
     *
     * @param customerId 客户ID
     * @return null 不存在
     */
    Customer getCustomerById(Integer customerId);


    /**
     * 根据客户ID列表执行批量删除
     *
     * @param ids 包含待删除的客户ID的list
     * @return Integer 删除的客户数量
     */
    Integer deleteBatch(List<Integer> ids);
}
