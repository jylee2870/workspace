package sga.sol.ac.core.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sga.sol.ac.core.entity.user.UserMoveRecids;
import sga.sol.ac.core.entity.user.UserGroupInfo;
import sga.sol.ac.core.entity.user.UserGroupRelation;

import sga.sol.ac.core.dao.user.UserGroupInfoDao;

/**
 * 사용자 그룹 서비스
 * 
 * @author		: swcho
 * @date		: 2014. 9. 29.
 */
@Service
public class UserGroupInfoService{
	@Autowired
	private UserGroupInfoDao userGroupInfoDao;
	
	public List<UserGroupInfo> selectUserGroupListAll() {
		return userGroupInfoDao.selectUserGroupListAll();
	}
	
	/**
	 * 레코드ID로 그룹 정보 조회
	 * 
	 * @param recid 사용자 그룹 레코드ID
	 * @return
	 */
	public UserGroupInfo getUserGroupInfoByRecid(int recid) {
		return userGroupInfoDao.getUserGroupInfoByRecid(recid);
	}
	
	/**
	 * 그룹 경로로 그룹 정보 조회
	 * 
	 * @param groupPath 사용자 그룹 경로
	 * @return
	 */
	public UserGroupInfo getUserGroupInfoByGroupPath(String groupPath) {
		return userGroupInfoDao.getUserGroupInfoByGroupPath(groupPath);
	}
	
	/**
	 *  사용자 레코드ID로 그룹 정보 조회
	 *  
	 * @param userRecid 사용자 레코드ID
	 * @return
	 */
	public UserGroupInfo getUserGroupInfoByUserRecid(int userRecid) {
		return userGroupInfoDao.getUserGroupInfoByUserRecid(userRecid);
	}
	
	/**
	 * 사용자 ID로 그룹 정보 조회
	 * 
	 * @param userId 사용자 ID
	 * @return
	 */
	public UserGroupInfo getUserGroupInfoByUserId(String userId) {
		return userGroupInfoDao.getUserGroupInfoByUserId(userId);
	}
	
	/**
	 * 지정한 사용자 그룹의 하위 사용자 그룹 목록을 조회
	 * <p>
	 * parentGroupId 레코드 객체 제외
	 * </p>
	 * 
	 * @param parentGroupId 사용자 그룹 레코드ID
	 * @return UserGroupInfo 객체 목록
	 */
	public List<UserGroupInfo> selectChildUserGroupList(int parentGroupId) {
		return userGroupInfoDao.selectChildUserGroupList(parentGroupId);
	}
	
	/**
	 * 지정한 사용자 그룹과 동일 레벨의 장비 그룹 목록을 조회
	 * <p>
	 * recid 레코드 객체 포함
	 * </p>
	 * 
	 * @param recid 사용자그룹 레코드ID
	 * @return UserGroupInfo 객체 목록
	 * @author: surfree
	 * @since:	2016. 6. 21.
	 */
	public List<UserGroupInfo> selectSiblingUserGroupList(int recid) {
		return userGroupInfoDao.selectSiblingUserGroupList(recid);
	}
	
	/**
	 * 지정한 그룹을 포함한 모든 상위 사용자 그룹 조회
	 * 
	 * @param recid 사용자그룹 레코드 ID
	 * @return UserGroupInfo 객체 목록
	 * @author: surfree
	 * @since:	2016. 6. 21.
	 */
	public List<UserGroupInfo> selectAllParentUserGroupList(int recid) {
		return userGroupInfoDao.selectAllParentUserGroupList(recid);
	}
	
	/**
	 * 지정한 그룹을 포함한 모든 하위 사용자 그룹 조회
	 * 
	 * @param recid 사용자그룹 레코드 ID
	 * @return UserGroupInfo 객체 목록
	 * @author: surfree
	 * @since:	2016. 6. 21.
	 */
	public List<UserGroupInfo> selectAllChildUserGroupList(int recid) {
		return userGroupInfoDao.selectAllChildUserGroupList(recid);
	}
	
	/**
	 * 자신을 포함한 하위 그룹 목록을 재귀적으로 조회
	 * 
	 * @param groupRecid 그룹 레코드 ID
	 * @param level 사용안함
	 * @return
	 */
	@Deprecated
	public List<UserGroupInfo> selectAllChildUserGroupListByRecid(int groupRecid, int level) {
		return userGroupInfoDao.selectAllChildUserGroupListByRecid(groupRecid, level);
	}
	
	/**
	 * 자신을 포함한 하위 그룹 목록을 재귀적으로 조회(사용자 레벨로 필터링)
	 * 
	 * @param groupRecid 그룹 레코드 ID
	 * @param level 사용자 레벨
	 * @return
	 */
	@Deprecated
	public List<UserGroupInfo> selectAllChildUserGroupListByRecidNUserLevel(int groupRecid, int level) {
		return userGroupInfoDao.selectAllChildUserGroupListByRecidNUserLevel(groupRecid, level);
	}	
	
	/**
	 * 사용자 그룹 추가
	 * 
	 * @param userGroupInfo
	 * @return
	 */
	public int insertUserGroupInfo(UserGroupInfo userGroupInfo){
		return userGroupInfoDao.insertUserGroupInfo(userGroupInfo);
	}
	
	/**
	 * 사용자 그룹 정보 수정
	 * 
	 * @param userGroupInfo
	 * @return
	 */
	public int updateUserGroupInfoByRecid(UserGroupInfo userGroupInfo){
		return userGroupInfoDao.updateUserGroupInfoByRecid(userGroupInfo);
	}
	
	/**
	 * 사용자 그룹 정보 수정 및 그룹 경로 변경
	 * 
	 * @param userGroupInfo
	 * @param oldGroupPath
	 * @param newGroupPath
	 * @return
	 */
	@Transactional
	public int updateUserGroupName(UserGroupInfo userGroupInfo, String oldGroupPath, String newGroupPath) {
		userGroupInfoDao.updateUserGroupInfoByGroupPath(userGroupInfo.getRecid(), oldGroupPath, newGroupPath);
		return userGroupInfoDao.updateUserGroupInfoByRecid(userGroupInfo);
	}
	
	/**
	 * 그룹 레코드ID로 사용자 그룹 삭제
	 * @param recid
	 * @return
	 */
	public int deleteUserGroupInfoByRecid(int recid) {
		return userGroupInfoDao.deleteUserGroupInfoByRecid(recid);
	}
	
	/**
	 * 그룹 레코드 ID로 연관된 그룹 Relation 획득
	 * 
	 * @param groupRecid
	 * @return
	 */
	public List<UserGroupRelation> selectUserGroupRelationByGroupRecid(int groupRecid) {
		return userGroupInfoDao.selectUserGroupRelationByGroupRecid(groupRecid);
	}
	
	public List<String> selectUserIdListByGroupRecid(int groupRecid) {
		return userGroupInfoDao.selectUserIdListByGroupRecid(groupRecid);
	}
	
	@Transactional
	public int updateUserGroupRelation(UserMoveRecids userMoveRecids){
		int resultCnt = 0;
		
		if(userMoveRecids!=null){
			int groupRecid = userMoveRecids.getGroupRecid();
			List<Integer> userRecids = userMoveRecids.getUserRecids();
			String updateUser = userMoveRecids.getUpdateUser();
			String updateIp = userMoveRecids.getUpdateIp();
			
			if(userRecids.size()>0){
				for(int userRecid : userRecids){
					UserGroupRelation userGroupRelation = new UserGroupRelation();
					userGroupRelation.setGroupRecid(groupRecid);
					userGroupRelation.setUserRecid(userRecid);
					userGroupRelation.setUpdateIp(updateIp);
					userGroupRelation.setUpdateUser(updateUser);
					userGroupInfoDao.updateUserGroupRelation(userGroupRelation);
				}
				
				resultCnt = 1;
			}
		}
		return resultCnt;
	}
	
	public int moveUserGroupToParentGroup(Map params){
		int resultCnt = 0;
		
		
		int parentGroupRecid =Integer.parseInt((String)params.get("parentGroupRecid"));
		
		String groupPath = "";
		UserGroupInfo updateGroupData = getUserGroupInfoByRecid(Integer.parseInt((String)params.get("groupRecid")));
		
		getRecursiveGroupPathData(parentGroupRecid,groupPath, updateGroupData);
		
		if(parentGroupRecid == 0){
			updateGroupData.setGroupPath("/"+updateGroupData.getGroupName());
		}else{
			updateGroupData.setGroupPath(updateGroupData.getGroupPath()+"/"+updateGroupData.getGroupName());
		}
		
		updateGroupData.setParentGroupRecid(parentGroupRecid);
		updateGroupData.setUpdateUser((String)params.get("updateUser"));
		updateGroupData.setUpdateIp((String)params.get("updateIp"));
		
		int recidCnt = userGroupInfoDao.updateUserGroupInfoByRecidForMoveGroup(updateGroupData);
		
		if(recidCnt>0){
			updateRecursiveChildGroupPath(updateGroupData, updateGroupData.getRecid());
			resultCnt = 1;
		}
		
		return resultCnt;
	}
	
	public void getRecursiveGroupPathData(int parentGroupRecid, String groupPath, UserGroupInfo resultItem){
		
		if(parentGroupRecid != 0){
			UserGroupInfo userGroupInfo = getUserGroupInfoByRecid(parentGroupRecid);
			groupPath = "/" + userGroupInfo.getGroupName() + groupPath;
			resultItem.setGroupPath(groupPath);
			getRecursiveGroupPathData(userGroupInfo.getParentGroupRecid(), groupPath, resultItem);
		}
	}
	
	public void updateRecursiveChildGroupPath(UserGroupInfo resultItem, int groupRecid){
		List<UserGroupInfo> childList = selectChildUserGroupList(groupRecid);
		
		if(childList.size()>0){
			for(UserGroupInfo childRowData : childList){
				childRowData.setGroupPath(resultItem.getGroupPath()+"/"+childRowData.getGroupName());
				childRowData.setUpdateIp(resultItem.getUpdateIp());
				childRowData.setUpdateUser(resultItem.getUpdateUser());
				
				userGroupInfoDao.updateUserGroupInfoByRecid(childRowData);
				
				updateRecursiveChildGroupPath(childRowData, childRowData.getRecid());
			}
		}
	}
	
	/**
	 * 새로운 그룹 경로 반환
	 * 지정한 그룹 레코드ID 하위에 그룹명으로 된 그룹 경로 반환
	 * 
	 * @param recid 그룹 레코드ID
	 * @param groupName 그룹명
	 * @return String 새로운 그룹 경로
	 */
	public String getNewGroupPath(int recid, String groupName) {
		String path = "";
		
		if(recid == 0) {
			path = "/" + groupName;
		}
		else {
			UserGroupInfo group = getUserGroupInfoByRecid(recid);
			if(group != null) {
				path = group.getGroupPath() + "/" + groupName;
			}
		}
		return path;
	}
	
	/**
	 * 현재 그룹 경로 반환
	 * 
	 * @param recid 그룹 레코드ID
	 * @return String 현재 그룹 경로
	 */
	public String getCurrentGroupPath(int recid) {
		String path = "";
		
		if(recid == 0) {
			path = "/";
		}
		else {
			UserGroupInfo group = getUserGroupInfoByRecid(recid);
			if(group != null) {
				path = group.getGroupPath();
			}
		}
		return path;
	}
	
	public List<Integer> collectionToIntegerList(List<UserGroupInfo> colls) {
		List<Integer> result = new ArrayList<Integer>(colls.size());
		
		for(UserGroupInfo user: colls) {
			result.add(user.getRecid());
		}
		
		return result;
	}
}
