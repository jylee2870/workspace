package sga.sol.ac.core.entity.policy;

import java.io.Serializable;

import sga.sol.ac.core.util.tools.CommunalModule;

/**
 * PlUserEquipBean
 * 
 * @author JannyWang
 * 
 */
public class PlUserEquip extends CommunalModule implements Serializable {

	private static final long serialVersionUID = 918183983888L;
	
	private int useFlag;			// 사용여부
	private int uidType;			// 사용자유형 0:사용자ID 1:사용자그룹ID
	private int uid;				// 사용자RECID
	private int eidType;			// 장비유형 0:장비 1:장비그룹
	private int eid;				// 장비RECID
	private int equipAccountId;		// 장비계정ID 
	private String groupKey; 
	private int equipAccountCnt;
	private int grpId;
	private int userGrpId;
	
	private int equipGrpId;
	
	private String allAccount;
	
	
	public int getEquipGrpId() {
		return equipGrpId;
	}

	public void setEquipGrpId(int equipGrpId) {
		this.equipGrpId = equipGrpId;
	}

	public int getUserGrpId() {
		return userGrpId;
	}

	public void setUserGrpId(int userGrpId) {
		this.userGrpId = userGrpId;
	}

	public int getGrpId() {
		return grpId;
	}

	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}

	public int getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(int useFlag) {
		this.useFlag = useFlag;
	}

	public int getUidType() {
		return uidType;
	}

	public void setUidType(int uidType) {
		this.uidType = uidType;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getEidType() {
		return eidType;
	}

	public void setEidType(int eidType) {
		this.eidType = eidType;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public int getEquipAccountId() {
		return equipAccountId;
	}

	public void setEquipAccountId(int equipAccountId) {
		this.equipAccountId = equipAccountId;
	}
	
	public String getGroupKey() {
		return groupKey;
	}

	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}

	public int getEquipAccountCnt() {
		return equipAccountCnt;
	}

	public void setEquipAccountCnt(int equipAccountCnt) {
		this.equipAccountCnt = equipAccountCnt;
	}

	public String getAllAccount() {
		return allAccount;
	}

	public void setAllAccount(String allAccount) {
		this.allAccount = allAccount;
	}

}
