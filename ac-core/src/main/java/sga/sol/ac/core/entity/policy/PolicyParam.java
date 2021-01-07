package sga.sol.ac.core.entity.policy;
/*
 * 2015.12.21 swcho 
 * 정책 적용 대상에서 type을 구분해서 사용할 필요가 있어 추가 
 */
public class PolicyParam {
	String policyId;
	int recid;
	int type;
	
	public PolicyParam() {
	}
	
	public PolicyParam(String policyId, int recid) {
		this.policyId = policyId;
		this.recid = recid;
	}
	
	public PolicyParam(String policyId, int recid, int type){
		this.policyId = policyId;
		this.recid = recid;
		this.type = type;
	}
	
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
