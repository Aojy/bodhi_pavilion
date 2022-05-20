package com.ojy.bodhi_pavilion.service.impl;

import com.ojy.bodhi_pavilion.dto.DishDto;
import com.ojy.bodhi_pavilion.mapper.DishFlavorMapper;
import com.ojy.bodhi_pavilion.mapper.DishMapper;
import com.ojy.bodhi_pavilion.pojo.Dish;
import com.ojy.bodhi_pavilion.pojo.DishFlavor;
import com.ojy.bodhi_pavilion.service.DishService;
import com.ojy.bodhi_pavilion.uitl.GetId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;


    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Override
    public boolean saveDish(DishDto dto){
        List<DishFlavor> flavors = dto.getFlavors();
        if (flavors != null) {
            for (DishFlavor flavor : flavors) {
                flavor.setId(GetId.getId());
                flavor.setDishId(dto.getId());
                flavor.setCreateTime(dto.getCreateTime());
                flavor.setCreateUser(dto.getCreateUser());
                flavor.setUpdateTime(dto.getUpdateTime());
                flavor.setUpdateUser(dto.getUpdateUser());
                flavor.setIsDeleted(0);
            }
            dishFlavorMapper.insertFlavors(flavors);
        }
        return dishMapper.insert(dto) > 0;
    }

    @Override
    public Map<String, Object> getDishList(Integer page, Integer pageSize, String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("start", (page - 1) * pageSize);
        map.put("size", pageSize);
        map.put("name", name);
        List<DishDto> data = dishMapper.selectDishList(map);
        int total = dishMapper.selectTotalByCondition(name);
        map.clear();
        map.put("records", data);
        map.put("total", total);

        return map;
    }

    @Override
    public DishDto getDish(String id) {
        Long idL = Long.parseLong(id);
        return dishMapper.selectById(idL);
    }

    @Override
    public boolean deleteDishByIds(String[] ids) {
        try {
            dishFlavorMapper.deleteByDishIds(ids);
            return dishMapper.deleteByIds(ids) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateDish(DishDto dto) {
        List<DishFlavor> flavors = dto.getFlavors();
        if (flavors != null) {
            for (DishFlavor flavor:flavors) {
                flavor.setUpdateUser(dto.getUpdateUser());
                flavor.setUpdateTime(dto.getUpdateTime());
                if (flavor.getId() == null) {
                    flavor.setId(GetId.getId());
                    flavor.setDishId(dto.getId());
                    flavor.setCreateTime(dto.getUpdateTime());
                    flavor.setCreateUser(dto.getUpdateUser());
                    flavor.setIsDeleted(0);
                }
            }
            String[] arr = new String[]{dto.getId()};
            dishFlavorMapper.deleteByDishIds(arr);
            dishFlavorMapper.insertFlavors(flavors);
        }
        return dishMapper.updateByIdSelective(dto) > 0;
    }

    @Override
    public List<Dish> queryDishByCategoryId(String categoryId, String name) {
        return dishMapper.selectDishByCategoryId(categoryId, name);
    }

    @Override
    public boolean updateDishStatus(String[] strings, Integer code) {
        Map<String, Object> map = new HashMap<>();
        map.put("ids", strings);
        map.put("code", code);
        return dishMapper.updateDishes(map) > 0;
    }
}
