package sga.sol.ac.core.entity.user;

import java.util.Date;

/**
 * 사용자와 사용자 그룹간 관계 객체
 * 
 * @author surfree
 *
 */
public class UserGroupRelation {
	int recid;
	int userRecid;
	int groupRecid;
	Date updateDt;
	String updateIp;
	String updateUser;
	
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
	public int getGroupRecid() {
		return groupRecid;
	}
	public void setGroupRecid(int groupRecid) {
		this.groupRecid = groupRecid;
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
}
