package com.ojy.bodhi_pavilion.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ShoppingCart {
    private String id;

    private String name;

    private String image;

    private String userId;

    private String dishId;

    private String setmealId;

    private String dishFlavor;

    private Integer number;

    private BigDecimal amount;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getSetmealId() {
        return setmealId;
    }

    public void setSetmealId(String setmealId) {
        this.setmealId = setmealId;
    }

    public String getDishFlavor() {
        return dishFlavor;
    }

    public void setDishFlavor(String dishFlavor) {
        this.dishFlavor = dishFlavor;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}