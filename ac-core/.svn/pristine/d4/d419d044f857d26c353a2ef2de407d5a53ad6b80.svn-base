package sga.sol.ac.core.entity.log;

import java.util.Date;

/**
 * 승인 상세 로그 객체
 * 
 * @author surfree
 * @date 2016. 5. 25 AuthDaemon에서 가져옴
 */
public class ApprovalDetailLog {
	public final static int LOG_TYPE_REQUEST = 0;
	public final static int LOG_TYPE_RESPONSE = 1;
	
	int recid;
	int logRecid;
	int logType;
	String message;
	Date createDt;
	
	public ApprovalDetailLog(){
	}
	
	public ApprovalDetailLog(int logType, String message) {
		this.logType = logType;
		this.message = message;
	}
	
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public int getLogRecid() {
		return logRecid;
	}
	public void setLogRecid(int logRecid) {
		this.logRecid = logRecid;
	}
	public int getLogType() {
		return logType;
	}
	public void setLogType(int logType) {
		this.logType = logType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	
	public static ApprovalDetailLog genereateApprovalDetailLog(int logType) {
		ApprovalDetailLog approvalDetailLog = new ApprovalDetailLog(logType, null);
		return approvalDetailLog;
	}
	
	public static ApprovalDetailLog genereateApprovalDetailLog(int logType, String message) {
		ApprovalDetailLog approvalDetailLog = new ApprovalDetailLog(logType, message);
		return approvalDetailLog;
	}
	
	public String getLogTypeKr() {
		if(logType == 0)
			return "요청";
		else
			return "응답";
	}
}
