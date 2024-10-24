package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * 新增菜品口味
     * @param dishFlavor
     */
    @Insert("insert into dish_flavor (dish_id, name, value) VALUES (#{dishId}, #{name}, #{value})")
    void insert(DishFlavor dishFlavor);

    /**
     * 根据菜品id查询口味
     * @param id
     * @return
     */
    @Select("select * from dish_flavor where dish_id = #{id}")
    List<DishFlavor> findByDishId(Long id);

    /**
     * 根据菜品id删除口味
     * @param id
     */
    @Delete("delete from dish_flavor where dish_id = #{id}")
    void deleteByDishId(Long id);

    /**
     * 批量插入
     * @param dishFlavors
     */
    void insertBatch(List<DishFlavor> dishFlavors);
}
