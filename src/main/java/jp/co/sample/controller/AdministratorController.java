package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
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
	 * @return insertAdministratorForm リクエストパラメータのインスタンス リクエストスコープに格納される
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministrator() {
		InsertAdministratorForm form = new InsertAdministratorForm();
		return form;
	}

	/**
	 * htmlファイルに遷移するためのメソッド.
	 * 
	 * @return "administrator/insert" フォワードする
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
}
