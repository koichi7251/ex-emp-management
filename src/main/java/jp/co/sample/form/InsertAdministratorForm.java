package jp.co.sample.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 管理者情報のリクエストパラメータ受け取り用のクラス.
 * 
 * @author koichi.nagata
 *
 */
public class InsertAdministratorForm {
	/** 名前 */
	@NotBlank(message="名前は必須です")
	private String name;
	/** メールアドレス */
	@Size(min=1,max=127,message="メールアドレスは1文字以上127文字以内で記載してください")
	@Email(message="メールアドレスの形式が不正です")
	private String mailAddress;
	/** パスワード */
	@NotBlank(message="パスワードは必須です。")
	private String password;

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getMailAddress() {
		return mailAddress;
	}

	public final void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}

}
