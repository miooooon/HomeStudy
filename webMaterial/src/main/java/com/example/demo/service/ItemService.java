package com.example.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ItemEntity;
import com.example.demo.repository.ItemDao;

@Service
public class ItemService {

    /** アイテムDAO */
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private ItemEntity itemEntity;

    public ArrayList<ItemEntity> getItemList(String itemtype) {
        List<Map<String, Object>> item = itemDao.getItemList(itemtype);
        if (item.isEmpty()) {
            return null;
        }
        // Modelクラスのインスタンスを生成して検索結果の情報をセット
        ArrayList<ItemEntity> itemList = new ArrayList<ItemEntity>();
        for (Map<String, Object> map : item) {
            itemEntity = new ItemEntity();
            itemEntity.setId((Integer) map.get("id"));
            itemEntity.setItemType((String) map.get("item_type"));
            itemEntity.setTitle((String) map.get("title"));
            itemEntity.setName((String) map.get("name"));
            Timestamp timestamp = (Timestamp) map.get("regist_date");
            itemEntity.setRegistDate(timestamp.toLocalDateTime());
            itemList.add(itemEntity);
        }
        return itemList;
    }

    public ArrayList<ItemEntity> getItem(Integer id) {
        List<Map<String, Object>> item = itemDao.getItem(id);
        if (item.isEmpty()) {
            return null;
        }
        // Modelクラスのインスタンスを生成して検索結果の情報をセット
        ArrayList<ItemEntity> itemDetail = new ArrayList<ItemEntity>();
        for (Map<String, Object> map : item) {
            itemEntity = new ItemEntity();
            itemEntity.setId((Integer) map.get("id"));
            itemEntity.setItemType((String) map.get("item_type"));
            itemEntity.setTitle((String) map.get("title"));
            itemEntity.setName((String) map.get("name"));
            itemEntity.setDetail((String) map.get("detail"));
            Timestamp timestamp = (Timestamp) map.get("regist_date");
            itemEntity.setRegistDate(timestamp.toLocalDateTime());
            itemDetail.add(itemEntity);
        }
        return itemDetail;
    }
}
