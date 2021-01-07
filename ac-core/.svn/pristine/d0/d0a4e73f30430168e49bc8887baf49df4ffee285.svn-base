package sga.sol.ac.core.service.equip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import sga.sol.ac.core.dao.equip.EquipManagementDao;
import sga.sol.ac.core.entity.equip.EquipGroupInfo;
import sga.sol.ac.core.entity.equip.EquipInfo;
import sga.sol.ac.core.entity.policy.PlUserEquip;
import sga.sol.ac.core.entity.policy.PlUserEquipForUser;
import sga.sol.ac.core.entity.user.UserInfo;
import sga.sol.ac.core.listener.annotation.DeleteUser;
import sga.sol.ac.core.listener.annotation.DeleteUserByLevel;
import sga.sol.ac.core.service.user.UserInfoService;
import sga.sol.ac.core.util.tools.AcCollectionUtils;

/**
 * 관리자 장비 관리 서비스
 
 * @author	swcho
 * @since	2014. 9. 29.
 * <ul>
 * <li>2016. 5. 24 swcho 사용하지 않는 메서드 삭제, 타 패키지 dao를 service로 교체</li>
 * <li>2016. 6. 13 surfree ManagerAuthorityService 통합. 관리 권한 서비스 - 사용자 관리, 장비 관리 등...</li>
 * <li>2016. 6. 21 surfree EquipTreeService에서 제공하는 일부 메소드 통합</li>
 * </ul>
 */
@Service
public class EquipManagementService {
	@Autowired
	EquipManagementDao equipManagementDao;
	
	@Autowired
	EquipInfoService equipInfoService;
	
	@Autowired
	EquipGroupInfoService equipGroupService;
	
	@Autowired
	UserInfoService userInfoService;
	
	/*
	 * 관리자 장비 관리 정책 목록 개수
	 */
	public int selectEquipAclForManagerListCount(PlUserEquipForUser info){
		return equipManagementDao.selectEquipAclForManagerListCount(info);
	}
	
	/*
	 * 관리자 장비 관리 정책 목록 조회
	 */
	public List<PlUserEquipForUser> selectEquipAclForManagerList(PlUserEquipForUser info){
		return equipManagementDao.selectEquipAclForManagerList(info);
	}
	
	/*
	 * 관리자 장비 관리 정책 목록 다운로드
	 */
	public List<PlUserEquipForUser> selectEquipAclForManagerListDownload(PlUserEquipForUser info){
		return equipManagementDao.selectEquipAclForManagerListDownload(info);
	}
	
	/**
	 * 관리자 관리 대상 장비 그룹 개수
	 * 
	 * @author LeeJungYoung
	 * @param param : userRecid
	 * @return
	 */
	public int selectEquipGroupInfoForUserMgrPolicyCount(Map<String, String> param){
		return equipManagementDao.selectEquipGroupInfoForUserMgrPolicyCount(param);
	}

	/**
	 * 관리자 관리 대상 장비 그룹 목록
	 * 
	 * @author LeeJungYoung
	 * @param param : userRecid
	 * @return
	 */
	public List<EquipGroupInfo> selectEquipGroupInfoForUserMgrPolicy(Map<String, String> param){
		return equipManagementDao.selectEquipGroupInfoForUserMgrPolicy(param);
	}
	
	/**
	 * 관리자 장비 관리 정책 리스트 카운트(관리자에게 장비 관리 할수 있는 권한 부여)
	 * 
	 * @author LeeJungYoung
	 * @param param : userRecid
	 * @return
	 */
	public int selectEquipInfoForUserMgrPolicyCount(Map<String, String> param){
		return equipManagementDao.selectEquipInfoForUserMgrPolicyCount(param);
	}
	
	
	/**
	 * 관리자 장비 관리 정책 리스트 보기(관리자에게 장비 관리 할수 있는 권한 부여)
	 * 
	 * @author LeeJungYoung
	 * @param param : userRecid
	 * @return
	 */
	public List<EquipInfo> selectEquipInfoForUserMgrPolicy(Map<String, String> param){
		return equipManagementDao.selectEquipInfoForUserMgrPolicy(param);
	}
	
	/**
	 * 지정한 사용자가 지정한 장비의 관리 여부를 반환한다.
	 * 
	 * @param uid 사용자 레코드ID
	 * @param eid 장비 레코드ID
	 * @return boolean 관리 개체 여부 
	 * @author: swcho
	 * @date:	2016. 1. 7.
	 * <p>
	 * 2016.5.24. surfree service로 노출<br/>
	 * </p>
	 */
	public boolean checkAssignedEquipForUser(int uid, int eid) {
		List<Integer> equips = equipManagementDao.getAssignedEquipsForUser(uid);
		return equips.contains(eid);
	}
	
	/**
	 * 지정한 사용자가 지정한 장비 그룹의 관리 여부를 반환한다.
	 * 
	 * @param uid 사용자 레코드ID
	 * @param eid 장비그룹 레코드ID
	 * @return boolean 관리 개체 여부
	 * @author: swcho
	 * @date:	2016. 1. 7.
	 * <p>
	 * 2016.5.24. surfree service로 노출<br/>
	 * </p>
	 */
	public boolean checkAssignedEquipGroupForUser(int uid, int groupId) {
		List<Integer> equipGroups = equipManagementDao.getAssignedEquipGroupsForUser(uid);
		return equipGroups.contains(groupId);
	}
	
	/**
	 * 관리자에게 할당된 장비의 레코드 ID 목록 조회
	 * 
	 * @param uid 관리자 레코드 ID
	 * @return 관리할 수 있는 장비의 레코드ID 목록
	 * @author: surfree
	 * @since:	2016. 6. 21.
	 */
	public List<Integer> getAssignedEquipIdsForUser(int uid) {
		return equipManagementDao.getAssignedEquipsForUser(uid);
	}
	
	/**
	 * 관리자에게 할당된 장비의 장비 그룹 레코드ID 목록 조회
	 * 
	 * @param uid 관리자 레코드 ID
	 * @return 관리할 수 있는 장비의 장비 그룹 레코드ID 목록
	 * @author: surfree
	 * @since:	2016. 6. 21.
	 */
	public List<Integer> getEquipGroupsFromAssignedEquipsForUser(int uid) {
		return equipManagementDao.getEquipGroupsFromAssignedEquipsForUser(uid);
	}
	
	/**
	 * 관리자에게 할당된 장비 그룹의 레코드 ID 목록 조회
	 * 
	 * @param uid 관리자 레코드 ID
	 * @return 관리할 수 있는 장비 그룹의 레코드ID 목록
	 * @author: surfree
	 * @since:	2016. 6. 21.
	 */
	public List<Integer> getAssignedEquipGroupIdsForUser(int uid) {
		return equipManagementDao.getAssignedEquipGroupsForUser(uid);
	}
	
	/**
	 * 관리자에게 할당된 장비를 모두 알아낸다.
	 * 
	 * <pre>
	 * plUserEquip의 equipGrpId속성이 설정되면 해당 그룹에 속한 목록만 반환한다.
	 * </pre>
	 * @param plUserEquip 검색 조건
	 * <ul>
	 * <li>uid: 관리자 레코드ID</li>
	 * <li>userGrpId: 관리자 그룹 레코드ID</li>
	 * <li>equipGrpId(Option): 장비그룹으로 반환 조건을 한정할 경우 장비그룹 레코드ID</li>
	 * </ul>
	 * @return
	 * 	관리자 장비관리 권한에 할당된 모든 장비 레코드ID 목록 리스트
	 * @Date		: 2015. 6. 29. 
	 * @Author		: surfree
	 * @since
	 * <ul>
	 * <li>2015. 12 .9. surfree 오류 수정</li>
	 * <li>2016. 6. 21. surfree selectPlEquipList 호출로 변경. selectPlEquipListString 통합</li>
	 * </ul>
	 */
	public List<Integer> selectPlEquipList(PlUserEquip plUserEquip) {
		Set<Integer> equipReturn = new HashSet<Integer>();
		
		// 장비 그룹을 선택한 경우 전체 관리 장비그룹 목록에서 선택한 그룹의 장비만 조회한다.
		if( plUserEquip.getEquipGrpId() > 0) {
			List<Integer> permEquipGroupIds = this.selectPermittedEquipGroupList(plUserEquip.getUid(), plUserEquip.getUserGrpId());
			
			// 선택한 장비 그릅 및 하위 그룹 목록 획득
			List<EquipGroupInfo> selectEquipGroups = equipGroupService.selectAllChildEquipGroupList(plUserEquip.getEquipGrpId());
			List<Integer> selectGroupIds = equipGroupService.collectionToIntegerList(selectEquipGroups);
			
			List<Integer> resultGroupIds = new ArrayList<Integer>();
			
			// retainAll() 메소드 호출을 위한 리스트 복사
			resultGroupIds.addAll(selectGroupIds);
			
			// 선택한 장비 그룹에서 권한이 없는 항목 제거
			resultGroupIds.retainAll(permEquipGroupIds);
			
			List<EquipInfo> equipListInGroups = equipInfoService.selectEquipListByGroupList(resultGroupIds);
			
			// 이 리스트를 문자열로 만든다 equipSb에 저장
			if(equipListInGroups != null) {
				for(EquipInfo c: equipListInGroups) {
					equipReturn.add(c.getRecid());
				}
			}
			
			// 관리자 관리 장비 목록 추가
			List<Integer> permEquipIds = equipManagementDao.getAssignedEquipsForUser(plUserEquip.getUid());
			
			List<EquipInfo> selectedEquipList = equipInfoService.selectEquipListByGroupList(selectGroupIds);
			List<Integer> selectedEquipIds = new ArrayList<Integer>();
			for(EquipInfo equip: selectedEquipList) {
				selectedEquipIds.add(equip.getRecid());
			}
			
			permEquipIds.retainAll(selectedEquipIds);
			
			
			// 관리자 관리 장비 목록 추가
			plUserEquip.setInEquipGroupStrToken(StringUtils.collectionToCommaDelimitedString(selectGroupIds));
			
			List<PlUserEquipForUser> equipList1 = equipManagementDao.selectPlEquipList(plUserEquip);
			if(equipList1 != null) {
				// 가져온 equipList를 리스트 문자열로 만든다
				for(PlUserEquipForUser c:equipList1)
				{
					equipReturn.add(c.getEid());
				}
			}
		}
		else {
			// 장비 그룹을 선택하지 않은 경우 전체 관리 장비 그룹의 목록으로 장비를 조회한다.
			List<Integer> permEquipGroupIds = this.selectPermittedEquipGroupList(plUserEquip.getUid(), plUserEquip.getUserGrpId());
			
			// 1. 관리 장비 그룹 목록 추가(없다면 추가하지 않는다)
			if(permEquipGroupIds != null && permEquipGroupIds.size() > 0) {
				List<EquipInfo> equipList = equipInfoService.selectEquipListByGroupList(permEquipGroupIds);
				
				if(equipList != null) {
					for(EquipInfo c: equipList) {
						equipReturn.add(c.getRecid());
					}
				}
			}
			
			// 2. 관리 장비 목록 추가
			List<Integer> permEquipIds = equipManagementDao.getAssignedEquipsForUser(plUserEquip.getUid());
			if(null != permEquipIds) {
				equipReturn.addAll(permEquipIds);
			}
		}
		
		return new ArrayList<Integer>(equipReturn);
	}
	
	public List<Integer> selectPlEquipList1(PlUserEquip plUserEquip) {
		Set<Integer> permEquipIds = selectPermittedEquipList(plUserEquip.getUid(), plUserEquip.getUserGrpId());
		
		if( plUserEquip.getEquipGrpId() > 0) {
			List<EquipGroupInfo> selectEquipGroups = equipGroupService.selectAllChildEquipGroupList(plUserEquip.getEquipGrpId());
			List<Integer> selectGroupIds = equipGroupService.collectionToIntegerList(selectEquipGroups);
			
			List<EquipInfo> selectedEquips = equipInfoService.selectEquipListByGroupList(selectGroupIds);
			List<Integer> selectedEquipIds = new ArrayList<Integer>();
			for(EquipInfo equip: selectedEquips) {
				selectedEquipIds.add(equip.getRecid());
			}
			
			permEquipIds.retainAll(selectedEquipIds);
		}
		
		// 전체를 구하고
		// 선택된 그룹의 모든 장비를 구해서
		// 전체에서 선택된 그룹을 빼면
		
		return new ArrayList<Integer>(permEquipIds);
	}
	
	/**
	 * 관리자에게 할당된 장비를 모두 알아낸다.
	 * 
	 * @param uid		관리자 레코드ID
	 * @param groupId	관리자 그룹 레코드ID
	 * @return
	 * 	장비 레코드ID 목록 SET
	 * 
	 * @Date		: 2015-12-10
	 * @author		: surfree
	 */
	public Set<Integer> selectPermittedEquipList(int uid, int groupId) {
		Set<Integer> resultEquipSet = new HashSet<Integer>();
		
		// 1. 관리자가 관리할 수 있는 장비 그룹 목록 조회
		List<Integer> equipGroupIds = this.selectPermittedEquipGroupList(uid, groupId); 
		
		if(equipGroupIds != null && equipGroupIds.size() > 0){
			// 관리할 수 있는 모든 장비 그룹 목록 추가
			List<EquipInfo> equipList = equipInfoService.selectEquipListByGroupList(equipGroupIds);
			
			if(null != equipList) {
				for(EquipInfo c:equipList) {
					resultEquipSet.add(c.getRecid());
				}
			}
			
		}
		
		// 2. 관리자가 관리할 수 있는 장비 목록 조회
		List<Integer> equipList = equipManagementDao.getAssignedEquipsForUser(uid);
		if(null != equipList) {
			resultEquipSet.addAll(equipList);
		}
		
		return resultEquipSet;
	}
	
	/**
	 * 관리자가 관리할 수 있는 관리 장비그룹의 모든 목록을 조회한다.
	 * <p>
	 * 명시적으로 지정된 관리 장비 그룹과 그에 해당하는 모든 하위 그룹 목록도 얻는다.<br/>
	 * 원래는 사용자가 속한 그룹에 대한 장비 그룹도 얻을 수 있었으나 스키마 변경으로 인해 관리자 전용으로 변경됨
	 * </p>
	 * 
	 * @param uid 관리자 레코드ID
	 * @param grpId 관리자 사용자그룹 레코드ID(사용 안함)
	 * @return 장비그룹 레코드ID 목록
	 * @author: swcho
	 * @since:	2015. 6. 25.
	 * <ul>
	 * <li>2015. 6. 21 surfree EquipTreeService에서 Equip ManagementSerivce로 이동.<br/>
	 * 						반환값을 Equip 객체에서 Integer List로 변경</li>
	 * </ul>
	 */
	public List<Integer> selectPermittedEquipGroupList(int uid, int grpId) {
		List<Integer> resultList = new ArrayList<Integer>();
		
		Set<Integer> equipGroupSet = new HashSet<Integer>();
		
		// 관리자가 관리할 수 있는 장비 그룹 조회
		List<Integer> assignedEquipGroups = equipManagementDao.getAssignedEquipGroupsForUser(uid);
		
		// 하위 그룹 정보를 포함한다.
		for( int equipGroupId : assignedEquipGroups){
			List<EquipGroupInfo> childEquipGroups = equipGroupService.selectAllChildEquipGroupList(equipGroupId);
			List<Integer> childGroupIds = equipGroupService.collectionToIntegerList(childEquipGroups);
			
			equipGroupSet.addAll(childGroupIds);
		}
		
		// 중복 제거된 그룹 레코드ID를 정렬한다.
		resultList.addAll(equipGroupSet);
		Collections.sort(resultList);
		
		return resultList;
	}
	
	// 장비에 관리자를 지정 2015.7.3 swcho
	// 이미 할당되어 있으면 insert 하지 않도록 query 수정 2016.1.7 swcho
	public int insertPlUserEquipDataOfEquipManager(int[] userRecids, int[] equipRecids, String userId, String userIp){
		int iRet = 1;
		
		for( int userRecid : userRecids){
			for( int equipRecid : equipRecids){
				iRet = iRet & equipManagementDao.insertPlUserEquipDataOfEquipManager(userRecid, equipRecid, userId, userIp);
			}
		}
		 
		return iRet;
	}
	
	// 장비그룹에 관리자를 지정 2015.7.3 swcho
	// 이미 할당되어 있으면 insert 하지 않도록 query 수정 2016.1.7 swcho
	public int insertPlUserEquipDataOfEquipGroupManager(int[] userRecids, int[] equipGroupRecids, String userId, String userIp){
		int iRet = 1;
		
		for( int userRecid : userRecids){
			for( int equipGroupRecid : equipGroupRecids){
				iRet = iRet & equipManagementDao.insertPlUserEquipDataOfEquipGroupManager(userRecid, equipGroupRecid, userId, userIp);
			}
		}
		
		return iRet;
	}
	
	/**
	 * @author LeeJungYoung
	 * @param userRecid 관리자 recid
	 * @description 관리자가 삭제시 관리자와 매핑되어있는 관리자 장비 관리정책을 지운다
	 * @History 2017.5.26 swcho 사용자 삭제시 관리하고 있는 
	 * 							장비와 장비 그룹이 먼저 삭제(해제)되어야 릴레이션 오류가 없다  
	 */
	@DeleteUser
	public void cancellationBeforeDeleteManager(int userRecid){
		equipManagementDao.cancellationEquipBeforeDeleteManager(userRecid);
		equipManagementDao.cancellationEquipGroupBeforeDeleteManager(userRecid);
	}
	
	/*
	 * 2017.5.26 swcho
	 * 사용자 등급(레벨)로 삭제할 때, 사용자를 등급으로 검색한 후, 
	 * 각 사용자이 관리하는 장비 또는 장비 그룹을 삭제(해제)한다.  
	 */
	@DeleteUserByLevel
	public void cancellationBeforeDeleteManagerByLevel(int userLevelCd){
		UserInfo param = new UserInfo();
		param.setUserLevelCd(userLevelCd);
		List<UserInfo> tmpList = userInfoService.getUserInfoExByParam(param); 
		
		List<Integer> userRecids = AcCollectionUtils.makeProperyListFromCollectionObject(tmpList, "userRecid");
		
		for ( Integer userRecid : userRecids){
			equipManagementDao.cancellationEquipBeforeDeleteManager(userRecid);
			equipManagementDao.cancellationEquipGroupBeforeDeleteManager(userRecid);
		}
	}
	
	/**
	 * 삭제된 장비에 대한 관리 지정을 해제(삭제) 한다
	 */
	public void cancellationOfDeletedEquip(int equipRecid){
		equipManagementDao.cancellationOfDeletedEquip(equipRecid);
	}
	
	/**
	 * 장비 관리 지정을 일괄적으로 삭제
	 * 2016.3.2 swcho
	 */
	public void cancellationOfDeletedEquip(){
		equipManagementDao.cancellationOfDeletedEquip();
	}
	
	/**
	 * 삭제된 장비 그룹에 대한 관리 지정을 해제(삭제) 한다
	 */
	public void cancellationOfDeletedEquipGroup(int equipGroupRecid){
		equipManagementDao.cancellationOfDeletedEquipGroup(equipGroupRecid);
	}
	
	// 관리자장비접근정책 삭제
	@Transactional
	public int deletePlUserEquipByRecid(int userRecid) {
		int iRet = 0;
		this.cancellationBeforeDeleteManager(userRecid);
		
		iRet = 1;
		
		return iRet;
	}
	
	// 관리자장비접근정책 삭제 (사용자 수정)
	public int deletePlUserEquipOfEquipManagerByuserRecidNAssignRecid(int userRecid, int[] assignRecids){
		int resultCnt = 0;
		
		for(int i=0; i<assignRecids.length; i++){
			Map<String, Integer> param = new HashMap<String, Integer>();
			param.put("userRecid", userRecid);
			param.put("assignRecid", assignRecids[i]);
			resultCnt = resultCnt+ equipManagementDao.deletePlUserEquipOfEquipManagerByuserRecidNAssignRecid(param);
		}
		
		return resultCnt;
	}
	
	// 관리자장비접근정책 삭제 (사용자 수정)
	public int deletePlUserEquipGroupOfEquipManagerByuserRecidNAssignRecid(int userRecid, int[] assignRecids){
		int resultCnt = 0;
		for(int i=0; i<assignRecids.length; i++){
			Map<String, Integer> param = new HashMap<String, Integer>();
			param.put("userRecid", userRecid);
			param.put("assignRecid", assignRecids[i]);
			resultCnt = resultCnt+ equipManagementDao.deletePlUserEquipGroupOfEquipManagerByuserRecidNAssignRecid(param);
		}
		
		return resultCnt;
	}
	
	/**
	 * @author hwbaek
	 * @date 2014. 10. 02.
	 * TODO : 관리자 장비 관리 정책 모두 삭제
	 */
	public int deletePlManagerEquipAll() {
		return equipManagementDao.deletePlManagerEquipAll();
	}
	
	/*
	 * 관리자 장비 그룹 관리 모두 삭제
	 * 2016.1.26 swcho
	 */
	public void deletePlManagerEquipGroupAll() {
		equipManagementDao.deletePlManagerEquipGroupAll();
	}
}
