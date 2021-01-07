package sga.sol.ac.core.service.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sga.sol.ac.core.Constants;
import sga.sol.ac.core.entity.log.LmManagementLog;
import sga.sol.ac.core.entity.policy.Policy;
import sga.sol.ac.core.entity.policy.PolicyObject;
import sga.sol.ac.core.entity.user.UserGroupInfo;
import sga.sol.ac.core.entity.user.UserInfo;
import sga.sol.ac.core.service.log.LmManagementLogService;
import sga.sol.ac.core.service.user.UserGroupInfoService;
import sga.sol.ac.core.service.user.UserInfoService;

@Service
public class PasswordPolicyService {
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
//	 * @description : 패스워드 정책 추가
//	 * @return List<Policy> 
//	 */
//	public int insertPasswordPolicy(int referencedRecid, int policyRecid, LmManagementLog logInfo) {
//		int resultCnt = 0;
//		
//		Policy applyPolicy = policyService.getAppropriatePolicyByUser(PolicyConstants.POLICY_PASSWORD, referencedRecid);
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
	 * @description : 패스워드 정책 사용자 지정
	 * @return List<Policy> 
	 */
	public int insertPasswordPolicy(int referencedRecid, int policyRecid, int policyObjectType, LmManagementLog logInfo) {
		int resultCnt = 0;
		
		Policy applyPolicy = null;
		if(policyObjectType == PolicyObjectType.POLICY_OBJECT_USERGROUP){
			applyPolicy = policyService.getPolicyInUserGroup(PolicyConstants.POLICY_PASSWORD, referencedRecid);
		}else if(policyObjectType == PolicyObjectType.POLICY_OBJECT_USER){
			applyPolicy = policyService.getAppropriatePolicyByUser(PolicyConstants.POLICY_PASSWORD, referencedRecid);
		}
		
		if(applyPolicy != null){
			PolicyObject deleteParam = new PolicyObject();
			deleteParam.setType(policyObjectType);
			deleteParam.setPolicyRecid(applyPolicy.getPolicyRecid());
			deleteParam.setReferencedRecid(referencedRecid);
			
			resultCnt = policyService.deletePolicyObject(deleteParam, logInfo);
//			if(resultCnt>0){
//				logInfo.setReturnCode(Js.SUCCESS);
//				setLogMsgObjectUserInfo(logInfo, policyObjectType, applyPolicy.getPolicyName(), "삭제");
//			}
			
		}
		PolicyObject insertParam = new PolicyObject();
		insertParam.setType(policyObjectType);
		insertParam.setPolicyRecid(policyRecid);
		insertParam.setReferencedRecid(referencedRecid);
		resultCnt = policyService.insertPolicyObject(insertParam, logInfo);
		
		if(resultCnt > 0){
			logInfo.setReturnCode(Constants.SUCCESS);
			Policy newPolicy = policyService.selectPolicyListInfo(insertParam.getPolicyRecid());
			setLogMsgObjectUserInfo(logInfo, policyObjectType, newPolicy.getPolicyName(), "등록");
		}
		
		return resultCnt;
	}
	
	/**
	 * @param policyId
	 * @description : 패스워드 정책 사용자 해지
	 * @return List<Policy> 
	 */
	public int deletePasswordPolicy(int referencedRecid, int policyRecid, int policyObjectType, LmManagementLog logInfo) {
		int resultCnt = 0;
		
		PolicyObject deleteParam = new PolicyObject();
		deleteParam.setType(policyObjectType);
		deleteParam.setPolicyRecid(policyRecid);
		deleteParam.setReferencedRecid(referencedRecid);
		
		Policy delPolicy = policyService.selectPolicyListInfo(policyRecid);
		
		resultCnt = policyService.deletePolicyObject(deleteParam, logInfo);
			
		return resultCnt;
	}
	
//	private PolicyObject getApplyPasswordPolicyForUser(List<PolicyObject> policyList){
//		PolicyObject resultObj = null;
//		
//		if(policyList.size()>0){
//			if(policyList.get(0).getType() == 2){
//				resultObj = policyList.get(0);
//			}
//		}
//		
//		return resultObj;
//	}
	
	private LmManagementLog setLogObjectUserInfo(LmManagementLog logInfo, int referecedRecid, int policyObjectType) 
	{
		if(PolicyObjectType.POLICY_OBJECT_USER==policyObjectType){
			UserInfo userInfo = userInfoService.getUserInfoExByRecid(referecedRecid);
			logInfo.setObjectUserNew(userInfo);
		}else if(PolicyObjectType.POLICY_OBJECT_USERGROUP==policyObjectType){
			UserGroupInfo userGroupInfo = userGroupInfoService.getUserGroupInfoByRecid(referecedRecid);
			logInfo.setObjUserNm(userGroupInfo.getGroupName());
			logInfo.setObjUserId(userGroupInfo.getGroupPath());
			logInfo.setObjUserRecId(userGroupInfo.getRecid());
		}
		
		return logInfo;
	}
	
	private void setLogMsgObjectUserInfo(LmManagementLog logInfo, int policyObjectType, String policyName , String type) 
	{
		if(PolicyObjectType.POLICY_OBJECT_USER==policyObjectType){
			
			logInfo.setReturnCode(Constants.SUCCESS);
			logInfo.setMessage("비밀번호 정책 : 사용자 "
								+logInfo.getObjUserNm()
								+" ["+logInfo.getObjUserId()
								+"]의 정책 ["
								+policyName
								+"] "+type);
			
		}else if(PolicyObjectType.POLICY_OBJECT_USERGROUP==policyObjectType){
			logInfo.setReturnCode(Constants.SUCCESS);
			logInfo.setMessage("비밀번호 정책 : 사용자그룹"
								+logInfo.getObjUserNm()
								+" ["+logInfo.getObjUserId()
								+"]의 정책 ["
								+policyName
								+"] "+type);
		}
		logService.insertLog(logInfo);
	}
	
}
