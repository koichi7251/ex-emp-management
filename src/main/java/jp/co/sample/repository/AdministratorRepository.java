package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;


/**
 * 管理者のデータベース情報を操作するリポジトリクラス
 * 
 * @author koichi.nagata
 *
 */
@Repository
public class AdministratorRepository {
	/**  Administratorからrow_mapperの生成  */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER =(rs,i)->{
		Administrator admin = new Administrator();
		admin.setId(rs.getInt("id"));
		admin.setName(rs.getString("name"));
		admin.setMailAddress(rs.getString("mail_address"));
		admin.setPassword(rs.getString("password"));
		return admin;
	};
	
	/**  プレースホルダの生成  */
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 管理者のアカウント生成機能
	 * @param admin
	 */
	public void insert(Administrator admin) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(admin);
			String insertSql = "insert into employees(name,mail_address,password) values (:name,:mail_address,:password)";
			template.update(insertSql, param);
	}
	
	/**
	 * ログイン用のメソッド
	 * @param mailAddress
	 * @param password
	 * @return メールアドレスとパスワードがデータと一致した場合そのカラムを、しない場合nullを返す
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress,String password) {
		String sql = "id,name,mail_address,password from administrator where mail_address = :mail_address AND password = :password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("meil_address", mailAddress).addValue("password", password);
		Administrator admin = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		return admin;
	}
	
}
