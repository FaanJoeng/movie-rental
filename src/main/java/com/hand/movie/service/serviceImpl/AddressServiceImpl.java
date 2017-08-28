package com.hand.movie.service.serviceImpl;

import com.hand.movie.dao.AddressDao;
import com.hand.movie.entity.Address;
import com.hand.movie.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Fan Yang
 * @since 2017/8/27
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressDao addressDao;

    /**
     * 获取所以地址
     * @return 包含Address对象的List
     */
    @Override
    public List<Address> getAll() {
        return addressDao.getAll();
    }
}
