package com.ojy.bodhi_pavilion.dto;

import com.ojy.bodhi_pavilion.pojo.OrderDetail;
import com.ojy.bodhi_pavilion.pojo.Orders;

import java.util.List;

public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;

    
    public String getUserName() {
        return userName;
    }

    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public String getPhone() {
        return phone;
    }

    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    public String getAddress() {
        return address;
    }

    
    public void setAddress(String address) {
        this.address = address;
    }

    
    public String getConsignee() {
        return consignee;
    }

    
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
