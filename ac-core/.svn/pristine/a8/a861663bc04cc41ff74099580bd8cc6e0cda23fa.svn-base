package sga.sol.ac.core.service.policy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sga.sol.ac.core.dao.policy.PolicyConfigDao;
import sga.sol.ac.core.dao.policy.PolicyDao;
import sga.sol.ac.core.entity.equip.EquipAccount;
import sga.sol.ac.core.entity.equip.EquipGroupInfo;
import sga.sol.ac.core.entity.equip.EquipInfo;
import sga.sol.ac.core.entity.log.LmManagementLog;
import sga.sol.ac.core.entity.policy.Equip2FactPolicy;
import sga.sol.ac.core.entity.policy.EquipArsApprovalPolicy;
import sga.sol.ac.core.entity.policy.Policy;
import sga.sol.ac.core.entity.policy.PolicyDataItem;
import sga.sol.ac.core.entity.policy.PolicyObject;
import sga.sol.ac.core.entity.policy.PolicyParam;
import sga.sol.ac.core.entity.policy.PolicyTarget;
import sga.sol.ac.core.entity.policy.User2FactAuthPolicy;
import sga.sol.ac.core.entity.policy.UserPasswordPolicy;
import sga.sol.ac.core.entity.policy.UserWebLoginPolicy;
import sga.sol.ac.core.entity.user.UserGroupInfo;
import sga.sol.ac.core.entity.user.UserInfo;
import sga.sol.ac.core.service.equip.EquipGroupInfoService;
import sga.sol.ac.core.service.log.LmManagementLogService;
import sga.sol.ac.core.service.user.UserInfoService;

@Service
public class PolicyService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PolicyDao policyDao;
	
	@Autowired
	PolicyConfigDao policyConfigDao;
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	LmManagementLogService logService;
	
	@Autowired
	PolicyLogService policyLogService;
	
	@Autowired
	EquipGroupInfoService equipGroupService;
	
	/**
	 * @Date 2016.04.01
	 * @author LeeJungYoung
	 * @Description policy_list 수정
	 * @return recid
	 */
	public int updatePolicyList(Map<String, String> param) {
		return policyDao.updatePolicyList(param);
	}
	
	/**
	 * @Date 2016.04.01
	 * @author LeeJungYoung
	 * @Description policy_list 추가
	 * @return recid
	 */
	public int insertPolicyListReturning(Map<String, Object> param) {
		return policyDao.insertPolicyListReturning(param);
	}
	
	/**
	 * 사용자 그룹으로 그룹에 속해 있는 정책을 역순으로 획득
	 * 
	 * @param policyId
	 * @param user
	 * @return
	 */
	public List<PolicyObject> getPoliciesByUserGroupObject(String policyId, int userGroupRecid) {
		List<PolicyObject> policyObjects = new ArrayList<PolicyObject>();
		
		List<PolicyObject> groupPolicyObjects = policyDao.getAppropriatePolicyObjectByUserGroup(new PolicyParam(policyId, userGroupRecid, PolicyObjectType.POLICY_OBJECT_USERGROUP));
		if(null != groupPolicyObjects && groupPolicyObjects.size() > 0) {
			policyObjects.addAll(groupPolicyObjects);
		}
		
		return policyObjects;
	}
	
	
	/**
	 * 사용자 또는 사용자 그룹을 policy_object으로 가지고 있는 모든 정책 대상 목록을 역순으로 획득한다.
	 * 
	 * @param policyId
	 * @param user
	 * @return
	 */
	
	public List<PolicyObject> getPoliciesByUserObject(String policyId, UserInfo user) {
		List<PolicyObject> policyObjects = new ArrayList<PolicyObject>();
		
		// castle 계정의 경우에는 따로 적용된 정책을 찾는다. 2016.6.23 swcho		
		if ( user.getRecid() == 1 && user.getUserId().equals("castle") ){
			List<Policy> castlePolicy = policyDao.getAppropriatePolicyObjectBySystemAdministrator(new PolicyParam(policyId, user.getRecid()));
			
			// null 체크 추가 size가 0인 list가 될 수 있음 2017.2.15 swcho AUTC-407
			if( null != castlePolicy && !castlePolicy.isEmpty() ){
				PolicyObject tmpPolicyObject = new PolicyObject();
				tmpPolicyObject.setPolicies(castlePolicy);
				tmpPolicyObject.setPolicyRecid(castlePolicy.get(0).getRecid());
				tmpPolicyObject.setType(2);
				tmpPolicyObject.setReferencedRecid(1);
				
				policyObjects.add(tmpPolicyObject);
				
				return policyObjects;
			}
		}
		
		// 정책 대상을 하위에서 상위로 배열한다.
		PolicyObject userPolicy = policyDao.getAppropriatePolicyObjectByObject(new PolicyParam(policyId, user.getRecid(), PolicyObjectType.POLICY_OBJECT_USER));
		if(null != userPolicy) {
			policyObjects.add(userPolicy);
		}
		
		
		System.out.println("user.getUserGroup().getRecid() >>> "+user.getUserGroup().getRecid());
		List<PolicyObject> groupPolicyObjects = policyDao.getAppropriatePolicyObjectByUserGroup(new PolicyParam(policyId, user.getUserGroup().getRecid(), PolicyObjectType.POLICY_OBJECT_USERGROUP));
		if(null != groupPolicyObjects && groupPolicyObjects.size() > 0) {
			policyObjects.addAll(groupPolicyObjects);
		}
		
		return policyObjects;
	}
	
	/*
	 * 2015.12.14 swcho
	 * 기본 정책을 List<PolicyObject> 형태로 리턴한다.
	 * 기본 정책이 하나 이상이면, 데이터가 잘못 입력된 것이므로 무조건 가장 먼저 검색된 정책을 적용한다.
	 */
	public List<PolicyObject> getDefaultPolicyByPolicyId(String policyId){
		List<Policy> defaultPolicy = policyDao.getDefaultPolicyByPolicyId(new PolicyParam(policyId,0));
		
		List<PolicyObject> policyObjects = null;
		// 기본정책은 PolicyId 당 하나씩만 있어야 하지만, 만약 하나 이상이 있다면, 무조건 첫번째만 적용한다
		if(defaultPolicy != null && defaultPolicy.size() !=0){
			policyObjects = new ArrayList<PolicyObject>();
			defaultPolicy = defaultPolicy.subList(0, 1);
			PolicyObject policyObject = new PolicyObject();
			policyObject.setPolicies(defaultPolicy);
			policyObject.setPolicyRecid(defaultPolicy.get(0).getRecid());
			policyObjects.add(policyObject);
			logger.info("Default policy applies to doing {}.", policyId);
		}
		
		return policyObjects;
	}
	
	
	/**
	 * 장비 그룹으로 그룹에 속해 있는 정책을 역순으로 획득
	 * 
	 * @param policyId
	 * @param user
	 * @return
	 */
	public List<PolicyObject> getPoliciesByEquipGroupObject(String policyId, int equipGroupRecid) {
		List<PolicyObject> policyObject = new ArrayList<PolicyObject>();
		
		List<PolicyObject> groupPolicyObjects = policyDao.getAppropriatePolicyObjectByEquipGroup(new PolicyParam(policyId, equipGroupRecid, PolicyObjectType.POLICY_OBJECT_USERGROUP));
		if(null != groupPolicyObjects && groupPolicyObjects.size() > 0) {
			policyObject.addAll(groupPolicyObjects);
		}
		
		return policyObject;
	}
	
	/**
	 * 장비 또는 장비 그룹을 policy_object으로 가지고 있는 모든 정책 대상 목록을 역순으로 획득한다.
	 * 
	 * @param policyId
	 * @param user
	 * @return
	 */
	public List<PolicyObject> getPoliciesByEquipObject(String policyId, EquipInfo equipInfo) {
		List<PolicyObject> policyTargets = new ArrayList<PolicyObject>();
		
		
		// 정책 대상을 하위에서 상위로 배열한다.
		PolicyObject policyTarget = policyDao.getAppropriatePolicyObjectByEquip(new PolicyParam(policyId, equipInfo.getRecid()));
		if(null != policyTarget) {
			policyTargets.add(policyTarget);
		}
		
		List<PolicyObject> groupPolicyObjects = policyDao.getAppropriatePolicyObjectByEquipGroup(new PolicyParam(policyId, equipInfo.getGroupInfo().getRecid()));
		if(null != groupPolicyObjects && groupPolicyObjects.size() > 0) {
			policyTargets.addAll(groupPolicyObjects);
		}
		
		return policyTargets;
	}
	
	/**
	 * 정책ID, Object Type, Object 레코드ID로 해당 정책을 조회한다.
	 * 
	 * @param param
	 * <ul>
	 * <li>policyId: 정책ID</li>
	 * <li>type: Object Type</li>
	 * <li>recid: Object 레코드ID</li>
	 * </ul>
	 * 
	 * @return PolicyObject 객체
	 * @author: surfree
	 * @date:	2016. 5. 26.
	 */
	public PolicyObject getAppropriatePolicyObjectByObject(PolicyParam param) {
		return policyDao.getAppropriatePolicyObjectByObject(param);
	}
	
	/**
	 * 사용자 다중 정책 처리를 위해 기존 함수 변경
	 * 
	 * @param policyId
	 * @param user
	 * @return
	 */
	public List<PolicyObject> getPoliciesByUserMultiObject(String policyId, UserInfo user) {
		List<PolicyObject> policyObjects = new ArrayList<PolicyObject>();
		
		// 정책 대상을 하위에서 상위로 배열한다.
		List<PolicyObject> userPolicys = policyDao.getAppropriatePolicyObjectByUsers(new PolicyParam(policyId, user.getRecid(), PolicyObjectType.POLICY_OBJECT_USER));
		if(null != userPolicys && userPolicys.size() > 0) {
			policyObjects.addAll(userPolicys);
		}
		
		List<PolicyObject> groupPolicyObjects = policyDao.getAppropriatePolicyObjectByUserGroup(new PolicyParam(policyId, user.getUserGroup().getRecid(), PolicyObjectType.POLICY_OBJECT_USERGROUP));
		if(null != groupPolicyObjects && groupPolicyObjects.size() > 0) {
			policyObjects.addAll(groupPolicyObjects);
		}
		
		return policyObjects;
	}
	
	/**
	 * 사용자에 대한 적합한 단일 정책을 반환한다. - 삭제예정
	 * 적용 대상: 웹 인증 정책, 2차 인증 정책, 패스워드 정책
	 * 
	 * @param policyId
	 * @param user
	 * @return
	 */
	//public Policy getAppropriatePolicyByUserObject(String policyId, UserInfo user) {
	//	Policy applyPolicy = null;
	//	List<PolicyObject> policyObjects = getPoliciesByUserObject(policyId, user);
		
	//	// 정책의 필터가 적용(0)일 경우에만 해당 정책을 획득하고 제외 정책이면 상위를 계속 검색한다.
	//	for(PolicyObject object: policyObjects) {
	//		if(object.getFilter() == 0) {
	//			applyPolicy = object.getPolicies().get(0);
				
	//			List<PolicyObject> objects = new ArrayList<PolicyObject>();
	//			objects.add(object);
	//			applyPolicy.setPolicyObject(objects);
	//			break;
	//		}
	//	}
		
	//	return applyPolicy;
	//}
	
	/**
	 * 사용자 정보에 맞는 2차 인증 정책을 반환한다.
	 * 사용자 정보를 통해서 사용자 또는 사용자 그룹(상위 포함)의 정책을 찾는다.
	 * 사용자 그룹의 정책이 중복될 경우 최하위 그룹의 정책을 반환한다.
	 * 
	 * @param policyId 정책
	 * @param user 사용자 정보
	 * @return 사용자에 해당하는 2차 인증 정책
	 */
	@Deprecated
	public Policy getAppropriate2FactAuthPolicy(String policyId, UserInfo user) {
		Policy resultPolicy = policyDao.getAppropriatePolicyByUser(new PolicyParam(policyId, user.getRecid()));
		
		if(null == resultPolicy){
			List<Policy> groupPolicies = policyDao.getAppropriatePolicyByGroup(new PolicyParam(policyId, user.getUserGroup().getRecid()));
			if(null == groupPolicies || 0 == groupPolicies.size()) {
				return null;
			}

			resultPolicy = groupPolicies.get(groupPolicies.size() - 1);
			logger.info("result: " + resultPolicy);
			
		}
		
		return resultPolicy;
	}

	/**
	 * 정책 목록에서 각 정책에 해당하는 사용자 그룹의 레코드ID 목록을 반환한다.
	 * @param policies
	 * @return
	 */
	public List<Integer> getGroupidFromPolicies(List<Policy> policies) {
		List <Integer> result = new ArrayList<Integer>();
		
		for(Policy policy: policies) {
			List<PolicyObject> objects = policy.getPolicyObject();
			result.add(objects.get(0).getReferencedRecid());
		}
		
		return result;
	}

	/**
	 * 사용자 그룹 목록에서 최하위의 그룹 정보를 얻는다.
	 * 입력한 그룹 목록은 단일 트리 구조여야 한다.
	 * @param groups 트리 구조의 그룹 정보 객체 목록
	 * @return 최하위 사용자 그룹 정보 객체
	 */
	public UserGroupInfo getLastChildItem(List<UserGroupInfo> groups) {
		int index = -1;
		int maxValue = 0;
		
		for(int i = 0; i < groups.size(); i++) {
			if(maxValue < groups.get(i).getParentGroupRecid()) {
				maxValue = groups.get(i).getParentGroupRecid();
				index = i;
			}
		}
		
		if(-1 != index) {
			return groups.get(index);
		}
		else {
			return groups.get(0);
		}
	}
	
	/**
	 * 
	 * @param param : 정책 리스트 정보 검색
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public Policy selectPolicyListInfo(int policyRecid) {
		return policyDao.selectPolicyListInfo(policyRecid);
	}
	
	/**
	 * 
	 * @param param : 정책 추가 (policy_object Insert)
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int insertPolicyObject(PolicyObject param, LmManagementLog logInfo) {
		int resultCnt = policyDao.insertPolicyObject(param);
		
		if(resultCnt>0){
			logInfo = policyLogService.setPolicyLog(logInfo, 1, param);
			logService.insertLog(logInfo);
		}
		
		return resultCnt;
	}
	
	/**
	 * 
	 * @param param : 정책 추가 (policy_object Insert)
	 * @return retunrn recid
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int insertPolicyObjectReturning(PolicyObject param, LmManagementLog logInfo) {
		int recid = policyDao.insertPolicyObjectReturning(param);
		
		if(recid>0){
			logInfo = policyLogService.setPolicyLog(logInfo, 1, param);
			logService.insertLog(logInfo);
		}
		
		return recid;
	}
	
	/**
	 * 
	 * @param param : 정책 추가 (policy_target Insert)
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
//	public int insertPolicyTarget(PolicyObject param) {
//		return policyDao.insertPolicyTarget(param);
//	}
	
	/**
	 * 
	 * @param param : 정책 해지 (policy_object Delete)
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int deletePolicyObject(PolicyObject param, LmManagementLog logInfo) {
		int resultCnt = policyDao.deletePolicyObject(param);
		if(resultCnt>0){
			logInfo = policyLogService.setPolicyLog(logInfo, 2, param);
			logService.insertLog(logInfo);
		}
		return resultCnt;
	}
	
	/**
	 * 
	 * @param param : 정책 삭제 (policy_object Delete)
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int deletePolicyObjectByPolicyRecid(int policyRecid) {
		deletePolicyTargetByPolicyRecid(policyRecid);
		return policyDao.deletePolicyObjectByPolicyRecid(policyRecid);
	}
	
	/**
	 * 
	 * @param param : 정책 삭제 (policy_target Delete)
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int deletePolicyTargetByPolicyRecid(int policyRecid) {
		return policyDao.deletePolicyTargetByPolicyRecid(policyRecid);
	}
	
	/**
	 * 
	 * @param param : 정책 삭제 (policy_object Delete)
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int deletePolicyInfo(int policyRecid) {
		return policyDao.deletePolicyInfo(policyRecid);
	}
	
	/**
	 * 
	 * @description 사용자그룹, 사용자, 장비그룹, 장비, 장비계정을 지우면 관련된 정책을 지운다
	 * @param param type : 1-사용자그룹 2-사용자 3-장비그룹 4-장비 5-장비계정
	 * @param param referencedRecid : recid
	 * @return int
	 * @date 2016.01.07
	 * @author LeeJungYoung
	 */
	public int deletePolicyObjectForRelativeTarget(int objectType, List<Integer> recidList) {
		int resultCnt = 0;
		policyDao.deletePolicyTargetByTargetReferencedRecid(objectType, recidList);
		policyDao.deletePolicyObjectForRelativeTarget(objectType, recidList);
		
		
		
		resultCnt = 1;
		return resultCnt;
	}
	
	/**
	 * @Date		: 2016. 3. 2. 
	 * @Author		: swcho
	 * @Description	: 대상타입(object type)의 연관된 정책을 일괄적으로 삭제한다  
	 */
	public int deletePolicyObjectForRelativeTarget(int objectType){
		int resultCnt = 0;
		policyDao.deletePolicyTargetByTargetReferencedRecid(objectType);
		policyDao.deletePolicyObjectForRelativeTarget(objectType);
		
		resultCnt = 1;
		return resultCnt;
	}
	
	/**
	 * @param policyId
	 * @description : 정책 ID로 정책별 조회 (policy_list) 
	 * @return List<Policy> 
	 */
	public List<Policy> getPolicyListUsingPolicyId(String policyId) {
		return policyDao.getPolicyListUsingPolicyId(policyId);
	}
	
	/**
	 * @Description : 사용자 정책 조회
	 * @param policyId : 정책 종류
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public Policy getAppropriatePolicyByUser(String policyId, int referencedRecid){
		return policyDao.getAppropriatePolicyByUser(new PolicyParam(policyId, referencedRecid));
	}
	
	/**
	 * @Description : 사용자그룹 정책 조회
	 * @param policyId : 정책 종류
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public Policy getPolicyInUserGroup(String policyId, int referencedRecid){
		return policyDao.getPolicyInUserGroup(new PolicyParam(policyId, referencedRecid));
	}
	
	/**
	 * @Description : 장비 속한 정책 조회
	 * @param policyId : 정책 종류
	 * @param referencedRecid : 사용자나 사용자 그룹 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public Policy getAppropriatePolicyByEquip(String policyId, int referencedRecid){
		return policyDao.getAppropriatePolicyByEquip(new PolicyParam(policyId, referencedRecid));
	}
	
	
	/**
	 * @Description : 장비계정 속한 정책 조회
	 * @param policyId : 정책 종류
	 * @param referencedRecid : 사용자나 사용자 그룹 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public Policy getAppropriatePolicyByEquipAccount(String policyId, int referencedRecid){
		return policyDao.getAppropriatePolicyByEquipAccount(new PolicyParam(policyId, referencedRecid));
	}
	
	/**
	 * @Description : 장비 속한 정책 조회
	 * @param policyId : 정책 종류
	 * @param referencedRecid : 사용자나 사용자 그룹 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public Policy getPolicyInEquipGroup(String policyId, int referencedRecid){
		return policyDao.getPolicyInEquipGroup(new PolicyParam(policyId, referencedRecid));
	}
	
	
	/************************************** 웹 로그인 정책(user_web_login_policy TABLE) **************************************/
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	@Deprecated
	public UserWebLoginPolicy selectWebLoginPolicyInfo(int policyRecid) {
//		return policyDao.selectWebLoginPolicyInfo(policyRecid);
		return (UserWebLoginPolicy) policyConfigDao.selectPolicyConfig(PolicyConstants.POLICY_WEB_LOGIN, policyRecid);
	}
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	@Deprecated
	public int deleteWebLoginPolicyInfo(int policyRecid) {
//		return policyDao.deleteWebLoginPolicyInfo(policyRecid);
		return policyConfigDao.deletePolicyConfig(PolicyConstants.POLICY_WEB_LOGIN, policyRecid);

	}
	
	/************************************** 패스워드 정책(user_password_policy TABLE) **************************************/
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	@Deprecated
	public UserPasswordPolicy selectPasswordPolicyInfo(int policyRecid) {
//		return policyDao.selectPasswordPolicyInfo(policyRecid);
		return (UserPasswordPolicy) policyConfigDao.selectPolicyConfig(PolicyConstants.POLICY_PASSWORD, policyRecid);
	}
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	@Deprecated
	public int deletePasswordPolicyInfo(int policyRecid) {
//		return policyDao.deletePasswordPolicyInfo(policyRecid);
		return policyConfigDao.deletePolicyConfig(PolicyConstants.POLICY_PASSWORD, policyRecid);
	}
	
	/************************************** 2차인증 정책(user_2fact_auth_policy TABLE) **************************************/
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	@Deprecated
	public User2FactAuthPolicy select2FactAuthPolicyInfo(int policyRecid) {
//		return policyConfigDao.select2FactAuthPolicyInfo(policyRecid);
		return (User2FactAuthPolicy) policyConfigDao.selectPolicyConfig(PolicyConstants.POLICY_2FACT_AUTH, policyRecid);
	}
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	@Deprecated
	public int delete2FactAuthPolicyInfo(int policyRecid) {
//		return policyDao.delete2FactAuthPolicyInfo(policyRecid);
		return policyConfigDao.deletePolicyConfig(PolicyConstants.POLICY_2FACT_AUTH, policyRecid);
	}
	
	/************************************** 장비 2차인증 정책(equip_2fact_policy TABLE) **************************************/
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	@Deprecated
	public Equip2FactPolicy selectEquip2FactPolicyInfo(int policyRecid) {
//		return policyDao.selectEquip2FactPolicyInfo(policyRecid);
		return (Equip2FactPolicy) policyConfigDao.selectPolicyConfig(PolicyConstants.POLICY_2FACT_EQUIP, policyRecid);
	}
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	@Deprecated
	public int deleteEquip2FactPolicyInfo(int policyRecid) {
//		return policyDao.deleteEquip2FactPolicyInfo(policyRecid);
		return policyConfigDao.deletePolicyConfig(PolicyConstants.POLICY_2FACT_EQUIP, policyRecid);
	}
	
	/************************************** 장비 ARS승인 정책(equip_2fact_policy TABLE) **************************************/
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 * TODO: 확인
	 */
	public EquipArsApprovalPolicy selectEquipArsApprovalPolicyInfo(int policyRecid) {
		EquipArsApprovalPolicy resultModel = null;
		
		PolicyDataItem policy = policyConfigDao.selectPolicyConfig(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN , policyRecid);
		
		if(policy instanceof EquipArsApprovalPolicy) {
			resultModel = (EquipArsApprovalPolicy)policy;
			
			if(resultModel.getArsUid1() != 0){
				resultModel.setArsApprover1(userInfoService.getUserInfoExByRecid(resultModel.getArsUid1()));
			}
			if(resultModel.getArsUid2() != 0){
				resultModel.setArsApprover2(userInfoService.getUserInfoExByRecid(resultModel.getArsUid2()));
			}
			if(resultModel.getArsUid3() != 0){
				resultModel.setArsApprover3(userInfoService.getUserInfoExByRecid(resultModel.getArsUid3()));
			}
		}
		
		return resultModel;
	}
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	@Deprecated
	public int deleteEquipArsApprovalPolicyInfo(int policyRecid) {
//		return policyDao.deleteEquipArsApprovalPolicyInfo(policyRecid);
		return policyConfigDao.deletePolicyConfig(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN, policyRecid);
	}
	
	/**
	 * 지정한 조건에 해당하는 PolicyTarget 목록을 얻는다.
	 * <p>
	 * PolicyTarget의 속성이 조회 조건이고 AND 연산으로 처리된다<br>
	 * 조건을 지정하지 않으면 전체 목록을 반환한다
	 * </p>
	 * 
	 * @param policyTarget 조회 조건
	 * <ul>
	 * <li>recid: 레코드ID</li>
	 * <li>objectRecid: PolicyObject 레코드ID</li> 
	 * <li>type: 참조 객체 종류</li> 
	 * <li>referencedRecid: 참조 객체 레코드ID</li> 
	 * </ul>
	 * @return PolicyTarget의 List
	 * @author: surfree
	 * @date:	2016. 2. 2.
	 */
	public List<PolicyTarget> selectPolicyTargetList(PolicyTarget policyTarget) {
		return policyDao.selectPolicyTargetList(policyTarget);
	}
	
	/**
	 * 지정한 사용자 객체(PolicyObject)에 대한 장비 접근 목록 중 <b>장비 그룹</b>에 해당하는 PolicyTarget 목록을 얻는다.
	 * <p>
	 * PolicyTarget에 매개변수에서 명시한 항목을 반드시 설정 해야한다<br>
	 * 반환되는 PolicyTarget 목록은 장비 그룹에 해당하는 목록으로 하위에서 상위 그룹의 순으로 정렬된다
	 * </p>
	 *  
	 * @param policyTarget
	 * <ul>
	 * <li>objectRecid: PolicyObject 레코드ID</li>
	 * <li>type: 장비 그룹 타입(3)</li>
	 * <li>referencedRecid: 장비 그룹 레코드ID</li>
	 * </ul>
	 * @return PolicyTarget(장비 그룹) 목록
	 * <p>하위에서 상위 그룹 순으로 정렬된다</p>
	 * @author: surfree
	 * @date:	2016. 2. 2.
	 */
	public List<PolicyTarget> selectPolicyTargetListByEquipGroup(PolicyTarget policyTarget) {
		return policyDao.selectPolicyTargetListByEquipGroup(policyTarget);
	}
	
	/**
	 * 지정한 사용자 객체(PolicyObject)에 대한 장비 접근 목록 중에 해당되는 정책 대상 항목(PolicyTarget)을 반환한다
	 * <p>
	 * 장비, 장비 그룹, 장비 계정(옵션)을 지정하여 PolicyObject와 연결된 PolicyTarget 중에서 해당하는 항목만 반환한다
	 * </p>
	 * 
	 * @param policyObjectRecid: PolicyTarget의 PolicyObject 레코드ID
	 * @param equipInfo : 장비 객체 정보
	 * @param equipGroupInfo: 장비 그룹 객체 정보
	 * @param equipAccount: 장비 계정 객체 정보(NULL일 경우 장비 계정 정책을 검사하지 않는다)
	 * @return PolicyTarget 해당하는 정책 대상 항목
	 * <p>해당하는 항목이 없을 경우 null 반환</p> 
	 * @author: surfree
	 * @date:	2016. 2. 2.
	 */
	public PolicyTarget getPolicyTargetByEquip(int policyObjectRecid, EquipInfo equipInfo, EquipGroupInfo equipGroupInfo, EquipAccount equipAccount) {
		PolicyTarget param = new PolicyTarget();
		
		if(0 == policyObjectRecid) {
			throw new RuntimeException("Invalid parameter. policyObjectRecid must not be 0.");
		}
		
		if(0 == equipInfo.getRecid()) {
			throw new RuntimeException("Invalid parameter. equipInfo.recid must not be 0.");
		}
		
		if(0 == equipGroupInfo.getRecid()) {
			throw new RuntimeException("Invalid parameter. equipGroupInfo.recid must not be 0.");
		}
		
		if(null != equipAccount) {
//			if(0 == equipAccount.getRecid()) {
//				throw new RuntimeException("Invalid parameter. equipAccount.recid must not be 0.");
//			}
			
			if(equipAccount.getRecid() > 0 ) {
				param.setObjectRecid(policyObjectRecid);
				param.setType(PolicyObjectType.POLICY_OBJECT_EQUIPACCOUNT);
				param.setReferencedRecid(equipAccount.getRecid());
				
				List<PolicyTarget> pts = selectPolicyTargetList(param);
				if(0 != pts.size()) {
					return pts.get(0);
				}
			}
		}
		
		// equipInfo에 대한 조회
		param.setObjectRecid(policyObjectRecid);
		param.setType(PolicyObjectType.POLICY_OBJECT_EQUIP);
		param.setReferencedRecid(equipInfo.getRecid());
		
		List<PolicyTarget> pts = selectPolicyTargetList(param);
		if(0 != pts.size()) {
			return pts.get(0);
		}
		
		// equipGroupInfo에 대한 조회
		param.setObjectRecid(policyObjectRecid);
		param.setType(PolicyObjectType.POLICY_OBJECT_EQUIPGROUP);
		param.setReferencedRecid(equipGroupInfo.getRecid());
		
		pts = selectPolicyTargetListByEquipGroup(param);
		if(0 != pts.size()) {
			return pts.get(0);
		}
		
		return null;
	}
	
	/**
	 * 사용자 객체와 장비 객체에 대한 사용자 장비 접근 2차 인증 정책 대상 항목(PolicyTarget) 목록을 획득한다.
	 * <p>
	 * 목록은 사용자 객체 TYPE, 장비 객체 TYPE 역순으로 정렬된다.
	 * </p>
	 * 
	 * @param userInfo 사용자 객체(사용자 그룹 정보 포함되어야 함)
	 * @param equipInfo 장비 객체
	 * @param equipGroupInfo 장비 그룹 객체
	 * @param equipAccount 장비 계정 객체(NULL일 경우 장비 계정 정책을 검사하지 않는다)
	 * @return PolicyTarget의 List
	 * <p>해당하는 항목이 없을 경우 size가 0인 목록이 반환된다</p>
	 * @author: surfree
	 * @date:	2016. 2. 2.
	 */
	public List<PolicyTarget> selectAppropriate2FactEquipPolices(UserInfo userInfo, EquipInfo equipInfo, EquipGroupInfo equipGroupInfo, EquipAccount equipAccount) {
		List<PolicyTarget> policyTargets = new ArrayList<PolicyTarget>();
		
		Map<Integer, Integer> equipGroupLevel = new HashMap<Integer, Integer>();
		List<EquipGroupInfo> equipGroups = equipGroupService.selectAllParentEquipGroupList(equipGroupInfo.getRecid());
		
		for(int i = 0; i < equipGroups.size(); i++) {
			EquipGroupInfo equipGroup = equipGroups.get(i);
			equipGroupLevel.put(equipGroup.getRecid(), i);
		}
		
		// 사용자 객체에 대한 PolicyObject 목록을 조회
		// PolicyObject 목록은 사용자-사용자그룹-상위그룹 순으로 정렬되어 조회된다.
		List<PolicyObject> equipPolicyObjs = this.getPoliciesByUserMultiObject(PolicyConstants.POLICY_2FACT_EQUIP, userInfo);
		
		for(PolicyObject policyObj: equipPolicyObjs) {
			// PolicyTarget 중에서 지정한 장비 객체에 적합한 정책을 찾는다
			PolicyTarget applyPolicyTarget = this.getPolicyTargetByEquip(policyObj.getRecid(), equipInfo, equipGroupInfo, equipAccount);
			
			// 적합한 PolicyTarget이 있으면 결과 목록에 추가한다
			if(null != applyPolicyTarget) {
				// 우선 순위 처리를 위해서 PolicyObject를 설정한다
				Policy policy = policyObj.getPolicies().get(0);
				
				List<PolicyObject> policyObjects = new ArrayList<PolicyObject>();
				policyObjects.add(policyObj);
				
				policy.setPolicyObject(policyObjects);
				
				// 정책 참조를 위해 PolicyTarget에 정책을 설정한다
				applyPolicyTarget.setPolicy(policy);
				
				// PolicyObject Type이 낮은 순, PolicyTarget Type이 낮은순으로 목록을 정렬한다.
				boolean addLast = true;
				for(int i = 0; i < policyTargets.size(); i++) {
					PolicyTarget policyTarget = policyTargets.get(i);
					PolicyObject policyObject = policyTarget.getPolicy().getPolicyObject().get(0);
					
					if( ( (policyObject.getType() == policyObj.getType()) &&
							(policyTarget.getType() < applyPolicyTarget.getType()) ) ||
						( (policyObject.getType() < policyObj.getType()) && 
							(policyTarget.getType() == applyPolicyTarget.getType()) ) ) {
						policyTargets.add(i, applyPolicyTarget);
						addLast = false;
						break;
					}
					else if( (policyObject.getReferencedRecid() == policyObj.getReferencedRecid()) &&
						(policyTarget.getType() == applyPolicyTarget.getType() && applyPolicyTarget.getType() == PolicyObjectType.POLICY_OBJECT_EQUIPGROUP) &&
						equipGroupLevel.get(policyTarget.getReferencedRecid()) > equipGroupLevel.get(applyPolicyTarget.getReferencedRecid())) {
						// 사용자 그룹이 같고 장비 그룹이 동일하면 그룹 하위 경로 순으로 정렬한다. 
						policyTargets.add(i, applyPolicyTarget);
						addLast = false;
						break;
					}
				}
					
				if(addLast) {
					policyTargets.add(applyPolicyTarget);
				}
			}
		}
		
		return policyTargets;
	}
	
	/**
	 * @description : 정책 중복으로 들어가는 경우를 체크
	 * @param policyObject = type, referencedRecid
	 * @author : LeeJungYoung
	 * @return
	 */
	public List<PolicyObject> selectDeplicatePolicyObjectList(Map<String, Object> param) {
		return policyDao.selectDeplicatePolicyObjectList(param);
	}
}
