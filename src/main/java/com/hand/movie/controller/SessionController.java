package com.hand.movie.controller;

import com.hand.movie.dto.Meta;
import com.hand.movie.dto.ResponseDto;
import com.hand.movie.dto.ResponseUtils;
import com.hand.movie.entity.Customer;
import com.hand.movie.service.CustomerService;
import com.hand.movie.utils.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 处理用户登录注销相关请求
 *
 * @author Fan Yang
 * @since 2017/8/27
 */
@Controller
public class SessionController {
    @Autowired
    CustomerService customerService;

    /**
     * 返回登录页面
     */
    @GetMapping(value = "/session", produces = MediaType.TEXT_HTML_VALUE)
    public String renderLoginPage(Model model) {
        return "session";
    }

    /**
     * 处理用户登录表单
     *
     * @param firstName 用户表单提交的firstName
     * @return 301 用户不存在
     */
    @PostMapping("/session")
    @ResponseBody
    public ResponseDto signIn(@RequestParam("firstName") String firstName, HttpSession session) {
        Customer customer = customerService.getCustomerByName(firstName.toUpperCase());

        if (customer != null) {
            //将用户的firstname放入session 的user属性中
            session.setAttribute("user", customer.getFirstName());
            return ResponseUtils.success(new Meta(ReturnCode.SIGNIN_SUCCESS.getValue(), "登录成功"));
        } else {
            //用户不存在
            return ResponseUtils.fail(new Meta(ReturnCode.USER_NOT_EXIST_ERROR.getValue(), "用户不存在"));
        }
    }

    /**
     * 处理用户注销
     *
     * @param session 携带验证用户是否登录字段的session
     * @return 211 用户注销成功
     */
    @DeleteMapping("/session")
    @ResponseBody
    public ResponseDto signOut(HttpSession session) {
        session.removeAttribute("user");
        return ResponseUtils.success(new Meta(ReturnCode.SIGNOUT_SUCCESS.getValue()));
    }

}
