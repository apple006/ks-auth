package com.kingfisher.mapper;

import java.util.List;

import com.kingfisher.model.Resources;
import tk.mybatis.mapper.common.Mapper;

public interface ResourcesMapper extends Mapper<Resources> {
	/**
	 * 查询用户的所有资源
	 */
	List<Resources> queryAllResources();
}