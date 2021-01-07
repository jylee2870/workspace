package sga.sol.ac.core.dao.policy;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.policy.PolicyObject;
import sga.sol.ac.core.entity.policy.UserAuthPolicyList;

/** 
* <ul>
*  <li>업무 그룹명 : AuthCastle </li>
*  <li>서브 업무명 : AuthCastle - 사용자 장비 접근 정책 DAO </li>
*  <li>작성자 : LEE JUNG YOUNG</li>
*  <li>작성일 : 2015. 12. 17 </li>
*  <li>========================================</li>
*  <li>변경자/변경일 : </li>
*  <li>변경사유/내역 : </li>
*  <li>========================================</li>
* </ul>
*/
@Repository
public class UserAuthPolicyDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	/*******************************************************************************
	 * @Description : 사용자 장비 접근 정책을 추가 하기전에 policy_object 테이블에 추가 사용자가 존재 하는지 판단하고
	 * @Description : 해당 정책에 엮어 있지 않으면 policy_object, policy_target 에 추가, 엮어 있으면 policy_target에만 추가
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.21
	 * @return List<UserAuthPolicyList>
	 */
	public PolicyObject checkPolicyObjectByPolicyList(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("checkPolicyObjectByPolicyList", param);
	}
	
	/*******************************************************************************
	 * @Description : 사용자 장비 접근 정책 추가
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.21
	 * @return List<UserAuthPolicyList>
	 */
	public int insertUserAuthPolicy(Map<String, Object> param) {
		return sqlSessionTemplate.insert("insertUserAuthPolicy", param);
	}
	
	
	/*******************************************************************************
	 * @Description : 사용자 장비 접근 정책 리스트 검색 (최고관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public List<UserAuthPolicyList> selectUserAuthPolicyListForSuperManager(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("selectUserAuthPolicyListForSuperManager", param);
	}
	
	/*******************************************************************************
	 * @Description : 사용자 장비 접근 정책 리스트 총 갯수 (최고관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public int selectUserAuthPolicyListCntForSuperManager(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("selectUserAuthPolicyListCntForSuperManager", param);
	}
	
	/*******************************************************************************
	 * @Description : 사용자 장비 접근 정책 리스트 검색 (관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public List<UserAuthPolicyList> selectUserAuthPolicyListForManager(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("selectUserAuthPolicyListForManager", param);
	}
	
	/*******************************************************************************
	 * @Description : 사용자 장비 접근 정책 리스트 총 갯수 (관리자)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public int selectUserAuthPolicyListCntForManager(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("selectUserAuthPolicyListCntForManager", param);
	}
	
	/*******************************************************************************
	 * @Description : 사용자 장비 접근 정책 가져오기
	 * @Description : 파라미터 값 없으면 전체 가져오기
	 * @Description : 파라미터 : objectRecid = int, objectType =int, targetRecids = list<Integer>, targetType = int  
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public List<UserAuthPolicyList> selectUserAuthPolicyInfoForSuperManager(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("selectUserAuthPolicyInfoForSuperManager", param);
	}
	
	/*******************************************************************************
	 * @Description : 사용자 장비 접근 정책 export
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public List<UserAuthPolicyList> selectUserAuthPolicyInfoExportForSuperManager(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("selectUserAuthPolicyInfoExportForSuperManager", param);
	}
	
	/*******************************************************************************
	 * @Description : 사용자 장비 접근 정책(policy_target) 삭제
	 * @Description : 해당 사용자 or 사용자 그룹에 매핑된 장비그룹 or 장비 or 장비계정 (policy_target)을 삭제
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.23
	 * @return List<UserAuthPolicyList>
	 */
	public int deletePolicyTargetForUserAuthPolicy(Map<String, Object> param) {
		return sqlSessionTemplate.delete("deletePolicyTargetForUserAuthPolicy", param);
	}
	
	/*******************************************************************************
	 * @Description : 해당 인증 대상 (policy_object)를 삭제
	 * @Description : 해당 인증 대상에 엮어 있는 타겟이 남아 있어 지우고 싶을 경우 param에 deleteTargetFlag = 'Y'를 추가
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.23
	 * @return List<UserAuthPolicyList>
	 */
	public int deletePolicyObjectForUserAuthPolicy(Map<String, Object> param) {
		return sqlSessionTemplate.delete("deletePolicyObjectForUserAuthPolicy", param);
	}

	/*
	 * 사용자 장비 접근 2차이 인증 정책 일괄 삭제
	 * 엑셀임포트시 기존 정책을 모두 지우고 입력할 경우 사용한다
	 * 2016.1.26 swcho
	 */
	public void deleteAllEquip2FactPolicy(){
		sqlSessionTemplate.delete("deleteAllEquip2FactPolicyTarget");
		sqlSessionTemplate.delete("deleteAllEquip2FactPolicyObject");
	}
}
