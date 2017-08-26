package com.hand.movie.service;

import com.hand.movie.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Fan Yang
 * @since 2017/8/26
 */
@Service
public interface CustomerService {
    /**
     * 获取所有用户
     */
    public List<Customer> getAll();
}
