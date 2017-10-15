package com.kingfisher.secruity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.kingfisher.mapper.ResourcesMapper;
import com.kingfisher.model.Resources;
import com.kingfisher.util.RedisCacheUtil;

/**
 * 加载资源与权限的对应关系
 * 该MyInvocationSecurityMetadataSourceService类在执行时会提取数据库中所有的用户权限，形成权限列表；
 * 并循环该权限列表，通过每个权限再从数据库中提取出该权限所对应的资源列表， 并将资源（URL）作为key，权限列表作为value，形成Map结构的数据。
 */
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Resource
	private ResourcesMapper resourcesMapper;
	// @Resource
	// private RedisCacheUtil RedisCacheUtil;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	/**
	 * @PostConstruct是Java EE 5引入的注解，
	 *                     Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时， @PostConstruct注解的方法会被自动触发，从而完成一些初始化工作，
	 * 
	 *                     //加载所有资源与权限的关系
	 */
	private void loadResourceDefine() {
		// List<Resources> list = RedisCacheUtil.getCacheList("resourcesList");
		System.out.println("数据库资源权限初始化-------------------");
		// resourceMap 相当于一个缓存
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Resources> list = resourcesMapper.queryAllResources();
			for (Resources resources : list) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				ConfigAttribute configAttribute = new SecurityConfig(resources.getPermssions());
				configAttributes.add(configAttribute);
				resourceMap.put(resources.getResurl(), configAttributes);
			}
		}

	}


	// 返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		// System.out.println("requestUrl is " + requestUrl);
		if (resourceMap == null) {
			loadResourceDefine();
		}
		// System.err.println("resourceMap.get(requestUrl);
		// "+resourceMap.get(requestUrl));
		if (requestUrl.indexOf("?") > -1) {
			requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
		}
		Collection<ConfigAttribute> configAttributes = resourceMap.get(requestUrl);
		return configAttributes;
	}

}