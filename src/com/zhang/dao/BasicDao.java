package com.zhang.dao;

import java.util.List;
import java.util.Map;

public interface BasicDao {
    public List<Map<String, Object>> select(String sql, List<Object> params, List<String> colList) throws Exception;
}
