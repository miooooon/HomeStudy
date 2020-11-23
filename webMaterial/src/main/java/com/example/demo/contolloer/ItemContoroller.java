package com.example.demo.contolloer;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.form.SearchForm;
import com.example.demo.model.ItemEntity;
import com.example.demo.service.ItemService;

@Controller
@RequestMapping("item")
//@SessionAttributes("registItemForm")
@SessionAttributes("searchForm")
public class ItemContoroller {

    /** 素材サービス */
    @Autowired
    private ItemService itemService;
//    @Autowired
//    private SearchForm searchForm;

    /** 初期化された登録フォーム */
    @ModelAttribute("searchForm")
    public SearchForm getSearchForm() {
        return new SearchForm();
    }

    /** トップページ */
    @GetMapping("/")
    public String mainTop(Model model, @ModelAttribute(value = "searchForm", binding = false)
    @Valid
    SearchForm searchForm) {
        // 入力用の空フォームを設定
        searchForm = this.getSearchForm();
        // セッションの値を更新
        model.addAttribute("searchForm", searchForm);
        // セッションの値を更新
        return "/main.html";
    }

    /** イラスト素材 */
    @GetMapping("picture")
    public String itemList(Model model, @ModelAttribute(value = "searchForm", binding = false)
    @Valid
    SearchForm searchForm) {
        // 入力用の空フォームを設定
        searchForm = this.getSearchForm();
        String itemType = "イラスト";
        // TODO enum運用にする。
//        ItemType.イラスト.getValue() ;
        ArrayList<ItemEntity> itemList = itemService.getItemList(itemType);
        // セッションの値を更新
        model.addAttribute("itemList", itemList);
        model.addAttribute("searchForm", searchForm);
        return "main/item.html";
    }

    /** 写真素材 */
    @GetMapping("photo")
    public String mainPhoto(Model model, @ModelAttribute(value = "searchForm", binding = false)
    @Valid
    SearchForm searchForm) {
        // 入力用の空フォームを設定
        searchForm = this.getSearchForm();
        String itemType = "写真";
        ArrayList<ItemEntity> itemList = itemService.getItemList(itemType);
        // セッションの値を更新
        model.addAttribute("itemList", itemList);
        model.addAttribute("searchForm", searchForm);
        return "main/item.html";
    }

    /** アイコン素材 */
    @GetMapping("icon")
    public String mainIcon(Model model, @ModelAttribute(value = "searchForm", binding = false)
    @Valid
    SearchForm searchForm) {
        // 入力用の空フォームを設定
        searchForm = this.getSearchForm();
        String itemType = "アイコン";
        // TODO 表示レイアウトアイコン用に考える
        ArrayList<ItemEntity> itemList = itemService.getItemList(itemType);
        // セッションの値を更新
        model.addAttribute("itemList", itemList);
        model.addAttribute("searchForm", searchForm);
        return "main/item.html";
    }

    /** 詳細ページ */
    @GetMapping("{id}")
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String itemDetail(Model model, @PathVariable(value = "id", required = false)
    Integer id, @ModelAttribute(value = "searchForm", binding = false)
    @Valid
    SearchForm searchForm) {
        // 入力用の空フォームを設定
        searchForm = this.getSearchForm();
        ArrayList<ItemEntity> itemDetail = itemService.getItem(id);
        // セッションの値を更新
        model.addAttribute("itemDetail", itemDetail);
        model.addAttribute("searchForm", searchForm);
        return "main/detail.html";
    }

    /** けんさく */
    @PostMapping("search")
    public String search(Model model, SearchForm searchForm) {
        ArrayList<ItemEntity> itemList = itemService.getSearchItem(searchForm.getKeyword());
        // TODO 2ワード以上検索
        // TODO タグ絞り込み機能
        // セッションの値を更新
        model.addAttribute("itemList", itemList);
        model.addAttribute("searchForm", searchForm);
        return "main/item.html";
    }

}
