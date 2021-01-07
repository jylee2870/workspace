package sga.sol.ac.core.entity.equip;

import java.util.Date;

/**
 * 장비 정보 객체
 * 
 * @author zhang bo guang
 * @history
 * <ul>
 * <li>2014.10.21 swcho:	정리&주석</li>
 * <li>2016.5.31 swcho:		CommualModule 상속 제거, CommualModule 호출 부분 임시 구현. PageModule 포함</li>
 * <li>2016.6.2 surfree:	필요없는 항목 정리 및 이름 변경</li>
 * <li>2016.6.3 surfree:	새로운 스키마에 맞게 정리</li>
 * </ul> 
 */
public class EquipInfo {
	int recid;
	String equipName;		// 장비명
	String ipPrimary;		// 주 IP
	String ipSecondary;		// 부 IP
	int aliasEnable;		// 장비별칭 사용 여부
	String alias;			// 장비별칭 ARS승인 인식 목적
	int equipType;			// 장비종류 1:Server 2:Network Device 3:Etc
	String vendorName;		// 장비 제조사
	String modelNo;			// 장비 모델
	int osRecid;			// os 레코드ID
	String description;
	
	Date createDt;
	String createIp;
	String createUser;
	Date updateDt;
	String updateIp;
	String updateUser;
	
	/** 장비 그룹 관계*/
	EquipGroupInfo groupInfo;
	/** 장비 OS 관계*/
	EquipOsInfo osInfo;
	/** 장비 서비스 관계*/
	EquipService service;
	
	public int getRecid() {
		return recid;
	}

	public void setRecid(int recid) {
		this.recid = recid;
	}
	
	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
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
	
	public int getAliasEnable() {
		return aliasEnable;
	}

	public void setAliasEnable(int aliasEnable) {
		this.aliasEnable = aliasEnable;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getEquipType() {
		return equipType;
	}

	public void setEquipType(int equipType) {
		this.equipType = equipType;
	}
	
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	
	public int getOsRecid() {
		return osRecid;
	}

	public void setOsRecid(int osRecid) {
		this.osRecid = osRecid;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreateDt() {
		return createDt;
	}
	
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	
	public String getCreateIp() {
		return createIp;
	}
	
	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}
	
	public String getCreateUser() {
		return createUser;
	}
	
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	public Date getUpdateDt() {
		return updateDt;
	}
	
	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}
	
	public String getUpdateIp() {
		return updateIp;
	}
	
	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp;
	}
	
	public String getUpdateUser() {
		return updateUser;
	}
	
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	public EquipGroupInfo getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(EquipGroupInfo groupInfo) {
		this.groupInfo = groupInfo;
	}

	public EquipOsInfo getOsInfo() {
		return osInfo;
	}

	public void setOsInfo(EquipOsInfo osInfo) {
		this.osInfo = osInfo;
	}

	public EquipService getService() {
		return service;
	}

	public void setService(EquipService service) {
		this.service = service;
	}

	/**
	 * excelView에서 이름 처리를 위한 문자열 반환 
	 * 
	 * @return 서버 종류 문자열
	 * @author: surfree
	 * @date:	2015. 4. 8.
	 */
	public String getServerType() {
		String serverType;
		
		switch(equipType)
		{
			case 1: 	serverType = "Server";			break;
			case 2: 	serverType = "Network Device";	break;
			default:	serverType = "Etc";				break;
		}
		
		return serverType;
	}
}
