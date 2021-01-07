package sga.sol.ac.core.entity.equip;

import java.util.Date;

/**
 * 장비 계정 객체
 * 
 * @author surfree
 * @date 2016. 6. 2.
 */
public class EquipAccount {
	int recid;
	int equipRecid;
	String domain;
	String account;
	int aliasEnable;
	String aliasEnableStr;
	String accountAlias;
	String pwd;
	int uid;
	int gid;
	String groupName;
	String loginShell;
	String homeDir;
	Date createDt;
	String createIp;
	String createUser;
	Date updateDt;
	String updateIp;
	String updateUser;
	EquipInfo equipInfo;
	
	String equipName;
	
	public String getAliasEnableStr() {
		return aliasEnableStr;
	}

	public void setAliasEnableStr(String aliasEnableStr) {
		this.aliasEnableStr = aliasEnableStr;
	}

	public int getRecid() {
		return recid;
	}
	
	public void setRecid(int recid) {
		this.recid = recid;
	}
	
	public int getEquipRecid() {
		return equipRecid;
	}
	
	public void setEquipRecid(int equipRecid) {
		this.equipRecid = equipRecid;
	}
	
	public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public int getAliasEnable() {
		return aliasEnable;
	}
	
	public void setAliasEnable(int aliasEnable) {
		this.aliasEnable = aliasEnable;
		if(aliasEnable == 1){
			setAliasEnableStr("사용");
		}else {
			setAliasEnableStr("사용안함");
		}
		
	}
	
	public String getAccountAlias() {
		return accountAlias;
	}
	
	public void setAccountAlias(String accountAlias) {
		this.accountAlias = accountAlias;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public int getGid() {
		return gid;
	}
	
	public void setGid(int gid) {
		this.gid = gid;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getLoginShell() {
		return loginShell;
	}
	
	public void setLoginShell(String loginShell) {
		this.loginShell = loginShell;
	}
	
	public String getHomeDir() {
		return homeDir;
	}
	
	public void setHomeDir(String homeDir) {
		this.homeDir = homeDir;
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

	public EquipInfo getEquipInfo() {
		return equipInfo;
	}

	public void setEquipInfo(EquipInfo equipInfo) {
		this.equipInfo = equipInfo;
		if(equipInfo.getEquipName() != null) setEquipName(equipInfo.getEquipName());
	}
	
	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
}
