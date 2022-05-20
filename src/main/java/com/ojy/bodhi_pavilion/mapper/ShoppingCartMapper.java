package com.ojy.bodhi_pavilion.mapper;

import com.ojy.bodhi_pavilion.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    int insert(ShoppingCart shoppingCart);

    ShoppingCart selectById(String id);

    int updateByIdSelective(ShoppingCart shoppingCart);

    List<ShoppingCart> selectShoppingCartByUserId(String id);

    Integer selectShoppingCartByDishId(ShoppingCart cart);

    int deleteCart(ShoppingCart cart);

    int deleteByUserId(String UserId);

}