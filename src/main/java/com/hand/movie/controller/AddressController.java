package com.hand.movie.controller;

import com.hand.movie.dto.ResponseDto;
import com.hand.movie.dto.ResponseUtils;
import com.hand.movie.entity.Address;
import com.hand.movie.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Fan Yang
 * @since 2017/8/26
 */
@RestController
public class AddressController {
    @Autowired
    AddressService addressService;

    /**
     * 请求地址列表
     *
     * @return 携带6条地址的DTO
     */
    @GetMapping("/address")
    public ResponseDto getAllAddress() {
        List<Address> addressList = addressService.getAll();
        return ResponseUtils.success().add("list", addressList);
    }
}
