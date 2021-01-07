package sga.sol.ac.core.entity.policy;

import java.util.HashMap;
import java.util.Map;

public abstract class PolicyDataItem implements IPolicyData {
	int recid;
	int policyRecid;
	
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
	
	protected Map<String, Object> createNewDataMap() {
		return new HashMap<String, Object>();
	}
	
	protected abstract Map<String, Object> createInternalDataMap();
	
	@Override
	public Map<String, Object> getDataMap() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("recid", this.getRecid());
		result.put("policyRecid", this.getPolicyRecid());
		
		result.putAll(createInternalDataMap());
		
		return result;
	}
}
