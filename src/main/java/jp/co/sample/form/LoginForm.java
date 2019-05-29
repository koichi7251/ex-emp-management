package jp.co.sample.form;

/**
 * ログイン時に使用するフォーム
 * 
 * @author koichi.nagata
 *
 */
public class LoginForm {
	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;

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
		return "LoginForm [mailAddress=" + mailAddress + ", password=" + password + "]";
	}

}
