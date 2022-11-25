package com.awei.server.mapper;

import com.awei.server.pojo.Admin;
import com.awei.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author awei
 * @since 2022-03-13
 */
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * @return
     * @description: 获取所有操作员
     * @param: keywords
     */
    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);
}
