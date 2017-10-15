package com.kingfisher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kingfisher.service.IService;

import tk.mybatis.mapper.common.Mapper;

public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    public int save(T entity) {
        return mapper.insert(entity);
    }

    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    //TODO 其他...
}
