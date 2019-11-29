package com.wsz.text.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
    Long selectCount();
    List<T> selectPage(Map<String,Object> map);
    int insert (T t);
}
