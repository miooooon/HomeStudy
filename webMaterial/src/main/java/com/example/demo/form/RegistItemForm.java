package com.example.demo.form;

public class RegistItemForm {

    /** 素材タイプ */
    private String itemType;
    
    /** 名前 */
    private String name;
    
    /** 表示タイトル */
    private String title;
    
    /** 詳細 */
    private String detail;
    
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
}
