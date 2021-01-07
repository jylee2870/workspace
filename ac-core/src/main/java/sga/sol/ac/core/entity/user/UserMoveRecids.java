package sga.sol.ac.core.entity.user;

import java.util.List;

public class UserMoveRecids {
	Integer groupRecid;
	List<Integer> userRecids;
	
	String updateUser;
	String updateIp;
	public Integer getGroupRecid() {
		return groupRecid;
	}
	public void setGroupRecid(Integer groupRecid) {
		this.groupRecid = groupRecid;
	}
	public List<Integer> getUserRecids() {
		return userRecids;
	}
	public void setUserRecids(List<Integer> userRecids) {
		this.userRecids = userRecids;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateIp() {
		return updateIp;
	}
	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}
}
