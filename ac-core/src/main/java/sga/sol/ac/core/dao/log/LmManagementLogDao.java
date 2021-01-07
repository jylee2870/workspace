package sga.sol.ac.core.dao.log;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.log.LmManagementLog;

/**
 * 관리 로그 DAO
 * 
 * @author surfree
 *
 */
@Repository
public class LmManagementLogDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// TODO : CONVERTING TO POSTGRE
	public int insertLog(LmManagementLog info) {
		return sqlSessionTemplate.insert("insertLmManagementLog", info);
	}
	
	public LmManagementLog selectLmManagementlogByRecid(int recid) {
		return sqlSessionTemplate.selectOne("selectLmManagementlogByRecid", recid);
	}

	public List<LmManagementLog> selectLmManagementLogList(LmManagementLog logInfo) {
		return sqlSessionTemplate.selectList("selectLmManagementLogList", logInfo);
	}

	public int selectLmManagementLogCount(LmManagementLog logInfo) {
		return (Integer)sqlSessionTemplate.selectOne("selectLmManagementCount", logInfo);
	}

	public List<LmManagementLog> selectLmManagementLogListByManage(LmManagementLog logInfo) {
		return sqlSessionTemplate.selectList("selectLmManagementLogListByManage", logInfo);
	}

	public int selectLmManagementLogCountByManage(LmManagementLog logInfo) {
		return (Integer)sqlSessionTemplate.selectOne("selectLmManagementLogCountByManage", logInfo);
	}
	
	/**
	 * @Date		: 2014. 9. 12. 
	 * @Author		: swcho
	 * @Description	: 관리로그 익스포트 (최고관리자)
	 */
	public List<LmManagementLog> selectLmManagementLogDownload(LmManagementLog logInfo) {
		return sqlSessionTemplate.selectList("selectLmManagementLogDownload", logInfo);
	}

	/**
	 * @Date		: 2014. 9. 12. 
	 * @Author		: swcho
	 * @Description	: 관리로그 익스포트 (관리자)
	 */
	public List<LmManagementLog> selectLmManagementLogDownloadByManage(LmManagementLog logInfo) {
		return sqlSessionTemplate.selectList("selectLmManagementLogDownloadByManage", logInfo);
	}
	
	/**
	 * @author hwbaek
	 * @date 2014. 12. 16.
	 * @description : 마지막 계정 동기화 로그 출력
	 * TODO: 검증 필요
	 */
	public LmManagementLog select_LmManagementLog_TOP_By_AccSync() {
		LmManagementLog log = (LmManagementLog) sqlSessionTemplate.selectOne("select_LmManagementLog_TOP_By_AccSync");
		if(log == null){
			log = new LmManagementLog();
			log.setLogSaveDt("");
			log.setMessage("동기화 이력이 없습니다.");
		}
		return log;
	}
	
	/**
	 * @author hwbaek
	 * @date 2015. 2. 2.
	 * @description : 마지막 OTP 동기화 로그 출력
	 */
	public LmManagementLog selectLmManagementLogTopByOTPSync(){
		LmManagementLog log = (LmManagementLog)sqlSessionTemplate.selectOne("selectLmManagementLogTopByOTPSync");
		if(log == null){
			log = new LmManagementLog();
			log.setLogSaveDt("");
			log.setMessage("동기화 이력이 없습니다.");
		}
		return log;
	}
}
