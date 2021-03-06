package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者情報の操作をするコントローラークラス.
 * 
 * @author koichi.nagata
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	/**
	 * 管理者情報を登録する.
	 * 
	 * @return form InsertAdministratorFormのリクエストパラメータのインスタンス リクエストスコープに格納される
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministrator() {
		InsertAdministratorForm form = new InsertAdministratorForm();
		return form;
	}

	/**
	 * 管理者登録画面へ遷移する.
	 * 
	 * @return 管理者登録画面
	 */

	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	
	@ModelAttribute
	public InsertAdministratorForm setUpForm() {
		return new InsertAdministratorForm();
	}
	
	/**
	 * 管理者フォームのデータをドメインにコピーしてサービスの登録用メソッドを呼び出して登録をする.
	 * 
	 * @param form viewから受け取った情報の入ったフォーム
	 * @return 「/」にリダイレクト
	 */

	@RequestMapping("/insert")
	public String insert
	(@Validated InsertAdministratorForm form,BindingResult result,RedirectAttributes redirectAttributes,Model model) {
		
		if(result.hasErrors()) {
			return "administrator/insert";
		}
		Administrator administrator = new Administrator();
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());
		administratorService.insert(administrator);
		return "redirect:/";
	}

	/**
	 * ログインする操作をするためのメソッド.
	 * 
	 * @return form LoginFormのリクエストパラメータのインスタンス リクエストスコープに格納される
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		LoginForm form = new LoginForm();
		return form;
	}

	/**
	 * ログイン用画面に遷移する.
	 * 
	 * @return ログイン用画面
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}

	@Autowired
	private HttpSession session;

	/**
	 * ログイン処理.
	 * 
	 * @param form
	 * @return formがnullでエラーメッセージ データが一致した場合従業員一覧画面
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Errors error) {
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		if (administrator == null) {
			error.rejectValue("mailAddress", null, "メールアドレスまたはパスワードが間違っています。");
			return "administrator/login";
		}

//		Administrator administrator = new Administrator();
		session.setAttribute("administratorName", administrator.getName());
		return "forward:/employee/showList";
	}

	/**
	 * ログアウト処理.
	 * 
	 * @param form  フォーム
	 * @param model モデル
	 * @return ログイン画面
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}        
}
