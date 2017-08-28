package com.hand.movie.utils;

/**
 * @author Fan Yang
 * @since 2017/8/27
 */
public enum ReturnCode {
    //默认成功返回
    SUCCESS(200),

    //登录成功
    SIGNIN_SUCCESS(210),

    //注销成功
    SIGNOUT_SUCCESS(211),

    //获取客户列表成功
    GET_CUSTOMER_LIST_SUCCESS(220),

    //新增客户成功
    ADD_CUSTOMER_SUCCESS(221),

    //更新客户成功
    UPDATE_CUSTOMER_SUCCESS(222),

    //删除客户成功
    DELETE_CUSTOMER_SUCCESS(223),

    //地址列表获取成功
    GET_ADDRESS_LIST_SUCCESS(230),

    //默认失败返回
    FAILURE(300),

    //用户不存在
    USER_NOT_EXIST_ERROR(301),

    //用户名错误
    USER_NAME_ERROR(311),

    //注销失败
    SIGNOUT_FAILURE(312),

    //客户列表为空
    CUSTOMER_LIST_EMPTY(320),

    //新增客户失败
    ADD_CUSTOMER_FAILURE(321),

    //更新客户失败
    UPDATE_CUSTOMER_FAILURE(322),

    //删除客户失败
    DELETE_CUSTOMER_FAILURE(323),

    //地址列表获取失败
    GET_ADDRESS_LIST_FAILURE(330),

    //服务器异常
    SERVER_ERROR(400);


    private int value;

    ReturnCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
