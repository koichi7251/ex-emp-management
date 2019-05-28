package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * 従業員のデータベース情報を操作するリポジトリクラス.
 * @author koichi.nagata
 *
 */
@Repository
public class EmployeeRepository {
	/**  Employeeからrow_mapperの生成  */
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER =(rs,i)->{
		Employee emp = new Employee();
		emp.setId(rs.getInt("id"));
		emp.setName(rs.getString("name"));
		emp.setImage(rs.getString("image"));
		emp.setGender(rs.getString("gender"));
		emp.setHireDate(rs.getDate("hire_date"));
		emp.setMailAddress(rs.getString("mail_address"));
		emp.setZipCode(rs.getString("zip_code"));
		emp.setAddress(rs.getString("address"));
		emp.setTelephone(rs.getString("telephone"));
		emp.setSalary(rs.getInt("salary"));
		emp.setCharacteristics(rs.getString("characteristics"));
		emp.setDependentsCount(rs.getInt("dependents_count"));
		return emp;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 従業員一覧を入社日順で昇順表示するメソッド.
	 * @return 全従業員一覧、いない場合サイズ0件の従業員一覧を返す
	 */
	public List<Employee> findAll(){
		String sql = "select id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count from employees order by hire_date";
		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		return employeeList;
	}
	
	/**
	 * 従業員をID検索してその従業員の情報を表示する.
	 * @param id　ID
	 * @return 従業員情報
	 */
	public Employee load(Integer id) {
		String sql = "select id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count from employees where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		return employee;
	}
	
	/**
	 * 従業員IDを指定して従業員の扶養人数を変更する.
	 * @param emp　更新したい従業員情報
	 */
	public void update(Employee emp) {
		String sql = "update employee set dependents_count = :dependent_count where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", emp.getId()).addValue("dependents_count", emp.getDependentsCount());
		template.update(sql, param);
	}
}
