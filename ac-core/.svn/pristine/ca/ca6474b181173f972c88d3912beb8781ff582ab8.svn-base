package sga.sol.ac.core.service.policy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import sga.sol.ac.core.Constants;
import sga.sol.ac.core.entity.log.LmManagementLog;
import sga.sol.ac.core.entity.policy.Policy;
import sga.sol.ac.core.entity.policy.PolicyObject;
import sga.sol.ac.core.entity.user.UserInfo;
import sga.sol.ac.core.service.equip.EquipManagementService;
import sga.sol.ac.core.service.log.LmManagementLogService;
import sga.sol.ac.core.dao.policy.EquipArsApprovalPolicyDao;
import sga.sol.ac.core.entity.policy.EquipArsApprovalPolicyList;
import sga.sol.ac.core.entity.policy.EquipArsApprovalPolicyListTypeApprover;

@Service
public class EquipArsApprovalPolicyService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EquipArsApprovalPolicyDao equipArsApprovalPolicyDao;
	
	@Autowired
	private PolicyService policyService;
	
	@Autowired
	private EquipManagementService equipManagementService; 
	
	@Autowired
	LmManagementLogService logService;
	
	/*******************************************************************************************************************
	 * 로그인 ARS 승인자 정책 
	 *******************************************************************************************************************/
	
	/*******************************************************************************
	 * @Description : 로그인 ARS 승인자 정책 리스트 검색 <장비별보기> (최고관리자) 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyList> selectArsApprovalLoginPolicyListForSuperManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_LOGIN);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListForSuperManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 로그인 ARS 승인자 정책 리스트 총갯수 <장비별보기> (최고관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalLoginPolicyListCntForSuperManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_LOGIN);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListCntForSuperManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 로그인 ARS 승인자 정책 리스트 검색 <승인자별보기> (최고관리자) 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyListTypeApprover> selectArsApprovalLoginPolicyListTypeApproverForSuperManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_LOGIN);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListTypeApproverForSuperManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 로그인 ARS 승인자 정책 리스트 총갯수 <승인자별보기> (최고관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalLoginPolicyListCntTypeApproverForSuperManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_LOGIN);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListCntTypeApproverForSuperManager(param);
	}
	
	public Map<String, Object> insertArsApprovalLoginPolicy(Map<String, Object> param, LmManagementLog logInfo){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<EquipArsApprovalPolicyList> returnList = new ArrayList<EquipArsApprovalPolicyList>(); 
		if(param.containsKey("objectReferencedRecids")){
			
			Boolean checkFlag = true;
			List<String> objectReferencedRecids = (List)param.get("objectReferencedRecids");
			for(String objectReferencedRecid : objectReferencedRecids){ // 추가 전 체크 
				Map<String, String> beforeCheckParam = new HashMap<String, String>();
				
				beforeCheckParam.put("objectType", (String)param.get("objectType"));
				beforeCheckParam.put("objectReferencedRecid", String.valueOf(objectReferencedRecid));
				beforeCheckParam.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_LOGIN);
				List<EquipArsApprovalPolicyList> checkList = equipArsApprovalPolicyDao.selectArsApprovalPolicyInfoForSuperManager(beforeCheckParam);
				
				if(checkList.size()>0){
					checkFlag = false;
					returnList.add(checkList.get(0));
				}
			}
			int insertCnt = 0;
			if(checkFlag){
				for(String objectReferencedRecid : objectReferencedRecids){ // 추가 전 체크 
					PolicyObject insertParam = new PolicyObject();
					insertParam.setPolicyRecid(Integer.parseInt((String)param.get("policyRecid")));
					insertParam.setType(Integer.parseInt((String)param.get("objectType")));
					insertParam.setReferencedRecid(Integer.parseInt(objectReferencedRecid));
					insertCnt = insertCnt+policyService.insertPolicyObject(insertParam, logInfo);
				}
			}
			
			resultMap.put("checkFlag", checkFlag);
			if(!checkFlag){
				resultMap.put("list", returnList);
			}
		}
		
		return resultMap;
	}
	
	/**
	 * @param policyId
	 * @description : 장비 수정에서 장비 정책 ARS 승인자 로그인 정책 지정
	 * @return List<Policy> 
	 */
	public int insertArsApproverLoginPolicy(int referencedRecid, int policyRecid, int policyObjectType, LmManagementLog logInfo) {
		int resultCnt = 0;
		
		Policy applyPolicy = null;
		
		if(policyObjectType == PolicyObjectType.POLICY_OBJECT_EQUIPGROUP){
			applyPolicy = policyService.getPolicyInEquipGroup(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN, referencedRecid);
		}else if(policyObjectType == PolicyObjectType.POLICY_OBJECT_EQUIP){
			applyPolicy = policyService.getAppropriatePolicyByEquip(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN, referencedRecid);
		}else if(policyObjectType == PolicyObjectType.POLICY_OBJECT_EQUIPACCOUNT){
			applyPolicy = policyService.getAppropriatePolicyByEquipAccount(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN, referencedRecid);
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
		
		if(resultCnt > 0){
			logInfo.setReturnCode(Constants.SUCCESS);
			Policy newPolicy = policyService.selectPolicyListInfo(insertParam.getPolicyRecid());
		}
		
		return resultCnt;
	}
	
	/*******************************************************************************************************************
	 * 로그인 ARS 승인자 정책 END
	 *******************************************************************************************************************/
	
	
	/*******************************************************************************************************************
	 * 파일접근 ARS 승인자 정책 
	 *******************************************************************************************************************/
	
	/*******************************************************************************
	 * @Description : 파일접근 ARS 승인자 정책 리스트 검색 <장비별보기> (최고관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyList> selectArsApprovalFileAccessPolicyListForSuperManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_FILE);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListForSuperManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 파일접근 ARS 승인자 정책 리스트 총갯수 <장비별보기> (최고관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalFileAccessPolicyListCntForSuperManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_FILE);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListCntForSuperManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 파일접근 ARS 승인자 정책 리스트 검색 <승인자별보기> (최고관리자) 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyListTypeApprover> selectArsApprovalFileAccessPolicyListTypeApproverForSuperManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_FILE);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListTypeApproverForSuperManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 파일접근 ARS 승인자 정책 리스트 총갯수 <승인자별보기> (최고관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalFileAccessPolicyListCntTypeApproverForSuperManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_FILE);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListCntTypeApproverForSuperManager(param);
	}
	
	public Map<String, Object> insertArsApprovalFileAccessPolicy(Map<String, Object> param, LmManagementLog logInfo){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<EquipArsApprovalPolicyList> returnList = new ArrayList<EquipArsApprovalPolicyList>(); 
		if(param.containsKey("objectReferencedRecids")){
			
			Boolean checkFlag = true;
			List<String> objectReferencedRecids = (List)param.get("objectReferencedRecids");
			for(String objectReferencedRecid : objectReferencedRecids){ // 추가 전 체크 
				Map<String, String> beforeCheckParam = new HashMap<String, String>();
				
				beforeCheckParam.put("objectType", (String)param.get("objectType"));
				beforeCheckParam.put("objectReferencedRecid", String.valueOf(objectReferencedRecid));
				beforeCheckParam.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_FILE);
				List<EquipArsApprovalPolicyList> checkList = equipArsApprovalPolicyDao.selectArsApprovalPolicyInfoForSuperManager(beforeCheckParam);
				
				if(checkList.size()>0){
					checkFlag = false;
					returnList.add(checkList.get(0));
				}
			}
			int insertCnt = 0;
			if(checkFlag){
				for(String objectReferencedRecid : objectReferencedRecids){ // 추가 전 체크 
					PolicyObject insertParam = new PolicyObject();
					insertParam.setPolicyRecid(Integer.parseInt((String)param.get("policyRecid")));
					insertParam.setType(Integer.parseInt((String)param.get("objectType")));
					insertParam.setReferencedRecid(Integer.parseInt(objectReferencedRecid));
					insertCnt = insertCnt+policyService.insertPolicyObject(insertParam, logInfo);
				}
			}
			
			resultMap.put("checkFlag", checkFlag);
			if(!checkFlag){
				resultMap.put("list", returnList);
			}
		}
		
		return resultMap;
	}
	
	/**
	 * @param policyId
	 * @description : 장비 수정에서 장비 정책 ARS 승인자 파일접근 정책 지정
	 * @return List<Policy> 
	 */
	public int insertArsApproverFileAccessPolicy(int referencedRecid, int policyRecid, int policyObjectType, LmManagementLog logInfo) {
		int resultCnt = 0;
		
		Policy applyPolicy = policyService.getAppropriatePolicyByEquip(PolicyConstants.POLICY_ARS_APPROVAL_FILE, referencedRecid);
		
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
		
		if(resultCnt > 0){
			logInfo.setReturnCode(Constants.SUCCESS);
			Policy newPolicy = policyService.selectPolicyListInfo(insertParam.getPolicyRecid());
		}
		
		return resultCnt;
	}
	
	/*******************************************************************************************************************
	 * 파일접근 ARS 승인자 정책 END 
	 *******************************************************************************************************************/
	
	
	/*******************************************************************************************************************
	 * 시스템종료 ARS 승인자 정책 
	 *******************************************************************************************************************/
	
	/*******************************************************************************
	 * @Description : 시스템종료 ARS 승인자 정책 리스트 검색 <장비별보기> (최고관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyList> selectArsApprovalShutdownPolicyListForSuperManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListForSuperManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 시스템종료 ARS 승인자 정책 리스트 총갯수 <장비별보기> (최고관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalShutdownPolicyListCntForSuperManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListCntForSuperManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 시스템종료 ARS 승인자 정책 리스트 검색 <승인자별보기> (최고관리자) 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyListTypeApprover> selectArsApprovalShutdownPolicyListTypeApproverForSuperManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListTypeApproverForSuperManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 시스템종료 ARS 승인자 정책 리스트 총갯수 <승인자별보기> (최고관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalShutdownPolicyListCntTypeApproverForSuperManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListCntTypeApproverForSuperManager(param);
	}
	
	public Map<String, Object> insertArsApprovalShutdownPolicy(Map<String, Object> param, LmManagementLog logInfo){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<EquipArsApprovalPolicyList> returnList = new ArrayList<EquipArsApprovalPolicyList>(); 
		if(param.containsKey("objectReferencedRecids")){
			
			Boolean checkFlag = true;
			List<String> objectReferencedRecids = (List)param.get("objectReferencedRecids");
			for(String objectReferencedRecid : objectReferencedRecids){ // 추가 전 체크 
				Map<String, String> beforeCheckParam = new HashMap<String, String>();
				
				beforeCheckParam.put("objectType", (String)param.get("objectType"));
				beforeCheckParam.put("objectReferencedRecid", String.valueOf(objectReferencedRecid));
				beforeCheckParam.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN);
				List<EquipArsApprovalPolicyList> checkList = equipArsApprovalPolicyDao.selectArsApprovalPolicyInfoForSuperManager(beforeCheckParam);
				
				if(checkList.size()>0){
					checkFlag = false;
					returnList.add(checkList.get(0));
				}
			}
			int insertCnt = 0;
			if(checkFlag){
				for(String objectReferencedRecid : objectReferencedRecids){ // 추가 전 체크 
					PolicyObject insertParam = new PolicyObject();
					insertParam.setPolicyRecid(Integer.parseInt((String)param.get("policyRecid")));
					insertParam.setType(Integer.parseInt((String)param.get("objectType")));
					insertParam.setReferencedRecid(Integer.parseInt(objectReferencedRecid));
					insertCnt = insertCnt+policyService.insertPolicyObject(insertParam, logInfo);
				}
			}
			
			resultMap.put("checkFlag", checkFlag);
			if(!checkFlag){
				resultMap.put("list", returnList);
			}
		}
		
		return resultMap;
	}
	
	/**
	 * @param policyId
	 * @description : 장비 수정에서 장비 정책 ARS 승인자 파일접근 정책 지정
	 * @return List<Policy> 
	 */
	public int insertArsApproverShutdownPolicy(int referencedRecid, int policyRecid, int policyObjectType, LmManagementLog logInfo) {
		int resultCnt = 0;
		
		Policy applyPolicy = policyService.getAppropriatePolicyByEquip(PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN, referencedRecid);
		
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
		
		if(resultCnt > 0){
			logInfo.setReturnCode(Constants.SUCCESS);
			Policy newPolicy = policyService.selectPolicyListInfo(insertParam.getPolicyRecid());
		}
		
		return resultCnt;
	}
	
	/*******************************************************************************************************************
	 * 시스템종료 ARS 승인자 정책 최고관리자
	 *******************************************************************************************************************/
	
	/*******************************************************************************
	 * @Description : 추가전 대상 장비(장비, 장비 계정, 장비 그룹)의 ARS 승인자 정책 검색
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyList> selectArsApprovalPolicyInfoForSuperManager(Map<String, String> param) {
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyInfoForSuperManager(param);
	}
	
	
	public int updateArsApprovalPolicy(Map<String, String> param, LmManagementLog logInfo){
		int resultCnt = 0;
		
		PolicyObject deleteParam = new PolicyObject();
		deleteParam.setType(Integer.parseInt(param.get("objectType")));
		deleteParam.setReferencedRecid(Integer.parseInt(param.get("objectReferencedRecid")));
		deleteParam.setPolicyRecid(Integer.parseInt(param.get("deletePolicyRecid")));
		if(policyService.deletePolicyObject(deleteParam, logInfo)>0){
			PolicyObject insertParam = new PolicyObject();
			insertParam.setType(Integer.parseInt(param.get("objectType")));
			insertParam.setReferencedRecid(Integer.parseInt(param.get("objectReferencedRecid")));
			insertParam.setPolicyRecid(Integer.parseInt(param.get("insertPolicyRecid")));
			resultCnt = resultCnt+policyService.insertPolicyObject(insertParam, logInfo);
		}
		
		return resultCnt;
	}
	
	public int deleteArsApprovalPolicy(Map<String, Object> param, LmManagementLog logInfo){
		int resultCnt = 0;
		
		List<Map<String, String>> paramArr = (List<Map<String,String>>)param.get("paramsArr");
		
		if(paramArr != null && paramArr.size()>0){
			for(Map<String, String> rowData : paramArr){
				PolicyObject deleteParam = new PolicyObject();
				deleteParam.setType(Integer.parseInt(rowData.get("type")));
				deleteParam.setReferencedRecid(Integer.parseInt(rowData.get("referencedRecid")));
				deleteParam.setPolicyRecid(Integer.parseInt(rowData.get("policyRecid")));
				resultCnt = resultCnt + policyService.deletePolicyObject(deleteParam, logInfo);
			}
		}
		
		return resultCnt;
	}
	
	/*******************************************************************************
	 * @Description : ARS 승인자 정책 엑셀익스포트
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyList> arsApprovalPolicyListExport(Map<String, Object> param) {
		return equipArsApprovalPolicyDao.arsApprovalPolicyListExport(param);
	}
	
	/*******************************************************************************
	 * @Description : 장비 수정에서 장비 ARS 승인자 정책 해지 시 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public int deleteArsApproverPolicy(int referencedRecid, int policyRecid, int policyObjectType, LmManagementLog logInfo) {
		int resultCnt = 0;
		
		PolicyObject deleteParam = new PolicyObject();
		deleteParam.setType(policyObjectType);
		deleteParam.setPolicyRecid(policyRecid);
		deleteParam.setReferencedRecid(referencedRecid);
		
		resultCnt = policyService.deletePolicyObject(deleteParam, logInfo);
			
		return resultCnt;
	}
	
	/*******************************************************************************************************************
	 * 시스템종료 ARS 승인자 정책 최고관리자 END
	 *******************************************************************************************************************/
	
	/*******************************************************************************************************************
	 * 시스템종료 ARS 승인자 정책 관리자
	 *******************************************************************************************************************/
	
	/*******************************************************************************
	 * @Description : 로그인 ARS 승인자 정책 리스트 검색 <장비별보기> (관리자) 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyList> selectArsApprovalLoginPolicyListForManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_LOGIN);
		
		UserInfo currentUser = (UserInfo)param.get("userData");
		
		//관리자가 관리할수 있는 장비그룹 
		List<Integer> equipGroupIds = equipManagementService.selectPermittedEquipGroupList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		
		if(null != equipGroupIds && equipGroupIds.size() > 0) {
			param.put("equipGroupRecids", StringUtils.collectionToCommaDelimitedString(equipGroupIds));
		}
		else {
			param.put("equipGroupRecids", "-1");
		}
		
		Set<Integer> permEquipSet = equipManagementService.selectPermittedEquipList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		Set<Integer> set = new HashSet<Integer>(equipManagementService.getAssignedEquipIdsForUser(currentUser.getRecid())); 
		permEquipSet.addAll(set);
		
		StringBuffer equipRecids = new StringBuffer();
		equipRecids.append(-1);
		for(Integer rowRecid : permEquipSet){
			equipRecids.append(",");
			equipRecids.append(rowRecid);
		}
		
		param.put("equipRecids", equipRecids);
		
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListForManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 로그인 ARS 승인자 정책 리스트 총갯수 <장비별보기> (관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalLoginPolicyListCntForManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_LOGIN);
		
		UserInfo currentUser = (UserInfo)param.get("userData");
		
		//관리자가 관리할수 있는 장비그룹 
		List<Integer> equipGroupIds = equipManagementService.selectPermittedEquipGroupList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		
		if(null != equipGroupIds && equipGroupIds.size() > 0) {
			param.put("equipGroupRecids", StringUtils.collectionToCommaDelimitedString(equipGroupIds));
		}
		else {
			param.put("equipGroupRecids", "-1");
		}
		
		Set<Integer> permEquipSet = equipManagementService.selectPermittedEquipList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		Set<Integer> set = new HashSet<Integer>(equipManagementService.getAssignedEquipIdsForUser(currentUser.getRecid())); 
		permEquipSet.addAll(set);
		
		StringBuffer equipRecids = new StringBuffer();
		equipRecids.append(-1);
		for(Integer rowRecid : permEquipSet){
			equipRecids.append(",");
			equipRecids.append(rowRecid);
		}
		
		param.put("equipRecids", equipRecids);
		
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListCntForManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 로그인 ARS 승인자 정책 리스트 검색 <승인자별보기> (관리자) 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyListTypeApprover> selectArsApprovalLoginPolicyListTypeApproverForManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_LOGIN);
		UserInfo currentUser = (UserInfo)param.get("userData");
		
		//관리자가 관리할수 있는 장비그룹 
		List<Integer> equipGroupIds = equipManagementService.selectPermittedEquipGroupList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		
		if(null != equipGroupIds && equipGroupIds.size() > 0) {
			param.put("equipGroupRecids", StringUtils.collectionToCommaDelimitedString(equipGroupIds));
		}
		else {
			param.put("equipGroupRecids", "-1");
		}
		
		Set<Integer> permEquipSet = equipManagementService.selectPermittedEquipList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		Set<Integer> set = new HashSet<Integer>(equipManagementService.getAssignedEquipIdsForUser(currentUser.getRecid()));
		permEquipSet.addAll(set);
		
		StringBuffer equipRecids = new StringBuffer();
		equipRecids.append(-1);
		for(Integer rowRecid : permEquipSet){
			equipRecids.append(",");
			equipRecids.append(rowRecid);
		}
		
		param.put("equipRecids", equipRecids);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListTypeApproverForManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 로그인 ARS 승인자 정책 리스트 총갯수 <승인자별보기> (관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalLoginPolicyListCntTypeApproverForManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_LOGIN);
		UserInfo currentUser = (UserInfo)param.get("userData");
		
		//관리자가 관리할수 있는 장비그룹 
		List<Integer> equipGroupIds = equipManagementService.selectPermittedEquipGroupList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		
		if(null != equipGroupIds && equipGroupIds.size() > 0) {
			param.put("equipGroupRecids", StringUtils.collectionToCommaDelimitedString(equipGroupIds));
		}
		else {
			param.put("equipGroupRecids", "-1");
		}
		
		Set<Integer> permEquipSet = equipManagementService.selectPermittedEquipList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		Set<Integer> set = new HashSet<Integer>(equipManagementService.getAssignedEquipIdsForUser(currentUser.getRecid())); 
		permEquipSet.addAll(set);
		
		StringBuffer equipRecids = new StringBuffer();
		equipRecids.append(-1);
		for(Integer rowRecid : permEquipSet){
			equipRecids.append(",");
			equipRecids.append(rowRecid);
		}
		
		param.put("equipRecids", equipRecids);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListCntTypeApproverForManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 파일접근 ARS 승인자 정책 리스트 검색 <장비별보기> (관리자) 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyList> selectArsApprovalFileAccessPolicyListForManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_FILE);
		
		UserInfo currentUser = (UserInfo)param.get("userData");
		
		//관리자가 관리할수 있는 장비그룹 
		List<Integer> equipGroupIds = equipManagementService.selectPermittedEquipGroupList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		
		if(null != equipGroupIds && equipGroupIds.size() > 0) {
			param.put("equipGroupRecids", StringUtils.collectionToCommaDelimitedString(equipGroupIds));
		}
		else {
			param.put("equipGroupRecids", "-1");
		}
		
		Set<Integer> permEquipSet = equipManagementService.selectPermittedEquipList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		Set<Integer> set = new HashSet<Integer>(equipManagementService.getAssignedEquipIdsForUser(currentUser.getRecid())); 
		permEquipSet.addAll(set);
		
		StringBuffer equipRecids = new StringBuffer();
		equipRecids.append(-1);
		for(Integer rowRecid : permEquipSet){
			equipRecids.append(",");
			equipRecids.append(rowRecid);
		}
		
		param.put("equipRecids", equipRecids);
		
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListForManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 파일접근 ARS 승인자 정책 리스트 총갯수 <장비별보기> (관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalFileAccessPolicyListCntForManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_FILE);
		
		UserInfo currentUser = (UserInfo)param.get("userData");
		
		//관리자가 관리할수 있는 장비그룹
		List<Integer> equipGroupIds = equipManagementService.selectPermittedEquipGroupList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		
		if(null != equipGroupIds && equipGroupIds.size() > 0) {
			param.put("equipGroupRecids", StringUtils.collectionToCommaDelimitedString(equipGroupIds));
		}
		else {
			param.put("equipGroupRecids", "-1");
		}
		
		Set<Integer> permEquipSet = equipManagementService.selectPermittedEquipList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		Set<Integer> set = new HashSet<Integer>(equipManagementService.getAssignedEquipIdsForUser(currentUser.getRecid())); 
		permEquipSet.addAll(set);
		
		StringBuffer equipRecids = new StringBuffer();
		equipRecids.append(-1);
		for(Integer rowRecid : permEquipSet){
			equipRecids.append(",");
			equipRecids.append(rowRecid);
		}
		
		param.put("equipRecids", equipRecids);
		
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListCntForManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 파일접근 ARS 승인자 정책 리스트 검색 <승인자별보기> (관리자) 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyListTypeApprover> selectArsApprovalFileAccessPolicyListTypeApproverForManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_FILE);
		UserInfo currentUser = (UserInfo)param.get("userData");
		
		//관리자가 관리할수 있는 장비그룹 
		List<Integer> equipGroupIds = equipManagementService.selectPermittedEquipGroupList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		
		if(null != equipGroupIds && equipGroupIds.size() > 0) {
			param.put("equipGroupRecids", StringUtils.collectionToCommaDelimitedString(equipGroupIds));
		}
		else {
			param.put("equipGroupRecids", "-1");
		}
		
		Set<Integer> permEquipSet = equipManagementService.selectPermittedEquipList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		Set<Integer> set = new HashSet<Integer>(equipManagementService.getAssignedEquipIdsForUser(currentUser.getRecid())); 
		permEquipSet.addAll(set);
		
		StringBuffer equipRecids = new StringBuffer();
		equipRecids.append(-1);
		for(Integer rowRecid : permEquipSet){
			equipRecids.append(",");
			equipRecids.append(rowRecid);
		}
		
		param.put("equipRecids", equipRecids);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListTypeApproverForManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 파일접근 ARS 승인자 정책 리스트 총갯수 <승인자별보기> (관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalFileAccessPolicyListCntTypeApproverForManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_FILE);
		UserInfo currentUser = (UserInfo)param.get("userData");
		
		//관리자가 관리할수 있는 장비그룹
		List<Integer> equipGroupIds = equipManagementService.selectPermittedEquipGroupList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		
		if(null != equipGroupIds && equipGroupIds.size() > 0) {
			param.put("equipGroupRecids", StringUtils.collectionToCommaDelimitedString(equipGroupIds));
		}
		else {
			param.put("equipGroupRecids", "-1");
		}
		
		Set<Integer> permEquipSet = equipManagementService.selectPermittedEquipList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		Set<Integer> set = new HashSet<Integer>(equipManagementService.getAssignedEquipIdsForUser(currentUser.getRecid())); 
		permEquipSet.addAll(set);
		
		StringBuffer equipRecids = new StringBuffer();
		equipRecids.append(-1);
		for(Integer rowRecid : permEquipSet){
			equipRecids.append(",");
			equipRecids.append(rowRecid);
		}
		
		param.put("equipRecids", equipRecids);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListCntTypeApproverForManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 시스템종료 ARS 승인자 정책 리스트 검색 <장비별보기> (관리자) 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyList> selectArsApprovalShutdownPolicyListForManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN);
		
		UserInfo currentUser = (UserInfo)param.get("userData");
		
		//관리자가 관리할수 있는 장비그룹 
		List<Integer> equipGroupIds = equipManagementService.selectPermittedEquipGroupList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		
		if(null != equipGroupIds && equipGroupIds.size() > 0) {
			param.put("equipGroupRecids", StringUtils.collectionToCommaDelimitedString(equipGroupIds));
		}
		else {
			param.put("equipGroupRecids", "-1");
		}
		
		Set<Integer> permEquipSet = equipManagementService.selectPermittedEquipList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		Set<Integer> set = new HashSet<Integer>(equipManagementService.getAssignedEquipIdsForUser(currentUser.getRecid())); 
		permEquipSet.addAll(set);
		
		StringBuffer equipRecids = new StringBuffer();
		equipRecids.append(-1);
		for(Integer rowRecid : permEquipSet){
			equipRecids.append(",");
			equipRecids.append(rowRecid);
		}
		
		param.put("equipRecids", equipRecids);
		
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListForManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 시스템종료 ARS 승인자 정책 리스트 총갯수 <장비별보기> (관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalShutdownPolicyListCntForManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN);
		
		UserInfo currentUser = (UserInfo)param.get("userData");
		
		//관리자가 관리할수 있는 장비그룹 
		List<Integer> equipGroupIds = equipManagementService.selectPermittedEquipGroupList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		
		if(null != equipGroupIds && equipGroupIds.size() > 0) {
			param.put("equipGroupRecids", StringUtils.collectionToCommaDelimitedString(equipGroupIds));
		}
		else {
			param.put("equipGroupRecids", "-1");
		}
		
		Set<Integer> permEquipSet = equipManagementService.selectPermittedEquipList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		Set<Integer> set = new HashSet<Integer>(equipManagementService.getAssignedEquipIdsForUser(currentUser.getRecid())); 
		permEquipSet.addAll(set);
		
		StringBuffer equipRecids = new StringBuffer();
		equipRecids.append(-1);
		for(Integer rowRecid : permEquipSet){
			equipRecids.append(",");
			equipRecids.append(rowRecid);
		}
		
		param.put("equipRecids", equipRecids);
		
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListCntForManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 시스템종료 ARS 승인자 정책 리스트 검색 <승인자별보기> (관리자) 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyListTypeApprover> selectArsApprovalShutdownPolicyListTypeApproverForManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN);
		UserInfo currentUser = (UserInfo)param.get("userData");
		
		//관리자가 관리할수 있는 장비그룹 
		List<Integer> equipGroupIds = equipManagementService.selectPermittedEquipGroupList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		
		if(null != equipGroupIds && equipGroupIds.size() > 0) {
			param.put("equipGroupRecids", StringUtils.collectionToCommaDelimitedString(equipGroupIds));
		}
		else {
			param.put("equipGroupRecids", "-1");
		}
		
		Set<Integer> permEquipSet = equipManagementService.selectPermittedEquipList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		Set<Integer> set = new HashSet<Integer>(equipManagementService.getAssignedEquipIdsForUser(currentUser.getRecid())); 
		permEquipSet.addAll(set);
		
		StringBuffer equipRecids = new StringBuffer();
		equipRecids.append(-1);
		for(Integer rowRecid : permEquipSet){
			equipRecids.append(",");
			equipRecids.append(rowRecid);
		}
		
		param.put("equipRecids", equipRecids);
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListTypeApproverForManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 시스템종료 ARS 승인자 정책 리스트 총갯수 <승인자별보기> (관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalShutdownPolicyListCntTypeApproverForManager(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN);
		
		UserInfo currentUser = (UserInfo)param.get("userData");
		
		//관리자가 관리할수 있는 장비그룹 
		List<Integer> equipGroupIds = equipManagementService.selectPermittedEquipGroupList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		
		if(null != equipGroupIds && equipGroupIds.size() > 0) {
			param.put("equipGroupRecids", StringUtils.collectionToCommaDelimitedString(equipGroupIds));
		}
		else {
			param.put("equipGroupRecids", "-1");
		}
		
		Set<Integer> permEquipSet = equipManagementService.selectPermittedEquipList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		Set<Integer> set = new HashSet<Integer>(equipManagementService.getAssignedEquipIdsForUser(currentUser.getRecid())); 
		permEquipSet.addAll(set);
		
		StringBuffer equipRecids = new StringBuffer();
		equipRecids.append(-1);
		for(Integer rowRecid : permEquipSet){
			equipRecids.append(",");
			equipRecids.append(rowRecid);
		}
		
		param.put("equipRecids", equipRecids);
		
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListCntTypeApproverForManager(param);
	}
	
	/*******************************************************************************
	 * @Description : ARS 승인자 정책 엑셀익스포트
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyList> arsApprovalPolicyListExportForManager(Map<String, Object> param) {
		
		UserInfo currentUser = (UserInfo)param.get("userData");
		
		//관리자가 관리할수 있는 장비그룹 
		List<Integer> equipGroupIds = equipManagementService.selectPermittedEquipGroupList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		
		if(null != equipGroupIds && equipGroupIds.size() > 0) {
			param.put("equipGroupRecids", StringUtils.collectionToCommaDelimitedString(equipGroupIds));
		}
		else {
			param.put("equipGroupRecids", "-1");
		}
		
		Set<Integer> permEquipSet = equipManagementService.selectPermittedEquipList(currentUser.getRecid(), currentUser.getUserGroup().getRecid());
		
		Set<Integer> set = new HashSet<Integer>(equipManagementService.getAssignedEquipIdsForUser(currentUser.getRecid()));
		permEquipSet.addAll(set);
		
		StringBuffer equipRecids = new StringBuffer();
		equipRecids.append(-1);
		for(Integer rowRecid : permEquipSet){
			equipRecids.append(",");
			equipRecids.append(rowRecid);
		}
		
		param.put("equipRecids", equipRecids);
		
		return equipArsApprovalPolicyDao.arsApprovalPolicyListExportForManager(param);
	}
	
	/*******************************************************************************************************************
	 * 시스템종료 ARS 승인자 정책 최고관리자 END
	 *******************************************************************************************************************/
	
	/*******************************************************************************************************************
	 * 로그인 ARS 승인자 정책 ARS 승인자
	 *******************************************************************************************************************/
	
	/*******************************************************************************
	 * @Description : 로그인 ARS 승인자 정책 리스트 검색 <승인자별보기> (ARS 승인자) 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyListTypeApprover> selectArsApprovalLoginPolicyListTypeApproverForArsApprover(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_LOGIN);
		UserInfo currentUser = (UserInfo)param.get("userData");
		param.put("approverRecid", currentUser.getRecid());
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListTypeApproverForArsApprover(param);
	}
	
	/*******************************************************************************
	 * @Description : 로그인 ARS 승인자 정책 리스트 총갯수 <승인자별보기> (ARS 승인자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalLoginPolicyListCntTypeApproverForArsApprover(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_LOGIN);
		UserInfo currentUser = (UserInfo)param.get("userData");
		param.put("approverRecid", currentUser.getRecid());
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListCntTypeApproverForArsApprover(param);
	}
	
	/*******************************************************************************************************************
	 * 로그인 ARS 승인자 정책 ARS 승인자 END
	 *******************************************************************************************************************/
	
	/*******************************************************************************************************************
	 * 파일접근 ARS 승인자 정책 ARS 승인자
	 *******************************************************************************************************************/
	
	/*******************************************************************************
	 * @Description : 파일접근 ARS 승인자 정책 리스트 검색 <승인자별보기> (ARS 승인자) 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyListTypeApprover> selectArsApprovalFileAccessPolicyListTypeApproverForArsApprover(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_FILE);
		UserInfo currentUser = (UserInfo)param.get("userData");
		param.put("approverRecid", currentUser.getRecid());
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListTypeApproverForArsApprover(param);
	}
	
	/*******************************************************************************
	 * @Description : 파일접근 ARS 승인자 정책 리스트 총갯수 <승인자별보기> (ARS 승인자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalFileAccessPolicyListCntTypeApproverForArsApprover(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_FILE);
		UserInfo currentUser = (UserInfo)param.get("userData");
		param.put("approverRecid", currentUser.getRecid());
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListCntTypeApproverForArsApprover(param);
	}
	
	/*******************************************************************************************************************
	 * 파일접근 ARS 승인자 정책 ARS 승인자 END
	 *******************************************************************************************************************/
	
	/*******************************************************************************************************************
	 * 시스템종료 ARS 승인자 정책 ARS 승인자
	 *******************************************************************************************************************/
	
	/*******************************************************************************
	 * @Description : 시스템종료 ARS 승인자 정책 리스트 검색 <승인자별보기> (ARS 승인자) 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyListTypeApprover> selectArsApprovalShutdownPolicyListTypeApproverForArsApprover(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN);
		UserInfo currentUser = (UserInfo)param.get("userData");
		param.put("approverRecid", currentUser.getRecid());
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListTypeApproverForArsApprover(param);
	}
	
	/*******************************************************************************
	 * @Description : 시스템종료 ARS 승인자 정책 리스트 총갯수 <승인자별보기> (ARS 승인자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.29
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalShutdownPolicyListCntTypeApproverForArsApprover(Map<String, Object> param) {
		param.put("policyId", PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN);
		UserInfo currentUser = (UserInfo)param.get("userData");
		param.put("approverRecid", currentUser.getRecid());
		return equipArsApprovalPolicyDao.selectArsApprovalPolicyListCntTypeApproverForArsApprover(param);
	}
	
	/*******************************************************************************************************************
	 * 시스템종료 ARS 승인자 정책 ARS 승인자 END
	 *******************************************************************************************************************/
	
	/*
	 * 장비 승인자 정책 삭제 - 데이터 가져오기 초기 등록시 일괄 삭제 후 입력해야 할 경우 사용
	 * 로그인, 파일접근, 시스템 종료가 사실상 같은 정책이기 때문에 policy_id로 선별적으로 삭제한다
	 * 2016.1.27 swcho   
	 */
	public void deleteAllEquipArsApprovalPolicy(String policyId){
		equipArsApprovalPolicyDao.deleteAllEquipArsApprovalPolicy(policyId);
	}
	
}
