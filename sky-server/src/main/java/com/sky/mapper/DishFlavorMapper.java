package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishFlavorMapper {

    /**
     * 新增菜品口味
     * @param dishFlavor
     */
    @Insert("insert into dish_flavor (dish_id, name, value) VALUES (#{dishId}, #{name}, #{value})")
    void insert(DishFlavor dishFlavor);
}
