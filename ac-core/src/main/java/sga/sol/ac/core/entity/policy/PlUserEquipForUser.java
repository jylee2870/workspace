package sga.sol.ac.core.entity.policy;

import java.io.Serializable;

import sga.sol.ac.core.util.tools.CommunalModule;

public class PlUserEquipForUser extends CommunalModule implements Serializable {
	private static final long serialVersionUID = 8392923727234L;
	
	int recid;
	int managerGrpId;
	
	int useFlag;
	int uidType;
	int uid;	
	int eidType;
	int eid;
	int accountId;
	int userLevelCd;
	int os;	
	int userGrpId;
	int userGroupGrpId;
	int equipGrpId;
	int equipGroupGrpId;
	int equipType;
	
	
	
	String userGrpPath;
	String userGrpNm;
	String userId;	
	String userNm;
	String userGroupGrpNm;
	String userGroupGrpPath;
	String equipNm;
	String equipNick;
	String osNm;

	String ipPrimary;
	String ipSecondary;
	String modelNo;
	String vendorNm;
	String equipGrpNm;
	String equipGrpPath;
	String equipGroupGrpNm;
	String equipGroupGrpPath;

	String accountNm;
	String domain;
	String accountNick;
	String authArs;
	String authPki;
	String authOtp;
	
	String strEquipType;
	
	// 2015.03.11 hwbaek 모든계정허용 flag
	String allAccount;

	int objectType;
	
	public String getAuthOtp() {
		return authOtp;
	}
	public void setAuthOtp(String authOtp) {
		this.authOtp = authOtp;
	}

	private String groupKey;
	
	
	public String getAuthArs() {
		return authArs;
	}
	public void setAuthArs(String authArs) {
		this.authArs = authArs;
	}
	public String getAuthPki() {
		return authPki;
	}
	public void setAuthPki(String authPki) {
		this.authPki = authPki;
	}	
	
	private int equipAccountCnt;

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
	public String getAccountNm() {
		return accountNm;
	}
	public void setAccountNm(String accountNm) {
		this.accountNm = accountNm;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getAccountNick() {
		return accountNick;
	}
	public void setAccountNick(String accountNick) {
		this.accountNick = accountNick;
	}
	
	public int getManagerGrpId() {
		return managerGrpId;
	}
	public void setManagerGrpId(int managerGrpId) {
		this.managerGrpId = managerGrpId;
	}
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
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
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getUserLevelCd() {
		return userLevelCd;
	}
	public void setUserLevelCd(int userLevelCd) {
		this.userLevelCd = userLevelCd;
	}
	public int getOs() {
		return os;
	}
	public void setOs(int os) {
		this.os = os;
	}
	public int getUserGrpId() {
		return userGrpId;
	}
	public void setUserGrpId(int userGrpId) {
		this.userGrpId = userGrpId;
	}
	public int getUserGroupGrpId() {
		return userGroupGrpId;
	}
	public void setUserGroupGrpId(int userGroupGrpId) {
		this.userGroupGrpId = userGroupGrpId;
	}
	public int getEquipGrpId() {
		return equipGrpId;
	}
	public void setEquipGrpId(int equipGrpId) {
		this.equipGrpId = equipGrpId;
	}
	public int getEquipGroupGrpId() {
		return equipGroupGrpId;
	}
	public void setEquipGroupGrpId(int equipGroupGrpId) {
		this.equipGroupGrpId = equipGroupGrpId;
	}
	public String getUserGrpPath() {
		return userGrpPath;
	}
	public void setUserGrpPath(String userGrpPath) {
		this.userGrpPath = userGrpPath;
	}
	public String getUserGrpNm() {
		return userGrpNm;
	}
	public void setUserGrpNm(String userGrpNm) {
		this.userGrpNm = userGrpNm;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getUserGroupGrpNm() {
		return userGroupGrpNm;
	}
	public void setUserGroupGrpNm(String userGroupGrpNm) {
		this.userGroupGrpNm = userGroupGrpNm;
	}
	
	public String getUserGroupGrpPath() {
		return userGroupGrpPath;
	}
	
	public String getOsNm() {
		return osNm;
	}
	
	public void setOsNm(String osNm) {
		this.osNm = osNm;
	}
	public void setUserGroupGrpPath(String userGroupGrpPath) {
		this.userGroupGrpPath = userGroupGrpPath;
	}
	public String getEquipNm() {
		return equipNm;
	}
	public void setEquipNm(String equipNm) {
		this.equipNm = equipNm;
	}
	public String getEquipNick() {
		return equipNick;
	}
	public void setEquipNick(String equipNick) {
		this.equipNick = equipNick;
	}
	public int getEquipType() {
		return equipType;
	}
	public void setEquipType(int equipType) {
		this.equipType = equipType;
	}
	public String getIpPrimary() {
		return ipPrimary;
	}
	public void setIpPrimary(String ipPrimary) {
		this.ipPrimary = ipPrimary;
	}
	public String getIpSecondary() {
		return ipSecondary;
	}
	public void setIpSecondary(String ipSecondary) {
		this.ipSecondary = ipSecondary;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public String getVendorNm() {
		return vendorNm;
	}
	public void setVendorNm(String vendorNm) {
		this.vendorNm = vendorNm;
	}
	public String getEquipGrpNm() {
		return equipGrpNm;
	}
	public void setEquipGrpNm(String equipGrpNm) {
		this.equipGrpNm = equipGrpNm;
	}
	public String getEquipGrpPath() {
		return equipGrpPath;
	}
	public void setEquipGrpPath(String equipGrpPath) {
		this.equipGrpPath = equipGrpPath;
	}
	public String getEquipGroupGrpNm() {
		return equipGroupGrpNm;
	}
	public void setEquipGroupGrpNm(String equipGroupGrpNm) {
		this.equipGroupGrpNm = equipGroupGrpNm;
	}
	public String getEquipGroupGrpPath() {
		return equipGroupGrpPath;
	}
	public void setEquipGroupGrpPath(String equipGroupGrpPath) {
		this.equipGroupGrpPath = equipGroupGrpPath;
	}
	public String getStrEquipType() {
		return strEquipType;
	}
	public void setStrEquipType(String strEquipType) {
		this.strEquipType = strEquipType;
	}
	public String getAllAccount() {
		return allAccount;
	}
	public void setAllAccount(String allAccount) {
		this.allAccount = allAccount;
	}
	public int getObjectType() {
		return objectType;
	}
	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}
	
}
