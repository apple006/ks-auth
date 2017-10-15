package com.kingfisher.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.kingfisher.model.Resources;

public interface ResourcesService {
	
	
	public PageInfo<Resources> selectByPage(Resources resources, int start, int length);
	
	public void addResources(Resources resources);
	
	public void delResources(Integer id);
	
	public List<Resources> loadMenu(Resources resources);

	public List<Resources>queryAll();
}