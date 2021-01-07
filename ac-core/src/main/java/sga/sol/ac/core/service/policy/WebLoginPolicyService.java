package sga.sol.ac.core.service.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sga.sol.ac.core.entity.log.LmManagementLog;
import sga.sol.ac.core.entity.policy.Policy;
import sga.sol.ac.core.entity.policy.PolicyObject;
import sga.sol.ac.core.service.log.LmManagementLogService;
import sga.sol.ac.core.service.user.UserGroupInfoService;
import sga.sol.ac.core.service.user.UserInfoService;

@Service
public class WebLoginPolicyService {
	@Autowired
	PolicyService policyService;
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	UserGroupInfoService userGroupInfoService;
	
	@Autowired
	LmManagementLogService logService;
	
//	/**
//	 * @param policyId
//	 * @description : 웹로그인 정책 추가 
//	 * @return List<Policy> 
//	 */
//	public int insertWebLoginPolicy(int referencedRecid, int policyRecid, LmManagementLog logInfo) {
//		int resultCnt = 0;
//		
//		Policy applyPolicy = policyService.getAppropriatePolicyByUser(PolicyConstants.POLICY_2FACT_AUTH, referencedRecid);
//		
//		if(applyPolicy != null){
//			PolicyObject deleteParam = new PolicyObject();
//			deleteParam.setType(2);
//			deleteParam.setPolicyRecid(applyPolicy.getPolicyRecid());
//			deleteParam.setReferencedRecid(referencedRecid);
//			
//			resultCnt = policyService.deletePolicyObject(deleteParam, logInfo);
//			
//		}
//		PolicyObject insertParam = new PolicyObject();
//		insertParam.setType(2);
//		insertParam.setPolicyRecid(policyRecid);
//		insertParam.setReferencedRecid(referencedRecid);
//		resultCnt = policyService.insertPolicyObject(insertParam, logInfo);
//		
//		return resultCnt;
//	}
	
	/**
	 * @param policyId
	 * @description : 웹로그인 정책 policy_list 리스트 가져오기
	 * @return List<Policy> 
	 */
	public int insertWebLoginPolicy(int referencedRecid, int policyRecid, int policyObjectType, LmManagementLog logInfo) {
		int resultCnt = 0;
		
		Policy applyPolicy = null;
		if(policyObjectType == PolicyObjectType.POLICY_OBJECT_USERGROUP){
			applyPolicy = policyService.getPolicyInUserGroup(PolicyConstants.POLICY_WEB_LOGIN, referencedRecid);
		}else if(policyObjectType == PolicyObjectType.POLICY_OBJECT_USER){
			applyPolicy = policyService.getAppropriatePolicyByUser(PolicyConstants.POLICY_WEB_LOGIN, referencedRecid);
		}
		
		if(applyPolicy != null){
			PolicyObject deleteParam = new PolicyObject();
			deleteParam.setType(policyObjectType);
			deleteParam.setPolicyRecid(applyPolicy.getPolicyRecid());
			deleteParam.setReferencedRecid(referencedRecid);
			
			resultCnt = policyService.deletePolicyObject(deleteParam, logInfo);
			
		}
		PolicyObject insertParam = new PolicyObject();
		insertParam.setType(policyObjectType);
		insertParam.setPolicyRecid(policyRecid);
		insertParam.setReferencedRecid(referencedRecid);
		resultCnt = policyService.insertPolicyObject(insertParam, logInfo);
		
		return resultCnt;
	}
	
	/**
	 * @param policyId
	 * @description : 패스워드 정책 사용자 해지
	 * @return List<Policy> 
	 */
	public int deleteWebLoginPolicy(int referencedRecid, int policyRecid, int policyObjectType, LmManagementLog logInfo) {
		int resultCnt = 0;
		
		PolicyObject deleteParam = new PolicyObject();
		deleteParam.setType(policyObjectType);
		deleteParam.setPolicyRecid(policyRecid);
		deleteParam.setReferencedRecid(referencedRecid);
		
		Policy delPolicy = policyService.selectPolicyListInfo(policyRecid);
		
		resultCnt = policyService.deletePolicyObject(deleteParam, logInfo);
			
		return resultCnt;
	}
	
}
