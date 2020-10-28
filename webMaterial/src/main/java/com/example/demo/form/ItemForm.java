package com.example.demo.form;

import java.io.Serializable;
import java.time.LocalDate;

public class ItemForm implements Serializable {
    


    private static final long serialVersionUID = 1L;
        

    /** ID */
//    @Id
//    @Column(name = "id")
    private int id;
    
    /** 素材タイプ */
//    @Column(name = "item_type")
    private String itemType;
    
    /** 名前 */
//    @Column(name = "name")
    private String name;
    
    /** 表示タイトル */
//    @Column(name = "title")
    private String title;
    
    /** 詳細 */
//    @Column(name = "detail")
    private String detail;
    
//    /** 素材タグ */
//    @Column(name = "item_tag")
//    List<ItemTag> itemTag;
    
    /** 登録日 */
//    @Column(name = "regist_date")
    private LocalDate registDate;

    
    /**
     * idを取得する。
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * idを設定します。
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * itemTypeを取得する。
     * @return itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * itemTypeを設定します。
     * @param itemType
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * nameを取得する。
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * nameを設定します。
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * titleを取得する。
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * titleを設定します。
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * detailを取得する。
     * @return detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * detailを設定します。
     * @param detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

//    /**
//     * itemTagを取得する。
//     * @return itemTag
//     */
//    public List<ItemTag> getItemTag() {
//        return itemTag;
//    }

//    /**
//     * itemTagを設定します。
//     * @param itemTag
//     */
//    public void setItemTag(List<ItemTag> itemTag) {
//        this.itemTag = itemTag;
//    }

    /**
     * registDateを取得する。
     * @return registDate
     */
    public LocalDate getRegistDate() {
        return registDate;
    }

    /**
     * registDateを設定します。
     * @param registDate
     */
    public void setRegistDate(LocalDate registDate) {
        this.registDate = registDate;
    }
}

