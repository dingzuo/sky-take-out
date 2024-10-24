package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishFlavorService;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @PostMapping
    public Result addDish(@RequestBody DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        // 插入菜品并返回菜品的id
        Long dishId = dishService.addDish(dish);

        if(dishId == null) {
            return Result.error("菜品名重复");
        }

        // 插入菜品口味
        List<DishFlavor> dishFlavors = dishDTO.getFlavors();

        dishFlavors.stream().forEach(dishFlavor -> {
            dishFlavor.setDishId(dishId);
        });
        // 批量插入
        dishFlavorService.addDishFlavors(dishFlavors);

        return Result.success();
    }

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        PageResult pageResult = dishService.page(dishPageQueryDTO);

        return Result.success(pageResult);
    }

    /**
     * 修改菜品状态
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result<String> setStatus(@PathVariable Integer status, Long id) {
        dishService.setStatus(status, id);
        return Result.success("更新成功");
    }

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<DishVO> findById(@PathVariable Long id) {
        Dish dish = dishService.findById(id);
        List<DishFlavor> dishFlavors = dishFlavorService.findByDishId(id);

        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(dishFlavors);
        return Result.success(dishVO);
    }

    /**
     * 更新菜品
     * @param dishDTO
     * @return
     */
    @PutMapping
    public Result updateDish(@RequestBody DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        // 更新菜品表
        dishService.updateDish(dish);

        // 更新菜品口味表
        dishFlavorService.deleteByDishId(dish.getId());
        for(DishFlavor dishFlavor: dishDTO.getFlavors()) {
            if(dishFlavor != null) {
                dishFlavor.setDishId(dish.getId());
                dishFlavorService.addDishFlavor(dishFlavor);
            }
        }
        return Result.success();
    }

    /**
     * 删除菜品
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result delete(String ids) {
        String[] strings = ids.split(",");
        for(String s: strings) {

            Long id = Long.valueOf(s);
            //删除菜品口味
            dishFlavorService.deleteByDishId(id);
            //删除菜品
            dishService.deleteById(id);
        }

        return Result.success();
    }

}
