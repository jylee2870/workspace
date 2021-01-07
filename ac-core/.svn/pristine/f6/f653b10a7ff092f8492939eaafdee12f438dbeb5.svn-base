package sga.sol.ac.core.service.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sga.sol.ac.core.dao.log.LmManagementLogDao;
import sga.sol.ac.core.entity.log.LmManagementLog;
import sga.sol.ac.core.entity.user.UserInfo;
import sga.sol.ac.core.log.ManagementLogKey;
import sga.sol.ac.core.Constants;

@Service
public class LmManagementLogService {
	@Autowired
	private LmManagementLogDao lmManagementLogDao;
	
	public LmManagementLogService() {
	}
	
	/**
	 * @author LeeJungYoung
	 * @description : 관리 로그 insert 
	 * @updateDate : 2015.02.05
	 * @param :  LmManagementLog info 
	 * @param : info.srcIp : 해당 서비스를 접속한 장비 IP
	 * @param : info.srcRecId : 해당 서비스를 접속한 사용자 레코드 아이디
	 * @param : info.srcId : 해당 서비스를 접속한 사용자 아이디
	 * @param : info.srcNm : 해당 서비스를 접속한 사용자 이름
	 * @param : info.srcTel : 해당 서비스를 접속한 사용자 전화 번호
	 * @param : info.srcDepart : 해당 서비스를 접속한 사용자 부서
	 * @param : info.srcCompany : 해당 서비스를 접속한 사용자 회사
	 * @param : info.srcLevel : 해당 서비스를 접속한 사용자 레벨
	 * @param : info.objUserRecId
	 * @param : info.objUserId
	 * @param : info.objUserNm
	 * @param : info.objUserLevel
	 * @param : info.objEquipRecId
	 * @param : info.objEquipNm
	 * @param : info.objEquipAlias
	 * @param : info.message : 로그 내용
	 * @param : info.returnCode : OutputData?
	 * @param : info.category : 해당 로그의 분류
	 * @param : info.cmd : 해당 로그의 분류 세부사항...
	 * @param : info.logLevel : 로그 레벨
	 * @param : info.riskLevel : 로그 위험 레벨
	 * @param : info.alarmMethod : 알람메시지 방법
	 */
	public int insertLog(LmManagementLog info){
		if( info.getReturnCode() == Constants.SUCCESS )
		{
			info.setAlarmMethod(ManagementLogKey.Level.INFO);
			info.setAlarmMethod(ManagementLogKey.RiskLevel.NONE);
			info.setAlarmMethod(ManagementLogKey.AlarmMethod.NONE);
		}
		else
		{
			info.setAlarmMethod(ManagementLogKey.Level.ERROR);
			info.setAlarmMethod(ManagementLogKey.RiskLevel.HIGH);
			info.setAlarmMethod(ManagementLogKey.AlarmMethod.NONE);
		}
		return lmManagementLogDao.insertLog(info);
	}
	
	@SuppressWarnings("unchecked")
	public List<LmManagementLog> selectLmManagementLogList(LmManagementLog logInfo){
		return  lmManagementLogDao.selectLmManagementLogList(logInfo);
	}
	
	@SuppressWarnings("unchecked")
	public int selectLmManagementLogCount(LmManagementLog logInfo){
		return lmManagementLogDao.selectLmManagementLogCount(logInfo);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<LmManagementLog> selectLmManagementLogListByManage(LmManagementLog logInfo){
		return lmManagementLogDao.selectLmManagementLogListByManage(logInfo);
	}
	
	@SuppressWarnings("unchecked")
	public int selectLmManagementLogCountByManage(LmManagementLog logInfo){
		return lmManagementLogDao.selectLmManagementLogCountByManage(logInfo);
	}
	
	/**
	 * @Date		: 2014. 9. 12. 
	 * @Author		: swcho
	 * @Description	: 관리로그 익스포트 (최고관리자)
	 */
	public List<LmManagementLog> selectLmManagementLogDownload(LmManagementLog logInfo){
		return  lmManagementLogDao.selectLmManagementLogDownload(logInfo);
	}
	
	/**
	 * @Date		: 2014. 9. 12. 
	 * @Author		: swcho
	 * @Description	: 관리로그 익스포트 (관리자)
	 */
	public List<LmManagementLog> selectLmManagementLogDownloadByManage(LmManagementLog logInfo){
		return lmManagementLogDao.selectLmManagementLogDownloadByManage(logInfo);
	}
	
	public LmManagementLog selectLmManagementlogByRecid(int recid){
		return lmManagementLogDao.selectLmManagementlogByRecid(recid);
	}
	
	/**
	 * @author hwbaek
	 * @date 2014. 12. 16.
	 * @description : 계정 동기화 마지막 로그
	 */
	public LmManagementLog select_LmManagementLog_TOP_By_AccSync(){
		return lmManagementLogDao.select_LmManagementLog_TOP_By_AccSync();
	}
	
	/**
	 * @author hwbaek
	 * @date 2015. 2. 2.
	 * @description : OTP 기기 연동 동기화 마지막 로그
	 */
	public LmManagementLog selectLmManagementLogTopByOTPSync(){
		return lmManagementLogDao.selectLmManagementLogTopByOTPSync();
	}
	
	public LmManagementLog managementLogActorBind(UserInfo user, String userIp){
		LmManagementLog logInfo = new LmManagementLog();
		logInfo.setSrcRecId( user.getRecid());
		logInfo.setSrcId(user.getUserId());
		logInfo.setSrcIp(userIp);
		logInfo.setSrcNm(user.getUserName());
		logInfo.setSrcLevel(user.getUserLevelCd());
		logInfo.setSrcTel(user.getPhone());
		logInfo.setSrcDepart(user.getDepartment());
		logInfo.setSrcCompany(user.getCompany()); 
		
		return logInfo;
	}
}
