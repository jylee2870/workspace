package sga.sol.ac.core.dao.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.user.UserGroupInfo;
import sga.sol.ac.core.entity.user.UserGroupRelation;
import sga.sol.ac.core.listener.annotation.DeleteUser;
import sga.sol.ac.core.listener.annotation.DeleteUserByLevel;

@Repository
public class UserGroupInfoDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<UserGroupInfo> selectUserGroupListAll() {
		return sqlSessionTemplate.selectList("selectUserGroupListAll");
	}
	
	/**
	 * 그룹 레코드ID로 사용자 그룹 정보 조회
	 * 
	 * @param recid 사용자 그룹 레코드ID
	 * @return
	 */
	public UserGroupInfo getUserGroupInfoByRecid(int recid) {
		return sqlSessionTemplate.selectOne("getUserGroupInfoByRecid", recid);
	}
	
	/**
	 * 사용자 그룹 경로로 사용자 그룹 정보 조회
	 * 
	 * @param groupPath 사용자 그룹 경로
	 * @return
	 */
	public UserGroupInfo getUserGroupInfoByGroupPath(String groupPath) {
		return sqlSessionTemplate.selectOne("getUserGroupInfoByGroupPath", groupPath);
	}
	
	/**
	 * 사용자 레코드ID로 사용자 그룹 정보 조회
	 * 
	 * @param userRecid 사용자 레코드ID
	 * @return
	 */
	public UserGroupInfo getUserGroupInfoByUserRecid(int userRecid) {
		return sqlSessionTemplate.selectOne("getUserGroupInfoByUserRecid", userRecid);
	}
	
	/**
	 * 사용자 ID로 사용자 그룹 정보 조회
	 * 
	 * @param userId 사용자 ID
	 * @return
	 */
	public UserGroupInfo getUserGroupInfoByUserId(String userId) {
		return sqlSessionTemplate.selectOne("getUserGroupInfoByUserId", userId);
	}
	
	/**
	 * 그룹 레코드 ID로 한 단계 아래 그룹 목록 조회
	 * 
	 * @param parentGroupId 그룹 레코드ID
	 * @return UserGroupInfo 객체 목록
	 */
	public List<UserGroupInfo> selectChildUserGroupList(int parentGroupId) {
		return sqlSessionTemplate.selectList("selectChildUserGroupList", parentGroupId);
	}
	
	public List<UserGroupInfo> selectSiblingUserGroupList(int recid) {
		return sqlSessionTemplate.selectList("selectSiblingUserGroupList", recid);
	}
	
	public List<UserGroupInfo> selectAllParentUserGroupList(int recid) {
		return sqlSessionTemplate.selectList("selectAllParentUserGroupList", recid);
	}
	
	public List<UserGroupInfo> selectAllChildUserGroupList(int recid) {
		return sqlSessionTemplate.selectList("selectAllChildUserGroupList", recid);
	}
	
	/**
	 * 그룹 레코드 ID로 자신을 포함한 하위 그룹을 재귀적으로 조회
	 * 
	 * @param groupRecid
	 * @param level
	 * @return
	 */
	@Deprecated
	public List<UserGroupInfo> selectAllChildUserGroupListByRecid(int groupRecid, int level) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("userLevelCd", level);
		map.put("groupRecid", groupRecid);
		
		return sqlSessionTemplate.selectList("selectAllChildUserGroupListByRecid", map);
	}
	
	@Deprecated
	public List<UserGroupInfo> selectAllChildUserGroupListByRecidNUserLevel(int groupRecid, int level) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("userLevelCd", level);
		map.put("groupRecid", groupRecid);
		
		return sqlSessionTemplate.selectList("selectAllChildUserGroupListByRecidNUserLevel", map);
	}
	
	public int insertUserGroupInfo(UserGroupInfo userGroupInfo) {
		return sqlSessionTemplate.insert("insertUserGroupInfo",userGroupInfo);
	}
	
	public int updateUserGroupInfoByRecid(UserGroupInfo userGroupInfo) {
		return sqlSessionTemplate.update("updateUserGroupInfoByRecid", userGroupInfo);
	}
	
	public int updateUserGroupInfoByGroupPath(int groupRecid, String oldGroupPath, String newGroupPath) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("recid", groupRecid);
		map.put("oldGroupPath", oldGroupPath);
		map.put("newGroupPath", newGroupPath);
		
		return sqlSessionTemplate.update("updateUserGroupInfoByGroupPath", map);
	}
	
	/**
	 * 그룹 이동시 parentRecid를 update를 쳐야하기 때문에 만듬
	 * @param userGroupInfo
	 * @author LeeJungYoung
	 * @date 2015.09.16
	 * @return
	 */
	public int updateUserGroupInfoByRecidForMoveGroup(UserGroupInfo userGroupInfo) {
		return sqlSessionTemplate.update("updateUserGroupInfoByRecidForMoveGroup", userGroupInfo);
	}
	
	public int deleteUserGroupInfoByRecid(int recid) {
		return sqlSessionTemplate.delete("deleteUserGroupInfoByRecid",recid);
	}
	
	public List<UserGroupRelation> selectUserGroupRelationByGroupRecid(int groupRecid) {
		return sqlSessionTemplate.selectList("selectUserGroupRelationByGroupRecid", groupRecid);
	}
	
	public List<String> selectUserIdListByGroupRecid(int groupRecid) {
		return sqlSessionTemplate.selectList("selectUserIdListByGroupRecid", groupRecid);
	}
	
	public int inserUserGroupRelation(UserGroupRelation userGroupRelation) {
		return sqlSessionTemplate.insert("inserUserGroupRelation", userGroupRelation);
	}
	
	// 이정용 추가
	public int updateUserGroupRelation(UserGroupRelation userGroupRelation) {
		return sqlSessionTemplate.update("updateUserGroupRelation", userGroupRelation);
	}
	
	@DeleteUser
	public int deleteUserGroupRelationByRecids(List<Integer> userRecids) {
		return sqlSessionTemplate.delete("deleteUserGroupRelationByRecids", userRecids);
	}
	
	@DeleteUserByLevel
	public int deleteAllUserGroupRelationByUserLevel(int userLevelCd) {
		return sqlSessionTemplate.delete("deleteAllUserGroupRelationByUserLevel", userLevelCd);
	}
}
