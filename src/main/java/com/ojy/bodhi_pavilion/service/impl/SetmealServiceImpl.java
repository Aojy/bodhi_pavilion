package com.ojy.bodhi_pavilion.service.impl;

import com.ojy.bodhi_pavilion.dto.SetmealDto;
import com.ojy.bodhi_pavilion.mapper.SetmealDishMapper;
import com.ojy.bodhi_pavilion.mapper.SetmealMapper;
import com.ojy.bodhi_pavilion.pojo.Setmeal;
import com.ojy.bodhi_pavilion.pojo.SetmealDish;
import com.ojy.bodhi_pavilion.service.SetmealService;
import com.ojy.bodhi_pavilion.util.GetId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    /**
     * 分页查询所有数据
     * @return
     */
    @Override
    public Map<String, Object> getSetmealList(Map<String, Object> map) {
        List<Setmeal> data = setmealMapper.selectSetmealList(map);
        int total = setmealMapper.selectTotalByCondition((String) map.get("name"));
        map.clear();
        map.put("records", data);
        map.put("total", total);
        return map;
    }

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    @Override
    public Setmeal getSetmeal(String id) {
        return setmealMapper.selectById(id);
    }

    /**
     * 保存新增的数据
     * @param dto
     * @return
     */
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

    /**
     * 修改对应数据信息
     * @param dto
     * @return
     */
    @Override
    public boolean updateSetmeal(SetmealDto dto) {
        List<SetmealDish> setmealDishes = dto.getSetmealDishes();


        setmealDishMapper.deleteByDishIds(new String[]{dto.getId()});
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
            setmealDishMapper.insertSetmealDishs(setmealDishes);
        }
        return setmealMapper.updateByIdSelective(dto) > 0;
    }

    /**
     * 删除对应id的数据
     * @param strings
     * @return
     */
    @Override
    public boolean deleteSetmealByIds(String[] strings) {
        setmealDishMapper.deleteByDishIds(strings);
        return setmealMapper.deleteByIds(strings) > 0;
    }

    /**
     * 修改对应id的status状态
     * @param map
     * @return
     */
    @Override
    public boolean updateSetmealStatus(Map<String, Object> map) {
        return setmealMapper.updateSetmeals(map) > 0;
    }

    /**
     * 查询对应categoryId的数据
     * @param categoryId
     * @return
     */
    @Override
    public List<Setmeal> getList(String categoryId) {
        return setmealMapper.selectListByCategoryId(categoryId);
    }


}
