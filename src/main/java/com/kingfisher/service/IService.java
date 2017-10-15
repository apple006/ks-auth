package com.kingfisher.service;

import java.util.List;

public interface IService<T> {

    /**
     * 根据指定的key查询
     * @param key
     * @return
     */
    T selectByKey(Object key);

    /**
     * 添加实体
     * @param entity
     * @return
     */
    int save(T entity);

    /**
     * 根据指定key删除实体
     * @param key
     * @return
     */
    int delete(Object key);

    /**
     * 批量更新
     * @param entity
     * @return
     */
    int updateAll(T entity);

    /**
     * 根据对象查询结果集
     * @param example
     * @return
     */
    List<T> selectByExample(Object example);

    //TODO 其他...
}
