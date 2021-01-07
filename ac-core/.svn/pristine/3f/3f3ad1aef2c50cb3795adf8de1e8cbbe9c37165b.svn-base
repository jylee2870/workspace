package sga.sol.ac.core.entity.log;

import java.io.Serializable;

import sga.sol.ac.core.entity.equip.EquipInfo;
import sga.sol.ac.core.entity.user.UserInfo;
import sga.sol.ac.core.util.tools.CommunalModule;

public class LmManagementLog extends CommunalModule implements Serializable {

	private static final long serialVersionUID = 2828227376329L;
	
	private int recid;
	private String srcIp;
	private int srcRecId;
	
	private String srcId;
	private String srcNm;
	private String srcTel;
	private String srcDepart;
	private String srcCompany;
	private int srcLevel;
	
	
	private int objUserRecId;
	private String objUserId;
	private String objUserNm;
	private int objUserLevel;
	
	private int objEquipRecId;
	private String objEquipNm;
	private String objEquipAlias;
	
	private String message;
	private int returnCode;
	private String logSaveDt;
	private String category;
	private String cmd;
	private int logLevel;
	private int riskLevel;
	private int alarmMethod;
	private int mgrGrpId;
	
	private String startDt;
	private String endDt;
	
//	/**
//	 * @author suchoi
//	 * @discription 관리 로그 익스포트 중 작업결과를 나타내기 위한 변수 추가
//	 */
	private String resultMessage;
	private String resultUserLevel;
	
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	
	public int getMgrGrpId() {
		return mgrGrpId;
	}
	public void setMgrGrpId(int mgrGrpId) {
		this.mgrGrpId = mgrGrpId;
	}
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public String getSrcIp() {
		return srcIp;
	}
	public void setSrcIp(String srcIp) {
		this.srcIp = srcIp;
	}
	public String getSrcId() {
		return srcId;
	}
	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}
	
	public int getObjUserLevel() {
		return objUserLevel;
	}
	
	public void setObjUserLevel(int objUserLevel) {
		this.objUserLevel = objUserLevel;
	}
	
	public int getSrcLevel() {
		return srcLevel;
	}
	public void setSrcLevel(int srcLevel) {
		this.srcLevel = srcLevel;
	}
	
	public int getSrcRecId() {
		return srcRecId;
	}
	public void setSrcRecId(int srcRecId) {
		this.srcRecId = srcRecId;
	}
	
	public String getSrcNm() {
		return srcNm;
	}
	public void setSrcNm(String srcNm) {
		this.srcNm = srcNm;
	}
	public String getSrcTel() {
		return srcTel;
	}
	public void setSrcTel(String srcTel) {
		this.srcTel = srcTel;
	}
	public String getSrcDepart() {
		return srcDepart;
	}
	public void setSrcDepart(String srcDepart) {
		this.srcDepart = srcDepart;
	}
	public String getSrcCompany() {
		return srcCompany;
	}
	public void setSrcCompany(String srcCompany) {
		this.srcCompany = srcCompany;
	}
	public int getObjUserRecId() {
		return objUserRecId;
	}
	public void setObjUserRecId(int objUserRecId) {
		this.objUserRecId = objUserRecId;
	}
	public String getObjUserId() {
		return objUserId;
	}
	public void setObjUserId(String objUserId) {
		this.objUserId = objUserId;
	}
	public String getObjUserNm() {
		return objUserNm;
	}
	public void setObjUserNm(String objUserNm) {
		this.objUserNm = objUserNm;
	}
	public int getObjEquipRecId() {
		return objEquipRecId;
	}
	public void setObjEquipRecId(int objEquipRecId) {
		this.objEquipRecId = objEquipRecId;
	}
	public String getObjEquipNm() {
		return objEquipNm;
	}
	public void setObjEquipNm(String objEquipNm) {
		this.objEquipNm = objEquipNm;
	}
	public String getObjEquipAlias() {
		return objEquipAlias;
	}
	public void setObjEquipAlias(String objEquipAlias) {
		this.objEquipAlias = objEquipAlias;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getLogSaveDt() {
		return logSaveDt;
	}
	public void setLogSaveDt(String logSaveDt) {
		this.logSaveDt = logSaveDt;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public int getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(int logLevel) {
		this.logLevel = logLevel;
	}
	public int getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(int riskLevel) {
		this.riskLevel = riskLevel;
	}
	public int getAlarmMethod() {
		return alarmMethod;
	}
	public void setAlarmMethod(int alarmMethod) {
		this.alarmMethod = alarmMethod;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public String getResultUserLevel() {
		return resultUserLevel;
	}
	public void setResultUserLevel(String resultUserLevel) {
		this.resultUserLevel = resultUserLevel;
	}

	public void setObjectUserNew(UserInfo userInfo) {
		setObjUserNm(userInfo.getUserName());
		setObjUserLevel(userInfo.getUserLevelCd());
		setObjUserId(userInfo.getUserId());
		setObjUserRecId(userInfo.getRecid());
	}
	
	public void setObjectUser(int recid, String userId, String userNm, int userLevelCd) {
		setObjUserRecId(recid);
		setObjUserId(userId);
		setObjUserNm(userNm);
		setObjUserLevel(userLevelCd);
	}
	
	public void setObjectEquip(EquipInfo equipInfo) {
		setObjEquipRecId(equipInfo.getRecid());
		setObjEquipNm(equipInfo.getEquipName());
		setObjEquipAlias(equipInfo.getAlias());
	}

	public static LmManagementLog makeLogObject(UserInfo User, String userIp, String category, String cmd) 
	{
		LmManagementLog logInfo = new LmManagementLog();
		
		logInfo.setCategory(category);
		logInfo.setCmd(cmd);
		logInfo.setSrcRecId( User.getRecid());
		logInfo.setSrcId(User.getUserId());
		logInfo.setSrcIp(userIp);
		logInfo.setSrcNm(User.getUserName());
		logInfo.setSrcLevel(User.getUserLevelCd());
		logInfo.setSrcTel(User.getPhone());
		logInfo.setSrcDepart(User.getDepartment());
		logInfo.setSrcCompany(User.getCompany());
		
		return logInfo;
	}
}
