package sga.sol.ac.core.service.log;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sga.sol.ac.core.dao.log.ApprovalLogDao;
import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.log.ApprovalDetailLog;
import sga.sol.ac.core.entity.log.ApprovalLog;

/**
 * 승인 로그 서비스
 * 
 * @author surfree
 * @date 2016. 5. 25 AuthDaemon에서 가져옴
 */
@Service
public class ApprovalLogService {
	@Autowired
	ApprovalLogDao approvalLogDao;
	
	public ApprovalLogService() {
	}
	
	public int insertApprovalLog(ApprovalLog approvalLog) {
		return approvalLogDao.insertApprovalLog(approvalLog);
	}
	
	public int insertApprovalDetailLog(ApprovalDetailLog approvalDetailLog) {
		return approvalLogDao.insertApprovalDetailLog(approvalDetailLog);
	}
	
	@Transactional
	public int insertApprovalRequestLog(ApprovalLog approvalLog, ApprovalDetailLog approvalDetailLog) {
		approvalLogDao.insertApprovalLog(approvalLog);
		
		int logRecid = approvalLog.getRecid();
		approvalDetailLog.setLogRecid(logRecid);
		
		return approvalLogDao.insertApprovalDetailLog(approvalDetailLog);
	}
	
	@Transactional
	public int updateApprovalLog(ApprovalLog approvalLog) {
		int logRecid = approvalLog.getRecid();
		if(logRecid <= 0) {
			throw new IllegalArgumentException("recid required.");
		}
		
		return approvalLogDao.updateApprovalLog(approvalLog);
	}
	
	@Transactional
	public int updateApprovalLog(ApprovalLog approvalLog, ApprovalDetailLog approvalDetailLog) {
		int logRecid = approvalLog.getRecid();
		if(logRecid <= 0) {
			throw new IllegalArgumentException("recid required.");
		}
		
		approvalLogDao.updateApprovalLog(approvalLog);
		approvalDetailLog.setLogRecid(logRecid);
		
		return approvalLogDao.insertApprovalDetailLog(approvalDetailLog);
	}
	
	public int getArsApprovalLogListCount(SearchParams param) {
		return approvalLogDao.getArsApprovalLogListCount(param);
	}
	
	public List<ApprovalLog> getArsApprovalLogList(SearchParams param) {
		return approvalLogDao.getArsApprovalLogList(param);
	}
	
	public ApprovalLog getArsApprovalLogInfo(int recid) {
		return approvalLogDao.getArsApprovalLogInfo(recid);
	}
	
	public List<ApprovalDetailLog> getArsApprovalDetailLogList(SearchParams param) {
		return approvalLogDao.getArsApprovalDetailLogList(param);
	}
}
