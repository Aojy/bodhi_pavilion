package com.ojy.bodhi_pavilion.service;

import com.ojy.bodhi_pavilion.pojo.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> queryShoppingCartByUserId(String id);

    boolean addCart(ShoppingCart cart);

    boolean subCart(ShoppingCart cart);

    boolean cleanCart(String id);
}
