package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /** ID */
    private int id;
    /** 素材タイプ */
    private String itemType;
    /** 名前 */
    private String name;
    /** 表示タイトル */
    private String title;
    /** 詳細 */
    private String detail;
    /** 登録日 */
    private LocalDateTime registDate;

    public ItemEntity() {
        this.id = 0;
        this.itemType = "";
        this.name = "";
        this.title = "";
        this.detail = "";
        this.registDate = null;
    }

    /**
     * idを取得する。
     * 
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * idを設定します。
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * itemTypeを取得する。
     * 
     * @return itemType
     */
    public String getItemType() {
        return this.itemType;
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
     * nameを取得する。
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * nameを設定します。
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * titleを取得する。
     * 
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * titleを設定します。
     * 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * detailを取得する。
     * 
     * @return detail
     */
    public String getDetail() {
        return this.detail;
    }

    /**
     * detailを設定します。
     * 
     * @param detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * registDateを取得する。
     * 
     * @return registDate
     */
    public LocalDateTime getRegistDate() {
        return this.registDate;
    }

    /**
     * registDateを設定します。
     * 
     * @param registDate
     */
    public void setRegistDate(LocalDateTime registDate) {
        this.registDate = registDate;
    }
}
