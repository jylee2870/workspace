package sga.sol.ac.core.service.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sga.sol.ac.core.entity.equip.EquipAccount;
import sga.sol.ac.core.entity.equip.EquipGroupInfo;
import sga.sol.ac.core.entity.equip.EquipInfo;
import sga.sol.ac.core.entity.log.LmManagementLog;
import sga.sol.ac.core.entity.policy.PolicyObject;
import sga.sol.ac.core.entity.policy.PolicyTarget;
import sga.sol.ac.core.entity.user.UserGroupInfo;
import sga.sol.ac.core.entity.user.UserInfo;
import sga.sol.ac.core.service.equip.EquipAccountService;
import sga.sol.ac.core.service.equip.EquipGroupInfoService;
import sga.sol.ac.core.service.equip.EquipInfoService;
import sga.sol.ac.core.service.log.LmManagementLogService;
import sga.sol.ac.core.service.user.UserGroupInfoService;
import sga.sol.ac.core.service.user.UserInfoService;

@Service
public class PolicyLogService {
	@Autowired
	LmManagementLogService logService;
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	UserGroupInfoService userGroupInfoService;
	
	@Autowired
	EquipGroupInfoService equipGroupInfoService;
	
	@Autowired
	EquipInfoService equipInfoService;
	
	@Autowired
	EquipAccountService equipAccountService;
	
	@Autowired
	PolicyService policyService;
	
	/**
	 * @param logInfo : 로그를 담을 모델
	 * @param logType 1 : 추가 2 : 삭제
	 * @param policyObject 정책 이벤트 obj
	 * @description : 정책 관련 로그를 찍기 위한 서비스 (PolicyObject만 있을 경우)
	 * @description : 해당 msg 셋팅
	 * @return List<Policy> 
	 */
	public LmManagementLog setPolicyLog(
			LmManagementLog logInfo,
			int logType,
			PolicyObject policyObject) {
		
		logInfo.setCategory("POLICY");
		logInfo.setMessage( makeHeaderInfo(logType, policyObject.getPolicyRecid() ).toString() + makeSubjectInfo(policyObject));
		return logInfo;
	}

	/*
	 * 정책 관련 로그를 찍기 위한 서비스 (PolicyObject, PolicyTarget 모두 있을 경우)
	 * 2016.1.27 swcho
	 */
	public LmManagementLog setPolicyLog(
			LmManagementLog logInfo,
			int logType,
			PolicyObject policyObject, PolicyTarget policyTarget) {
		
		logInfo.setCategory("POLICY");
		logInfo.setMessage( makeHeaderInfo(logType, policyObject.getPolicyRecid() ).toString() 
				+ makeSubjectInfo(policyObject)
				+ makeSubjectInfo(policyTarget) );
		return logInfo;
	}
	
	// 정책 관련 로그 표시 메세지
	public StringBuilder makeHeaderInfo(int logType, int policyRecid){
		String logTypeStr = ((logType == 1) ? "추가" : "삭제");
		String policyName = getPolicyName(policyRecid);
		
		StringBuilder msg = new StringBuilder();
		msg.append("[ "+policyName+"] 정책 "+logTypeStr);
		
		return msg;
	}
	
	// 정책 적용 내용 메세지(object 정보)
	public StringBuilder makeSubjectInfo(PolicyObject object){
		return subjectInfoMessage(0, object.getType(), object.getReferencedRecid() );
	}

	// 정책 적용 내용 메세지(target 정보)
	public StringBuilder makeSubjectInfo(PolicyTarget target){
		return subjectInfoMessage(1, target.getType(), target.getReferencedRecid() );
	}
	
	// 정책 로그 메세지에서 대상에 대한 내용을 만든다
	private StringBuilder subjectInfoMessage(int subjectCategory, int subjectType, int referencedRecid){
		StringBuilder msg = new StringBuilder();
		
		String category = subjectCategory == 0 ? "적용 대상" : "목표 대상"; 
		
		switch (subjectType) {
		case PolicyObjectType.POLICY_OBJECT_USERGROUP:
			UserGroupInfo userGroupInfo = (UserGroupInfo)selectPolicyObject(subjectType, referencedRecid);
			
			msg.append("\n[ "+ category + " 사용자 그룹 정보 ]");
			msg.append("\n사용자 그룹경로 : "+ userGroupInfo.getGroupPath());
			msg.append("\n사용자 그룹명 : "+ userGroupInfo.getGroupName());
			break;
		case PolicyObjectType.POLICY_OBJECT_USER:
			UserInfo userInfo = (UserInfo)selectPolicyObject(subjectType, referencedRecid);
			
			msg.append("\n[ "+ category + " 사용자 정보 ]");
			msg.append("\n사용자 그룹경로 : "+ userInfo.getUserGroup().getGroupPath());
			msg.append("\n사용자명 : "+ userInfo.getUserName());
			msg.append("\n사용자ID : "+ userInfo.getUserId());
			break;
		case PolicyObjectType.POLICY_OBJECT_EQUIPGROUP:
			EquipGroupInfo equipGroupInfo = (EquipGroupInfo)selectPolicyObject(subjectType, referencedRecid);
			
			msg.append("\n[ "+ category + " 장비 그룹 정보 ]");
			msg.append("\n장비 그룹경로 : "+ equipGroupInfo.getGroupPath());
			msg.append("\n장비 그룹명 : "+ equipGroupInfo.getGroupName());
			break;
		case PolicyObjectType.POLICY_OBJECT_EQUIP:
			EquipInfo cmEquipInfo = (EquipInfo)selectPolicyObject(subjectType, referencedRecid);
			
			msg.append("\n[ "+ category + " 장비 정보 ]");
			msg.append("\n장비명 : "+ cmEquipInfo.getEquipName());
			msg.append("\n장비IP : "+ cmEquipInfo.getIpPrimary());
			break;
		case PolicyObjectType.POLICY_OBJECT_EQUIPACCOUNT:
			EquipAccount cmEquipAccount = (EquipAccount)selectPolicyObject(subjectType, referencedRecid);
			 
			msg.append("\n[ "+ category + " 장비 정보 ]");
			msg.append("\n장비명 : "+ cmEquipAccount.getEquipInfo().getEquipName());
			msg.append("\n장비 계정명 : "+ cmEquipAccount.getAccount());
			break;
		}
		
		return msg;
	}

	public String getPolicyName(int policyRecid){
		return policyService.selectPolicyListInfo(policyRecid).getPolicyName();
	} 
	
	public Object selectPolicyObject(int objectType, int objectReferencedRecid){
		
		switch (objectType) {
		case PolicyObjectType.POLICY_OBJECT_USERGROUP:
			return userGroupInfoService.getUserGroupInfoByRecid(objectReferencedRecid); 
		case PolicyObjectType.POLICY_OBJECT_USER:
			return userInfoService.getUserInfoExByRecid(objectReferencedRecid);
			
		case PolicyObjectType.POLICY_OBJECT_EQUIPGROUP:
			return equipGroupInfoService.selectEquipGroupInfoByRecid(objectReferencedRecid);
		case PolicyObjectType.POLICY_OBJECT_EQUIP:
			return equipInfoService.selectEquipInfoByRecid(objectReferencedRecid);
		case PolicyObjectType.POLICY_OBJECT_EQUIPACCOUNT:
			return equipAccountService.selectEquipAccountByRecid(objectReferencedRecid);
		default : 
			return null;
		}
	}
	
}
