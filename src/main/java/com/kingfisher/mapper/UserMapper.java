package com.kingfisher.mapper;

import java.util.List;

import com.kingfisher.model.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Integer userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Integer userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	User queryByUserName(String username);
}