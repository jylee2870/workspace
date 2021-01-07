package sga.sol.ac.core.entity.policy;

public class PolicyTarget {
	int recid;
	int objectRecid;
	int type;
	int referencedRecid;
	int filter;
	int priority;
	Policy policy;
	
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public int getObjectRecid() {
		return objectRecid;
	}
	public void setObjectRecid(int objectRecid) {
		this.objectRecid = objectRecid;
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
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("recid=" + recid);
		sb.append(",objectRecid=" + objectRecid);
		sb.append(",type=" + type);
		sb.append(",referenceRecid=" + referencedRecid);
		
		return sb.toString();
	}
}
