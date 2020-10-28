package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ItemDao {

    @Autowired
    JdbcTemplate data; // Spring JDBCのテンプレートクラス

    /** アイテム一覧取得 */
    public List<Map<String, Object>> getItemList(String itemType) {
        List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
        itemList = data.queryForList(
                "select id, item_type, name, title, regist_date from item_detail  where item_type = ? order by id",
                new Object[] { itemType });
        // 検索結果が空でないことをチェック
        if (itemList.isEmpty()) {
            return null;
        }
        return itemList;
    }

    /** アイテム詳細取得 */
    public List<Map<String, Object>> getItem(Integer id) {
        List<Map<String, Object>> itemDetail = new ArrayList<Map<String, Object>>();
        itemDetail = data.queryForList(
                "select id, item_type, name, title, detail, regist_date from item_detail  where id = ?",
                new Object[] { id });
        // 検索結果が空でないことをチェック
        if (itemDetail.isEmpty()) {
            return null;
        }
        return itemDetail;
    }
//
//    /**
//     * コネクションをクローズします.
//     */
//    public void close() {
//
//      try {
//
//        // データベースとの接続を解除する
//        if(con != null) {
//          con.close();
//        }
//        if(ps != null) {
//          ps.close();
//        }
//        if(rs != null) {
//          rs.close();
//        }
//
//      } catch (SQLException se) {
//
//        // データベースとの接続解除に失敗した場合
//        se.printStackTrace();
//      }
//    }
}
