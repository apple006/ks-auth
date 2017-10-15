package com.kingfisher.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.kingfisher.model.User;

public interface UserService extends IService<User> {
	
	/**
     * 分页查询
     * @param user
     * @param start
     * @param length
     * @return
     */
    PageInfo<User> selectByPage(User user, int start, int length);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 根据用户ID 删除用户
     * @param userid
     */
    void delUser(Integer userid);
    
    /**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Integer userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Integer userId);
	

}
