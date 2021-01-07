package sga.sol.ac.core.dao.equip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.equip.EquipGroupInfo;
import sga.sol.ac.core.entity.equip.EquipInfo;
import sga.sol.ac.core.entity.policy.PlUserEquip;
import sga.sol.ac.core.entity.policy.PlUserEquipForUser;

/**
 * 관리자 장비 관리 권한 DAO
 * 
 * @author JannyWang
 * @since
 * <ul>
 * <li>2016. 6. 13. surfree: ManagerAuthorityDao와 통합(관리 권한 DAO - 사용자 관리, 장비 관리)</li>
 * </ul>
 */
@Repository
public class EquipManagementDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 관리자 장비 관리 정책 목록 개수
	public int selectEquipAclForManagerListCount(PlUserEquipForUser info) {
		return sqlSessionTemplate.selectOne("selectEquipAclForUserListCount",info);
	}
		
	// 관리자 장비 관리 정책 목록 조회
	public List<PlUserEquipForUser> selectEquipAclForManagerList(PlUserEquipForUser info) {
		return sqlSessionTemplate.selectList("selectEquipAclForUserList", info);
	}
		
	// 관리자 장비 관리 정책 목록 다운로드
	public List<PlUserEquipForUser> selectEquipAclForManagerListDownload(PlUserEquipForUser info) {
		return sqlSessionTemplate.selectList("selectEquipAclForManagerListDownload", info);
	}
	
	/**
	 * 관리자가 관리하는 장비 그룹 목록 개수
	 *
	 * 
	 * @param info
	 * @author LeeJungYoung
	 * @return LIST
	 */
	public int selectEquipGroupInfoForUserMgrPolicyCount(Map<String, String> param){
		return sqlSessionTemplate.selectOne("selectEquipGroupInfoForUserMgrPolicyCount", param);
	}
	
	/**
	 *관리자가 관리하는 장비 그룹 목록
	 * 
	 * TODO: 패키지 이동 필요 surfree
	 * @author LeeJungYoung
	 * @param param : userRecid
	 * @return
	 */
	public List<EquipGroupInfo> selectEquipGroupInfoForUserMgrPolicy(Map<String, String> param){
		return sqlSessionTemplate.selectList("selectEquipGroupInfoForUserMgrPolicy", param);
	}
	
	/**
	 * 관리자가 관리하는 장비 목록 개수
	 * 
	 * @param param
	 * @author LeeJungYoung
	 * @return LIST
	 */
	public int selectEquipInfoForUserMgrPolicyCount(Map<String, String> param) {
		return sqlSessionTemplate.selectOne("selectEquipInfoForUserMgrPolicyCount",param);
	}
	
	/**
	 * 관리자가 관리하는 장비 목록
	 * 
	 * @param info
	 * @author LeeJungYoung
	 * @return LIST
	 */
	public List<EquipInfo> selectEquipInfoForUserMgrPolicy(Map<String, String> param) {
		return sqlSessionTemplate.selectList("selectEquipInfoForUserMgrPolicy",param);
	}
	
	/*
	 * 관리자가 관리하는 장비 목록(장비 그룹 선택 시 해당 장비 그룹의 장비만 반환) 
	 * FIXME: 수정 필요
	 * 
	 * 1. 해당 관리자에 대한 관리 장비 목록 획득
	 * 2. 장비의 장비그룹 필요
	 * 2-1. 또는 모든 장비 목록 필요
	 * 3. 관리 장비 목록에서 장비 목록과 일치하는 것만 남김
	 */
	public List<PlUserEquipForUser> selectPlEquipList(PlUserEquip plUserEquip) {
		Validate.isTrue(plUserEquip.getUid() > 0, "uid must be greater than zero");
		Validate.notEmpty(plUserEquip.getInEquipGroupStrToken(), "inEquipGroupStrToken must be not empty");
		
		// 관리자가 관리하는 장비 목록 중 지정한 장비 그룹 하위의 장비 목록
		return sqlSessionTemplate.selectList("selecteCmEquipInfoList009", plUserEquip);
	}
	
	/**
	 * 관리자에게 할당된 장비 그룹 목록 반환
	 * 
	 * @param uid 관리자 레코드 ID
	 * @return 관리할 수 있는 장비 그룹 레코드ID 목록
	 * @author swcho
	 * @since 2015. 6. 26.
	 */ 
	public List<Integer> getAssignedEquipGroupsForUser(int uid) {
		return sqlSessionTemplate.selectList("getAssignedEquipGroupsForUser", uid);
	}
	
	/**
	 * 관리자에게 할당된 장비의 장비 그룹 목록 반환
	 * 
	 * @param uid 관리자 레코드 ID
	 * @return 관리할 수 있는 장비의 장비 그룹 목록
	 * @author: surfree
	 * @since:	2016. 6. 21.
	 */
	public List<Integer> getEquipGroupsFromAssignedEquipsForUser(int uid) {
		return sqlSessionTemplate.selectList("getEquipGroupsFromAssignedEquipsForUser", uid);
	}
	
	/**
	 * 관리자에게 할당된 장비 목록 반환
	 * 
	 * @param uid 관리자 레코드ID
	 * @return 관리할 수 있는 장비 레코드ID 목록
	 */
	public List<Integer> getAssignedEquipsForUser(int uid) {
		return sqlSessionTemplate.selectList("getAssignedEquipsForUser", uid);
	}
	
	public int insertPlUserEquipDataOfEquipManager(int userRecid, int equipRecid, String userId, String userIp) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userRecid", userRecid);
		param.put("equipRecid", equipRecid);
		param.put("userId", userId);
		param.put("userIp", userIp);
		 
		return sqlSessionTemplate.insert("insertPlUserEquipDataOfEquipManager", param);
	}
	
	public int insertPlUserEquipDataOfEquipGroupManager(int userRecid, int equipGroupRecid, String userId, String userIp) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userRecid", userRecid); 
		param.put("equipGroupRecid", equipGroupRecid);
		param.put("userId", userId);
		param.put("userIp", userIp);
		
		return sqlSessionTemplate.insert("insertPlUserEquipDataOfEquipGroupManager", param);
	}
	
	/**
	 * 
	 * 관리자가 삭제시 관리자 장비 관리정책 장비 관련 테이블에 관리자와 매핑되어있는 정책을 지운다
	 * 
	 * @author LeeJungYoung
	 * @param userRecid 관리자 recid
	 *  
	 */
	public void cancellationEquipBeforeDeleteManager(int userRecid) {
		sqlSessionTemplate.delete("cancellationEquipBeforeDeleteManager", userRecid);
	}
	
	/**
	 * 관리자가 삭제시 관리자 장비 관리정책 장비그룹 관련 테이블에 관리자와 매핑되어있는 정책을 지운다
	 * 
	 * @author LeeJungYoung
	 * @param userRecid 관리자 recid
	 */
	public void cancellationEquipGroupBeforeDeleteManager(int userRecid) {
		sqlSessionTemplate.delete("cancellationEquipGroupBeforeDeleteManager", userRecid);
	}
	
	/**
	 * 삭제된 장비에 대한 관리 지정을 해제(삭제) 한다
	 * 
	 * @param equipRecid 장비 레코드ID
	 */
	public void cancellationOfDeletedEquip(int equipRecid) {
		sqlSessionTemplate.delete("cancellationOfDeletedEquip", equipRecid);
	}
	
	/**
	 * 장비 관리 지정을 일괄적으로 삭제
	 * 
	 * @author: swcho
	 * @date:	2016. 6. 10.
	 */
	public void cancellationOfDeletedEquip() {
		sqlSessionTemplate.delete("cancellationOfDeletedEquip", 0);
	}
	
	/**
	 * 삭제된 장비 그룹에 대한 관리 지정을 해제(삭제) 한다
	 * 
	 * @param equipGroupRecid 장비그룹 레코드ID
	 */
	public void cancellationOfDeletedEquipGroup(int equipGroupRecid) {
		sqlSessionTemplate.delete("cancellationOfDeletedEquipGroup", equipGroupRecid);
	}
	
	public int deletePlUserEquipByRecid(int recid) {
		return sqlSessionTemplate.delete("deletePlUserEquipByRecid",recid);
	}
	
	/**
	 * @Description :장비 관리 정책 삭제 (사용자 수정)
	 * @param param : userRecid, assignRecid로 구성된 MAP
	 * @return
	 */
	public int deletePlUserEquipOfEquipManagerByuserRecidNAssignRecid(Map<String, Integer> param) {
		return sqlSessionTemplate.delete("deletePlUserEquipOfEquipManagerByuserRecidNAssignRecid", param );
	}
	
	/**
	 * @Description :장비그룹 관리 정책 삭제 (사용자 수정)
	 * @param param : userRecid, assignRecid로 구성된 MAP
	 * @return
	 */
	public int deletePlUserEquipGroupOfEquipManagerByuserRecidNAssignRecid( Map<String, Integer> param) {
		return sqlSessionTemplate.delete("deletePlUserEquipGroupOfEquipManagerByuserRecidNAssignRecid", param );
	}
	
	/**
	 * @author hwbaek
	 * @date 2014. 10.02.
	 * TODO : 관리자 장비 관리 정책 모두 삭제 (장비만)
	 */
	public int deletePlManagerEquipAll() {
		return sqlSessionTemplate.delete("deletePlManagerEquipAll");
	}
	/*
	 * 관리자 장비 그룹 관리 정책 모두 삭제 - 관리 장비 그룹을 모두 삭제
	 * 2016.1.26 swcho
	 */
	public int deletePlManagerEquipGroupAll() {
		return sqlSessionTemplate.delete("deletePlManagerEquipGroupAll");
	}
}