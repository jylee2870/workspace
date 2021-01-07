package sga.sol.ac.core.entity.policy;

import java.util.Map;

import sga.sol.ac.core.entity.user.UserInfo;


public class EquipArsApprovalPolicy extends PolicyDataItem {
	int bypass = 0;
	int validTime = 0;
	int arsUid1 = 0;
	UserInfo arsApprover1 = null;
	int arsUid2 = 0;
	UserInfo arsApprover2 = null;
	int arsUid3 = 0;
	UserInfo arsApprover3 = null;
	
	public int getArsUid1() {
		return arsUid1;
	}
	public void setArsUid1(int arsUid1) {
		this.arsUid1 = arsUid1;
	}
	public UserInfo getArsApprover1() {
		return arsApprover1;
	}
	public void setArsApprover1(UserInfo arsApprover1) {
		this.arsApprover1 = arsApprover1;
	}
	public int getArsUid2() {
		return arsUid2;
	}
	public void setArsUid2(int arsUid2) {
		this.arsUid2 = arsUid2;
	}
	public UserInfo getArsApprover2() {
		return arsApprover2;
	}
	public void setArsApprover2(UserInfo arsApprover2) {
		this.arsApprover2 = arsApprover2;
	}
	public int getArsUid3() {
		return arsUid3;
	}
	public void setArsUid3(int arsUid3) {
		this.arsUid3 = arsUid3;
	}
	public UserInfo getArsApprover3() {
		return arsApprover3;
	}
	public void setArsApprover3(UserInfo arsApprover3) {
		this.arsApprover3 = arsApprover3;
	}
	public int getBypass() {
		return bypass;
	}
	public void setBypass(int bypass) {
		this.bypass = bypass;
	}
	public int getValidTime() {
		return validTime;
	}
	public void setValidTime(int validTime) {
		this.validTime = validTime;
	}
	
	@Override
	protected Map<String, Object> createInternalDataMap() {
		Map<String, Object> result = createNewDataMap();
		
		result.put("bypass", getBypass());
		result.put("validTime", getValidTime());
		result.put("arsUid1", getArsUid1());
		result.put("arsApprover1", getArsApprover1());
		result.put("arsUid2", getArsUid2());
		result.put("arsApprover2", getArsApprover2());
		result.put("arsUid3", getArsUid3());
		result.put("arsApprover3", getArsApprover3());
		
		return result;
	}
}
