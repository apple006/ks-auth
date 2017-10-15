package com.kingfisher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.kingfisher.mapper.UserMapper;
import com.kingfisher.model.User;
import com.kingfisher.service.UserService;

import tk.mybatis.mapper.entity.Example;

@Service("userService")
public class UserServiceImpl extends BaseService<User> implements UserService {
	
	@Autowired
	private UserMapper userMapper;

    @Override
    public PageInfo<User> selectByPage(User user, int start, int length) {
        int page = start/length+1;
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        //判断传过来的用户名是否为空 不为空就拼接sql
        if (StringUtil.isNotEmpty(user.getUsername())) {
            criteria.andLike("username", "%" + user.getUsername() + "%");
        }
        if (user.getId() != null) {
            criteria.andEqualTo("id", user.getId());
        }
        if (user.getEnable() != null) {
            criteria.andEqualTo("enable", user.getEnable());
        }
        //分页查询
        PageHelper.startPage(page, length);
        List<User> userList = selectByExample(example);
        return new PageInfo<User>(userList);
    }

    @Override
	public User selectByUsername(String username) {
		return userMapper.queryByUserName(username);
	}

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void delUser(Integer userid) {
        //删除用户表
        mapper.deleteByPrimaryKey(userid);
//        //删除用户角色表
//        Example example = new Example(UserRole.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("userid",userid);
//        userRoleMapper.deleteByExample(example);
    }

	@Override
	public List<String> queryAllPerms(Integer userId) {
		return userMapper.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
