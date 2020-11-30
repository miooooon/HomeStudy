package com.example.demo.form;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.example.demo.domain.ItemTag;
import com.example.demo.domain.ItemType;

import lombok.Getter;
import lombok.Setter;


public class RequestForm implements Serializable {
    
    private static final long serialVersionUID = 1L;

//    /** 種類 */
//    private int itemType;
//    
//    /** タグ */
//    private int itemTag;
//
//    
//    /**
//     * itemTypeを取得する。
//     * @return itemType
//     */
//    public int getItemType() {
//        return itemType;
//    }
//
//    
//    /**
//     * itemTypeを設定します。
//     * @param itemType
//     */
//    public void setItemType(int itemType) {
//        this.itemType = itemType;
//    }
//
//    
//    /**
//     * itemTagを取得する。
//     * @return itemTag
//     */
//    public int getItemTag() {
//        return itemTag;
//    }
//
//    
//    /**
//     * itemTagを設定します。
//     * @param itemTag
//     */
//    public void setItemTag(int itemTag) {
//        this.itemTag = itemTag;
//    }
    
    /** 種類 */
    private ItemType itemType;
    
    /** タグ */
    private ItemTag itemTag;

    
    /**
     * itemTypeを取得する。
     * @return itemType
     */
    public ItemType getItemType() {
        return itemType;
    }

    /**
     * itemTypeを設定します。
     * @param itemType
     */
    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    /**
     * itemTagを取得する。
     * @return itemTag
     */
    public ItemTag getItemTag() {
        return itemTag;
    }

    /**
     * itemTagを設定します。
     * @param itemTag
     */
    public void setItemTag(ItemTag itemTag) {
        this.itemTag = itemTag;
    }
}
