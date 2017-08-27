package com.hand.movie.service.serviceImpl;

import com.hand.movie.dao.CustomerDao;
import com.hand.movie.entity.Customer;
import com.hand.movie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Fan Yang
 * @since 2017/8/27
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerDao customerDao;

    /**
     * @return 包含所有客户信息的list
     */
    @Override
    public List<Customer> getAllWithAddress() {
        return customerDao.getAllWithAddress();
    }

    /**
     * 插入新客户
     *
     * @param customer 待插入的新用户对象
     * @return 1 插入成功
     */
    @Override
    public int insertCustomer(Customer customer) {
        return customerDao.insertSelective(customer);
    }

    /**
     * 更新客户信息
     *
     * @param customer 待更新的用户对象
     * @return 1 更新成功
     */
    @Override
    public int updateCustomer(Customer customer) {
        return customerDao.updateByPrimaryKeySelective(customer);
    }

    /**
     * 删除客户
     *
     * @param customerId 带删除用户ID
     * @return 1 删除成功
     */
    @Override
    public int deleteCustomer(Integer customerId) {
        return customerDao.deleteByPrimaryKey(customerId);
    }
}
