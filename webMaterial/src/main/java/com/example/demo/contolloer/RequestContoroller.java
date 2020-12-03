package com.example.demo.contolloer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.domain.ItemTag;
import com.example.demo.domain.ItemType;
import com.example.demo.form.RegistItemForm;
import com.example.demo.form.RequestForm;
import com.example.demo.model.ItemEntity;
import com.example.demo.model.RequestEntity;
import com.example.demo.service.RequestService;

@Controller
@RequestMapping("request")
@SessionAttributes("requestForm")
public class RequestContoroller {

    /** アンケートサービス */
    @Autowired
    private RequestService requestService;

    /** 初期化されたフォーム */
    @ModelAttribute("requestForm")
    public RequestForm getRequestForm() {
        return new RequestForm();
    }

    /** トップページ */
    /** アンケート画面 */
    @GetMapping("top")
    public String requestTop(Model model,@ModelAttribute(value = "requestForm", binding = false)
    @Valid RequestForm requestForm) {
     // 入力用の空フォームを設定
        requestForm =this.getRequestForm();
        

        //選択肢
            Map<String, String> selectItemType = new LinkedHashMap<String, String>();
            selectItemType.put("1", "イラスト");
            selectItemType.put("2", "写真");
            selectItemType.put("3", "アイコン");


            Map<String, String> selectItemTag = new LinkedHashMap<String, String>();
            selectItemTag.put("1", "かわいい");
            selectItemTag.put("2", "かっこいい");
            selectItemTag.put("3", "おしゃれ");
            selectItemTag.put("4", "シンプル");
            selectItemTag.put("5", "ネタ");

        // セッションの値を更新
        model.addAttribute("requestForm", requestForm);
        model.addAttribute("selectItems", selectItemType);
        model.addAttribute("selectTags", selectItemTag);
        return "request/top.html";
    }

    /**
     * 入力画面：確認ボタン押下時の処理。
     */
    @PostMapping("top")
    public String validateForRequest(@ModelAttribute(value = "requestForm")
    @Valid RequestForm requestForm, BindingResult bindingResult) {
        // エラーがある場合、画面遷移する
        if (bindingResult.hasErrors()) {
            return "request/top.html";
        }
        // エラーが無ければ確認画面にリダイレクト
        return "redirect:confirm";
    }

    /**
     * 入力確認画面
     */
    @GetMapping("confirm")
    public String requestConfirm(Model model, @ModelAttribute(value = "requestForm", binding = false)
    RequestForm requestForm) {
        // セッションの値を更新

        ItemType itemType = ItemType.of (Integer.valueOf(requestForm.getItemType()));
        ItemTag itemTag = ItemTag.of (Integer.valueOf(requestForm.getItemTag()));

        model.addAttribute("selectItems", itemType);
        model.addAttribute("selectTags", itemTag);
        return "request/confirm.html";
    }

    /**
     * 入力確認画面：完了ボタン押下時の処理を行います。
     */
    @PostMapping("confirm")
    public String validateRequestConfirm(Model model, @ModelAttribute(value = "requestForm")
    @Valid
    RequestForm requestForm, BindingResult bindingResult) {
        // 登録のために詰めなおし
        RequestEntity request = convertFormToItem(requestForm);
        // 登録処理
        requestService.registRequest(request);
        // エラーが無ければ完了画面にリダイレクト
        return "redirect:complete";
    }

    /** 登録フォームからエンティティに詰めなおし */
    private RequestEntity convertFormToItem(RequestForm requestForm) {
        RequestEntity request = new RequestEntity();
        request.setItemType(Integer.valueOf(requestForm.getItemType()));
        request.setItemTag(Integer.valueOf(requestForm.getItemTag()));
        // 登録日を今の時間に設定
        request.setRegistDate(LocalDateTime.now());
        return request;
    }

    /**
     * 完了画面
     */
    @GetMapping("complete")
    public String showComplete(Model model, @ModelAttribute(value = "requestForm", binding = false)
    RequestForm requestForm) {
        model.addAttribute("requestForm", requestForm);
        // 画面描画
        return "request/complete.html";
    }
}
