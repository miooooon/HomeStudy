package com.example.demo.contolloer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.bean.ItemTypeBean;
import com.example.demo.domain.ItemTag;
import com.example.demo.domain.ItemType;
import com.example.demo.form.RegistItemForm;
import com.example.demo.form.RequestForm;
import com.example.demo.form.RequestForm;
import com.example.demo.model.ItemEntity;
import com.example.demo.service.ItemService;
import com.example.demo.service.RequestService;
import com.example.demo.service.RequestService;

@Controller
@RequestMapping("request")
//@SessionAttributes("requestForm")
public class RequestContoroller {

    /** アンケートサービス */
    @Autowired
    private RequestService requestService;

    /** 初期化されたアンケートフォーム */
    @ModelAttribute("requestForm")
    public RequestForm getRequestForm() {
        return new RequestForm();
    }

    /** トップページ */
    /** アンケート画面 */
    @GetMapping("top")
    public String requestTop(Model model 
            ,@ModelAttribute(value = "requestForm", binding = false)
    @Valid
    RequestForm requestForm
    ) {

        // セッションの値を更新
        model.addAttribute("selectItems", ItemType.values());
        model.addAttribute("selectTags", ItemTag.values());
        return "request/top.html";
    }

    /**
     * 入力画面：確認ボタン押下時の処理を行います。
     * @param inputForm 入力フォーム
     * @param bindingResult バインド結果
     * @return 画面テンプレート
     */
    @PostMapping("top")
    public String validateForRequest(@ModelAttribute("requestForm")
    @Valid
    RequestForm requestForm, BindingResult bindingResult) {
        // エラーがある場合、自画面遷移する
        if (bindingResult.hasErrors()) {
            return "request/top.html";
        }
//        HttpSession session = request.getSession();
//        session.setAttribute("hogeForm", inputForm);
     // エラーが無ければ確認画面にリダイレクト
        return "redirect:confirm";
    }
    



    
    
    /**
     * 入力確認画面：初期表示時の処理を行います。
     * 
     * @param model
     * @param hogeForm
     * @return
     */
    @GetMapping("confirm")
    public String requestConfirm(Model model, @ModelAttribute(value = "requestForm", binding = false)
    @Valid RequestForm requestForm) {
        

        
            // セッションの値を更新
        model.addAttribute("selectItems", requestForm);
            return "request/confirm.html";
    }

    
    
    /**
     * 入力確認画面：完了ボタン押下時の処理を行います。
     * 
     * @param model
     * @param hogeForm
     * @return
     */
    @PostMapping("confirm")
    public String validateRequestConfirm(Model model, 
            @ModelAttribute(value = "requestForm", binding = false)
            @Valid
            RequestForm requestForm, BindingResult bindingResult) {

     // 登録のために詰めなおし
        // 登録処理

     // エラーが無ければ完了画面にリダイレクト
//        model.addAttribute("requestForm", requestForm);
//        return "request/confirm.html";
        return "redirect:complete";
    }
    /**
     * 完了画面：画面の表示処理を行います。
     * @param model モデル
     * @param commentCd コメントコード
     * @param inputForm 入力フォーム（バインド対象外、直前の処理名を取るために必要）
     * @return 画面テンプレート
     */
//    @GetMapping({"complete", "complete/{commentCd}"})
//    public String showComplete(Model model,
//            @PathVariable(value = "commentCd", required = false) Integer commentCd,
//            @ModelAttribute(value = "commentInputForm", binding = false) CommentInputForm inputForm) {
//
//        // 編集削除判定用のコメントコード
//        if (commentCd != null) {
//            model.addAttribute("commentCd", commentCd);
//        }
//
//        // 入力、削除等のメッセージ
//        RemoteProcedure procedure = inputForm.getTargetProcedure();
//        model.addAttribute("targetProcedure", procedure.toString());
//
//        // 登録完了に伴い、入力フォームを初期化（リロード用に直前の処理種別だけは復元）
//        CommentInputForm emptyForm = this.getDefaultInputForm();
//        emptyForm.setTargetProcedure(procedure);
//        model.addAttribute("commentInputForm", emptyForm);
//
//        // 画面描画
//        return templateLocation + "/complete.html";
//    }
//}

    /** 登録画面 */
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
}
