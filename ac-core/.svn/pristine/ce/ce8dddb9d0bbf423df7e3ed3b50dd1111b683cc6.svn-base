package sga.sol.ac.core.service.policy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sga.sol.ac.core.dao.policy.PolicyConfigDao;
import sga.sol.ac.core.entity.equip.EquipAccount;
import sga.sol.ac.core.entity.equip.EquipGroupInfo;
import sga.sol.ac.core.entity.equip.EquipInfo;
import sga.sol.ac.core.entity.log.LmManagementLog;
import sga.sol.ac.core.entity.policy.EquipArsApprovalPolicy;
import sga.sol.ac.core.entity.policy.Policy;
import sga.sol.ac.core.entity.policy.PolicyDataItem;
import sga.sol.ac.core.entity.policy.PolicyId;
import sga.sol.ac.core.entity.policy.PolicyObject;
import sga.sol.ac.core.entity.user.UserGroupInfo;
import sga.sol.ac.core.entity.user.UserInfo;
import sga.sol.ac.core.log.ManagementLogMsg;
import sga.sol.ac.core.service.comparator.ComparatorFactory;
import sga.sol.ac.core.service.comparator.ComparatorFactory.SortOrder;
import sga.sol.ac.core.service.equip.EquipAccountService;
import sga.sol.ac.core.service.equip.EquipGroupInfoService;
import sga.sol.ac.core.service.equip.EquipInfoService;
import sga.sol.ac.core.service.log.LmManagementLogService;
import sga.sol.ac.core.service.user.UserGroupInfoService;
import sga.sol.ac.core.service.user.UserInfoService;
import sga.sol.ac.core.util.tools.AcCollectionUtils;

@Service
public class PolicyConfigService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PolicyConfigDao policyConfigDao;
	
	@Autowired
	PolicyService policyService;
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	UserGroupInfoService userGroupInfoService;
	
	@Autowired
	EquipInfoService equipInfoService;
	
	@Autowired
	EquipGroupInfoService equipGroupInfoService;
	
	@Autowired
	EquipAccountService equipAccountService;
	
	@Autowired
	PolicyIdManager policyIdManager;
	
	@Autowired
	private LmManagementLogService logService;

	ManagementLogMsg logMsgFactory = new ManagementLogMsg();
	
	final String lang = "ko";
	
	public int getPolicyConfigListCount(Map param) {
		return policyConfigDao.getPolicyConfigListCount(param);
	}
	
	public List<Map<String, Object>> getPolicyConfigList(Map<String, String> param) {
		
		String sortFieldNm = "recid";
		String sortDir = "DESC";
		
		if(param.containsKey("sort")){
			if(param.get("sort").equals("policyIdData")) sortFieldNm = "policy_id";
			else if(param.get("sort").equals("policyName")) sortFieldNm = "policy_name";
			else if(param.get("sort").equals("policyName")) sortFieldNm = "policy_name";
			else if(param.get("sort").equals("createUser")) sortFieldNm = "create_user";
			else if(param.get("sort").equals("createDt")) sortFieldNm = "create_dt";
		} 
		if(param.containsKey("dir")) sortDir = param.get("dir");
		
		param.put("sortFieldNm", sortFieldNm);
		param.put("sortDir", sortDir);
		
		List<Policy> resultList =  policyConfigDao.getPolicyConfigList(param);
		
		List<Map<String, Object>> resultMap = new ArrayList<Map<String,Object>>();
		for(Policy row: resultList){
			PolicyId policyIdData =  getPolictIdInfo(row.getPolicyId());
			
			Map<String, Object> resultObj = new HashMap<String, Object>();
			resultObj = AcCollectionUtils.ConverObjectToMap(row);
			resultObj.put("policyIdData", policyIdData);
			resultMap.add(resultObj);
		}
		
		return resultMap;
	}
	
	public PolicyId getPolictIdInfo(String policyId) {
//		PolicyIdManager policyInfo = PolicyIdManager.getInstance();
//		return policyInfo.getPolicyById(policyId);
		return policyIdManager.getPolicyById(policyId);
		// FIXME: 삭제 코드 CORE에서 구현
/*
		PolicyId resultInfo = new PolicyId();
		
		 if(policyId.equals(PolicyConstants.POLICY_WEB_LOGIN)){
				resultInfo.setPolicyId(PolicyConstants.POLICY_WEB_LOGIN);
				resultInfo.setPolicyTitle("웹로그인 정책");
				resultInfo.setTableName("user_web_login_policy");
				resultInfo.setRecid(1);
				resultInfo.setType("O");
		}else if(policyId.equals(PolicyConstants.POLICY_PASSWORD)){
			resultInfo.setPolicyId(PolicyConstants.POLICY_PASSWORD);
			resultInfo.setPolicyTitle("비밀번호 정책");
			resultInfo.setTableName("user_password_policy");
			resultInfo.setRecid(2);
			resultInfo.setType("O");
		}else if(policyId.equals(PolicyConstants.POLICY_2FACT_AUTH)){
			resultInfo.setPolicyId(PolicyConstants.POLICY_2FACT_AUTH);
			resultInfo.setPolicyTitle("사용자 2차인증 정책");
			resultInfo.setTableName("user_2fact_auth_policy");
			resultInfo.setRecid(3);
			resultInfo.setType("O");
		}else if(policyId.equals(PolicyConstants.POLICY_2FACT_EQUIP)){
			resultInfo.setPolicyId(PolicyConstants.POLICY_2FACT_EQUIP);
			resultInfo.setPolicyTitle("사용자 장비접근 2차인증 정책");
			resultInfo.setTableName("equip_2fact_policy");
			resultInfo.setRecid(4);
			resultInfo.setType("N");
		}else if(policyId.equals(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN)){
			resultInfo.setPolicyId(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN);
			resultInfo.setPolicyTitle("ARS 승인자 로그인 정책");
			resultInfo.setTableName("equip_account_ars_policy");
			resultInfo.setRecid(5);
			resultInfo.setType("T");
		}else if(policyId.equals(PolicyConstants.POLICY_ARS_APPROVAL_FILE)){
			resultInfo.setPolicyId(PolicyConstants.POLICY_ARS_APPROVAL_FILE);
			resultInfo.setPolicyTitle("ARS 승인자 파일접근 정책");
			resultInfo.setTableName("equip_account_ars_policy");
			resultInfo.setRecid(6);
			resultInfo.setType("T");
		}else if(policyId.equals(PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN)){
			resultInfo.setPolicyId(PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN);
			resultInfo.setPolicyTitle("ARS 승인자 시스템종료 정책");
			resultInfo.setTableName("equip_account_ars_policy");
			resultInfo.setRecid(7);
			resultInfo.setType("T");
		}
		
		return resultInfo;
*/
	}
	
	public List<PolicyId> getPolicyIdData(){
//		PolicyIdManager policyInfo = PolicyIdManager.getInstance();
//		return policyInfo.getPolicyList();
		return policyIdManager.getPolicyList();
// FIXME: 삭제 코드 CORE에서 구현
/*
		List<PolicyId> resultList = new ArrayList<PolicyId>();
		
		PolicyId policyWebLogin = new PolicyId();
		policyWebLogin.setPolicyId(PolicyConstants.POLICY_WEB_LOGIN);
		policyWebLogin.setPolicyTitle("웹로그인 정책");
		policyWebLogin.setTableName("user_web_login_policy");
		policyWebLogin.setRecid(1);
		policyWebLogin.setType("O");
		resultList.add(policyWebLogin);
		
		PolicyId policyPassword = new PolicyId();
		policyPassword.setPolicyId(PolicyConstants.POLICY_PASSWORD);
		policyPassword.setPolicyTitle("비밀번호 정책");
		policyPassword.setRecid(2);
		policyPassword.setTableName("user_password_policy");
		policyPassword.setType("O");
		resultList.add(policyPassword);
		
		PolicyId policy2FactAuth = new PolicyId();
		policy2FactAuth.setPolicyId(PolicyConstants.POLICY_2FACT_AUTH);
		policy2FactAuth.setPolicyTitle("사용자 2차인증 정책");
		policy2FactAuth.setRecid(3);
		policy2FactAuth.setTableName("user_2fact_auth_policy");
		policy2FactAuth.setType("O");
		resultList.add(policy2FactAuth);
		
		PolicyId policy2FactEquip = new PolicyId();
		policy2FactEquip.setPolicyId(PolicyConstants.POLICY_2FACT_EQUIP);
		policy2FactEquip.setPolicyTitle("사용자 장비접근 2차인증 정책");
		policy2FactEquip.setRecid(4);
		policy2FactEquip.setTableName("equip_2fact_policy");
		policy2FactEquip.setType("T");
		resultList.add(policy2FactEquip);
		
		PolicyId policyArsApprovalLogin = new PolicyId();
		policyArsApprovalLogin.setPolicyId(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN);
		policyArsApprovalLogin.setPolicyTitle("ARS 승인자 로그인 정책");
		policyArsApprovalLogin.setRecid(5);
		policyArsApprovalLogin.setTableName("equip_account_ars_policy");
		policyArsApprovalLogin.setType("T");
		resultList.add(policyArsApprovalLogin);
		
		PolicyId policyArsApprovalFile = new PolicyId();
		policyArsApprovalFile.setPolicyId(PolicyConstants.POLICY_ARS_APPROVAL_FILE);
		policyArsApprovalFile.setPolicyTitle("ARS 승인자 파일접근 정책");
		policyArsApprovalFile.setRecid(6);
		policyArsApprovalFile.setTableName("equip_account_ars_policy");
		policyArsApprovalFile.setType("T");
		resultList.add(policyArsApprovalFile);
		
		PolicyId policyArsApprovalShutdown = new PolicyId();
		policyArsApprovalShutdown.setPolicyId(PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN);
		policyArsApprovalShutdown.setPolicyTitle("ARS 승인자 시스템종료 정책");
		policyArsApprovalShutdown.setRecid(7);
		policyArsApprovalShutdown.setTableName("equip_account_ars_policy");
		policyArsApprovalShutdown.setType("T");
		resultList.add(policyArsApprovalShutdown);
		
		return resultList;
*/
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int insertPolicyConfig(Map<String, Object> param, LmManagementLog logInfo){
		int resultCnt = 0;
		
		if(param != null){
			String policyId = (String)param.get("policyId");
			
			try {
				int policyRecid = policyService.insertPolicyListReturning(param);
				param.put("policyRecid", policyRecid);
				
				resultCnt = policyConfigDao.insertPolicyConfig(policyId, param);
			}
			catch(IllegalArgumentException ex) {
				logger.error(ex.getMessage(), ex);
				resultCnt = 0;
			}
			
/*
			if(policyId.equals(PolicyConstants.POLICY_2FACT_AUTH)) resultCnt = policyConfigDao.insertPolicyConfigForPolicy2FactAuth(param);
			else if(policyId.equals(PolicyConstants.POLICY_2FACT_EQUIP)) resultCnt = policyConfigDao.insertPolicyConfigForPolicy2FactEquip(param);
			else if(policyId.equals(PolicyConstants.POLICY_PASSWORD)) resultCnt = policyConfigDao.insertPolicyConfigForPolicyPassword(param);
			else if(policyId.equals(PolicyConstants.POLICY_WEB_LOGIN)) resultCnt = policyConfigDao.insertPolicyConfigForPolicyWebLogin(param);
			else if(   policyId.equals(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN)
					|| policyId.equals(PolicyConstants.POLICY_ARS_APPROVAL_FILE)
					|| policyId.equals(PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN)
					){
				resultCnt = policyConfigDao.insertPolicyConfigForPolicyArsApproval(param);
			} 
*/
		}
		
		if(resultCnt > 0) {
			logger.info("정책 설정 추가 성공");
			logInfo.setMessage("정책 설정 추가 정책 : "+ param.get("policyId"));
		}else{
			logger.info("정책 설정 추가 실패");
			logInfo.setMessage("정책 설정 추가 이벤트 실패");
		}
		logService.insertLog(logInfo);
		
		return resultCnt;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int updatePolicyConfig(Map<String, String> param, LmManagementLog logInfo){
		int resultCnt = 0;
		
		if(param != null){
			String policyId = param.get("policyId");
			
			try {
				policyConfigDao.updatePolicyConfig(policyId, param);
				
/*
			if(policyId.equals(PolicyConstants.POLICY_2FACT_AUTH)){
				 policyConfigDao.updatePolicyConfigForPolicy2FactAuth(param);
			}else if(policyId.equals(PolicyConstants.POLICY_2FACT_EQUIP)){
				policyConfigDao.updatePolicyConfigForPolicy2FactEquip(param);
			}else if(policyId.equals(PolicyConstants.POLICY_PASSWORD)){
				policyConfigDao.updatePolicyConfigForPolicyPassword(param);
			}else if(policyId.equals(PolicyConstants.POLICY_WEB_LOGIN)){
				policyConfigDao.updatePolicyConfigForPolicyWebLogin(param);
			}else if(   policyId.equals(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN)
					|| policyId.equals(PolicyConstants.POLICY_ARS_APPROVAL_FILE)
					|| policyId.equals(PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN)
					){
				policyConfigDao.updatePolicyConfigForPolicyArsApproval(param);
			}
*/
				
				if(policyService.updatePolicyList(param) > 0){
					resultCnt = 1;
				}
			}
			catch(IllegalArgumentException ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		
		if(resultCnt>0){
			logger.info("정책 설정 수정 성공");
			logInfo.setMessage("정책 설정 수정 정책 : "+param.get("policyId")+ ", policyRecid : "+ param.get("recid"));
		}else{
			logger.info("정책 설정 수정 실패");
			logInfo.setMessage("정책 설정 수정 이벤트 실패");
		}
		logService.insertLog(logInfo);
		
		return resultCnt;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int deletePolicyConfig(int[] policyRecids, LmManagementLog logInfo){
		int resultCnt = 0;
		
		int delCntObject = 0;
		int delCntTarget = 0;
		if(policyRecids.length > 0){
			
			for(int i=0; i<policyRecids.length; i++){
				Policy policyInfo = policyService.selectPolicyListInfo(policyRecids[i]);
				PolicyId policyIdInfo = getPolictIdInfo(policyInfo.getPolicyId());
				
				if(policyInfo != null){
					
					delCntTarget += policyService.deletePolicyObjectByPolicyRecid(policyRecids[i]);
					
					policyConfigDao.deletePolicyConfig(policyIdInfo.getPolicyId(), policyRecids[i]);
// FIXME: 삭제 코드
/*
					if(policyIdInfo.getPolicyId().equals(PolicyConstants.POLICY_PASSWORD)){
						policyService.deletePasswordPolicyInfo(policyRecids[i]);
					}else if(policyIdInfo.getPolicyId().equals(PolicyConstants.POLICY_WEB_LOGIN)){
						policyService.deleteWebLoginPolicyInfo(policyRecids[i]);
					}else if(
							policyIdInfo.getPolicyId().equals(PolicyConstants.POLICY_ARS_APPROVAL_FILE) ||
							policyIdInfo.getPolicyId().equals(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN) ||
							policyIdInfo.getPolicyId().equals(PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN)
							){
						policyService.deleteEquipArsApprovalPolicyInfo(policyRecids[i]);
					}else if(policyIdInfo.getPolicyId().equals(PolicyConstants.POLICY_2FACT_AUTH)){
						policyService.delete2FactAuthPolicyInfo(policyRecids[i]);
					}else if(policyIdInfo.getPolicyId().equals(PolicyConstants.POLICY_2FACT_EQUIP)){
						policyService.deleteEquip2FactPolicyInfo(policyRecids[i]);
					}
*/
				}
				
				resultCnt += policyService.deletePolicyInfo(policyInfo.getRecid());
			}
			
			
		}
		
		if(resultCnt>0){
			logger.info("정책 설정 삭제 성공");
			logInfo.setMessage("정책 설정 "+resultCnt+"개 삭제 : "
					+ ((delCntObject>0) ? "정책 OBJECT "+delCntObject+"개 삭제" : "")
					+ ((delCntTarget>0) ? "정책 TARGET "+delCntTarget+"개 삭제" : "")
			);
		}else{
			logger.info("정책 설정 삭제 실패");
			logInfo.setMessage("정책 설정 삭제 이벤트 실패");
		}
		logService.insertLog(logInfo);
		
		return resultCnt;
	}

	/**
	 * 
	 * @Description : deletePolicyConfig 함수에서 대처
	 * @return
	 */
	@Deprecated
	private boolean deletePolicyIdTableInfo(String policyId, int policyRecid){
		boolean resultFlag = false;
		
		if(0 != policyConfigDao.deletePolicyConfig(policyId, policyRecid)) {
			resultFlag = true;
		}
/*		
		if(policyId.equals(PolicyConstants.POLICY_2FACT_AUTH)){
			policyService.delete2FactAuthPolicyInfo(policyRecid);
			resultFlag = true;
		}else if(policyId.equals(PolicyConstants.POLICY_2FACT_EQUIP)){
			policyService.deleteEquip2FactPolicyInfo(policyRecid);
			resultFlag = true;
		}else if(policyId.equals(PolicyConstants.POLICY_PASSWORD)){
			policyService.deletePasswordPolicyInfo(policyRecid);
			resultFlag = true;
		}else if(policyId.equals(PolicyConstants.POLICY_WEB_LOGIN)){
			policyService.deleteWebLoginPolicyInfo(policyRecid);
			resultFlag = true;
		}else if(policyId.equals(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN)
				|| policyId.equals(PolicyConstants.POLICY_ARS_APPROVAL_FILE)
				|| policyId.equals(PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN)
				){
			policyService.deleteEquipArsApprovalPolicyInfo(policyRecid);
			resultFlag = true;
		}
*/
		
		return resultFlag;
	}
	
	
	public Map<String, Object> getPolicyConfigInfo(Map<String, String> param){
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
		
		if(param != null){
			int policyRecid = Integer.parseInt(param.get("policyRecid"));
			String policyId = String.valueOf(param.get("policyId"));
			PolicyId policyIdData = getPolictIdInfo(policyId);
			
			Policy policyInfo = policyService.selectPolicyListInfo(policyRecid);
			resultMap = AcCollectionUtils.ConverObjectToMap(policyInfo);
			resultMap.put("policyIdData", policyIdData);
			
			PolicyDataItem policy = policyConfigDao.selectPolicyConfig(policyId, policyRecid);
			
			// 2016.10.20 surfree
			// EquipArsApprovalPolicy 정보 획득 시 ars 승인자 정보를 추가로 조회한다.
			// 기존 코드(selectEquipArsApprovalPolicyInfo)를 공통코드(selectPolicyConfig)로 변경하면서 추가 코드를 처리 못함
			if(policy instanceof EquipArsApprovalPolicy) {
				EquipArsApprovalPolicy resultModel = null;
				
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
			
			if(policy != null) {
				Map<String, Object> detailMapObj = policy.getDataMap();
				
				if(detailMapObj != null) {
					detailMapObj.remove("recid");
					resultMap.putAll(detailMapObj);
				}
			}
// FIXME: 코드 삭제(검토 후 삭제)
/*			
			Map<String, Object> detailMapObj = new HashMap<String, Object>();
			if(policyId.equals(PolicyConstants.POLICY_2FACT_AUTH)){
				detailMapObj = AcCollectionUtils.ConverObjectToMap(policyService.select2FactAuthPolicyInfo(policyRecid));
			}else if(policyId.equals(PolicyConstants.POLICY_2FACT_EQUIP)){
				detailMapObj = AcCollectionUtils.ConverObjectToMap(policyService.selectEquip2FactPolicyInfo(policyRecid));
			}else if(policyId.equals(PolicyConstants.POLICY_PASSWORD)){
				detailMapObj = AcCollectionUtils.ConverObjectToMap(policyService.selectPasswordPolicyInfo(policyRecid));
			}else if(policyId.equals(PolicyConstants.POLICY_WEB_LOGIN)){
				detailMapObj = AcCollectionUtils.ConverObjectToMap(policyService.selectWebLoginPolicyInfo(policyRecid));
			}else if(  policyId.equals(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN)
					|| policyId.equals(PolicyConstants.POLICY_ARS_APPROVAL_FILE)
					|| policyId.equals(PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN)
					){
				detailMapObj = AcCollectionUtils.ConverObjectToMap(policyService.selectEquipArsApprovalPolicyInfo(policyRecid));
			}

			if(detailMapObj!=null){
				Set set = detailMapObj.keySet();
				Iterator iter = set.iterator();
				 
				while(iter.hasNext()){
					String key = (String) iter.next();
					Object value = (Object)detailMapObj.get(key);
					if(!key.equals("recid")){
						resultMap.put(key, value);
					}
				}
			}
*/
		}
		
		return resultMap;
	}
	
	public int getPolicyObjectListInfoCount(Map<String, String> param){
		return policyConfigDao.getPolicyObjectListInfoCount(param);
	}
	
	public List<UserInfo> getPolicyObjectUserListInfo(Map<String, String> param){
		List<UserInfo> resultList = new ArrayList<UserInfo>();
		
		List<PolicyObject> policyObjectList =  policyConfigDao.getPolicyObjectListInfo(param);
		
		if(policyObjectList.size()>0){
			for(PolicyObject row : policyObjectList){
					UserInfo userRow = userInfoService.getUserInfoExByRecid(row.getReferencedRecid());
					if(userRow != null){
						resultList.add(userRow);
					}
					
			}
		}
		if(param.containsKey("sort")){
			SortOrder sortOrder = SortOrder.getSort(param.get("dir"));
			
			if(param.get("sort").equalsIgnoreCase("userId")){
				Collections.sort(resultList, ComparatorFactory.getUserIdComparator(sortOrder));
			}
			else if(param.get("sort").equalsIgnoreCase("userName")){
				Collections.sort(resultList, ComparatorFactory.getUserNameComparator(sortOrder));
			}
			else if(param.get("sort").equalsIgnoreCase("userGroup")){
				Collections.sort(resultList, ComparatorFactory.getUserAssocGroupPathComparator(sortOrder));
			}
			
		}
		
		return resultList;
	}
	
	public List<UserGroupInfo> getPolicyObjectUserGroupListInfo(Map<String, String> param){
		List<UserGroupInfo> resultList = new ArrayList<UserGroupInfo>();
		
		List<PolicyObject> policyObjectList =  policyConfigDao.getPolicyObjectListInfo(param);
		
		if(policyObjectList.size()>0){
			for(PolicyObject row : policyObjectList){
					UserGroupInfo userRow = userGroupInfoService.getUserGroupInfoByRecid(row.getReferencedRecid());
					if(userRow != null){
						resultList.add(userRow);
					}
			}
			
			if(param.containsKey("sort")){
				SortOrder sortOrder = SortOrder.getSort(param.get("dir"));
				
				if(param.get("sort").equalsIgnoreCase("groupName")){
					Collections.sort(resultList, ComparatorFactory.getUserGroupNameComparator(sortOrder));
				}
				else if(param.get("sort").equalsIgnoreCase("groupPath")){
					Collections.sort(resultList, ComparatorFactory.getUserGroupPathComparator(sortOrder));
				}
			}
		}
		
		return resultList;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> insertPolicyObject(Map<String, Object> param, LmManagementLog logInfo){
		int resultCnt = 0;
		Map<String, Object> resultParam = new HashMap<String, Object>();
		resultParam.put("resultCnt", 0);
		resultParam.put("list", null);
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		
		int policyRecid = (Integer) param.get("policyRecid");
		int type = (Integer) param.get("policyObjectType");
		
		Policy policyListObj = policyService.selectPolicyListInfo(policyRecid);
		String policyName = policyListObj.getPolicyName();
		
		// 2016-04-26 이정용 수정 : 중복 체크
		Map<String, Object> depulicateParam = new HashMap<String, Object>();
		depulicateParam.put("policyId", (String)policyListObj.getPolicyId());
		depulicateParam.put("type", (Integer)param.get("policyObjectType"));
		List<Integer> referencedRecids = (List<Integer>)param.get("referencedRecids");
		depulicateParam.put("referencedRecids", referencedRecids);
		List<PolicyObject> checkDepulicationList =  policyService.selectDeplicatePolicyObjectList(depulicateParam);
		
		if(checkDepulicationList.size() > 0){
			for(PolicyObject obj : checkDepulicationList){
				referencedRecids.remove(new Integer(obj.getReferencedRecid()));
				
				String depulicatePolicyName = (policyService.selectPolicyListInfo(obj.getPolicyRecid())).getPolicyName();
				resultList.add(resultInfoDataForInsert(type, obj.getReferencedRecid(), false, depulicatePolicyName));
			}
		}
		
		if(referencedRecids.size()>0){
			for(Integer referencedRecid : referencedRecids){
				PolicyObject insertParam = new PolicyObject();
				insertParam.setType(type);
				insertParam.setPolicyRecid(policyRecid);
				insertParam.setReferencedRecid(referencedRecid);
				
				if(policyService.deletePolicyObject(insertParam, logInfo)>0){
					logger.info("기존에 지정 되어 있던 정책 삭제");
				}
				
				if(policyService.insertPolicyObject(insertParam, logInfo) > 0){
					resultCnt++;
					resultList.add(resultInfoDataForInsert(type, referencedRecid, true, policyName));
				}
			}
		}
		
		resultParam.put("resultCnt", resultCnt);
		resultParam.put("list", resultList);
		return resultParam;
	}
	
	public Map<String, Object> resultInfoDataForInsert(int type, int referencedRecid, boolean success, String policyName){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ObjectMapper m = new ObjectMapper();
		
		if(type == PolicyObjectType.POLICY_OBJECT_USERGROUP){
			UserGroupInfo userGroupInfo = userGroupInfoService.getUserGroupInfoByRecid(referencedRecid);
			resultMap = m.convertValue(userGroupInfo, Map.class);
			resultMap.put("success", success);
			resultMap.put("policyName", policyName);
			
		}else if(type == PolicyObjectType.POLICY_OBJECT_USER){
			UserInfo userInfo = userInfoService.getUserInfoByRecid(referencedRecid);
			resultMap = m.convertValue(userInfo, Map.class);
			resultMap.put("success", success);
			resultMap.put("policyName", policyName);
		}else if(type == PolicyObjectType.POLICY_OBJECT_EQUIPGROUP){
			EquipGroupInfo equipGroupInfo = equipGroupInfoService.selectEquipGroupInfoByRecid(referencedRecid);
			resultMap = m.convertValue(equipGroupInfo, Map.class);
			resultMap.put("success", success);
			resultMap.put("policyName", policyName);
		}else if(type == PolicyObjectType.POLICY_OBJECT_EQUIP){
			EquipInfo equipInfo = equipInfoService.selectEquipInfoByRecid(referencedRecid);
			resultMap = m.convertValue(equipInfo, Map.class);
			resultMap.put("success", success);
			resultMap.put("policyName", policyName);
		}else if(type == PolicyObjectType.POLICY_OBJECT_EQUIPACCOUNT){
			EquipAccount equipAccountInfo = equipAccountService.selectEquipAccountByRecid(referencedRecid);
			resultMap = m.convertValue(equipAccountInfo, Map.class);
			resultMap.put("success", success);
			resultMap.put("policyName", policyName);
		}
		
		return resultMap;
	}
	
	public int deletePolicyObject(Map<String, Object> param, LmManagementLog logInfo){
		int resultCnt = 0;
		
		int[] referencedRecids = (int[])param.get("referencedRecids");
		int policyRecid = (Integer) param.get("policyRecid");
		int type = (Integer) param.get("policyObjectType");
		String typeStr = (type==1) ? "사용자그룹" : "사용자" ; 
		
		if(referencedRecids.length>0){
			String msg = "정책 해지 성공 [ "+typeStr+" ]\n"+typeStr+" RECID : [ ";
			for(int i=0; i<referencedRecids.length; i++){
				PolicyObject insertParam = new PolicyObject();
				insertParam.setType(type);
				insertParam.setPolicyRecid(policyRecid);
				insertParam.setReferencedRecid(referencedRecids[i]);
				if(policyService.deletePolicyObject(insertParam, logInfo) > 0){
					resultCnt++;
					if(i==0) msg += referencedRecids[i];
					else msg += ", "+referencedRecids[i];
				}
			}
			logInfo.setMessage(msg);;
		}else{
			String msg = "정책 해지 실패";
		}
//		logService.insertLog(logInfo);
		
		return resultCnt;
	}
	
	
	public List<Map<String, Object>> getPolicyObjectEquipListInfo(Map<String, String> param){
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		List<PolicyObject> policyTargetList =  policyConfigDao.getPolicyObjectListInfo(param);
		
		if(policyTargetList.size()>0){
			for(PolicyObject row : policyTargetList){
				EquipInfo equipRow = equipInfoService.selectEquipInfoByRecid(row.getReferencedRecid());
				Map<String, Object> rowMap = new HashMap<String, Object>();
				rowMap.put("recid", equipRow.getRecid());
				rowMap.put("group", equipRow.getGroupInfo().getGroupPath());
				rowMap.put("equipNm", equipRow.getEquipName());
				rowMap.put("ipPrimary", equipRow.getIpPrimary());
				rowMap.put("sevNm", StringUtils.defaultIfEmpty(equipRow.getService().getServiceName(), "-"));
				
				if(equipRow != null){
					resultList.add(rowMap);
				}
					
			}
			
			if(param.containsKey("sort")){
				if(param.get("sort").equalsIgnoreCase("group")){
					if(param.get("dir").equalsIgnoreCase("DESC")){
						Collections.sort(resultList, new Comparator<Map<String, Object>>() {
							@Override
							public int compare(Map<String, Object> o1, Map<String, Object> o2) {
								return (o2.get("group").equals(o1.get("group")) ? 1: -1);
								
							}
						});
					}else{
						Collections.sort(resultList, new Comparator<Map<String, Object>>() {
							@Override
							public int compare(Map<String, Object> o1, Map<String, Object> o2) {
								return (o1.get("group").equals(o2.get("group")) ? -1: 1);
							}
						});
					}
				}else if(param.get("sort").equalsIgnoreCase("equipNm")){
					if(param.get("dir").equalsIgnoreCase("DESC")){
						Collections.sort(resultList, new Comparator<Map<String, Object>>() {
							@Override
							public int compare(Map<String, Object> o1, Map<String, Object> o2) {
								return (o2.get("equipNm").equals(o1.get("equipNm")) ? 1: -1);
								
							}
						});
					}else{
						Collections.sort(resultList, new Comparator<Map<String, Object>>() {
							@Override
							public int compare(Map<String, Object> o1, Map<String, Object> o2) {
								return (o1.get("equipNm").equals(o2.get("equipNm")) ? -1: 1);
							}
						});
					}
				}else if(param.get("sort").equalsIgnoreCase("ipPrimary")){
					if(param.get("dir").equalsIgnoreCase("DESC")){
						Collections.sort(resultList, new Comparator<Map<String, Object>>() {
							@Override
							public int compare(Map<String, Object> o1, Map<String, Object> o2) {
								return (o2.get("ipPrimary").equals(o1.get("ipPrimary")) ? 1: -1);
								
							}
						});
					}else{
						Collections.sort(resultList, new Comparator<Map<String, Object>>() {
							@Override
							public int compare(Map<String, Object> o1, Map<String, Object> o2) {
								return (o1.get("ipPrimary").equals(o2.get("ipPrimary")) ? -1: 1);
							}
						});
					}
				}
			}
		}
		
		return resultList;
	}
	
	public List<EquipGroupInfo> getPolicyObjectEquipGroupListInfo(Map<String, String> param){
		List<EquipGroupInfo> resultList = new ArrayList<EquipGroupInfo>();
		
		List<PolicyObject> policyTargetList =  policyConfigDao.getPolicyObjectListInfo(param);
		
		if(policyTargetList.size()>0){
			for(PolicyObject row : policyTargetList){
				EquipGroupInfo equipRow = equipGroupInfoService.selectEquipGroupInfoByRecid(row.getReferencedRecid());
				if(equipRow != null){
					resultList.add(equipRow);
				}
			}
			
			if(param.containsKey("sort")){
				SortOrder sortOrder = SortOrder.getSort(param.get("dir"));
				
				if(param.get("sort").equalsIgnoreCase("grpNm")){
					Collections.sort(resultList, ComparatorFactory.getEquipGroupNameComparator(sortOrder));
				}
				else if(param.get("sort").equalsIgnoreCase("grpPath")){
					Collections.sort(resultList, ComparatorFactory.getEquipGroupPathComparator(sortOrder));
				}
			}
		}
		
		return resultList;
	}
	
	public List<EquipAccount> getPolicyObjectEquipAccountListInfo(Map<String, String> param){
		List<EquipAccount> resultList = new ArrayList<EquipAccount>();
		
		List<PolicyObject> policyTargetList =  policyConfigDao.getPolicyObjectListInfo(param);

		if(policyTargetList.size()>0){
			for(PolicyObject row : policyTargetList){
				EquipAccount equipRow = equipAccountService.selectEquipAccountByRecid(row.getReferencedRecid());
//				CmEquipInfo equipInfo = equipInfoService.selectCmEquipInfoByRecid(equipRow.getEquipRecid());
//				equipRow.setEquipNm(equipInfo.getEquipNm()); 
				if(equipRow != null){
					resultList.add(equipRow);
				}
			}
			
			if(param.containsKey("sort")){
				SortOrder sortOrder = SortOrder.getSort(param.get("dir"));
				
				if(param.get("sort").equalsIgnoreCase("equipNm")){
					Collections.sort(resultList, ComparatorFactory.getEquipAccountAssocEquipComparator(sortOrder));
				}
				else if(param.get("sort").equalsIgnoreCase("account")){
					Collections.sort(resultList, ComparatorFactory.getEquipAccountNameComparator(sortOrder));
				}
			}
			
		}
		
		return resultList;
	}
}
