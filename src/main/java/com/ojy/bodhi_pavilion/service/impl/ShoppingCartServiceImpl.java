package com.ojy.bodhi_pavilion.service.impl;

import com.ojy.bodhi_pavilion.mapper.ShoppingCartMapper;
import com.ojy.bodhi_pavilion.pojo.ShoppingCart;
import com.ojy.bodhi_pavilion.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    /**
     * 查询对应购物车的商品数据
     * @param id
     * @return
     */
    @Override
    public List<ShoppingCart> queryShoppingCartByUserId(String id) {
        return shoppingCartMapper.selectShoppingCartByUserId(id);
    }

    /**
     * 增加商品
     * @param cart
     * @return
     */
    @Override
    public boolean addCart(ShoppingCart cart) {
        Integer count = shoppingCartMapper.selectShoppingCartByDishId(cart);
        if (count != null && count > 0) {
            cart.setNumber(++count);
            return shoppingCartMapper.updateByIdSelective(cart) > 0;
        }
        return shoppingCartMapper.insert(cart) > 0;
    }

    /**
     * 减少商品
     * @param cart
     * @return
     */
    @Override
    public boolean subCart(ShoppingCart cart) {
        Integer count = shoppingCartMapper.selectShoppingCartByDishId(cart);
        if (count != null && count > 1) {
            cart.setNumber(--count);
            return shoppingCartMapper.updateByIdSelective(cart) > 0;
        }
        return shoppingCartMapper.deleteCart(cart) > 0;
    }

    /**
     * 清空购物车
     * @param id
     * @return
     */
    @Override
    public boolean cleanCart(String id) {
        return shoppingCartMapper.deleteByUserId(id) > 0;
    }
}
