package sga.sol.ac.core.dao.log;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.log.AuthDetailLog;
import sga.sol.ac.core.entity.log.AuthLog;

@Repository
public class AuthLogDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	public int insertAuthLog(AuthLog authLog) {
		return sqlSessionTemplate.insert("insertAuthLog", authLog);
	}
	
	public int insertAuthDetailLog(AuthDetailLog authDetailLog) {
		return sqlSessionTemplate.insert("insertAuthDetailLog", authDetailLog);
	}
	
	public int updateAuthLog(AuthLog authLog) {
		return sqlSessionTemplate.update("updateAuthLog", authLog);
	}
	
	public int getAuthLogListCount(SearchParams param) {
		return sqlSessionTemplate.selectOne("getAuthLogListCount", param);
	}
	
	public List<AuthLog> getAuthLogList(SearchParams param) {
		return sqlSessionTemplate.selectList("getAuthLogList", param);
	}
	
	public AuthLog getAuthLogInfo(int recid) {
		return sqlSessionTemplate.selectOne("getAuthLogInfo", recid);
	}
	
	public List<AuthDetailLog> getAuthDetailLogList(SearchParams param) {
		return sqlSessionTemplate.selectList("getAuthDetailLogList", param);
	}
}
