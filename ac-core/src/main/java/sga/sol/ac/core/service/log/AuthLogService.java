package sga.sol.ac.core.service.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sga.sol.ac.core.dao.log.AuthLogDao;
import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.log.AuthDetailLog;
import sga.sol.ac.core.entity.log.AuthLog;

@Service
public class AuthLogService {
	@Autowired
	AuthLogDao authLogDao;
	
	public AuthLogService() {
	}
	
	public int insertAuthLog(AuthLog authLog) {
		return authLogDao.insertAuthLog(authLog);
	}
	
	public int insertAuthDetailLog(AuthDetailLog authDetailLog) {
		return authLogDao.insertAuthDetailLog(authDetailLog);
	}
	
	@Transactional
	public int insertAuthRequestLog(AuthLog authLog, AuthDetailLog authDetailLog) {
		authLogDao.insertAuthLog(authLog);
		
		int logRecid = authLog.getRecid();
		authDetailLog.setLogRecid(logRecid);
		
		return authLogDao.insertAuthDetailLog(authDetailLog);
	}
	
	@Transactional
	public int updateAuthLog(AuthLog authLog, AuthDetailLog authDetailLog) {
		int logRecid = authLog.getRecid();
		if(logRecid <= 0) {
			throw new IllegalArgumentException("recid required.");
		}
		
		authLogDao.updateAuthLog(authLog);
		authDetailLog.setLogRecid(logRecid);
		
		return authLogDao.insertAuthDetailLog(authDetailLog);
	}
	
	public int getAuthLogListCount(SearchParams param) {
		return authLogDao.getAuthLogListCount(param);
	}
	
	public List<AuthLog> getAuthLogList(SearchParams param) {
		return authLogDao.getAuthLogList(param);
	}
	
	public AuthLog getAuthLogInfo(int recid) {
		return authLogDao.getAuthLogInfo(recid);
	}
	
	public List<AuthDetailLog> getAuthDetailLogList(SearchParams param) {
		return authLogDao.getAuthDetailLogList(param);
	}
}
