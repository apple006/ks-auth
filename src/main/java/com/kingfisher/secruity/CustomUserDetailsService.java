package com.kingfisher.secruity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.kingfisher.model.User;
import com.kingfisher.service.UserService;
import com.kingfisher.util.RedisCacheUtil;

/**
 * 封装用户信息逻辑 Spring secruity 会拿这个对象进行处理
 */
@Component("myUserDetailService")
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	// @Autowired
	// private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;
//	@Resource
//	private RedisCacheUtil RedisCacheUtil;

	@Override
	public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
		// 根据用户名查找用户信息
		User user = userService.selectByUsername(loginName);
		if (user == null) {
			throw new UsernameNotFoundException("not found");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();

//		List<String> perms = RedisCacheUtil.getCacheList("permsList");
//		if(perms == null) {
//			perms = userService.queryAllPerms(user.getId());
//		}
		List<String> perms = userService.queryAllPerms(user.getId());
		for (String perm : perms) {
			authorities.add(new SimpleGrantedAuthority(perm));
		}
		// 参数username是前台传过来的用户名
		// String password = passwordEncoder.encode("123456"); //在用户注册的时候做这一步
		// logger.info("数据库对应的密码为： " + password);
		return new org.springframework.security.core.userdetails.User(loginName, user.getPassword(), true, true, true,
				true, authorities);
	}

}