package com.awei.server.service;

import com.awei.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author awei
 * @since 2022-03-13
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 根据角色获取菜单列表
     *
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * @return
     * @description: 查询所有菜单
     * @param:
     */
    List<Menu> getAllMenus();
}
