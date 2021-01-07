package sga.sol.ac.core.entity.log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 승인 로그 객체
 * 
 * @author surfree
 * @date 2016. 5. 25 AuthDaemon에서 가져옴
 */
public class ApprovalLog {

	public final static int REQ_TYPE_LOGIN			= 1;
	public final static int REQ_TYPE_FILE_ACCESS	= 2;
	public final static int REQ_TYPE_SHUTDOWN		= 4;
	
	public final static int RESULT_SUCCESS			= 1;
	public final static int RESULT_FAILED			= 0;
	
	int recid;
	int reqType;
	int userRecid;
	String userId;
	int equipRecid;
	String equipName;
	String equipIp;
	String equipAccountName;
	String workerName;
	String workerTel;
	String workerGroup;
	String reqData;
	String remoteIp;
	Integer success;
	String resultCode;
	String resultMsg;
	Date createDt;
	
	public ApprovalLog() {
	}
	
	public ApprovalLog(int reqType) {
		this.reqType = reqType;
	}

	public int getRecid() {
		return recid;
	}

	public void setRecid(int recid) {
		this.recid = recid;
	}

	public int getReqType() {
		return reqType;
	}

	public void setReqType(int reqType) {
		this.reqType = reqType;
	}

	public int getUserRecid() {
		return userRecid;
	}

	public void setUserRecid(int userRecid) {
		this.userRecid = userRecid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getEquipRecid() {
		return equipRecid;
	}

	public void setEquipRecid(int equipRecid) {
		this.equipRecid = equipRecid;
	}

	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public String getEquipIp() {
		return equipIp;
	}

	public void setEquipIp(String equipIp) {
		this.equipIp = equipIp;
	}

	public String getEquipAccountName() {
		return equipAccountName;
	}

	public void setEquipAccountName(String equipAccountName) {
		this.equipAccountName = equipAccountName;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getWorkerTel() {
		return workerTel;
	}

	public void setWorkerTel(String workerTel) {
		this.workerTel = workerTel;
	}

	public String getWorkerGroup() {
		return workerGroup;
	}

	public void setWorkerGroup(String workerGroup) {
		this.workerGroup = workerGroup;
	}

	public String getReqData() {
		return reqData;
	}

	public void setReqData(String reqData) {
		this.reqData = reqData;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	
	public String getReqTypeKr() {
		switch(reqType) {
		case 1:
			return "로그인 요청";
		case 2:
			return "파일접근 요청";
		case 4:
			return "시스템 종료 요청";
		default:
			return "알수없음";
		}
	}
	
	public String getSuccessKr() {
		if(1 == this.success)
			return "성공";
		else
			return "실패";
	}
	
	public String getResultCodeKr() {
		if(resultCode != null && resultCode.equals("0000")) {
			return "성공";
		}
		else {
			return "실패";
		}
	}
	
	public String getCreateDtStr() {
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return dtFormat.format(this.createDt);
	}
}
