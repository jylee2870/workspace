package sga.sol.ac.core.entity.policy;

import java.util.Map;

public class User2FactAuthPolicy extends PolicyDataItem implements IPolicyData {
	int maxFailedCount;
	int authTypes;
	
	public int getMaxFailedCount() {
		return maxFailedCount;
	}
	public void setMaxFailedCount(int maxFailedCount) {
		this.maxFailedCount = maxFailedCount;
	}
	public int getAuthTypes() {
		return authTypes;
	}
	public void setAuthTypes(int authTypes) {
		this.authTypes = authTypes;
	}

	@Override
	protected Map<String, Object> createInternalDataMap() {
		Map<String, Object> result = createNewDataMap();
		
		result.put("maxFailedCount", this.getMaxFailedCount());
		result.put("authTypes", this.getAuthTypes());
		
		return result;
	}

}