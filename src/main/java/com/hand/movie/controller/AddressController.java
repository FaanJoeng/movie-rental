package com.hand.movie.controller;

import com.hand.movie.dto.Meta;
import com.hand.movie.dto.ResponseDto;
import com.hand.movie.dto.ResponseUtils;
import com.hand.movie.entity.Address;
import com.hand.movie.service.AddressService;
import com.hand.movie.utils.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 处理地址相关请求
 * @author Fan Yang
 * @since 2017/8/26
 */
@RestController
public class AddressController {
    @Autowired
    AddressService addressService;

    /**
     * 请求地址列表
     * @return 230 请求成功
     * @return 330 地址列表为空
     */
    @GetMapping("/address")
    public ResponseDto getAllAddress() {
        List<Address> addressList = addressService.getAll();

        if (addressList.size() == 0) {
            //地址列表为空
            return ResponseUtils.fail(new Meta(ReturnCode.GET_ADDRESS_LIST_FAILURE.getValue()));
        } else {
            //正常返回
            return ResponseUtils.success(new Meta(ReturnCode.GET_ADDRESS_LIST_SUCCESS.getValue())).add("list", addressList);
        }
    }
}
