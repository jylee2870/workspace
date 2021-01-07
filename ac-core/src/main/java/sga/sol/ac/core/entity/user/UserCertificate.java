package sga.sol.ac.core.entity.user;

import java.util.Date;

/**
 * 사용자 인증서 객체
 * 
 * @author surfree
 * @History swcho
 * 			2016.7.29 사용자아이디, 사용자명 추가
 *
 */
public class UserCertificate {
	int recid;
	int userRecid;
	int enabled;
	String issuer;
	String serialNum;
	String subject;
	Date validFromDt;
	Date validToDt;
	
	String userId;
	String userName;
	
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public int getUserRecid() {
		return userRecid;
	}
	public void setUserRecid(int userRecid) {
		this.userRecid = userRecid;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
