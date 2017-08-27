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
import org.springframework.web.bind.annotation.*;

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
     * @param pn 起始页码
     * @return JSON格式的Customer信息 每个包含10条信息
     */
    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseDto getCustomersWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<Customer> customerList = customerService.getAllWithAddress();

        PageInfo page = new PageInfo(customerList, 10);
        return ResponseUtils.success().add("page", page);
    }


    /**
     * 处理客户删除 将Customer表单active字段从1置为0
     * @param customerId 将路径的的客户ID赋值给customerId 并据此删除
     * @return
     */
    @DeleteMapping(value = "/customers/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseDto deleteCustomer(@PathVariable("id") Integer customerId) {
        boolean isSuccess = (1 == customerService.deleteCustomer(customerId));
        if (isSuccess) {
            return ResponseUtils.success();
        } else {
            return ResponseUtils.fail();
        }
    }


    /**
     * 处理客户信息更新
     *
     * @param customer 包含待更新客户信息的Customer对象
     * @return
     */
    @PutMapping(value = "/customers/{customerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseDto updateCustomer(Customer customer) {
        boolean isSuccess = (1 == customerService.updateCustomer(customer));
        if (isSuccess) {
            return ResponseUtils.success();
        } else {
            return ResponseUtils.fail();
        }
    }

    /**
     * 处理新增客户信息
     *
     * @param customer 包含待插入新客户信息的Customer对象
     * @return
     */
    @PostMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseDto insertCustomer(Customer customer) {
        boolean isSuccess = (1 == customerService.insertCustomer(customer));
        if (isSuccess) {
            return ResponseUtils.success();
        } else {
            return ResponseUtils.fail();
        }
    }
}
