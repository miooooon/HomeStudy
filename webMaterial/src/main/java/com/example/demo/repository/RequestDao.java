package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.RequestEntity;

@Repository
public class RequestDao {
    
    @Autowired
    JdbcTemplate data; // Spring JDBCのテンプレートクラス

    public void registRequest(RequestEntity request) {
        String sql = "insert into request_item(item_type, item_tag, regist_date)value(?, ?, ?)";
        data.update(sql, new Object[] { request.getItemType(), request.getItemTag(), request.getRegistDate() });
    }
}
