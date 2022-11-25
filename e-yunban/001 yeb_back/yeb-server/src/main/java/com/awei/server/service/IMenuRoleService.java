package com.awei.server.service;

import com.awei.server.pojo.MenuRole;
import com.awei.server.pojo.RespBean;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author awei
 * @since 2022-03-13
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * @return
     * @description: 更新角色菜单
     * @param: rid
     * @param: mids
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
