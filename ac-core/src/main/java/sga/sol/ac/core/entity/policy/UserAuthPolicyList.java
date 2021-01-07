package sga.sol.ac.core.entity.policy;

/**
 * 사용자 2차인증 정책 목록 DTO
 * <p>
 * FIXME: 2016.05.26. surfree 각 엔티티별 분리할것
 * </p>
 * 
 * @author LEE JUNG YOUNG
 * @date 2015. 12. 17
 * 
 */
public class UserAuthPolicyList{
	int objectRecid = 0;
	int objectType = 0;
	int objectReferencedRecid = 0;
	String userGroupName = null;
	String userGroupPath = null;
	String userId = null;
	String userName = null;
	
	int targetRecid = 0;
	int targetType = 0;
	int targetReferencedRecid = 0;
	String equipGroupName = null;
	String equipGroupPath = null;
	String equipName = null;
	String equipIp = null;
	String equipAccountName = null;
	String domain = null;
	
	String policyName = null;
	int policyRecid = 0;
	int authTypes = 0;
	
	public String getUserGroupName() {
		return userGroupName;
	}
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}
	public String getUserGroupPath() {
		return userGroupPath;
	}
	public void setUserGroupPath(String userGroupPath) {
		this.userGroupPath = userGroupPath;
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
	public String getEquipGroupName() {
		return equipGroupName;
	}
	public void setEquipGroupName(String equipGroupName) {
		this.equipGroupName = equipGroupName;
	}
	public String getEquipGroupPath() {
		return equipGroupPath;
	}
	public void setEquipGroupPath(String equipGroupPath) {
		this.equipGroupPath = equipGroupPath;
	}
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public String getEquipIp() {
		return equipIp;
	}
	public void setEquipIp(String equipIp) {
		this.equipIp = equipIp;
	}
	public String getEquipAccountName() {
		return equipAccountName;
	}
	public void setEquipAccountName(String equipAccountName) {
		this.equipAccountName = equipAccountName;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public int getAuthTypes() {
		return authTypes;
	}
	public void setAuthTypes(int authTypes) {
		this.authTypes = authTypes;
	}
	public int getObjectRecid() {
		return objectRecid;
	}
	public void setObjectRecid(int objectRecid) {
		this.objectRecid = objectRecid;
	}
	public int getTargetRecid() {
		return targetRecid;
	}
	public void setTargetRecid(int targetRecid) {
		this.targetRecid = targetRecid;
	}
	public int getObjectType() {
		return objectType;
	}
	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}
	public int getTargetType() {
		return targetType;
	}
	public void setTargetType(int targetType) {
		this.targetType = targetType;
	}
	public int getObjectReferencedRecid() {
		return objectReferencedRecid;
	}
	public void setObjectReferencedRecid(int objectReferencedRecid) {
		this.objectReferencedRecid = objectReferencedRecid;
	}
	public int getTargetReferencedRecid() {
		return targetReferencedRecid;
	}
	public void setTargetReferencedRecid(int targetReferencedRecid) {
		this.targetReferencedRecid = targetReferencedRecid;
	}
	public int getPolicyRecid() {
		return policyRecid;
	}
	public void setPolicyRecid(int policyRecid) {
		this.policyRecid = policyRecid;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
}
