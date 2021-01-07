package sga.sol.ac.core.entity.policy;

import java.util.Map;

public class UserWebLoginPolicy extends PolicyDataItem {
	int maxFailedCount;
	int loginMethod;
	
	public int getMaxFailedCount() {
		return maxFailedCount;
	}
	public void setMaxFailedCount(int maxFailedCount) {
		this.maxFailedCount = maxFailedCount;
	}
	public int getLoginMethod() {
		return loginMethod;
	}
	public void setLoginMethod(int loginMethod) {
		this.loginMethod = loginMethod;
	}
	@Override
	protected Map<String, Object> createInternalDataMap() {
		Map<String, Object> result = createNewDataMap();
		
		result.put("maxFailedCount", getMaxFailedCount());
		result.put("loginMethod", getLoginMethod());
		
		return result;
	}
	
}
