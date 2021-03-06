package sga.sol.ac.core.service.policy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import sga.sol.ac.core.entity.log.LmManagementLog;
import sga.sol.ac.core.entity.policy.PolicyObject;
import sga.sol.ac.core.entity.policy.UserAuthPolicyList;
import sga.sol.ac.core.entity.user.UserGroupInfo;
import sga.sol.ac.core.entity.user.UserInfo;
import sga.sol.ac.core.service.log.LmManagementLogService;
import sga.sol.ac.core.service.user.UserGroupInfoService;
import sga.sol.ac.core.dao.policy.UserAuthPolicyDao;

@Service
public class UserAuthPolicyService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserAuthPolicyDao userAuthPolicyDao;
	
	@Autowired
	UserGroupInfoService userGroupService;
	
	@Autowired
	LmManagementLogService logService;
	
	@Autowired
	PolicyService policyService;
	
	/*******************************************************************************
	 * @Description : 사용자/장비 2차 인증 정책 추가
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.21
	 * @return List<UserAuthPolicyList>
	 */
	public Map<String, Object> insertUserAuthPolicy(Map<String, Object> param, LmManagementLog logInfo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<UserAuthPolicyList> list = new ArrayList<UserAuthPolicyList>();
		// 사용자인증정책 추가시 policyRecid가 파라미터에 포함된다
		// 장비와 정책은 1:1 매핑으로 장비에 따른 정책은 하나이기 때문에 중복 검사를 할때 policyRecid로 검사를 하게 되면 
		// 중복된 장비를 찾을 수 없다. 
		Map<String, Object> checkParam = new HashMap<String, Object>();
		
		checkParam.putAll(param);
		if(checkParam.containsKey("policyRecid")){
			checkParam.remove("policyRecid");
		}
		list = userAuthPolicyDao.selectUserAuthPolicyInfoForSuperManager(checkParam);
		
		boolean checkFlag = false;
		if(list.size() == 0){
			param.put("objectRecid", "");
			
			PolicyObject policyObject = userAuthPolicyDao.checkPolicyObjectByPolicyList(param);
			if(policyObject != null){
				param.put("objectRecid", String.valueOf(policyObject.getRecid()));
			}else{
				PolicyObject insertObj = new PolicyObject();
				insertObj.setPolicyRecid(Integer.parseInt((String)param.get("policyRecid"))
						);
				insertObj.setType(Integer.parseInt((String)param.get("objectType")));
				insertObj.setReferencedRecid(Integer.parseInt((String)param.get("objectReferencedRecid")));
				int policyObjectRecid = policyService.insertPolicyObjectReturning(insertObj, logInfo);
				param.put("objectRecid", policyObjectRecid);
			}
			
			if(userAuthPolicyDao.insertUserAuthPolicy(param)>0){
				checkFlag = true;
			}
			
			logInfo.setMessage("사용자/장비 2차 인증 정책 추가");
			logService.insertLog(logInfo);
		}else{
			checkFlag = false;
			resultMap.put("list", list);
			logInfo.setMessage("사용자/장비 2차 인증 정책 추가 실패");
			logService.insertLog(logInfo);
		}
		
		resultMap.put("checkFlag", checkFlag);
		
		return resultMap;
	}
	
	/**
	 * 사용자 장비 접근 정책 추가
	 * (중복 체크 기능 없음. 데이터 가져오기 용도 외에 사용 금지)
	 * 
	 * @param param
	 * @return
	 * @author: LeeJungYoung
	 * @date:	2015.12.21
	 */
	public int insertUserAuthPolicy(Map<String, Object> param) {
		return userAuthPolicyDao.insertUserAuthPolicy(param);
	}
	
	/*******************************************************************************
	 * @Description : 사용자/장비 2차 인증 정책 리스트 검색 (최고관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public List<UserAuthPolicyList> selectUserAuthPolicyListForSuperManager(Map<String, Object> param) {
		return userAuthPolicyDao.selectUserAuthPolicyListForSuperManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 사용자/장비 2차 인증 정책 엑셀익스포트
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public List<UserAuthPolicyList> selectUserAuthPolicyInfoExportForSuperManager(Map<String, Object> param) {
		return userAuthPolicyDao.selectUserAuthPolicyInfoExportForSuperManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 사용자/장비 2차 인증 정책 리스트 총갯수 (최고관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public int selectUserAuthPolicyListCntForSuperManager(Map<String, Object> param) {
		return userAuthPolicyDao.selectUserAuthPolicyListCntForSuperManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 사용자/장비 2차 인증 정책 리스트 검색 (관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public List<UserAuthPolicyList> selectUserAuthPolicyListForManager(Map<String, Object> param) {
		UserInfo currentUser = (UserInfo)param.get("userData");
		
		List<UserGroupInfo> userGroups = userGroupService.selectAllChildUserGroupList(currentUser.getUserGroup().getRecid());
		List<Integer> userGroupIds = userGroupService.collectionToIntegerList(userGroups);
		
		if(userGroupIds != null && userGroupIds.size() > 0) {
			param.put("userGroupRecids", StringUtils.collectionToCommaDelimitedString(userGroupIds)); 
		}
		else {
			param.put("userGroupRecids", "-1");
		}
		
		return userAuthPolicyDao.selectUserAuthPolicyListForManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 사용자/장비 2차 인증 정책 리스트 총갯수 (관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public int selectUserAuthPolicyListCntForManager(Map<String, Object> param) {
		
		UserInfo currentUser = (UserInfo)param.get("userData");
		
		List<UserGroupInfo> userGroups = userGroupService.selectAllChildUserGroupList(currentUser.getUserGroup().getRecid());
		List<Integer> userGroupIds = userGroupService.collectionToIntegerList(userGroups);
		
		if(userGroupIds != null && userGroupIds.size() > 0) {
			param.put("userGroupRecids", StringUtils.collectionToCommaDelimitedString(userGroupIds)); 
		}
		else {
			param.put("userGroupRecids", "-1");
		}
		
		return userAuthPolicyDao.selectUserAuthPolicyListCntForManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 사용자/장비 2차 인증 정책 추가 전 체크 
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.21
	 * @return List<Map>
	 */
	public Map<String, Object> checkBeforeAddUserAuthPolicy(Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<UserAuthPolicyList> list = new ArrayList<UserAuthPolicyList>();
		if(param.containsKey("policyRecid")){
			param.remove("policyRecid");
			//장비에 따른 정책은 1:1 매핑이기 때문에 policyRecid 까지 조회를 하게 되면 장비에 따른 정책이 나오질 않는다
		}
		list = userAuthPolicyDao.selectUserAuthPolicyInfoForSuperManager(param);
		
		boolean checkFlag = true;
		
		if(list.size()>0){
			checkFlag = false;
		}
		resultMap.put("checkFlag", checkFlag);
		resultMap.put("list", list);
		
		return resultMap; 
	}

	/**
	 * 사용자 장비 접근 정책을 추가 하기전에 policy_object 테이블에 추가 사용자가 존재 하는지 판단하고
	 * 해당 정책에 엮어 있지 않으면 policy_object, policy_target 에 추가, 엮어 있으면 policy_target에만 추가
	 * TODO: core module 로 이동이 필요한지 확인 필요 surfree
	 * 
	 * @param param
	 * @return sga.sol.ac.core.entity.policy.PolicyObject
	 * @author: LeeJungYoung
	 * @date:	2015.12.21
	 */
	public PolicyObject checkPolicyObjectByPolicyList(Map<String, Object> param) {
		return userAuthPolicyDao.checkPolicyObjectByPolicyList(param);
	}
	
	public List<UserAuthPolicyList> selectUserAuthPolicyInfoForSuperManager(Map<String, Object> param){
		return userAuthPolicyDao.selectUserAuthPolicyInfoForSuperManager(param);
	}
	
	/*******************************************************************************
	 * @Description : 사용자/장비 2차 인증 정책(policy_target) 삭제
	 * @Description : 해당 사용자 or 사용자 그룹에 매핑된 장비그룹 or 장비 or 장비계정 (policy_target)을 삭제
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.23
	 * @return List<UserAuthPolicyList>
	 */
	public Map<String, Object> deletePolicyTargetForUserAuthPolicy(Map<String, Object> param, LmManagementLog logInfo) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int resultCnt = userAuthPolicyDao.deletePolicyTargetForUserAuthPolicy(param);
		
		resultMap.put("resultCnt", resultCnt);
		resultMap.put("success", false);
		if(resultCnt>0){
			resultMap.put("success", true);
			
			logInfo.setMessage("대상 장비에 따른 사용자/장비 2차 인증 정책 삭제 완료");
			logService.insertLog(logInfo);
			
			Map<String, Object> checkParam = new HashMap<String, Object>();
			checkParam.putAll(param);
			
			if(checkParam.containsKey("targetType")){
				checkParam.remove("targetType");
			}
			
			if(checkParam.containsKey("targetReferencedRecids")){
				checkParam.remove("targetReferencedRecids");
			}
			
			logger.info("check >>>> checkParam  >> "+checkParam.toString());
			List<UserAuthPolicyList> list = selectUserAuthPolicyInfoForSuperManager(checkParam);
			logger.info("check >>>> list.size()  >> "+list.size());
			
			if(list.size() == 0){
				if(userAuthPolicyDao.deletePolicyObjectForUserAuthPolicy(checkParam)>0){
					if(checkParam.containsKey("objectRecid")){
						checkParam.remove("objectRecid");
						if(selectUserAuthPolicyInfoForSuperManager(checkParam).size()==0){
							resultMap.put("removeObject", true);
						}else{
							resultMap.put("removeObject", false);
						}
					}
				};
			}
		}
		
		return resultMap;
		
	}
	
	/*******************************************************************************
	 * @Description : 해당 인증 대상 (policy_object, policy_target)를 삭제
	 * @Description : 해당 인증 대상에 엮어 있는 타겟이 남아 있어 지우고 싶을 경우 param에 deleteTargetFlag = 'Y'를 추가
	 * @Description : 해당 인증 대상에 관여된 대상 장비와 인증 대상을 모두 삭제
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.23
	 * @return List<UserAuthPolicyList>
	 */
	public int deletePolicyObjectNTargetForUserAuthPolicy(Map<String, Object> param, LmManagementLog logInfo) {
		
		int objectType = 0;
		if(param.containsKey("objectType")){
			objectType = Integer.parseInt((String)param.get("objectType"));
		}
		int objectReferencedRecid = 0;
		if(param.containsKey("objectReferencedRecid")){
			objectReferencedRecid = Integer.parseInt((String)param.get("objectReferencedRecid"));
		}
		
		logger.info("check >>>> param  >> "+param.toString());
		List<UserAuthPolicyList> list = selectUserAuthPolicyInfoForSuperManager(param);
		logger.info("check >>>> list.size()  >> "+list.size());
		
		int resultCnt = 0;
		if(list.size()>0){
			if(userAuthPolicyDao.deletePolicyTargetForUserAuthPolicy(param)>0){
				resultCnt = userAuthPolicyDao.deletePolicyObjectForUserAuthPolicy(param);
			}
		}
		
		if(resultCnt>0){
			logInfo.setMessage("사용자/장비 2차 인증 정책 삭제 성공\n"
					+ ((objectType==1) ? "사용자그룹" : "사용자") +" RECID : "+ objectReferencedRecid
					
			);  
					
			logService.insertLog(logInfo);
		}
		
		return resultCnt;
	}
	
	/*
	 * 사용자 장비 접근 2차이 인증 정책 일괄 삭제
	 * 엑셀임포트시 기존 정책을 모두 지우고 입력할 경우 사용한다
	 * 2016.1.26 swcho
	 */
	public void deleteAllEquip2FactPolicy(){
		userAuthPolicyDao.deleteAllEquip2FactPolicy();
	}
}
