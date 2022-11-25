package com.awei.server.controller;


import com.awei.server.pojo.Menu;
import com.awei.server.service.IAdminService;
import com.awei.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author awei
 * @since 2022-03-13
 */
@RestController
@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "通过用户id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId(){
        return adminService.getMenusByAdminId();
    }
}
