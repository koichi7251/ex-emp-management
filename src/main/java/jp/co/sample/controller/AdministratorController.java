package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	 * 管理者情報を登録するメソッド.
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

	/**
	 * 管理者フォームのデータをドメインにコピーしてサービスの登録用メソッドを呼び出して登録をするメソッド.
	 * 
	 * @param form viewから受け取った情報の入ったフォーム
	 * @return 「/」にリダイレクト
	 */

	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
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
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, Errors error) {
		if (administratorService.login(form.getMailAddress(), form.getPassword()) == null) {
			error.rejectValue("mail_address", null, "メールアドレスまたはパスワードが間違っています。");
		} else {
			Administrator administrator = new Administrator();
			session.setAttribute("administratroName", administrator.getName());
		}
		return "forward:/employee/showList";
	}
}
