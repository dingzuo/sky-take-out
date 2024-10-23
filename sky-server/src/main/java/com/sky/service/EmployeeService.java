package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;


public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 按id查询
     * @param id
     * @return
     */
    Employee findById(Long id);

    /**
     * 修改员工账户状态
     * @param id
     * @param status
     */
    void setStatus(Long id, Integer status);

    /**
     * 更新用户信息
     * @param employeeDTO
     * @return
     */
    void updateEmployee(EmployeeDTO employeeDTO);
}
