package sga.sol.ac.core.dao.policy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.policy.Equip2FactPolicy;
import sga.sol.ac.core.entity.policy.EquipArsApprovalPolicy;
import sga.sol.ac.core.entity.policy.Policy;
import sga.sol.ac.core.entity.policy.PolicyObject;
import sga.sol.ac.core.entity.policy.PolicyParam;
import sga.sol.ac.core.entity.policy.PolicyTarget;
import sga.sol.ac.core.entity.policy.User2FactAuthPolicy;
import sga.sol.ac.core.entity.policy.UserPasswordPolicy;
import sga.sol.ac.core.entity.policy.UserWebLoginPolicy;

@Repository
public class PolicyDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * @Date 2016.04.01
	 * @author LeeJungYoung
	 * @Description policy_list 추가
	 * @return recid
	 */
	public int insertPolicyListReturning(Map<String, Object> param) {
		sqlSessionTemplate.insert("insertPolicyList", param);
		
		Object objRecid = param.get("recid");
		
		if(objRecid instanceof BigDecimal) {
			BigDecimal bdRecid = (BigDecimal)objRecid;
			return Integer.parseInt(bdRecid.toString());
		}
		else {
			return (Integer)objRecid;
		}
	}
	
	/**
	 * @Date 2016.04.01
	 * @author LeeJungYoung
	 * @Description policy_list 수정
	 * @return recid
	 */
	public int updatePolicyList(Map<String, String> param) {
		return sqlSessionTemplate.insert("updatePolicyList", param);
	}
	
	public Policy getPolicyInUserGroup(PolicyParam param) {
		return sqlSessionTemplate.selectOne("getPolicyInUserGroup", param);
	}
	
	public Policy getPolicyInEquipGroup(PolicyParam param) {
		return sqlSessionTemplate.selectOne("getPolicyInEquipGroup", param);
	}
	
	//public List<Policy> getAppropriatePolicyByGroup(int recid) {
	public List<Policy> getAppropriatePolicyByGroup(PolicyParam param) {
		return sqlSessionTemplate.selectList("getAppropriatePolicyByGroup", param);
	}
	
	public Policy getAppropriatePolicyByUser(PolicyParam param) {
		return sqlSessionTemplate.selectOne("getAppropriatePolicyByUser", param);
	}
	
	public Policy getAppropriatePolicyByEquipAccount(PolicyParam param) {
		return sqlSessionTemplate.selectOne("getAppropriatePolicyByEquipAccount", param);
	}
	
	public Policy getAppropriatePolicyByEquip(PolicyParam param) {
		return sqlSessionTemplate.selectOne("getAppropriatePolicyByEquip", param);
	}
	
	public List<Policy> getAppropriatePolicyByEquipGroup(PolicyParam param) {
		return sqlSessionTemplate.selectOne("getAppropriatePolicyByEquipGroup", param);
	}
	
	public PolicyObject getAppropriatePolicyObjectByObject(PolicyParam param) {
		return sqlSessionTemplate.selectOne("getAppropriatePolicyObjectByObject", param);
	}
	
	/**
	 * 2016.6.23 swcho 시스템 관리자에게 맞는 정책을 찾는다 
	 * 
   	 * @param PolicyParam policyId 지정시 해당 정책 종류에서 찾는다
	 * @return
	 */
	public List<Policy> getAppropriatePolicyObjectBySystemAdministrator(PolicyParam param){
		return sqlSessionTemplate.selectList("getAppropriatePolicyBySystemAdministrator", param);
	}
	
	public List<PolicyObject> getAppropriatePolicyObjectByUsers(PolicyParam param) {
		return sqlSessionTemplate.selectList("getAppropriatePolicyObjectByObject", param);
	}
	
	public List<PolicyObject> getAppropriatePolicyObjectByUserGroup(PolicyParam param) {
		return sqlSessionTemplate.selectList("getAppropriatePolicyObjectByUserGroup", param);
	}
	
	/*
	 * 2015.12.14 swcho
	 * PolicyId에 맞는 기본 정책을 검색한다
	 * 기본 정책은 각ID별로 하나만 들어있고, policy_list 테이블에서 default_flag가 1인 것들이다.
	 */
	public List<Policy> getDefaultPolicyByPolicyId(PolicyParam param){
		return sqlSessionTemplate.selectList("getDefaultPolicyByPolicyId",param);
	}
	
	/**
	 * 이정용 추가 장비에 따른 정책 가져오기
	 * 
	 * @param param
	 * @return
	 */
	@Deprecated
	public List<PolicyTarget> getAppropriatePolicyObjectByEquips(PolicyParam param) {
		return sqlSessionTemplate.selectList("getAppropriatePolicyObjectByEquip", param);
	}
	
	public PolicyObject getAppropriatePolicyObjectByEquip(PolicyParam param) {
		return sqlSessionTemplate.selectOne("getAppropriatePolicyObjectByEquip", param);
	}
	
	/**
	 * 이정용 추가 장비그룹에 따른 정책 가져오기
	 * @param param
	 * @return
	 */
	public List<PolicyObject> getAppropriatePolicyObjectByEquipGroup(PolicyParam param) {
		return sqlSessionTemplate.selectList("getAppropriatePolicyObjectByEquipGroup", param);
	}
	
	@Deprecated
	public PolicyTarget getPolicyTargetByEquip(int policyRecid, int type, int recid) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("policyRecid", policyRecid);
		param.put("type", type);
		param.put("recid", recid);
		
		return sqlSessionTemplate.selectOne("getPolicyTargetByEquip", param);
	}
	
	@Deprecated
	public List<PolicyTarget> getPolicyTargetByEquipType(int type, int recid) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("type", type);
		param.put("recid", recid);
		
		return sqlSessionTemplate.selectList("getPolicyListTargetByEquip", param);
	}
	
	@Deprecated
	public List<PolicyTarget> getPolicyTargetByEquipGroup(int policyRecid, int equipGroupRecid) {
	//public List<PolicyTarget> getPolicyTargetByEquipGroup(PolicyParam param) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("policyRecid", policyRecid);
		param.put("equipGroupRecid", equipGroupRecid);
		
		return sqlSessionTemplate.selectList("getPolicyTargetByEquipGroup", param);
	}
	
	@Deprecated
	public List<PolicyTarget> getPolicyTargetByEquipGroupList(int equipGroupRecid) {
		return sqlSessionTemplate.selectList("getPolicyListTargetByEquipGroup", equipGroupRecid);
	}
	
	public List<Policy> getPolicyListUsingPolicyId(String policyId) {
		return sqlSessionTemplate.selectList("getPolicyListUsingPolicyId", policyId);
	}
	
	
	/**
	 * 
	 * @param param : 정책 검색 (policy_object Select)
	 * @return Policy
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public Policy selectPolicyListInfo(int policyRecid) {
		return sqlSessionTemplate.selectOne("selectPolicyListInfo", policyRecid);
	}
	
	
	/**
	 * 
	 * @param param : 정책 추가 (policy_object Insert)
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int insertPolicyObject(PolicyObject param) {
		return sqlSessionTemplate.insert("insertPolicyObject", param);
	}
	
	/**
	 * 
	 * @param param : 정책 추가 (policy_object Insert)
	 * @return 추가된 Object recid
	 * @date 2016.04.01
	 * @author LeeJungYoung
	 */
	public int insertPolicyObjectReturning(PolicyObject param) {
		sqlSessionTemplate.insert("insertPolicyObject", param);
		
		return param.getRecid();
	}
	
	/**
	 * 
	 * @param param : 정책 추가 (policy_object Insert)
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
//	public int insertPolicyTarget(PolicyObject param) {
//		return sqlSessionTemplate.insert("insertPolicyTarget", param);
//	}
	
	/**
	 * 
	 * @param param : 정책 해지 (policy_object Delete)
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int deletePolicyObject(PolicyObject param) {
		return sqlSessionTemplate.delete("deletePolicyObject", param);
	}
	
	/**
	 * 
	 * @param param : 정책 해지 (policy_object Delete)
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int deletePolicyTarget(PolicyObject param) {
		return sqlSessionTemplate.delete("deletePolicyTarget", param);
	}
	
	/**
	 * 
	 * @param param : 정책 TARGET 삭제 (policy_object Delete)
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int deletePolicyObjectByPolicyRecid(int PolicyRecid) {
		return sqlSessionTemplate.delete("deletePolicyObjectByPolicyRecid", PolicyRecid);
	}
	
	/**
	 * 
	 * @param param : 정책 OBJECT 삭제 (policy_object Delete)
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int deletePolicyTargetByPolicyRecid(int PolicyRecid) {
		return sqlSessionTemplate.delete("deletePolicyTargetByPolicyRecid", PolicyRecid);
	}
	
	/**
	 * 
	 * @param param : 정책 삭제
	 * @return int
	 * @date 2015.10.01
	 * @author LeeJungYoung
	 */
	public int deletePolicyInfo(int PolicyRecid) {
		return sqlSessionTemplate.delete("deletePolicyInfo", PolicyRecid);
	}
	
	/**
	 * 
	 * @description 사용자그룹, 사용자, 장비그룹, 장비, 장비계정을 지우면 관련된 정책을 지운다
	 * @param param type : 1-사용자그룹 2-사용자 3-장비그룹 4-장비 5-장비계정
	 * @param param referencedRecid : recid
	 * @return int
	 * @date 2016.01.07
	 * @author LeeJungYoung
	 * 
	 * 2016.6.30 swcho 정책이 있는 대상 삭제시 매핑된 타켓 순차적으로 삭제하도록 수정
	 */
	public int deletePolicyObjectForRelativeTarget(int objectType, List<Integer> recidList) {
		int resultCnt = 0;
		
		if(recidList.size()>0){
			for(int recid: recidList){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("type", objectType);
				param.put("referencedRecid", recid);
				sqlSessionTemplate.delete("deletePolicyObjectForRelativeTarget1", param);
				sqlSessionTemplate.delete("deletePolicyObjectForRelativeTarget2", param);
			}
			resultCnt = 1;
		}
		
		return resultCnt;
	}

	/**
	 * @Date		: 2016. 3. 2. 
	 * @Author		: swcho
	 * @Description	: 지정 타입이 object type일 경우 일괄 삭제 (object에 target이 속하게 되므로 target도 함께 삭제됨)
	 */
	public int deletePolicyObjectForRelativeTarget(int objectType) {
		int resultCnt = 0;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", objectType);
		
		if(sqlSessionTemplate.delete("deletePolicyObjectForRelativeTarget1", param) > 0){
			resultCnt = sqlSessionTemplate.delete("deletePolicyObjectForRelativeTarget2", param);
		}
		
		return resultCnt;
	}
	
	/**
	 * 
	 * @description 장비그룹, 장비, 장비계정을 지우면 관련된 정책 (object_target)을 지운다
	 * @param param type : 3-장비그룹 4-장비 5-장비계정
	 * @param param referencedRecid : recid
	 * @return int
	 * @date 2016.01.07
	 * @author LeeJungYoung
	 */
	public int deletePolicyTargetByTargetReferencedRecid(int objectType, List<Integer> recidList) {
		int resultCnt = 0;
		
		if(recidList.size()>0){
			for(int recid: recidList){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("type", objectType);
				param.put("referencedRecid", recid);
				resultCnt = resultCnt+sqlSessionTemplate.delete("deletePolicyTargetByTargetReferencedRecid", param);
			}
		}
		
		return resultCnt;
	}
	
	/**
	 * @Date		: 2016. 3. 2. 
	 * @Author		: swcho
	 * @Description	: 지정 타입이 target type 일 경우 일괄 삭제
	 */
	public int deletePolicyTargetByTargetReferencedRecid(int objectType) {
		int resultCnt = 0;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", objectType);
		resultCnt = sqlSessionTemplate.delete("deletePolicyTargetByTargetReferencedRecid", param);
		
		return resultCnt;
	}
	
/************************************** 장비 2차인증 정책(equip_2fact_policy TABLE) **************************************/
	
	public List<PolicyTarget> selectPolicyTargetList(PolicyTarget policyTarget) {
		return sqlSessionTemplate.selectList("selectPolicyTargetList", policyTarget);
	}
	
	public List<PolicyTarget> selectPolicyTargetListByEquipGroup(PolicyTarget policyTarget) {
		if(0 == policyTarget.getReferencedRecid()) {
			throw new IllegalArgumentException("policyTaget.referencedRecid must not be empty.");
		}
		
		if(0 == policyTarget.getObjectRecid()) {
			throw new IllegalArgumentException("polityTarget.objectRecid must not be empty.");
		}
		
		if(3 != policyTarget.getType()) {
			throw new IllegalArgumentException("polityTarget.type must be 3.");
		}
			
		
		return sqlSessionTemplate.selectList("selectPolicyTargetListByEquipGroup", policyTarget);
	}
	
	/**
	 * @description : 정책 중복으로 들어가는 경우를 체크
	 * @param policyObject = type, referencedRecid
	 * @author : LeeJungYoung
	 * @return
	 */
	public List<PolicyObject> selectDeplicatePolicyObjectList(Map<String, Object> params) {
		return sqlSessionTemplate.selectList("selectDeplicatePolicyObjectList", params);
	}
	
}
