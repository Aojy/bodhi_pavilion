package com.ojy.bodhi_pavilion.service.impl;

import com.ojy.bodhi_pavilion.dto.SetmealDto;
import com.ojy.bodhi_pavilion.mapper.SetmealDishMapper;
import com.ojy.bodhi_pavilion.mapper.SetmealMapper;
import com.ojy.bodhi_pavilion.pojo.Setmeal;
import com.ojy.bodhi_pavilion.pojo.SetmealDish;
import com.ojy.bodhi_pavilion.service.SetmealService;
import com.ojy.bodhi_pavilion.uitl.GetId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Override
    public Map<String, Object> getSetmealList(Integer page, Integer pageSize, String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("start", (page - 1) * pageSize);
        map.put("size", pageSize);
        map.put("name", name);
        List<Setmeal> data = setmealMapper.selectSetmealList(map);
        int total = setmealMapper.selectTotalByCondition(name);
        map.clear();
        map.put("records", data);
        map.put("total", total);
        return map;
    }

    @Override
    public Setmeal getSetmeal(String id) {
        return setmealMapper.selectById(id);
    }

    @Override
    public boolean saveSetmeal(SetmealDto dto) {
        List<SetmealDish> setmealDishes = dto.getSetmealDishes();
        if (setmealDishes != null) {
            for (SetmealDish sd: setmealDishes) {
                sd.setId(GetId.getId());
                sd.setSetmealId(dto.getId());
                sd.setCreateTime(dto.getCreateTime());
                sd.setCreateUser(dto.getCreateUser());
                sd.setUpdateTime(dto.getUpdateTime());
                sd.setUpdateUser(dto.getUpdateUser());
                sd.setIsDeleted(0);
                sd.setSort(0);
            }
            setmealDishMapper.insertSetmealDishs(setmealDishes);
        }
        return setmealMapper.insert(dto) > 0;
    }

    @Override
    public boolean updateSetmeal(SetmealDto dto) {
        List<SetmealDish> setmealDishes = dto.getSetmealDishes();
        if (setmealDishes != null) {
            for (SetmealDish sd: setmealDishes) {
                sd.setUpdateTime(dto.getUpdateTime());
                sd.setUpdateUser(dto.getUpdateUser());
                if (sd.getId() == null) {
                    sd.setId(GetId.getId());
                    sd.setSetmealId(dto.getId());
                    sd.setCreateTime(dto.getUpdateTime());
                    sd.setCreateUser(dto.getUpdateUser());
                    sd.setIsDeleted(0);
                    sd.setSort(0);
                }
            }
            String[] arr = new String[]{dto.getId()};
            setmealDishMapper.deleteByDishIds(arr);
            setmealDishMapper.insertSetmealDishs(setmealDishes);
        }
        return setmealMapper.updateByIdSelective(dto) > 0;
    }

    @Override
    public boolean deleteSetmealByIds(String[] strings) {
        setmealDishMapper.deleteByDishIds(strings);
        return setmealMapper.deleteByIds(strings) > 0;
    }

    @Override
    public boolean updateSetmealStatus(String[] strings, Integer code) {
        Map<String, Object> map = new HashMap<>();
        map.put("ids", strings);
        map.put("code", code);
        return setmealMapper.updateSetmeals(map) > 0;
    }

    @Override
    public List<Setmeal> getList(String categoryId) {
        return setmealMapper.selectListByCategoryId(categoryId);
    }


}
