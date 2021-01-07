package sga.sol.ac.core.entity.policy;

import java.util.Date;
import java.util.List;

public class Policy {
	int recid;
	int policyRecid;
	int category;
	String policyId;
	String policyName;
	int priority;
	Date createDt;
	String createIp;
	String createUser;
	Date updateDt;
	String updateIp;
	String updateUser;
	int enable;
	Date fromDt;
	Date toDt;
	String memo;
	// 초기 정책 추가 2016.01.06 LJY
	int defaultFlag; 
	
	List<PolicyObject> policyObject;
	List<PolicyTarget> policyTarget;
	
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public int getPolicyRecid() {
		return policyRecid;
	}
	public void setPolicyRecid(int policyRecid) {
		this.policyRecid = policyRecid;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
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
	public int getDefaultFlag() {
		return defaultFlag;
	}
	public void setDefaultFlag(int defaultFlag) {
		this.defaultFlag = defaultFlag;
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
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public Date getFromDt() {
		return fromDt;
	}
	public void setFromDt(Date fromDt) {
		this.fromDt = fromDt;
	}
	public Date getToDt() {
		return toDt;
	}
	public void setToDt(Date toDt) {
		this.toDt = toDt;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public List<PolicyObject> getPolicyObject() {
		return policyObject;
	}
	public void setPolicyObject(List<PolicyObject> policyObject) {
		this.policyObject = policyObject;
	}
	public List<PolicyTarget> getPolicyTarget() {
		return policyTarget;
	}
	public void setPolicyTarget(List<PolicyTarget> policyTarget) {
		this.policyTarget = policyTarget;
	}
	
	@Override
	public String toString() {
		return String.format("recid:%d, policyId:%s, policyName:%s, objectSize:%d, targetSize:%d", recid, policyId, policyName, 
				(policyObject == null ? 0 : policyObject.size()),
				(policyTarget == null ? 0 : policyTarget.size()));
	}

}
