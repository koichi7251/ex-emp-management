package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者情報のサービスクラス.
 * @author koichi.nagata
 *
 */
@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	/**
	 * 管理者情報を登録するメソッド.
	 * @param administrator　管理者情報を受け取る
	 * 管理者情報のリポジトリから、登録用のメソッドを呼び出す
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}
}