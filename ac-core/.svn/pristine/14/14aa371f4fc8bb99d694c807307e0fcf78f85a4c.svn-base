package sga.sol.ac.core.entity.policy;

import java.util.Map;

public class Equip2FactPolicy extends PolicyDataItem {
	int authTypes = 0;
	
	public int getAuthTypes() {
		return authTypes;
	}
	public void setAuthTypes(int authTypes) {
		this.authTypes = authTypes;
	}
	
	@Override
	protected Map<String, Object> createInternalDataMap() {
		Map<String, Object> result = createNewDataMap();
		
		result.put("authTypes", getAuthTypes());
		
		return result;
	}
}
