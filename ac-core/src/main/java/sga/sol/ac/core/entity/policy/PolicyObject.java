package sga.sol.ac.core.entity.policy;

import java.util.List;

public class PolicyObject {
	int recid;
	int policyRecid;
	int type;
	int referencedRecid;
	int filter;
	int priority;
	List<Policy> policies;
	
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getReferencedRecid() {
		return referencedRecid;
	}
	public void setReferencedRecid(int referencedRecid) {
		this.referencedRecid = referencedRecid;
	}
	public int getFilter() {
		return filter;
	}
	public void setFilter(int filter) {
		this.filter = filter;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public List<Policy> getPolicies() {
		return policies;
	}
	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}
	
	@Override
	public String toString() {
		return String.format("policy_id:%d, ref_id:%d, filter:%d, policy_size:%d", policyRecid, referencedRecid, filter, (policies == null ? 0 : policies.size()));
	}
}
