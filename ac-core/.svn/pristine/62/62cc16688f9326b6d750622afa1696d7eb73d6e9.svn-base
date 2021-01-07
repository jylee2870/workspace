package sga.sol.ac.core.entity.user;

import java.util.Date;

/**
 * 사용자 2차 인증 상태 객체
 * 
 * @author surfree
 *
 */
public class User2FactStatus {
	int userRecid;
	int locked;
	Integer failedCount;
	Date lastConnectDt;
	int lastConnectMethod;
	String lastConnectIp;
	
	public int getUserRecid() {
		return userRecid;
	}
	public void setUserRecid(int userRecid) {
		this.userRecid = userRecid;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public Integer getFailedCount() {
		return failedCount;
	}
	public void setFailedCount(Integer failedCount) {
		this.failedCount = failedCount;
	}
	public Date getLastConnectDt() {
		return lastConnectDt;
	}
	public void setLastConnectDt(Date lastConnectDt) {
		this.lastConnectDt = lastConnectDt;
	}
	public int getLastConnectMethod() {
		return lastConnectMethod;
	}
	public void setLastConnectMethod(int lastConnectMethod) {
		this.lastConnectMethod = lastConnectMethod;
	}
	public String getLastConnectIp() {
		return lastConnectIp;
	}
	public void setLastConnectIp(String lastConnectIp) {
		this.lastConnectIp = lastConnectIp;
	}
	public boolean is2FactLocked() {
		return (locked == 1 ? true: false);
	}
	public String getAuthStatusString() {
		if(locked == 1) {
			return "잠김";
		}
		else {
			return "정상";
		}
	}
}
