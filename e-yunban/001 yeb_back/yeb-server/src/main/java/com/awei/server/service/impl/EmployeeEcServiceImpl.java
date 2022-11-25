package com.awei.server.service.impl;

import com.awei.server.mapper.EmployeeEcMapper;
import com.awei.server.mapper.EmployeeMapper;
import com.awei.server.pojo.Employee;
import com.awei.server.pojo.EmployeeEc;
import com.awei.server.pojo.RespPageBean;
import com.awei.server.service.IEmployeeEcService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author awei
 * @since 2022-03-13
 */
@Service
public class EmployeeEcServiceImpl extends ServiceImpl<EmployeeEcMapper, EmployeeEc> implements IEmployeeEcService {


}
