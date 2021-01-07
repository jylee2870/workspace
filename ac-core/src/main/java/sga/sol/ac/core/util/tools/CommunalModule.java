package sga.sol.ac.core.util.tools;

import org.codehaus.jackson.annotate.JsonProperty;

import sga.sol.ac.core.util.tools.PageModule;

/**
 * 공용 BEAN
 * 거의 대부분의 DTO 객체가 상속받아서 사용합니다.
 * TODO: 객체 분리는 고려해봐야 할 대상임.
 * 
 * @author JannyWang
 * - 2015-01-21(surfree) 중국어 주석 수정
 */
public class CommunalModule extends PageModule {
	@JsonProperty
	public int recid;											// 레코드 ID 
	public int recid_ex;	

	private String descr;									// 설명
	private String logRegDt;								// 등록 일시
	private String logRegIp;								// 등록 IP
	private String logRegId;								// 등록 사용자ID
	private String logChgInfoDt;							// 수정 일시
	private String logChgInfoIp;							// 수정한 IP
	private String logChgInfoId;							// 수정한 사용자ID
	
	private String inGroup;
	private String inEquipGroupStrToken;
	private String inEquipStrToken;
	private String inUserGroupStrToken;
	private String inUserStrToken;	
	private String inUserStrTokenEx;
	private String inEquipAccountStrToken;
	private int userGroupRecid;
	private int equipGroupRecid;

	public String getInEquipAccountStrToken() {
		return inEquipAccountStrToken;
	}

	public void setInEquipAccountStrToken(String inEquipAccountStrToken) {
		this.inEquipAccountStrToken = inEquipAccountStrToken;
	}

	public int getUserGroupRecid() {
		return userGroupRecid;
	}

	public void setUserGroupRecid(int userGroupRecid) {
		this.userGroupRecid = userGroupRecid;
	}

	public int getEquipGroupRecid() {
		return equipGroupRecid;
	}

	public void setEquipGroupRecid(int equipGroupRecid) {
		this.equipGroupRecid = equipGroupRecid;
	}

	public String getInUserStrTokenEx() {
		return inUserStrTokenEx;
	}

	public void setInUserStrTokenEx(String inUserStrTokenEx) {
		this.inUserStrTokenEx = inUserStrTokenEx;
	}

	public String getInEquipGroupStrToken() {
		return inEquipGroupStrToken;
	}

	public void setInEquipGroupStrToken(String inEquipGroupStrToken) {
		this.inEquipGroupStrToken = inEquipGroupStrToken;
	}

	public String getInEquipStrToken() {
		return inEquipStrToken;
	}

	public void setInEquipStrToken(String inEquipStrToken) {
		this.inEquipStrToken = inEquipStrToken;
	}

	public String getInUserGroupStrToken() {
		return inUserGroupStrToken;
	}

	public void setInUserGroupStrToken(String inUserGroupStrToken) {
		this.inUserGroupStrToken = inUserGroupStrToken;
	}

	public String getInUserStrToken() {
		return inUserStrToken;
	}

	public void setInUserStrToken(String inUserStrToken) {
		this.inUserStrToken = inUserStrToken;
	}

	public String getInGroup() {
		return inGroup;
	}

	public void setInGroup(String inGroup) {
		this.inGroup = inGroup;
	}

	public int getRecid_ex() {
		return recid_ex;
	}

	public void setRecid_ex(int recid_ex) {
		this.recid_ex = recid_ex;
	}

	public int getRecid() {
		return recid;
	}

	public void setRecid(int recid) {
		this.recid = recid;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getLogRegDt() {
		return logRegDt;
	}

	public void setLogRegDt(String logRegDt) {
		this.logRegDt = logRegDt;
	}

	public String getLogRegIp() {
		return logRegIp;
	}

	public void setLogRegIp(String logRegIp) {
		this.logRegIp = logRegIp;
	}

	public String getLogRegId() {
		return logRegId;
	}

	public void setLogRegId(String logRegId) {
		this.logRegId = logRegId;
	}

	public String getLogChgInfoDt() {
		return logChgInfoDt;
	}

	public void setLogChgInfoDt(String logChgInfoDt) {
		this.logChgInfoDt = logChgInfoDt;
	}

	public String getLogChgInfoIp() {
		return logChgInfoIp;
	}

	public void setLogChgInfoIp(String logChgInfoIp) {
		this.logChgInfoIp = logChgInfoIp;
	}

	public String getLogChgInfoId() {
		return logChgInfoId;
	}

	public void setLogChgInfoId(String logChgInfoId) {
		this.logChgInfoId = logChgInfoId;
	}
}
