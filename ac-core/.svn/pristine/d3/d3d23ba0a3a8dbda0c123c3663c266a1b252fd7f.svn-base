package sga.sol.ac.core.entity.user;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 사용자 상태 객체
 * 
 * @author surfree
 *
 */
public class UserStatus {
	int userRecid;
	int validEnabled;
	Date validFromDt;
	Date validToDt;
	Date pwdExpiredDt;
	Date updateDt;
	String updateIp;
	String updateUser;
	
	public int getUserRecid() {
		return userRecid;
	}
	public void setUserRecid(int userRecid) {
		this.userRecid = userRecid;
	}
	public int getValidEnabled() {
		return validEnabled;
	}
	public void setValidEnabled(int validEnabled) {
		this.validEnabled = validEnabled;
	}
	public Date getValidFromDt() {
		return validFromDt;
	}
	public void setValidFromDt(Date validFromDt) {
		this.validFromDt = validFromDt;
	}
	public Date getValidToDt() {
		return validToDt;
	}
	public void setValidToDt(Date validToDt) {
		this.validToDt = validToDt;
	}
	public Date getPwdExpiredDt() {
		return pwdExpiredDt;
	}
	public void setPwdExpiredDt(Date pwdExpiredDt) {
		this.pwdExpiredDt = pwdExpiredDt;
	}
	public Date getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}
	public String getUpdateIp() {
		return updateIp;
	}
	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public boolean isImplicitPeriod() {
		return (validEnabled == 1);
	}
	
	public String getValidPeriodString() {
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(validEnabled == 1) {
			return dtFormat.format(getValidFromDt()) + "~" + dtFormat.format(getValidToDt());
		}
		else {
			return "제한없음";
		}
	}
}
