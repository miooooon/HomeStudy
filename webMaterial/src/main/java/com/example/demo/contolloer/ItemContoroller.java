package com.example.demo.contolloer;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation

.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.form.RegistItemForm;
import com.example.demo.model.ItemEntity;
import com.example.demo.service.ItemService;
import com.example.demo.service.RegistItemService;

@Controller
@RequestMapping("item")
@SessionAttributes("registItemForm")
public class ItemContoroller {

    /** 素材サービス */
    @Autowired
    private ItemService itemService;

    /** トップページ */
    @GetMapping("/")
    public String mainTop(Model model) {
        // セッションの値を更新
        return "/main.html";
    }

    /** イラスト素材 */
    @GetMapping("picture")
    public String itemList(Model model) {
        String itemType = "イラスト";
        ArrayList<ItemEntity> itemList = itemService.getItemList(itemType);
        // セッションの値を更新
        model.addAttribute("itemList", itemList);
        return "main/photo.html";
    }

    /** 写真素材 */
    @GetMapping("photo")
    public String mainPhoto(Model model) {
        String itemType = "写真";
        ArrayList<ItemEntity> itemList = itemService.getItemList(itemType);
        // セッションの値を更新
        model.addAttribute("itemList", itemList);
        return "main/photo.html";
    }

    /** アイコン素材 */
    @GetMapping("icon")
    public String mainIcon(Model model) {
        String itemType = "アイコン";
        //TODO 表示レイアウトアイコン用に考える
        ArrayList<ItemEntity> itemList = itemService.getItemList(itemType);
        // セッションの値を更新
        model.addAttribute("itemList", itemList);
        return "main/photo.html";
    }

    /** 詳細ページ */
    @GetMapping("{id}")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String itemDetail(Model model, @PathVariable(value = "id", required = false)
    Integer id) {
        ArrayList<ItemEntity> itemDetail = itemService.getItem(id);
        // セッションの値を更新
        model.addAttribute("itemDetail", itemDetail);
        return "main/detail.html";
    }

//    /** 何故か反映されないので退避 */
//    /** アイテム登録サービス */
//    @Autowired
//    private RegistItemService registItemService;
//
//    /** 初期化された登録フォーム */
//    @ModelAttribute("registItemForm")
//    public RegistItemForm getRegistItemForm() {
//        return new RegistItemForm();
//    }
//
//    /** 登録画面 */
//    @GetMapping("top")
//    public String showRegistForm(Model model, @ModelAttribute(value = "registItemForm", binding = false)
//    @Valid
//    RegistItemForm registForm) {
//        // 入力用の空フォームを設定
//        registForm = this.getRegistItemForm();
//        // セッションの値を更新
//        model.addAttribute("registItemForm", registForm);
//        return "regist/regist.html";
//    }
//
//    /** 登録確認画面：登録ボタン押下時 */
//    @Transactional
//    @PostMapping("top")
//    public String registItem(@ModelAttribute(value = "registItemForm")
//    @Valid
//    RegistItemForm registForm, BindingResult bindingResult) {
//        // 登録のために詰めなおし
//        ItemEntity item = convertFormToItem(registForm);
//        // TODO 同じファイル名のデータが登録されている場合はエラー
//        // 登録処理
//        registItemService.registItem(item);
//        // エラーが無ければ完了画面にリダイレクト
//        return "redirect:complete";
//    }
//
//    /** 登録フォームからエンティティに詰めなおし */
//    private ItemEntity convertFormToItem(RegistItemForm registForm) {
//        ItemEntity item = new ItemEntity();
//        item.setItemType(registForm.getItemType());
//        item.setName(registForm.getName());
//        item.setTitle(registForm.getTitle());
//        item.setDetail(registForm.getDetail());
//        // 登録日を今の時間に設定
//        item.setRegistDate(LocalDateTime.now());
//        return item;
//    }
//
//    /** 完了画面 */
//    @GetMapping("complete")
//    public String registComplete(Model model, @ModelAttribute(value = "registItemForm", binding = false)
//    RegistItemForm registForm) {
//        // 登録完了に伴い、入力フォームを初期化（リロード用に直前の処理種別だけは復元）
//        RegistItemForm emptyForm = this.getRegistItemForm();
//        model.addAttribute("msg", registForm.getTitle() + "を追加しました");
//        model.addAttribute("registItemForm", emptyForm);
//        // 画面描画
//        return "/regist/complete.html";
//    }
}
