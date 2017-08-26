package com.hand.movie.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hand.movie.dto.ResponseDto;
import com.hand.movie.dto.ResponseUtils;
import com.hand.movie.entity.Customer;
import com.hand.movie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Fan Yang
 * @since 2017/8/26
 */

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    /**
     * 处理客户信息查询
     *
     * @param pn 起始页码
     * @return JSON格式的Customer信息
     */
    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseDto getCustomersWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<Customer> customerList = customerService.getAll();

        PageInfo page = new PageInfo(customerList, 10);
        return ResponseUtils.success().add("page", page);
    }


    /**
     * @param
     * @return
     */
    @DeleteMapping(value = "/customers/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseDto deleteCustomer() {
        return null;
    }
}
