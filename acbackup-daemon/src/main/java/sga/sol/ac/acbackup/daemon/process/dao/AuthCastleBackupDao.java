package sga.sol.ac.acbackup.daemon.process.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.acbackup.daemon.process.entity.AuthLog;


/**
 * @author surfree
 */
@Repository
public class AuthCastleBackupDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<Map<String, Object>> selectAuthLog(Map<String, String> param) {
		return sqlSessionTemplate.selectList("selectLogList", param);
	}

}