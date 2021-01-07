package sga.sol.ac.core.entity.user;

import java.util.Date;

/**
 * 사용자 그룹 객체
 * 
 * @author surfree
 *
 */
public class UserGroupInfo {
	int recid;
	String groupName;
	String groupPath;
	int parentGroupRecid;
	Date createDt;
	String createIp;
	String createUser;
	Date updateDt;
	String updateIp;
	String updateUser;
	
	public UserGroupInfo() {
	}
	
	public UserGroupInfo (int groupRecid, int parentGroupRecid){
		this.recid = groupRecid;
		this.parentGroupRecid = parentGroupRecid;
	}
	
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupPath() {
		return groupPath;
	}
	public void setGroupPath(String groupPath) {
		this.groupPath = groupPath;
	}
	public int getParentGroupRecid() {
		return parentGroupRecid;
	}
	public void setParentGroupRecid(int parentGroupRecid) {
		this.parentGroupRecid = parentGroupRecid;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public String getCreateIp() {
		return createIp;
	}
	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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
