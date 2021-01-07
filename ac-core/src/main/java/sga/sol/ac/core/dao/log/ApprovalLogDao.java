package  sga.sol.ac.core.dao.log;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.log.ApprovalDetailLog;
import sga.sol.ac.core.entity.log.ApprovalLog;

/**
 * 승인 로그 DAO
 * 
 * @author surfree
 * @date 2016. 5. 25 AuthDaemon에서 가져옴
 */
@Repository
public class ApprovalLogDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	public int insertApprovalLog(ApprovalLog approvalLog) {
		return sqlSessionTemplate.insert("insertApprovalLog", approvalLog);
	}
	
	public int insertApprovalDetailLog(ApprovalDetailLog approvalDetailLog) {
		return sqlSessionTemplate.insert("insertApprovalDetailLog", approvalDetailLog);
	}
	
	public int updateApprovalLog(ApprovalLog approvalLog) {
		return sqlSessionTemplate.update("updateApprovalLog", approvalLog);
	}
	
	public int getArsApprovalLogListCount(SearchParams param) {
		return sqlSessionTemplate.selectOne("getArsApprovalLogListCount", param);
	}
	
	public List<ApprovalLog> getArsApprovalLogList(SearchParams param) {
		return sqlSessionTemplate.selectList("getArsApprovalLogList", param);
	}
	
	public ApprovalLog getArsApprovalLogInfo(int recid) {
		return sqlSessionTemplate.selectOne("getArsApprovalLogInfo", recid);
	}
	
	public List<ApprovalDetailLog> getArsApprovalDetailLogList(SearchParams param) {
		return sqlSessionTemplate.selectList("getArsApprovalDetailList", param);
	}
}