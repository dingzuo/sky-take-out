package com.sky.service.impl;

import com.sky.entity.Dish;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    /**
     * 新增菜品
     * @param dish
     * @return
     */
    @Override
    public Long addDish(Dish dish) {
        // 查询菜品名是否存在
        Dish existsDish = dishMapper.findByName(dish.getName());
        if(existsDish != null) {
            return null;
        }

        // 插入菜品
        dishMapper.insert(dish);

        // 查询刚刚插入菜品的id
        dish = dishMapper.findByName(dish.getName());

        return dish.getId();
    }
}
