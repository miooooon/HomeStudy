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
    /** 種類 */
    private String itemType;
    
    /** タグ */
    private String itemTag;

    /**
     * itemTypeを取得する。
     * 
     * @return itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * itemTypeを設定します。
     * 
     * @param itemType
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * itemTagを取得する。
     * 
     * @return itemTag
     */
    public String getItemTag() {
        return itemTag;
    }

    /**
     * itemTagを設定します。
     * 
     * @param itemTag
     */
    public void setItemTag(String itemTag) {
        this.itemTag = itemTag;
    }
}
