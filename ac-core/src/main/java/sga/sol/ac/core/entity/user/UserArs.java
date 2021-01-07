package sga.sol.ac.core.entity.user;

/**
 * 사용자 ARS 정보 객체
 * 
 * @author surfree
 *
 */
public class UserArs {
	int userRecid;
	String arsTel;
	String arsTelDesc;
	int validTime;
	String arsPassword;
	
	public int getUserRecid() {
		return userRecid;
	}
	public void setUserRecid(int userRecid) {
		this.userRecid = userRecid;
	}
	public String getArsTel() {
		return arsTel;
	}
	public void setArsTel(String arsTel) {
		this.arsTel = arsTel;
	}
	public String getArsTelDesc() {
		return arsTelDesc;
	}
	public void setArsTelDesc(String arsTelDesc) {
		this.arsTelDesc = arsTelDesc;
	}
	public int getValidTime() {
		return validTime;
	}
	public void setValidTime(int validTime) {
		this.validTime = validTime;
	}
	public String getArsPassword() {
		return arsPassword;
	}
	public void setArsPassword(String arsPassword) {
		this.arsPassword = arsPassword;
	}
}
