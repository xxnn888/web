package com.awei.server.mapper;

import com.awei.server.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author awei
 * @since 2022-03-13
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * @return
     * @description: 获取所有员工（分页）
     * @param: page
     * @param: employee
     * @param: beginDateScope
     */
    IPage<Employee> getEmployeeByPage(Page<Employee> page, @Param("employee") Employee employee, @Param("beginDateScope") LocalDate[] beginDateScope);

    /**
     * @return
     * @description: 查询员工
     * @param: id
     */
    List<Employee> getEmployee(Integer id);

    /**
     * @return
     * @description: 获取所有员工账套
     * @param: page
     */
    IPage<Employee> getEmployeeWithSalary(Page<Employee> page);
}
