package com.hand.movie.entity;

import java.util.Date;

/**
 * @author Fan Yang
 * @since 2017/8/26
 */
public class Customer {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer addId;
    private Date lastUpdate;

    //TODO 项目无关字段
    private Integer storeId;
    private Date createDate;

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
