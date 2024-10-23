package com.sky.service;

import com.sky.entity.Dish;

public interface DishService {

    /**
     * 新增菜品
     * @param dish
     * @return
     */
    Long addDish(Dish dish);
}
