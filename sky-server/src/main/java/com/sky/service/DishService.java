package com.sky.service;

import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

public interface DishService {

    /**
     * 新增菜品
     * @param dish
     * @return
     */
    Long addDish(Dish dish);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 修改菜品状态
     * @param status
     * @param id
     */
    void setStatus(Integer status, Long id);

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    Dish findById(Long id);

    /**
     * 更新菜品
     * @param dish
     */
    void updateDish(Dish dish);

    /**
     * 删除菜品
     * @param id
     */
    void deleteById(Long id);
}
