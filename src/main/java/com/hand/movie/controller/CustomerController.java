package com.hand.movie.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hand.movie.dto.Meta;
import com.hand.movie.dto.ResponseDto;
import com.hand.movie.dto.ResponseUtils;
import com.hand.movie.entity.Customer;
import com.hand.movie.service.CustomerService;
import com.hand.movie.utils.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户信息处理增删改查
 * @author Fan Yang
 * @since 2017/8/26
 */

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    /**
     * 处理客户信息查询
     * @param pn 起始页码
     * @return 220 获取客户列表成功
     * @return 320 客户列表为空
     */
    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDto getCustomersWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {

        /**
         * 设置分页的起始页及每页大小
         * @param pn 起始页
         * @param pageSize 每页大小， 设置为10
         */
        PageHelper.startPage(pn, 10);

        List<Customer> customerList = customerService.getAllWithAddress();

        PageInfo page = new PageInfo(customerList, 10);

        if (customerList.size() == 0) {
            //客户列表为空
            return ResponseUtils.fail(new Meta(ReturnCode.CUSTOMER_LIST_EMPTY.getValue(), "没有相关信息"));
        } else {
            //客户列表不为空
            return ResponseUtils.success(new Meta(ReturnCode.GET_CUSTOMER_LIST_SUCCESS.getValue())).add("page", page);
        }

    }

    /**
     * 处理客户批量或单个删除 将Customer表单active字段从1置为0
     * @param ids 将路径的的客户ID赋值给ids 并据此删除
     * @return 223 删除客户成功
     * @return 323 删除客户失败
     */
    @DeleteMapping(value = "/customers/{ids}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDto deleteCustomer(@PathVariable("ids") String ids) {
        boolean isSuccess = false;
        Integer sum = 0;

        if (ids.contains("-")) {
            //包含多个待删除的ID 将字符串转换成整数list
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = ids.split("-");
            for (String string : str_ids) {
                del_ids.add(Integer.valueOf(string));
            }

            if ((sum = customerService.deleteBatch(del_ids)) != null) {
                isSuccess = true;
            }

        } else {
            //单条删除 正常返回值为1
            isSuccess = (1 == customerService.deleteCustomer(Integer.valueOf(ids)));
            sum = 1;
        }

        if (isSuccess) {
            //删除成功
            return ResponseUtils.success(new Meta(ReturnCode.DELETE_CUSTOMER_SUCCESS.getValue(), "删除成功")).add("sum", sum);
        } else {
            //删除失败
            return ResponseUtils.fail(new Meta(ReturnCode.DELETE_CUSTOMER_FAILURE.getValue(), "删除失败"));
        }
    }


    /**
     * 处理客户信息更新
     * @param customer 包含待更新客户信息的Customer对象 路径中customerId自动封装进Customer对象
     * @return 222 更新客户信息成功
     * @return 322 更新客户信息失败
     */
    @PutMapping(value = "/customers/{customerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDto updateCustomer(@Valid Customer customer, BindingResult result) {
        //执行后端验证，如果更新用户表单字段格式错误则执行
        if (result.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResponseUtils.fail(new Meta(ReturnCode.UPDATE_CUSTOMER_FAILURE.getValue(), "字段格式不正确")).add("errorFields", map);
        }

        //返回更新数 本项目为单条更新 正常返回值为1
        boolean isSuccess = (1 == customerService.updateCustomer(customer));
        if (isSuccess) {
            //更新成功
            return ResponseUtils.success(new Meta(ReturnCode.UPDATE_CUSTOMER_SUCCESS.getValue()));
        } else {
            //更新失败
            return ResponseUtils.fail(new Meta(ReturnCode.UPDATE_CUSTOMER_FAILURE.getValue()));
        }
    }


    /**
     * 处理新增客户信息
     * @param customer 包含待插入新客户信息的Customer对象
     * @return 221 新增客户成功
     * @return 321 新增客户失败
     */
    @PostMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDto insertCustomer(@Valid Customer customer, BindingResult result) {
        //如果新增用户表单字段格式错误则执行
        if (result.hasErrors()) {
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResponseUtils.fail(new Meta(ReturnCode.ADD_CUSTOMER_FAILURE.getValue(), "字段格式不正确")).add("errorFields", map);
        }

        boolean isSuccess = (1 == customerService.insertCustomer(customer));

        if (isSuccess) {
            //新增成功
            return ResponseUtils.success(new Meta(ReturnCode.ADD_CUSTOMER_SUCCESS.getValue()));
        } else {
            //新增失败
            return ResponseUtils.fail(new Meta(ReturnCode.ADD_CUSTOMER_FAILURE.getValue()));
        }
    }


    /**
     * 根据客户ID查询用客户信息
     *
     * @param customerId 客户的customerId
     * @return 301 客户不存在
     */
    @GetMapping(value = "/customers/{customerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDto getCustomerByName(@PathVariable("customerId") Integer customerId) {
        Customer customer = customerService.getCustomerById(customerId);

        if (customer != null) {
            //正常返回
            return ResponseUtils.success(new Meta(ReturnCode.SUCCESS.getValue(), "获取客户信息成功")).add("customer", customer);
        } else {
            //用户不存在
            return ResponseUtils.fail(new Meta(ReturnCode.USER_NOT_EXIST_ERROR.getValue(), "用户不存在"));
        }
    }
}
