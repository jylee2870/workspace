package sga.sol.ac.core.entity.user;

import java.util.Date;

/**
 * 사용자 웹 상태 객체
 * 
 * @author surfree
 *
 */
public class UserWebStatus {
	int userRecid;
	Integer loginFailedCnt;
	Date lastTryConnectDt;
	int lastTryConnectMethod;
	String lastTryConnectIp;
	Date lastConnectDt;
	int lastConnectMethod;
	String lastConnectIp;
	int locked;
	
	public int getUserRecid() {
		return userRecid;
	}
	public void setUserRecid(int userRecid) {
		this.userRecid = userRecid;
	}
	public Integer getLoginFailedCnt() {
		return loginFailedCnt;
	}
	public void setLoginFailedCnt(Integer loginFailedCnt) {
		this.loginFailedCnt = loginFailedCnt;
	}
	public Date getLastTryConnectDt() {
		return lastTryConnectDt;
	}
	public void setLastTryConnectDt(Date lastTryConnectDt) {
		this.lastTryConnectDt = lastTryConnectDt;
	}
	public int getLastTryConnectMethod() {
		return lastTryConnectMethod;
	}
	public void setLastTryConnectMethod(int lastTryConnectMethod) {
		this.lastTryConnectMethod = lastTryConnectMethod;
	}
	public String getLastTryConnectIp() {
		return lastTryConnectIp;
	}
	public void setLastTryConnectIp(String lastTryConnectIp) {
		this.lastTryConnectIp = lastTryConnectIp;
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
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	
	public String getAccountStatusString() {
		if(locked == 1) {
			return "잠김";
		}
		else {
			return "정상";
		}
	}
}
