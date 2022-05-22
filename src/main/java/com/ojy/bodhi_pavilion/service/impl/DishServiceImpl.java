package com.ojy.bodhi_pavilion.service.impl;

import com.ojy.bodhi_pavilion.dto.DishDto;
import com.ojy.bodhi_pavilion.mapper.DishFlavorMapper;
import com.ojy.bodhi_pavilion.mapper.DishMapper;
import com.ojy.bodhi_pavilion.pojo.Dish;
import com.ojy.bodhi_pavilion.pojo.DishFlavor;
import com.ojy.bodhi_pavilion.service.DishService;
import com.ojy.bodhi_pavilion.util.GetId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;


    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    /**
     * 保存新增数据
     * @param dto
     * @return
     */
    @Override
    public boolean saveDish(DishDto dto){
        List<DishFlavor> flavors = dto.getFlavors();
        // 当flavors不为null时，新增dishFlavor数据
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

    /**
     * 分页查询所有数据
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> getDishList(Map<String, Object> map) {
        List<DishDto> data = dishMapper.selectDishList(map);
        int total = dishMapper.selectTotalByCondition((String) map.get("name"));
        // 清空map
        map.clear();
        map.put("records", data);
        map.put("total", total);

        return map;
    }

    /**
     * 查询对应id数据
     * @param id
     * @return
     */
    @Override
    public DishDto getDish(String id) {
        return dishMapper.selectById(id);
    }

    /**
     * 删除对应id数据
     * @param ids
     * @return
     */
    @Override
    public boolean deleteDishByIds(String[] ids) {
        dishFlavorMapper.deleteByDishIds(ids);
        return dishMapper.deleteByIds(ids) > 0;
    }

    /**
     * 修改对应数据
     * @param dto
     * @return
     */
    @Override
    public boolean updateDish(DishDto dto) {
        List<DishFlavor> flavors = dto.getFlavors();

        // 清空对应DishId的DishFlavor数据
        dishFlavorMapper.deleteByDishIds(new String[]{dto.getId()});
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
            dishFlavorMapper.insertFlavors(flavors);
        }

        return dishMapper.updateByIdSelective(dto) > 0;
    }

    /**
     * 查询categoryId的所有数据Dish数据
     * @param categoryId
     * @param name
     * @return
     */
    @Override
    public List<Dish> queryDishByCategoryId(String categoryId, String name) {
        return dishMapper.selectDishByCategoryId(categoryId, name);
    }

    /**
     * 修改status状态
     * @param map
     * @return
     */
    @Override
    public boolean updateDishStatus(Map<String, Object> map) {
        return dishMapper.updateDishes(map) > 0;
    }
}
