package com.example.demo.contolloer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

import com.example.demo.domain.ItemType;
import com.example.demo.form.QuestionForm;
import com.example.demo.model.ItemEntity;
import com.example.demo.service.ItemService;
import com.example.demo.service.QuestionService;

@Controller
@RequestMapping("question")
@SessionAttributes("questionForm")
public class QuestionContoroller {

    /** アンケートサービス */
    @Autowired
    private QuestionService questionService;

    /** 初期化されたアンケートフォーム */
    @ModelAttribute("questionForm")
    public QuestionForm getQuestionForm() {
        return new QuestionForm();
    }

    /** トップページ */



    /** アンケート画面 */
//    @GetMapping("picture")
//    public String questionSend(Model model, @ModelAttribute(value = "questionForm", binding = false)
//    @Valid
//    QuestionForm questionForm) {
//        ArrayList<ItemEntity> itemList = questionService.getItemList(itemType);
//        
//
//
////        model.addAttribute("enum", ItemType.values());
//        // セッションの値を更新
////        model.addAttribute("itemList", itemList);
//        model.addAttribute("itemTypeList", ItemType.values());
//        return "main/question.html";
//    }
    @GetMapping("top")
    public String questionTop(Model model,@ModelAttribute(value = "questionForm", binding = false)
    @Valid QuestionForm inputForm) {

     // 入力用の空フォームを設定
        inputForm = this.getQuestionForm();
        
//        Map<String, String> selectItems =
//                Collections.unmodifiableMap(new LinkedHashMap<String, String>() {

        //アイテムタイプ選択
                    Map<String, Object> selectItems = new LinkedHashMap<String, Object>() {
                        private static final long serialVersionUID = 1L;
                {
                  put("select_A", "A");
                  put("select_B", "B");
                  put("select_C", "C");
                  put("select_D", "D");
                  put("select_E", "E");
                }
              };
              
              //タグ選択
              Map<String, Object> selectTags = new LinkedHashMap<String, Object>() {
                  private static final long serialVersionUID = 1L;
          {
            put("select_A", "A");
            put("select_B", "B");
            put("select_C", "C");
            put("select_D", "D");
            put("select_E", "E");
          }
        };
        

              // セッションの値を更新
        model.addAttribute("selectItems", selectItems);
        model.addAttribute("selectTags", selectTags);
            return "question/top.html";
    }

    @PostMapping("top")
    public String questionTop(Model model,@ModelAttribute(value = "questionForm", binding = false)
            @Valid QuestionForm inputForm,
            BindingResult bindingResult, HttpServletRequest request) {

        // エラーがある場合、自画面遷移する
        if (bindingResult.hasErrors()) {
            return "question/top.html";
        }

        HttpSession session = request.getSession();
        session.setAttribute("hogeForm", inputForm);
        return "redirect:/question/confirm";
    }
    
    /**
     * 確認画面
     * @param model
     * @param hogeForm
     * @return
     */
//    @GetMapping("confirm")
//    public String questionConfirm(Model model,RedirectAttributes redirectAttributes,
//@ModelAttribute(value = "questionForm", binding = false)
//     QuestionForm hogeForm) {
//            // セッションの値を更新
//        model.addAttribute("selectItems", selectItems);
//            model.addAttribute("hogeContactForm", hogeContactForm);
//            return "question/confirm.html";
//    }
    
    
    /**
     * 確認画面
     * @param model
     * @param hogeForm
     * @return
     */
    @PostMapping("confirm")
    public String validateQuestionConfirm(Model model,RedirectAttributes redirectAttributes,
@ModelAttribute(value = "questionForm", binding = false)
    @Valid  QuestionForm inputForm, BindingResult bindingResult) {
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
        model.addAttribute("questionForm", inputForm);

            return "question/confirm.html";
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
