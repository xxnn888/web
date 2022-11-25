package com.awei.server.mapper;

import com.awei.server.pojo.AdminRole;
import com.awei.server.pojo.RespBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author awei
 * @since 2022-03-13
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * @return
     * @description: 跟新操作员角色
     * @param: adminId
     * @param: rids
     */
    Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
