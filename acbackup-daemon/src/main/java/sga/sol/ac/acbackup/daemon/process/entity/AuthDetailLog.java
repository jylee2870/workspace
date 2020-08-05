package sga.sol.ac.acbackup.daemon.process.entity;

import java.util.Date;

public class AuthDetailLog {
	public final static int LOG_TYPE_REQUEST = 0;
	public final static int LOG_TYPE_RESPONSE = 1;
	
	int recid;
	int logRecid;
	int logType;
	String message;
	Date createDt;
	
	public AuthDetailLog(){
	}
	
	public AuthDetailLog(int logType, String message) {
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
	
	public String getLogTypeKr() {
		if(logType == 0)
			return "요청";
		else
			return "응답";
	}
	
	public static AuthDetailLog genereateAuthDetailLog(int logType) {
		AuthDetailLog authDetailLog = new AuthDetailLog(logType, null);
		return authDetailLog;
	}
	
	public static AuthDetailLog genereateAuthDetailLog(int logType, String message) {
		AuthDetailLog authDetailLog = new AuthDetailLog(logType, message);
		return authDetailLog;
	}
}
