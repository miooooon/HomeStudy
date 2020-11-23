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
    private ItemType itemType;
    
    /** タグ */
    private ItemTag itemTag;
    
    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public ItemTag getItemTag() {
        return itemTag;
    }

    public void setItemTag(ItemTag itemTag) {
        this.itemTag = itemTag;
    }

//        @NotBlank(message="選択してね")
//        private String category;
//
//        @NotBlank(message="何か入力してね")
//        @Length(max=1000)
//        private String content;
//
//        public Map<String, String> getCategoryList() {
//        Map<String, String> categoryMap = new LinkedHashMap<String, String>();
//            categoryMap.put("1", "カテゴリー１");
//            categoryMap.put("2", "カテゴリー２");
//            categoryMap.put("3", "カテゴリー３");
//            return categoryMap;
//        }
    
}
