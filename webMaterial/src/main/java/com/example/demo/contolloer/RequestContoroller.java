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
//            ,@ModelAttribute(value = "requestForm", binding = false)
//    @Valid
//    RequestForm inputForm
    ) {

        // セッションの値を更新
        model.addAttribute("selectItems", ItemType.values());
        model.addAttribute("selectTags", ItemTag.values());
        return "request/top.html";
    }

    @PostMapping("top")
    public String requestTop(Model model, @ModelAttribute(value = "requestForm", binding = false)
    @Valid
    RequestForm inputForm, BindingResult bindingResult, HttpServletRequest request) {
        // エラーがある場合、自画面遷移する
        if (bindingResult.hasErrors()) {
            return "request/top.html";
        }
        HttpSession session = request.getSession();
        session.setAttribute("hogeForm", inputForm);
        return "redirect:/request/confirm";
    }
    /**
     * 確認画面
     * 
     * @param model
     * @param hogeForm
     * @return
     */
    @GetMapping("confirm")
    public String requestConfirm(Model model,RedirectAttributes redirectAttributes,
@ModelAttribute(value = "requestForm", binding = false)
     RequestForm requestForm) {
            // セッションの値を更新
        model.addAttribute("selectItems", requestForm);
            return "request/confirm.html";
    }

    /**
     * 確認画面
     * 
     * @param model
     * @param hogeForm
     * @return
     */
    @PostMapping("confirm")
    public String validateRequestConfirm(Model model, RedirectAttributes redirectAttributes,
            @ModelAttribute(value = "requestForm", binding = false)
            @Valid
            RequestForm inputForm, BindingResult bindingResult) {
        // 入力確認画面からの復帰か否かを判定
//        if (!inputForm.isCreateForm()) {
//            throw new HttpBadRequestException(this.getClass(), messageSourceAccessor.getMessage("errors.mng.transition.create"));
//        }
        // 入力確認画面用にフォームを設定
//        if (inputForm.getCmtStatusSelect() != null) {
//            inputForm.setCmtStatusFlg(CmtStatusFlg.of(inputForm.getCmtStatusSelect()));
//        } else {
//            inputForm.setCmtStatusFlg(CmtStatusFlg.使用可);
//        }
        // セッションの値を更新
        model.addAttribute("requestForm", inputForm);
        return "request/confirm.html";
    }
//    @GetMapping
//    public ModelAndView index() {
//       ModelAndView model = new ModelAndView();
//       model.setAttribute("statusList", ItemType.values());
//       return model;
//    }
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
