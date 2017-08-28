package com.hand.movie.entity;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author Fan Yang
 * @since 2017/8/26
 */
public class Customer {
    @DecimalMin(value = "1", message = "客户ID必须大于1")
    private Integer customerId;

    @Pattern(regexp = "^[\\w]{1,10}$", message = "FirstName 为1-10位字母或数字或下划线组合")
    @NotNull(message = "FirstName字段不能为空")
    private String firstName;

    @Pattern(regexp = "^[\\w]{1,10}$", message = "LastName 为1-10位字母或数字或下划线组合")
    @NotNull(message = "LastName字段不能为空")
    private String lastName;

    @Email(message = "邮箱格式不正确")
    private String email;

    @DecimalMin(value = "1", message = "地址ID必须大于1")
    @NotNull(message = "地址ID不能为空")
    private Integer addId;

    @Null(message = "lastUpdate从后端填充")
    private Date lastUpdate;

    //TODO 项目无关字段
    @Null(message = "storeID必须为空")
    private Integer storeId;

    @Null(message = "createDate必须为空")
    private Date createDate;

    @Null(message = "address必须为空")
    private Address address;

    public Customer() {
        super();
    }

    public Customer(Integer customerId, String firstName,
                    String lastName, String email,
                    Integer addId, Date lastUpdate,
                    Integer storeId, Date createDate,
                    Address address) {
        super();
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addId = addId;
        this.lastUpdate = lastUpdate;
        this.storeId = storeId;
        this.createDate = createDate;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", addId=" + addId +
                ", lastUpdate=" + lastUpdate +
                ", storeId=" + storeId +
                ", createDate=" + createDate +
                ", address=" + address +
                '}';
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAddId() {
        return addId;
    }

    public void setAddId(Integer addId) {
        this.addId = addId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
