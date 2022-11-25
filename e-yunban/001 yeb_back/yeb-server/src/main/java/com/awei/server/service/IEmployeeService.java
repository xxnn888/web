package com.awei.server.service;

import com.awei.server.pojo.Employee;
import com.awei.server.pojo.RespBean;
import com.awei.server.pojo.RespPageBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author awei
 * @since 2022-03-13
 */
public interface IEmployeeService extends IService<Employee> {
    /**
     * @return
     * @description: 获取所有员工（分页）
     * @param: currentPage
     * @param: size
     * @param: employee
     * @param: beginDateScope
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * @return
     * @description: 获取工号
     * @param:
     */
    RespBean maxWorkID();

    /**
     * @return
     * @description: 添加员工
     * @param: employee
     */
    RespBean addEmp(Employee employee);

    /**
     * @return
     * @description: 查询员工
     * @param: id
     */
    List<Employee> getEmployee(Integer id);

    /**
     * @return
     * @description: 获取所有员工账套
     * @param: currentPage
     * @param: size
     */
    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);
}
