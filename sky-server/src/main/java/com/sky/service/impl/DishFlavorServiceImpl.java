package com.sky.service.impl;

import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.service.DishFlavorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DishFlavorServiceImpl implements DishFlavorService {

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    /**
     * 新增菜品口味
     * @param dishFlavor
     */
    @Override
    public void addDishFlavor(DishFlavor dishFlavor) {
        dishFlavorMapper.insert(dishFlavor);
    }

    @Override
    public void addDishFlavors(List<DishFlavor> dishFlavors) {
        dishFlavorMapper.insertBatch(dishFlavors);
    }

    /**
     * 根据菜品id查询口味
     * @param id
     * @return
     */
    @Override
    public List<DishFlavor> findByDishId(Long id) {

        return dishFlavorMapper.findByDishId(id);
    }

    /**
     * 根据菜品id删除口味
     * @param id
     */
    @Override
    public void deleteByDishId(Long id) {
        dishFlavorMapper.deleteByDishId(id);
    }
}
