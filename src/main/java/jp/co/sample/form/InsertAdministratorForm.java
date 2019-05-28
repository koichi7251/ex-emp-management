package jp.co.sample.form;


/**
 * 管理者情報のリクエストパラメータ受け取り用のクラス.
 * @author koichi.nagata
 *
 */
public class InsertAdministratorForm {
	/**  名前  */
	private String name;
	/**  メールアドレス  */
	private String mailAddress;
	/**  パスワード  */
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
