package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        // 返回insert语句生成的主键值
        return dish.getId();
    }

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @Override
    public PageResult page(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<Dish> dishes = dishMapper.page(dishPageQueryDTO);
        PageResult pageResult = new PageResult(dishes.getTotal(), dishes.getResult());
        return pageResult;
    }

    /**
     * 修改菜品状态
     * @param status
     * @param id
     */
    @Override
    public void setStatus(Integer status, Long id) {
        Dish dish = Dish.builder()
                .id(id)
                .status(status)
                .build();
        dishMapper.update(dish);
    }

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @Override
    public Dish findById(Long id) {
        Dish dish = dishMapper.findById(id);
        return dish;
    }

    /**
     * 更新菜品
     * @param dish
     */
    @Override
    public void updateDish(Dish dish) {
        dishMapper.update(dish);
    }

    /**
     * 根据id删除菜品
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        dishMapper.delete(id);
    }
}
