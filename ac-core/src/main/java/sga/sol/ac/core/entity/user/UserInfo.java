package sga.sol.ac.core.entity.user;

import java.util.Date;

/**
 * 사용자 정보 객체
 * <p>
 * 하위 객체로 아래 객체가 포함된다
 * <ul>
 * <li>UserGroupInfo</li>
 * <li>UserStatus</li>
 * <li>UserWebStatus</li>
 * <li>User2FactStatus</li>
 * </p>
 * 
 * TODO
 * <p>
 * UserGroupInfo 객체는 castle 계정 때문에 null 값 검사가 필요하다.
 * getUserGroup 사용시 userGroup이 null이면 빈 객체 생성해서 리턴 (안전?) 2015.08.18 swcho
 * </p>  
 * 
 * @author surfree
 *
 */
public class UserInfo {
	int recid = 0;
	String userId;
	String pwd;
	String userName;
	String email;
	String mobile;
	String phone;
	String company;
	String department;
	String position;
	int userLevelCd;
	String description;
	int disabled;
	Date createDt;
	String createIp;
	String createUser;
	Date updateDt;
	String updateIp;
	String updateUser;
	Date updatePwdDt;
	
	UserGroupInfo userGroup;
	UserGroupRelation userGroupRelation;
	UserStatus userStatus;
	UserWebStatus userWebStatus;
	User2FactStatus user2FactStatus;
	UserArs userArs;
	
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getUserLevelCd() {
		return userLevelCd;
	}
	public void setUserLevelCd(int userLevelCd) {
		this.userLevelCd = userLevelCd;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDisabled() {
		return disabled;
	}
	public void setDisabled(int disabled) {
		this.disabled = disabled;
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
	public Date getUpdatePwdDt() {
		return updatePwdDt;
	}
	public void setUpdatePwdDt(Date updatePwdDt) {
		this.updatePwdDt = updatePwdDt;
	}
	public boolean isUserDisabled() {
		return (disabled != 0);
	}
	public UserGroupInfo getUserGroup() {
		if (this.userGroup != null){
			return userGroup;
		}else{
			return new UserGroupInfo();
		}
	}
	public void setUserGroup(UserGroupInfo userGroup) {
		this.userGroup = userGroup;
	}
	public UserGroupRelation getUserGroupRelation() {
		return userGroupRelation;
	}
	public void setUserGroupRelation(UserGroupRelation userGroupRelation) {
		this.userGroupRelation = userGroupRelation;
	}
	public UserStatus getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
	public UserWebStatus getUserWebStatus() {
		return userWebStatus;
	}
	public void setUserWebStatus(UserWebStatus userWebStatus) {
		this.userWebStatus = userWebStatus;
	}
	public User2FactStatus getUser2FactStatus() {
		return user2FactStatus;
	}
	public void setUser2FactStatus(User2FactStatus user2FactStatus) {
		this.user2FactStatus = user2FactStatus;
	}
	public UserArs getUserArs() {
		return userArs;
	}
	
	public void setUserArs(UserArs userArs) {
		this.userArs = userArs;
	}
	
	/**
	 * 하위 객체에 user_recid를 설정한다.
	 */
	public void applyInfo() {
		int userRecid = this.getRecid();
		
		if(null != userGroupRelation) {
			userGroupRelation.setUserRecid(userRecid);
			userGroupRelation.setUpdateIp(getCreateIp());
			userGroupRelation.setUpdateUser(getCreateUser());
		}
		
		if(null != userStatus) {
			userStatus.setUserRecid(userRecid);
			userStatus.setUpdateIp(getCreateIp());
			userStatus.setUpdateUser(getCreateUser());
		}
		
		if(null != userWebStatus) {
			userWebStatus.setUserRecid(userRecid);
		}
		
		if(null != user2FactStatus) {
			user2FactStatus.setUserRecid(userRecid);
		}
	}
}
