//package jp.co.marble.controller.application;
//
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.context.support.MessageSourceAccessor;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.bind.support.SessionStatus;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import jp.co.marble.dao.result.TakeDayOffSpeciallyInfo;
//import jp.co.marble.domain.AppformStatus;
//import jp.co.marble.domain.ShTypeCd;
//import jp.co.marble.domain.common.ApplicationTransitionParam;
//import jp.co.marble.dto.application.TakeDayOffSpeciallyAppBeforeDto;
//import jp.co.marble.dto.application.TakeDayOffSpeciallyAppDto;
//import jp.co.marble.form.RemoteProcedureForm.RemoteProcedure;
//import jp.co.marble.form.application.ApplicationCommentInputForm;
//import jp.co.marble.form.application.TakeDayOffSpeciallyAppDetailForm;
//import jp.co.marble.form.application.TakeDayOffSpeciallyAppInputForm;
//import jp.co.marble.form.application.TakeDayOffSpeciallyAppSessionForm;
//import jp.co.marble.logic.AppformHistoryAccessor;
//import jp.co.marble.mapper.application.TakeDayOffSpeciallyMapper;
//import jp.co.marble.security.LoginAccount;
//import jp.co.marble.service.ApplicationCheckService;
//import jp.co.marble.service.TakeDayOffSpeciallyAppService;
//import jp.co.marble.session.bean.TakeDayOffSpeciallyAppSession;
//import jp.co.marble.workflow.FlowDefinitionOperation;
//import jp.co.marble.workflow.entity.app.Emp;
//
///**
// * 特別休暇申請コントローラ
// *
// * @author ZYYX
// *
// */
//@Controller
//@RequestMapping("application/take_day_off_specially")
//@SessionAttributes({"takeDayOffSpeciallyAppInputForm","ApplicationCommentInputForm","takeDayOffSpeciallyAppDto","takeDayOffSpeciallyAppBeforeDto","takeDayOffSpeciallyInfo"})
//public class TakeDayOffSpeciallyAppController extends BaseApplicationFormController {
//
//    /** テンプレートの場所 */
//    private String templateLocation = this.getClass().getAnnotation(RequestMapping.class).value()[0];
//
//    /** 特別休暇申請サービス */
//    @Autowired
//    private TakeDayOffSpeciallyAppService takeDayOffSpeciallyAppService;
//
//    /** 申請チェックサービス */
//    @Autowired
//    private ApplicationCheckService applicationCheckService;
//
//    /** 特別休暇MAPPER */
//    @Autowired
//    private TakeDayOffSpeciallyMapper takeDayOffSpeciallyMapper;
//
//    /** 帳票履歴DB操作クラス */
//    @Autowired
//    private AppformHistoryAccessor appformHistoryAccessor;
//
//    /** 特別休暇申請セッション保持クラス */
//    @Autowired
//    private TakeDayOffSpeciallyAppSession takeDayOffSpeciallyAppSession;
//
//    /** セッションキー */
//    private static final String COMMENT_HISTORY = "commentHistory";
//    private static final String INPUT_FORM = "takeDayOffSpeciallyAppInputForm";
//    private static final String CONFIRM_DTO = "takeDayOffSpeciallyAppDto";
//    private static final String COMMENT_FORM = "applicationCommentInputForm";
//    private static final String DETAIL_FORM = "takeDayOffSpeciallyForm";
//    private static final String BEFORE_DTO = "takeDayOffSpeciallyAppBeforeDto";
//
//    /** 申請・承認一覧画面リダイレクトURL */
//    private static final String TO_RECOGNITION_SEARCH = "redirect:../../../recognition/search";
//    private static final String TO_APPLICATION_SEARCH = "redirect:../../../application/search";
//
//    /** 承認画面からの遷移フラグ */
//    private static final String FLG_FROM_RECOGNITION = "1";
//    /** 他画面から遷移フラグ */
//    private static final String BACK_FLAG = "1";
//
//    /** メッセージ管理 */
//    private MessageSourceAccessor messageSourceAccessor;
//    @Autowired
//    private void setMessageSourceAccessor(MessageSource messages) {
//        this.messageSourceAccessor = new MessageSourceAccessor(messages);
//    }
//
//    /**
//     * 初期化した入力フォームを取得します。
//     * @return 特別休暇申請入力フォーム
//     */
//    @ModelAttribute(INPUT_FORM)
//    public TakeDayOffSpeciallyAppInputForm getDefaultInputForm() {
//        TakeDayOffSpeciallyAppInputForm inputForm = new TakeDayOffSpeciallyAppInputForm();
//        inputForm.setShTypeOpts(takeDayOffSpeciallyAppService.getShTypeList());
//        return inputForm;
//    }
//
//    /**
//     * 初期化した変更前DTOを取得します。
//     * @return 変更前DTO
//     */
//    @ModelAttribute(BEFORE_DTO)
//    public TakeDayOffSpeciallyAppBeforeDto getDefaultBeforeDto() {
//        return new TakeDayOffSpeciallyAppBeforeDto();
//    }
//
//    /**
//     * 入力画面：初期表示を行います。
//     * @param model モデル
//     * @param userInfo ログイン者情報
//     * @param backFlg 他画面から戻ってきた場合にONとなるフラグ
//     * @param errorMessage エラーメッセージ
//     * @param status セッションステータス
//     * @return 画面テンプレート
//     */
//    @GetMapping("create")
//    public String showCreateForm(Model model,
//            @AuthenticationPrincipal LoginAccount userInfo,
//            @ModelAttribute("backFlg") String backFlg,
//            @ModelAttribute("errorMessage") String errorMessage,
//            SessionStatus status) {
//        // 他画面からの遷移
//        if (BACK_FLAG.equals(backFlg)
//                || !StringUtils.isEmpty(errorMessage)) {
//            return templateLocation + "/input.html";
//        }
//
//        // 処理の開始に伴い、一律でセッション情報を破棄
//        status.setComplete();
//        TakeDayOffSpeciallyAppInputForm inputForm = this.getDefaultInputForm();
//        // 繰越休暇数の取得
//        inputForm.setTrnsTotalNum(takeDayOffSpeciallyAppService.getTrnsTotalNum(userInfo.getEmpCd(), userInfo.getEmpDivCd()));
//        inputForm.setTargetProcedure(RemoteProcedure.入力);
//        model.addAttribute(INPUT_FORM, inputForm);
//        return templateLocation + "/input.html";
//    }
//
//    /**
//     * 入力画面：新規作成時、保存・申請ボタン押下時処理を行います。
//     * @param model モデル
//     * @param select 保存/申請選択
//     * @param userInfo ログイン者情報
//     * @param inputForm 入力フォーム
//     * @param bindingResult バインド結果
//     * @return 画面テンプレート
//     */
//    @PostMapping("create")
//    public String validateInputForm(Model model,
//            @RequestParam(value = "select") String select,
//            @AuthenticationPrincipal LoginAccount userInfo,
//            @ModelAttribute(INPUT_FORM) @Validated TakeDayOffSpeciallyAppInputForm inputForm,
//            BindingResult bindingResult) {
//
//        // 入力チェック
//        if (bindingResult.hasErrors()) {
//            return templateLocation + "/input.html";
//        }
//
//        TakeDayOffSpeciallyAppDto dto =
//                takeDayOffSpeciallyMapper.convertFormToDto(inputForm, ApplicationTransitionParam.of(select), inputForm.getAppidCd());
//        // 実休暇日数を設定
//        dto.setShNum(takeDayOffSpeciallyAppService.getShNum(dto.getOpenDate(), dto.getEndDate(), userInfo.getEmpCd()));
//        if (dto.getShNum() == 0) {
//            // 実休暇日数が0の場合エラー
//            model.addAttribute("errorMessage", messageSourceAccessor.getMessage("errors.app.not.shNum"));
//            return templateLocation + "/input.html";
//        }
//
//        if (ShTypeCd.繰越休暇.getValue() == inputForm.getShType()
//                && dto.getTrnsTotalNum().compareTo(dto.getShNum()) < 0) {
//            // 繰越休暇の場合、残繰越休暇より実休暇日数が大きい場合エラー
//            model.addAttribute("errorMessage", messageSourceAccessor.getMessage("errors.app.asc.exceeds"));
//            return templateLocation + "/input.html";
//        }
//        // セッション更新
//        model.addAttribute(CONFIRM_DTO, dto);
//        return "redirect:confirm?select=" + select;
//    }
//
//    /**
//     * 戻るボタン押下時の処理を行います。
//     * @param inputForm 入力フォーム
//     * @param redirectAttributes リダイレクトパラメータ
//     * @return リダイレクト
//     */
//    @GetMapping("back")
//    public String backToForm(
//            @ModelAttribute(value = INPUT_FORM, binding = false) TakeDayOffSpeciallyAppInputForm inputForm,
//            RedirectAttributes redirectAttributes) {
//        String action = "create";
//        if (RemoteProcedure.編集.equals(inputForm.getTargetProcedure())) {
//            action = "list/" + inputForm.getAppidCd();
//        }
//        redirectAttributes.addFlashAttribute("backFlg", BACK_FLAG);
//        return "redirect:" + action;
//    }
//
//    /**
//     * 入力画面：編集表示を行います。
//     * @param model モデル
//     * @param redirectAttributes リダイレクトアトリビュート
//     * @param userInfo ログイン者情報
//     * @param appidCd 帳票コード
//     * @param rcg 承認フラグ
//     * @param backFlg 他画面から戻ってきた場合にONとなるフラグ
//     * @param errorMessage エラーメッセージ
//     * @param inputForm 入力フォーム
//     * @return 画面テンプレート
//     */
//    @GetMapping("list/{appidCd}")
//    public String showEditForm(Model model,
//            RedirectAttributes redirectAttributes,
//            @AuthenticationPrincipal LoginAccount userInfo,
//            @PathVariable(value = "appidCd", required = false) Integer appidCd,
//            @RequestParam(value = "rcg", required = false) String rcg,
//            @ModelAttribute("backFlg") String backFlg,
//            @ModelAttribute("errorMessage") String errorMessage,
//            @ModelAttribute(value = INPUT_FORM, binding = false) TakeDayOffSpeciallyAppInputForm inputForm) {
//
//        model.addAttribute("rcgFlg", rcg);
//
//        // 他画面からの遷移
//        if (BACK_FLAG.equals(backFlg)
//                || !StringUtils.isEmpty(errorMessage)) {
//            return templateLocation + "/input.html";
//        }
//
//        // 帳票コードがない場合エラー
//        if (appidCd == null) {
//            redirectAttributes.addFlashAttribute("errorMessage", messageSourceAccessor.getMessage("errors.db.not.exists"));
//            return FLG_FROM_RECOGNITION.equals(rcg) ? TO_RECOGNITION_SEARCH : TO_APPLICATION_SEARCH;
//        }
//
//        // データ取得
//        TakeDayOffSpeciallyInfo takeDayOffSpeciallyInfo = takeDayOffSpeciallyAppService.getTakeDayOffSpeciallyApp(appidCd, rcg, userInfo.isRousei());
//        if (takeDayOffSpeciallyInfo == null) {
//            redirectAttributes.addFlashAttribute("errorMessage", messageSourceAccessor.getMessage("errors.db.not.exists"));
//            return FLG_FROM_RECOGNITION.equals(rcg) ? TO_RECOGNITION_SEARCH : TO_APPLICATION_SEARCH;
//        }
//        // 編集開始時のデータをセッションに設定
//        TakeDayOffSpeciallyAppSessionForm sessionForm = new TakeDayOffSpeciallyAppSessionForm();
//        sessionForm.setTakeDayOffSpeciallyInfo(takeDayOffSpeciallyInfo);
//        sessionForm.setRcgFlg(rcg);
//        takeDayOffSpeciallyAppSession.setSessionForm(sessionForm);
//
//        // 画面情報設定
//        inputForm = this.convertEntityToForm(takeDayOffSpeciallyInfo);
//        inputForm.setTargetProcedure(RemoteProcedure.編集);
//        inputForm.setAppidCd(appidCd);
//        model.addAttribute(INPUT_FORM, inputForm);
//        return templateLocation + "/input.html";
//    }
//
//    /**
//     * 特別休暇エンティティから入力フォームへ詰めなおします。
//     * @param takeDayOffSpeciallyInfo 特別休暇エンティティ
//     * @return 入力フォーム
//     */
//    private TakeDayOffSpeciallyAppInputForm convertEntityToForm(TakeDayOffSpeciallyInfo takeDayOffSpeciallyInfo) {
//        TakeDayOffSpeciallyAppInputForm inputForm = takeDayOffSpeciallyMapper.convertEntityToForm(takeDayOffSpeciallyInfo);
//        inputForm.setShTypeOpts(takeDayOffSpeciallyAppService.getShTypeList());
//        return inputForm;
//    }
//
//    /**
//     * 入力画面：編集時、保存・申請ボタン押下時処理を行います。
//     * @param model モデル
//     * @param select 保存/申請選択
//     * @param userInfo ログイン者情報
//     * @param inputForm 入力フォーム
//     * @param bindingResult バインド結果
//     * @return 画面テンプレート
//     */
//    @PostMapping("list/{appidCd}")
//    public String validateEditForm(Model model,
//            @RequestParam(value = "select") String select,
//            @AuthenticationPrincipal LoginAccount userInfo,
//            @ModelAttribute(INPUT_FORM) @Validated TakeDayOffSpeciallyAppInputForm inputForm,
//            BindingResult bindingResult) {
//
//        TakeDayOffSpeciallyAppSessionForm sessionForm = (TakeDayOffSpeciallyAppSessionForm) takeDayOffSpeciallyAppSession.getSessionForm();
//        if (sessionForm != null) {
//            model.addAttribute("rcgFlg", sessionForm.getRcgFlg());
//        }
//
//        // 入力チェック
//        if (bindingResult.hasErrors()) {
//            return templateLocation + "/input.html";
//        }
//
//        TakeDayOffSpeciallyAppDto dto =
//                takeDayOffSpeciallyMapper.convertFormToDto(inputForm, ApplicationTransitionParam.of(select), inputForm.getAppidCd());
//        TakeDayOffSpeciallyAppBeforeDto beforeDto =
//                takeDayOffSpeciallyMapper.convertEntityToBeforeDto(sessionForm.getTakeDayOffSpeciallyInfo());
//        // 実休暇日数を設定
//        dto.setShNum(takeDayOffSpeciallyAppService.getShNum(dto.getOpenDate(), dto.getEndDate(), beforeDto.getEmpCd()));
//
//        // 実休暇日数が0の場合エラー
//        if (dto.getShNum() == 0) {
//            model.addAttribute("errorMessage", messageSourceAccessor.getMessage("errors.app.not.shNum"));
//            return templateLocation + "/input.html";
//        }
//
//        // 繰越休暇の場合、残繰越休暇より実休暇日数が大きい場合エラー
//        if (ShTypeCd.繰越休暇.getValue() == inputForm.getShType()
//                && dto.getTrnsTotalNum().compareTo(dto.getShNum()) < 0) {
//            model.addAttribute("errorMessage", messageSourceAccessor.getMessage("errors.app.asc.exceeds"));
//            return templateLocation + "/input.html";
//        }
//
//        // セッション更新
//        model.addAttribute(BEFORE_DTO, beforeDto);
//        model.addAttribute(CONFIRM_DTO, dto);
//
//        return "redirect:../confirm?select=" + select;
//    }
//
//    /**
//     * 確認画面：初期表示を行います。
//     * @param model モデル
//     * @param redirectAttributes リダイレクトアトリビュート
//     * @param select 保存/申請選択
//     * @param userInfo ログイン者情報
//     * @param dto 画面DTO
//     * @return 画面テンプレート
//     */
//    @GetMapping("confirm")
//    public String showConfirm(Model model,
//            RedirectAttributes redirectAttributes,
//            @RequestParam("select") String select,
//            @AuthenticationPrincipal LoginAccount userInfo,
//            @ModelAttribute(value = CONFIRM_DTO, binding = false) TakeDayOffSpeciallyAppDto dto) {
//
//        // 編集申請時のみコメント履歴を取得し画面に設定
//        if (ApplicationTransitionParam.編集申請.equals(ApplicationTransitionParam.of(select))) {
//            model.addAttribute(COMMENT_HISTORY, appformHistoryAccessor.getAppformHistorysByAppidCd(dto.getAppidCd()));
//        }
//
//        model.addAttribute(COMMENT_FORM, new ApplicationCommentInputForm());
//        return templateLocation + "/confirm.html";
//    }
//
//    /**
//     * 削除確認画面：初期表示を行います。
//     * @param model モデル
//     * @param appidCd 帳票コード
//     * @param rcg 承認フラグ
//     * @param userInfo ログイン者情報
//     * @param redirectAttributes リダイレクトアトリビュート
//     * @return 画面テンプレート
//     */
//    @GetMapping("delete/{appidCd}")
//    public String showConfirmDelete(Model model,
//            @PathVariable(value = "appidCd", required = false) Integer appidCd,
//            @RequestParam(value = "rcg", required = false) String rcg,
//            @AuthenticationPrincipal LoginAccount userInfo,
//            RedirectAttributes redirectAttributes) {
//
//        // 帳票コードがない場合エラー
//        if (appidCd == null) {
//            redirectAttributes.addFlashAttribute("errorMessage", messageSourceAccessor.getMessage("errors.db.not.exists"));
//            return FLG_FROM_RECOGNITION.equals(rcg) ? TO_RECOGNITION_SEARCH : TO_APPLICATION_SEARCH;
//        }
//
//        // データ取得
//        TakeDayOffSpeciallyInfo takeDayOffSpeciallyInfo = takeDayOffSpeciallyAppService.getTakeDayOffSpeciallyApp(appidCd, rcg, userInfo.isRousei());
//        if (takeDayOffSpeciallyInfo == null) {
//            redirectAttributes.addFlashAttribute("errorMessage", messageSourceAccessor.getMessage("errors.db.not.exists"));
//            return FLG_FROM_RECOGNITION.equals(rcg) ? TO_RECOGNITION_SEARCH : TO_APPLICATION_SEARCH;
//        }
//
//        // 編集開始時のデータをセッションに設定
//        TakeDayOffSpeciallyAppSessionForm sessionForm = new TakeDayOffSpeciallyAppSessionForm();
//        sessionForm.setTakeDayOffSpeciallyInfo(takeDayOffSpeciallyInfo);
//        sessionForm.setRcgFlg(rcg);
//        takeDayOffSpeciallyAppSession.setSessionForm(sessionForm);
//
//        // 対象操作を設定
//        TakeDayOffSpeciallyAppInputForm inputForm = this.getDefaultInputForm();
//        inputForm.setTargetProcedure(RemoteProcedure.削除);
//        model.addAttribute(INPUT_FORM, inputForm);
//
//        // 画面情報設定
//        model.addAttribute(CONFIRM_DTO, takeDayOffSpeciallyMapper.convertEntityToDto(takeDayOffSpeciallyInfo, ApplicationTransitionParam.削除, appidCd));
//        model.addAttribute(COMMENT_FORM, new ApplicationCommentInputForm());
//        model.addAttribute("rcgFlg", rcg);
//        return templateLocation + "/confirm.html";
//    }
//
//    /**
//     * 確認画面：完了ボタン押下時処理を行います。
//     * @param model モデル
//     * @param userInfo ログイン情報
//     * @param dto 画面DTO
//     * @param beforeDto 変更前DTO
//     * @param commentInputForm コメント入力フォーム
//     * @param bindingResult バインド結果
//     * @return 画面テンプレート
//     */
//    @Transactional
//    @PostMapping("confirm")
//    public String validateConfirmForm(Model model,
//            @AuthenticationPrincipal LoginAccount userInfo,
//            @ModelAttribute(value = CONFIRM_DTO, binding = false) TakeDayOffSpeciallyAppDto dto,
//            @ModelAttribute(value = BEFORE_DTO, binding = false) TakeDayOffSpeciallyAppBeforeDto beforeDto,
//            @ModelAttribute(COMMENT_FORM) @Validated ApplicationCommentInputForm commentInputForm,
//            BindingResult bindingResult) {
//
//        // 入力チェック
//        if (bindingResult.hasErrors()) {
//            return templateLocation + "/confirm.html";
//        }
//
//        Optional<String> errorMessage = Optional.empty();
//        switch (dto.getAppTransParam()) {
//        case 保存:
//            // 登録処理（新規保存時はチェック処理はなし）
//            takeDayOffSpeciallyAppService.saveApp(dto, userInfo.getEmpCd());
//            return "redirect:complete";
//
//        case 申請:
//            // エラーチェック
//            TakeDayOffSpeciallyAppDto newAppDto = this.validateApp(dto, userInfo, beforeDto);
//            if (newAppDto.getErrorMessage() != null) {
//                model.addAttribute("errorMessage",  messageSourceAccessor.getMessage(newAppDto.getErrorMessage()));
//                return templateLocation + "/confirm.html";
//            }
//            // 登録処理
//            takeDayOffSpeciallyAppService.registerNewApp(newAppDto, userInfo, commentInputForm.getComment());
//            return "redirect:complete";
//
//        case 編集保存:
//            // エラーチェック
//            errorMessage = takeDayOffSpeciallyAppService.checkEditSaveApp(dto, userInfo.isRousei(), beforeDto.getEmpCd());
//            if (errorMessage.isPresent()) {
//                model.addAttribute("errorMessage",  messageSourceAccessor.getMessage(errorMessage.get()));
//                return templateLocation + "/confirm.html";
//            }
//            // 登録処理
//            takeDayOffSpeciallyAppService.saveEditApp(dto, userInfo, beforeDto);
//            return "redirect:complete";
//        case 編集申請:
//            // エラーチェック
//            TakeDayOffSpeciallyAppDto editAppDto = this.validateApp(dto, userInfo, beforeDto);
//            if (editAppDto.getErrorMessage() != null) {
//                model.addAttribute("errorMessage",  messageSourceAccessor.getMessage(editAppDto.getErrorMessage()));
//                return templateLocation + "/confirm.html";
//            }
//            // 登録処理
//            takeDayOffSpeciallyAppService.registerEditApp(userInfo, editAppDto, commentInputForm.getComment(), beforeDto);
//            return "redirect:complete";
//
//        case 削除:
//            // エラーチェック
//            errorMessage = takeDayOffSpeciallyAppService.checkDeleteApp(dto, userInfo.getEmpCd(), userInfo.isRousei());
//            if (errorMessage.isPresent()) {
//                model.addAttribute("errorMessage",  messageSourceAccessor.getMessage(errorMessage.get()));
//                return templateLocation + "/confirm.html";
//            }
//            // 登録処理
//            takeDayOffSpeciallyAppService.deleteApp(dto, userInfo);
//            return "redirect:complete";
//
//        default:
//            return templateLocation + "/confirm.html";
//        }
//    }
//
//    /**
//     * 申請時のチェックを行います。
//     * @param dto 画面DTO
//     * @param userInfo ログイン情報
//     * @param beforeDto 変更前DTO
//     * @return 画面DTO
//     */
//    private TakeDayOffSpeciallyAppDto validateApp(
//            TakeDayOffSpeciallyAppDto dto, LoginAccount userInfo, TakeDayOffSpeciallyAppBeforeDto beforeDto) {
//        TakeDayOffSpeciallyAppDto appDto = takeDayOffSpeciallyMapper.clone(dto);
//        Optional<String> errorMessage = Optional.empty();
//
//        // TODO   勤務表が開かれている場合（処理中の勤務表データがセッション上に存在する場合）、エラーとする
//
//        if (ApplicationTransitionParam.申請.equals(dto.getAppTransParam())) {
//            // 新規申請時
//            errorMessage = takeDayOffSpeciallyAppService.checkNewApp(appDto, userInfo.getEmpCd(), userInfo.isRousei());
//        } else {
//            // 編集申請時
//            errorMessage = takeDayOffSpeciallyAppService.checkEditApp(appDto, beforeDto.getEmpCd(), userInfo.isRousei(), beforeDto);
//        }
//        if (errorMessage.isPresent()) {
//            appDto.setErrorMessage(errorMessage.get());
//            return appDto;
//        }
//
//        // 承認フローチェック
//        String flowDefFile = this.getFlowDefFile(userInfo.getEmpAdcGrpCd());
//        if (flowDefFile == null) {
//            appDto.setErrorMessage("errors.flow.not.exists.approver");
//            return appDto;
//        }
//        FlowDefinitionOperation flowDefinitionOperation = new FlowDefinitionOperation(flowDefFile);
//        // 承認者存在チェック
//        if (!applicationCheckService.isApprover(flowDefinitionOperation.getFirstEmp().get().stream().map(Emp::getEmpCd).collect(Collectors.toList()))) {
//            appDto.setErrorMessage("errors.flow.not.exists.approver");
//            return appDto;
//        }
//
//        // ステータス更新
//        flowDefinitionOperation.forceAllLayerStatus(AppformStatus.承認待);
//        appDto.setFlowDefFile(flowDefinitionOperation.parseFlowDefFile());
//        return appDto;
//    }
//
//    /**
//     * フロー定義を取得します。
//     * @param grpCd 所属コード
//     * @return フロー定義
//     */
//    private String getFlowDefFile(Integer grpCd) {
//        TakeDayOffSpeciallyAppSessionForm sessionForm = (TakeDayOffSpeciallyAppSessionForm) takeDayOffSpeciallyAppSession.getSessionForm();
//        if (sessionForm == null
//                || sessionForm.getTakeDayOffSpeciallyInfo() == null
//                || sessionForm.getTakeDayOffSpeciallyInfo().getFlowDefFile() == null) {
//            return takeDayOffSpeciallyAppService.getFlowDefFile(grpCd);
//        }
//        return sessionForm.getTakeDayOffSpeciallyInfo().getFlowDefFile();
//    }
//
//    /**
//     * 詳細画面：初期表示時の処理を行います。
//     * @param model   モデル
//     * @param appidCd 帳票データコード
//     * @param redirectAttributes リダイレクトアトリビュート
//     * @return 画面テンプレート
//     */
//    @GetMapping("detail/{appidCd}")
//    public String showDetail(Model model,
//            @PathVariable("appidCd") Integer appidCd,
//            RedirectAttributes redirectAttributes) {
//
//        TakeDayOffSpeciallyInfo takeDayOffSpeciallyInfo = takeDayOffSpeciallyAppService.findByAppId(appidCd);
//        if (takeDayOffSpeciallyInfo == null) {
//            redirectAttributes.addFlashAttribute("errorMessage", messageSourceAccessor.getMessage("errors.app.appidCd.match.no.data"));
//            return super.fromRecognition() ? TO_RECOGNITION_SEARCH : TO_APPLICATION_SEARCH;
//        }
//        // 特別休暇詳細を検索、フォームに転記
//        TakeDayOffSpeciallyAppDetailForm  detailForm = takeDayOffSpeciallyMapper.convertEntityToDetailForm(takeDayOffSpeciallyInfo);
//
//        // セッションの値を更新
//        model.addAttribute(DETAIL_FORM, detailForm);
//        model.addAttribute(COMMENT_HISTORY, appformHistoryAccessor.getAppformHistorysByAppidCd(appidCd));
//        model.addAttribute("rcg", super.fromRecognition());
//
//        // 画面表示
//        return templateLocation + "/detail.html";
//    }
//
//    /**
//     * 完了画面：初期表示を行います。
//     * @param model モデル
//     * @param inputForm 入力フォーム
//     * @return 画面テンプレート
//     */
//    @GetMapping("complete")
//    public String showComplete(Model model,
//            @ModelAttribute(value = INPUT_FORM, binding = false) TakeDayOffSpeciallyAppInputForm inputForm) {
//
//        RemoteProcedure procedure = inputForm.getTargetProcedure();
//        if (procedure != null) {
//            model.addAttribute("targetProcedure", procedure.toString());
//        }
//        // セッション初期化
//        TakeDayOffSpeciallyAppInputForm emptyForm = this.getDefaultInputForm();
//        emptyForm.setTargetProcedure(procedure);
//        model.addAttribute(INPUT_FORM, emptyForm);
//
//        return templateLocation + "/complete.html";
//    }
//}
//
//
