package com.ojy.bodhi_pavilion.dto;

import com.ojy.bodhi_pavilion.pojo.Dish;
import com.ojy.bodhi_pavilion.pojo.DishFlavor;

import java.util.List;

public class DishDto extends Dish {

    private List<DishFlavor> flavors;

    private String categoryName;

    public List<DishFlavor> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<DishFlavor> flavors) {
        this.flavors = flavors;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
