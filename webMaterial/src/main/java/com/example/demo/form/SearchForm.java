package com.example.demo.form;

import java.io.Serializable;
import java.time.LocalDate;

public class SearchForm  {
    

//
//    private static final long serialVersionUID = 1L;
//        
//
    /** 検索ワード */
    private String keyword;
    


    /**
     * keywordを取得する。
     * @return keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * keywordを設定します。
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
