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
     * @param customer 待插入的新用客对象
     * @return 1 插入成功
     */
    int insertCustomer(Customer customer);


    /**
     * 更新客户信息
     * @param customer 待更新的客户对象
     * @return 1 更新成功
     */
    int updateCustomer(Customer customer);


    /**
     * 删除客户
     * @param customerId 带删除客户ID
     * @return 1 删除成功
     */
    int deleteCustomer(Integer customerId);


    /**
     * 根据客户的firstName查找客户信息
     *
     * @param name 客户的firstName
     * @return Customer 客户信息对象
     */
    Customer getCustomerByName(String name);


    /**
     * 根据客户的id查找客户信息
     *
     * @param id 客户的ID
     * @return Customer 客户信息对象
     */
    Customer getCustomerById(Integer id);

    /**
     * 根据客户ID列表执行批量删除
     *
     * @param ids 包含待删除的客户ID的list
     * @return Integer 删除的客户数量
     */
    Integer deleteBatch(List<Integer> ids);
}
