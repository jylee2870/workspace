package sga.sol.ac.core.dao.policy;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.policy.Equip2FactPolicy;
import sga.sol.ac.core.entity.policy.EquipArsApprovalPolicy;
import sga.sol.ac.core.entity.policy.Policy;
import sga.sol.ac.core.entity.policy.PolicyDataItem;
import sga.sol.ac.core.entity.policy.PolicyCrudHandler;
import sga.sol.ac.core.entity.policy.PolicyObject;
import sga.sol.ac.core.entity.policy.User2FactAuthPolicy;
import sga.sol.ac.core.entity.policy.UserPasswordPolicy;
import sga.sol.ac.core.entity.policy.UserWebLoginPolicy;
import sga.sol.ac.core.service.policy.PolicyConstants;
import sga.sol.ac.core.service.policy.PolicyIdManager;

@Repository
@DependsOn("acCoreContextUtil")
public class PolicyConfigDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	PolicyIdManager policyIdManager;
	/*
	Map<String, PolicyHandler> handlerData = new HashMap<String, PolicyHandler>();
	
	Map<String, String> selectHandler = new HashMap<String, String>();
	Map<String, String> insertHandler = new HashMap<String, String>();
	Map<String, String> updateHandler = new HashMap<String, String>();
	Map<String, String> deleteHandler = new HashMap<String, String>();
	*/
	Map<String, PolicyCrudHandler> handlerData = null;
	
	public PolicyConfigDao() {
		/*
		selectHandler.put(PolicyConstants.POLICY_WEB_LOGIN, "selectWebLoginPolicyInfo");
		selectHandler.put(PolicyConstants.POLICY_PASSWORD, "selectPasswordPolicyInfo");
		selectHandler.put(PolicyConstants.POLICY_2FACT_AUTH, "select2FactAuthPolicyInfo");
		selectHandler.put(PolicyConstants.POLICY_2FACT_EQUIP, "selectEquip2FactPolicyInfo");
		selectHandler.put(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN, "selectEquipArsApprovalPolicyInfo");
		selectHandler.put(PolicyConstants.POLICY_ARS_APPROVAL_FILE, "selectEquipArsApprovalPolicyInfo");
		selectHandler.put(PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN, "selectEquipArsApprovalPolicyInfo");
		
		insertHandler.put(PolicyConstants.POLICY_WEB_LOGIN, "insertPolicyConfigForPolicyWebLogin");
		insertHandler.put(PolicyConstants.POLICY_PASSWORD, "insertPolicyConfigForPolicyPassword");
		insertHandler.put(PolicyConstants.POLICY_2FACT_AUTH, "insertPolicyConfigForPolicy2FactAuth");
		insertHandler.put(PolicyConstants.POLICY_2FACT_EQUIP, "insertPolicyConfigForPolicy2FactEquip");
		insertHandler.put(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN, "insertPolicyConfigForPolicyArsApproval");
		insertHandler.put(PolicyConstants.POLICY_ARS_APPROVAL_FILE, "insertPolicyConfigForPolicyArsApproval");
		insertHandler.put(PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN, "insertPolicyConfigForPolicyArsApproval");
		
		updateHandler.put(PolicyConstants.POLICY_WEB_LOGIN, "updatePolicyConfigForPolicyWebLogin");
		updateHandler.put(PolicyConstants.POLICY_PASSWORD, "updatePolicyConfigForPolicyPassword");
		updateHandler.put(PolicyConstants.POLICY_2FACT_AUTH, "updatePolicyConfigForPolicy2FactAuth");
		updateHandler.put(PolicyConstants.POLICY_2FACT_EQUIP, "updatePolicyConfigForPolicy2FactEquip");
		updateHandler.put(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN, "updatePolicyConfigForPolicyArsApproval");
		updateHandler.put(PolicyConstants.POLICY_ARS_APPROVAL_FILE, "updatePolicyConfigForPolicyArsApproval");
		updateHandler.put(PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN, "updatePolicyConfigForPolicyArsApproval");
		
		deleteHandler.put(PolicyConstants.POLICY_WEB_LOGIN, "deleteWebLoginPolicyInfo");
		deleteHandler.put(PolicyConstants.POLICY_PASSWORD, "deletePasswordPolicyInfo");
		deleteHandler.put(PolicyConstants.POLICY_2FACT_AUTH, "delete2FactAuthPolicyInfo");
		deleteHandler.put(PolicyConstants.POLICY_2FACT_EQUIP, "deleteEquip2FactPolicyInfo");
		deleteHandler.put(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN, "deleteEquipArsApprovalPolicyInfo");
		deleteHandler.put(PolicyConstants.POLICY_ARS_APPROVAL_FILE, "deleteEquipArsApprovalPolicyInfo");
		deleteHandler.put(PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN, "deleteEquipArsApprovalPolicyInfo");
		
		handlerData.put(PolicyConstants.POLICY_WEB_LOGIN, new PolicyHandler(
				"selectWebLoginPolicyInfo",
				"insertPolicyConfigForPolicyWebLogin",
				"updatePolicyConfigForPolicyWebLogin",
				"deleteWebLoginPolicyInfo"));
		
		handlerData.put(PolicyConstants.POLICY_PASSWORD, new PolicyHandler(
				"selectPasswordPolicyInfo",
				"insertPolicyConfigForPolicyPassword",
				"updatePolicyConfigForPolicyPassword",
				"deletePasswordPolicyInfo"));
		
		handlerData.put(PolicyConstants.POLICY_2FACT_AUTH, new PolicyHandler(
				"select2FactAuthPolicyInfo",
				"insertPolicyConfigForPolicy2FactAuth",
				"updatePolicyConfigForPolicy2FactAuth",
				"delete2FactAuthPolicyInfo"));
		
		handlerData.put(PolicyConstants.POLICY_2FACT_EQUIP, new PolicyHandler(
				"selectEquip2FactPolicyInfo",
				"insertPolicyConfigForPolicy2FactEquip",
				"updatePolicyConfigForPolicy2FactEquip",
				"deleteEquip2FactPolicyInfo"));
		
		handlerData.put(PolicyConstants.POLICY_ARS_APPROVAL_FILE, new PolicyHandler(
				"selectEquipArsApprovalPolicyInfo",
				"insertPolicyConfigForPolicyArsApproval",
				"deleteEquipArsApprovalPolicyInfo",
				"deleteEquipArsApprovalPolicyInfo"));
		
		handlerData.put(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN, new PolicyHandler(
				"selectEquipArsApprovalPolicyInfo",
				"insertPolicyConfigForPolicyArsApproval",
				"deleteEquipArsApprovalPolicyInfo",
				"deleteEquipArsApprovalPolicyInfo"));
		
		handlerData.put(PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN, new PolicyHandler(
				"selectEquipArsApprovalPolicyInfo",
				"insertPolicyConfigForPolicyArsApproval",
				"deleteEquipArsApprovalPolicyInfo",
				"deleteEquipArsApprovalPolicyInfo"));
		*/
		
//		PolicyIdManager policyInfo = PolicyIdManager.getInstance();
//		handlerData = policyInfo.getHandlerInfo();
//		handlerData = policyIdManager.getHandlerInfo();
	}
	
	@PostConstruct
	public void setHandler() {
		handlerData = policyIdManager.getHandlerInfo();
	}
	
	public int getPolicyConfigListCount(Map param) {
		return sqlSessionTemplate.selectOne("getPolicyConfigListCount", param);
	}
	
	public List<Policy> getPolicyConfigList(Map param) {
		return sqlSessionTemplate.selectList("getPolicyConfigList", param);
	}
	
	/*******************************************************************************
	 * @Description : 정책에 적용된 리스트 총 카운트
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.11.23
	 * @return int
	 */
	public int getPolicyObjectListInfoCount(Map<String, String> param) {
		return sqlSessionTemplate.selectOne("getPolicyObjectListInfoCount", param);
	}
	
	/*******************************************************************************
	 * @Description : 정책에 적용된 리스트 조회
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.11.23
	 * @return int
	 */
	public List<PolicyObject> getPolicyObjectListInfo(Map<String, String> param) {
		return sqlSessionTemplate.selectList("getPolicyObjectListInfo", param);
	}
	
	public PolicyDataItem selectPolicyConfig(String policyId, int policyRecid) {
/*
		String handler = selectHandler.get(policyId);
		
		if(handler == null) {
			throw new IllegalArgumentException(String.format("unsupported policy [%s]", policyId));
		}
		return sqlSessionTemplate.selectOne(handler, policyRecid);
*/
		PolicyCrudHandler handler = handlerData.get(policyId);
		
		if(handler == null) {
			throw new IllegalArgumentException(String.format("unsupported policy [%s]", policyId));
		}
		return sqlSessionTemplate.selectOne(handler.getSelectHandler(), policyRecid);
	}
	
	public int insertPolicyConfig(String policyId, Map param) {
/*
		String handler = insertHandler.get(policyId);
		
		if(handler == null) {
			throw new IllegalArgumentException(String.format("unsupported policy [%s]", policyId));
		}
		return sqlSessionTemplate.insert(handler, param);
*/
		PolicyCrudHandler handler = handlerData.get(policyId);
		
		if(handler == null) {
			throw new IllegalArgumentException(String.format("unsupported policy [%s]", policyId));
		}
		return sqlSessionTemplate.insert(handler.getInsertHandler(), param);
	}
	
	public int updatePolicyConfig(String policyId, Map param) {
/*
		String handler = updateHandler.get(policyId);
		
		if(handler == null) {
			throw new IllegalArgumentException(String.format("unsupported policy [%s]", policyId));
		}
		return sqlSessionTemplate.update(handler, param);
*/
		PolicyCrudHandler handler = handlerData.get(policyId);
		
		if(handler == null) {
			throw new IllegalArgumentException(String.format("unsupported policy [%s]", policyId));
		}
		return sqlSessionTemplate.update(handler.getUpdateHandler(), param);
	}
	
	public int deletePolicyConfig(String policyId, int policyRecid) {
/*
		String handler = deleteHandler.get(policyId);
		
		if(handler == null) {
			throw new IllegalArgumentException(String.format("unsupported policy [%s]", policyId));
		}
		return sqlSessionTemplate.delete(handler, policyRecid);
*/
		PolicyCrudHandler handler = handlerData.get(policyId);
		
		if(handler == null) {
			throw new IllegalArgumentException(String.format("unsupported policy [%s]", policyId));
		}
		return sqlSessionTemplate.delete(handler.getDeleteHandler(), policyRecid);
	}
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public UserWebLoginPolicy selectWebLoginPolicyInfo(int policyRecid) {
		return sqlSessionTemplate.selectOne("selectWebLoginPolicyInfo", policyRecid);
	}
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public UserPasswordPolicy selectPasswordPolicyInfo(int policyRecid) {
		return sqlSessionTemplate.selectOne("selectPasswordPolicyInfo", policyRecid);
	}
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public User2FactAuthPolicy select2FactAuthPolicyInfo(int policyRecid) {
		return sqlSessionTemplate.selectOne("select2FactAuthPolicyInfo", policyRecid);
	}
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public Equip2FactPolicy selectEquip2FactPolicyInfo(int policyRecid) {
		return sqlSessionTemplate.selectOne("selectEquip2FactPolicyInfo", policyRecid);
	}
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public EquipArsApprovalPolicy selectEquipArsApprovalPolicyInfo(int policyRecid) {
		return sqlSessionTemplate.selectOne("selectEquipArsApprovalPolicyInfo", policyRecid);
	}
	
	/*******************************************************************************
	 * @Description : 정책 성정 추가 [비밀번호 정책]
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.11.23
	 * @return int
	 */
	@Deprecated
	public int insertPolicyConfigForPolicyPassword(Map param) {
		return sqlSessionTemplate.insert("insertPolicyConfigForPolicyPassword", param);
	}
	
	/*******************************************************************************
	 * @Description : 정책 성정 추가 [웹로그인 정책 ('WEB_LOGIN_POLICY')]
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.11.23
	 * @return int
	 */
	@Deprecated
	public int insertPolicyConfigForPolicyWebLogin(Map param) {
		return sqlSessionTemplate.insert("insertPolicyConfigForPolicyWebLogin", param);
	}
	
	/*******************************************************************************
	 * @Description : 정책 성정 추가 [ARS 승인 정책 ('ARS_APPROVAL_POLICY')]
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.11.23
	 * @return int
	 */
	@Deprecated
	public int insertPolicyConfigForPolicyArsApproval(Map param) {
		return sqlSessionTemplate.insert("insertPolicyConfigForPolicyArsApproval", param);
	}
	
	/*******************************************************************************
	 * @Description : 정책 성정 추가 [장비 2차인증 정책 ('2FACT_EQUIP_POLICY')]
	 * @update : 사용 안함 : 방식 바뀜 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.11.23
	 * @return int
	 */
	@Deprecated
	public int insertPolicyConfigForPolicy2FactEquip(Map param) {
		return sqlSessionTemplate.insert("insertPolicyConfigForPolicy2FactEquip", param);
	}
	
	/*******************************************************************************
	 * @Description : 정책 성정 추가 [사용자 2차인증 정책 ('2FACT_AUTH_POLICY')]
	 * @update : 사용 안함 : 방식 바뀜 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.11.23
	 * @return int
	 */
	@Deprecated
	public int insertPolicyConfigForPolicy2FactAuth(Map param) {
		return sqlSessionTemplate.insert("insertPolicyConfigForPolicy2FactAuth", param);
	}
	
	/*******************************************************************************
	 * @Description : 정책 성정 수정 [비밀번호 정책]
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.11.23
	 * @return int
	 */
	@Deprecated
	public int updatePolicyConfigForPolicyPassword(Map param) {
		return sqlSessionTemplate.update("updatePolicyConfigForPolicyPassword", param);
	}
	
	/*******************************************************************************
	 * @Description : 정책 성정 수정 [웹로그인 정책 ('WEB_LOGIN_POLICY')]
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.11.23
	 * @return int
	 */
	@Deprecated
	public int updatePolicyConfigForPolicyWebLogin(Map param) {
		return sqlSessionTemplate.update("updatePolicyConfigForPolicyWebLogin", param);
	}
	
	/*******************************************************************************
	 * @Description : 정책 성정 수정 [ARS 승인 정책 ('ARS_APPROVAL_POLICY')]
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.11.23
	 * @return int
	 */
	@Deprecated
	public int updatePolicyConfigForPolicyArsApproval(Map param) {
		return sqlSessionTemplate.update("updatePolicyConfigForPolicyArsApproval", param);
	}
	
	/*******************************************************************************
	 * @Description : 정책 성정 수정 [장비 2차인증 정책 ('2FACT_EQUIP_POLICY')]
	 * @update : 사용 안함 : 방식 바뀜 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.11.23
	 * @return int
	 */
	@Deprecated
	public int updatePolicyConfigForPolicy2FactEquip(Map param) {
		return sqlSessionTemplate.update("updatePolicyConfigForPolicy2FactEquip", param);
	}
	
	/*******************************************************************************
	 * @Description : 정책 성정 수정 [사용자 2차인증 정책 ('2FACT_AUTH_POLICY')]
	 * @update : 사용 안함 : 방식 바뀜 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.11.23
	 * @return int
	 */
	@Deprecated
	public int updatePolicyConfigForPolicy2FactAuth(Map param) {
		return sqlSessionTemplate.update("updatePolicyConfigForPolicy2FactAuth", param);
	}
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int deleteWebLoginPolicyInfo(int policyRecid) {
		return sqlSessionTemplate.delete("deleteWebLoginPolicyInfo", policyRecid);
	}
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int deletePasswordPolicyInfo(int policyRecid) {
		return sqlSessionTemplate.delete("deletePasswordPolicyInfo", policyRecid);
	}
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int delete2FactAuthPolicyInfo(int policyRecid) {
		return sqlSessionTemplate.delete("delete2FactAuthPolicyInfo", policyRecid);
	}
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int deleteEquipArsApprovalPolicyInfo(int policyRecid) {
		return sqlSessionTemplate.delete("deleteEquipArsApprovalPolicyInfo", policyRecid);
	}
	
	/**
	 * 
	 * @param policyRecid : policy_list의 recid
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int deleteEquip2FactPolicyInfo(int policyRecid) {
		return sqlSessionTemplate.delete("deleteEquip2FactPolicyInfo", policyRecid);
	}
}
