package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ItemEntity;

@Repository
public class RegistItemDao {

    @Autowired
    JdbcTemplate data; // Spring JDBCのテンプレートクラス

    public void registItem(ItemEntity item) {
        String sql = "insert into item_detail(item_type, name, title, detail, regist_date)value(?, ?, ?, ?, ?)";
        data.update(sql, new Object[] { item.getItemType(), item.getName(), item.getTitle(), item.getDetail(),
                item.getRegistDate() });
    }
}
