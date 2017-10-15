package com.kingfisher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kingfisher.mapper.ResourcesMapper;
import com.kingfisher.model.Resources;
import com.kingfisher.service.ResourcesService;

@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService{
	
	@Autowired
	private ResourcesMapper  resourcesMapper;

	@Override
	public PageInfo<Resources> selectByPage(Resources resources, int start, int length) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addResources(Resources resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delResources(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Resources> loadMenu(Resources resources) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resources> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}