package com.ojy.bodhi_pavilion.dto;

import com.ojy.bodhi_pavilion.pojo.Setmeal;
import com.ojy.bodhi_pavilion.pojo.SetmealDish;

import java.util.List;

public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;

    public List<SetmealDish> getSetmealDishes() {
        return setmealDishes;
    }

    public void setSetmealDishes(List<SetmealDish> setmealDishes) {
        this.setmealDishes = setmealDishes;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
