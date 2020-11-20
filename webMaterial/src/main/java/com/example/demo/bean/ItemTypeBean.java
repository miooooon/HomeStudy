package com.example.demo.bean;

import java.util.List;

import com.example.demo.domain.ItemType;

public class ItemTypeBean {

    private List<ItemType> itemType = null;
    private ItemType selected = null;

    public List<ItemType> getItemType() {
        return itemType;
    }

    public void setItemType(List<ItemType> itemType) {
        this.itemType = itemType;
    }

    public ItemType getSelected() {
        return selected;
    }

    public void setItemType(ItemType selected) {
        this.selected = selected;
    }

    public void setSelected(ItemType イラスト) {
        // TODO 自動生成されたメソッド・スタブ
        
    }

}