package jp.co.sample.form;

/**
 * 従業員の情報の更新時に使用する
 * 
 * @author koichi.nagata
 *
 */
public class UpdateEmployeeForm {
	/** 従業員ID */
	private String id;
	/** 扶養人数 */
	private String dependentsCount;
	
	
	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}
	public final String getDependentsCount() {
		return dependentsCount;
	}
	public final void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}
	
	
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
	}
	
	
}
