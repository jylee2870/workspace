package sga.sol.ac.core.entity.equip;

import java.util.Date;

public class EquipGroupRelation {
	private int recid;
	private int equipRecid;
	private int groupRecid;
	Date updateDt;
	String updateIp;
	String updateUser;

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

	public int getEquipGrpId() {
		return groupRecid;
	}

	public void setEquipGrpId(int groupRecid) {
		this.groupRecid = groupRecid;
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
}
