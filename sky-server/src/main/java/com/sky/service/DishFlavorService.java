package com.sky.service;

import com.sky.entity.DishFlavor;

import java.util.List;

public interface DishFlavorService {
    /**
     * 新增菜品口味
     * @param dishFlavor
     */
    void addDishFlavor(DishFlavor dishFlavor);

    void addDishFlavors(List<DishFlavor> dishFlavors);

    /**
     * 根据菜品id查询口味
     * @param id
     * @return
     */
    List<DishFlavor> findByDishId(Long id);

    /**
     * 根据菜品id删除口味
     * @param id
     */
    void deleteByDishId(Long id);
}
