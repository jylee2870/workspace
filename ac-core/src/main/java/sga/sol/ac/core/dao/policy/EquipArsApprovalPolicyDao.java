package sga.sol.ac.core.dao.policy;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.policy.EquipArsApprovalPolicyList;
import sga.sol.ac.core.entity.policy.EquipArsApprovalPolicyListTypeApprover;

/** 
* <ul>
*  <li>업무 그룹명 : AuthCastle </li>
*  <li>서브 업무명 : AuthCastle - ARS 승인자 정책 DAO </li>
*  <li>작성자 : LEE JUNG YOUNG</li>
*  <li>작성일 : 2015. 12. 29 </li>
*  <li>========================================</li>
*  <li>변경자/변경일 : </li>
*  <li>변경사유/내역 : </li>
*  <li>========================================</li>
* </ul>
*/
@Repository
public class EquipArsApprovalPolicyDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/*******************************************************************************************************************
	 * 시스템종료 ARS 승인자 정책 최고관리자 
	 *******************************************************************************************************************/
	
	/*******************************************************************************
	 * @Description : ARS 승인자 정책 리스트 검색 <장비별보기> (최고관리자)
	 * @Description : 파라미터 중 policyId를 3가지로 분류하여 가져올수 있음
	 * @Description : 로그인 : ARS_APPROVAL_LOGIN_POLICY, 파일접근 : ARS_APPROVAL_FILE_POLICY, 시스템종료 : ARS_APPROVAL_SHUTDOWN_POLICY
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyList> selectArsApprovalPolicyListForSuperManager(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("selectArsApprovalPolicyListForSuperManager", param);
	}
	
	/*******************************************************************************
	 * @Description : ARS 승인자 정책 리스트 총 갯수 <장비별보기> (최고관리자)
	 * @Description : 파라미터 중 policyId를 3가지로 분류하여 가져올수 있음
	 * @Description : 로그인 : ARS_APPROVAL_LOGIN_POLICY, 파일접근 : ARS_APPROVAL_FILE_POLICY, 시스템종료 : ARS_APPROVAL_SHUTDOWN_POLICY
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalPolicyListCntForSuperManager(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("selectArsApprovalPolicyListCntForSuperManager", param);
	}
	
	/*******************************************************************************
	 * @Description : ARS 승인자 정책 리스트 검색 <승인자별보기> (최고관리자)
	 * @Description : 파라미터 중 policyId를 3가지로 분류하여 가져올수 있음
	 * @Description : 로그인 : ARS_APPROVAL_LOGIN_POLICY, 파일접근 : ARS_APPROVAL_FILE_POLICY, 시스템종료 : ARS_APPROVAL_SHUTDOWN_POLICY
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyListTypeApprover> selectArsApprovalPolicyListTypeApproverForSuperManager(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("selectArsApprovalPolicyListTypeApproverForSuperManager", param);
	}
	
	/*******************************************************************************
	 * @Description : ARS 승인자 정책 리스트 총 갯수 <승인자별보기> (최고관리자)
	 * @Description : 파라미터 중 policyId를 3가지로 분류하여 가져올수 있음
	 * @Description : 로그인 : ARS_APPROVAL_LOGIN_POLICY, 파일접근 : ARS_APPROVAL_FILE_POLICY, 시스템종료 : ARS_APPROVAL_SHUTDOWN_POLICY
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalPolicyListCntTypeApproverForSuperManager(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("selectArsApprovalPolicyListCntTypeApproverForSuperManager", param);
	}
	
	/*******************************************************************************
	 * @Description : ARS 승인자 정책 추가 전 해당 장비에 할당 되어진 ARS 승인자 정책 검색 (최고관리자)
	 * @Description : 파라미터(param)에 policyId가 있으면 해당 ARS 승인자 정책만 검색 없으면 ARS 승인자 정책 모두 가져옴 최대 3개 (로그인, 파일접근, 시스템종료)
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyList> selectArsApprovalPolicyInfoForSuperManager(Map<String, String> param) {
		return sqlSessionTemplate.selectList("selectArsApprovalPolicyInfoForSuperManager", param);
	}
	
	/*******************************************************************************
	 * @Description : ARS 승인자 정책 엑셀 익스포트
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2016.01.05
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyList> arsApprovalPolicyListExport(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("selectArsApprovalPolicyListExportForSuperManager", param);
	}
	
	/*******************************************************************************************************************
	 * 시스템종료 ARS 승인자 정책 최고관리자 END
	 *******************************************************************************************************************/
	
	/*******************************************************************************************************************
	 * 시스템종료 ARS 승인자 정책 관리자
	 *******************************************************************************************************************/
	
	/*******************************************************************************
	 * @Description : ARS 승인자 정책 리스트 검색 <장비별보기> (관리자)
	 * @Description : 파라미터 중 policyId를 3가지로 분류하여 가져올수 있음
	 * @Description : 로그인 : ARS_APPROVAL_LOGIN_POLICY, 파일접근 : ARS_APPROVAL_FILE_POLICY, 시스템종료 : ARS_APPROVAL_SHUTDOWN_POLICY
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyList> selectArsApprovalPolicyListForManager(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("selectArsApprovalPolicyListForManager", param);
	}
	
	/*******************************************************************************
	 * @Description : ARS 승인자 정책 리스트 총 갯수 <장비별보기> (관리자)
	 * @Description : 파라미터 중 policyId를 3가지로 분류하여 가져올수 있음
	 * @Description : 로그인 : ARS_APPROVAL_LOGIN_POLICY, 파일접근 : ARS_APPROVAL_FILE_POLICY, 시스템종료 : ARS_APPROVAL_SHUTDOWN_POLICY
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalPolicyListCntForManager(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("selectArsApprovalPolicyListCntForManager", param);
	}
	
	/*******************************************************************************
	 * @Description : ARS 승인자 정책 리스트 검색 <승인자별보기> (관리자)
	 * @Description : 파라미터 중 policyId를 3가지로 분류하여 가져올수 있음
	 * @Description : 로그인 : ARS_APPROVAL_LOGIN_POLICY, 파일접근 : ARS_APPROVAL_FILE_POLICY, 시스템종료 : ARS_APPROVAL_SHUTDOWN_POLICY
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyListTypeApprover> selectArsApprovalPolicyListTypeApproverForManager(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("selectArsApprovalPolicyListTypeApproverForManager", param);
	}
	
	/*******************************************************************************
	 * @Description : ARS 승인자 정책 리스트 총 갯수 <승인자별보기> (관리자)
	 * @Description : 파라미터 중 policyId를 3가지로 분류하여 가져올수 있음
	 * @Description : 로그인 : ARS_APPROVAL_LOGIN_POLICY, 파일접근 : ARS_APPROVAL_FILE_POLICY, 시스템종료 : ARS_APPROVAL_SHUTDOWN_POLICY
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2015.12.17
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalPolicyListCntTypeApproverForManager(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("selectArsApprovalPolicyListCntTypeApproverForManager", param);
	}
	
	/*******************************************************************************
	 * @Description : ARS 승인자 정책 엑셀 익스포트
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2016.01.05
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyList> arsApprovalPolicyListExportForManager(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("selectArsApprovalPolicyListExportForManager", param);
	}
	
	/*******************************************************************************************************************
	 * 시스템종료 ARS 승인자 정책 관리자 END
	 *******************************************************************************************************************/
	
	/*******************************************************************************************************************
	 * ARS 승인자 정책 ARS 승인자
	 *******************************************************************************************************************/
	
	/*******************************************************************************
	 * @Description : ARS 승인자 정책 리스트 검색  (ARD 승인자)
	 * @Description : 파라미터 중 policyId를 3가지로 분류하여 가져올수 있음
	 * @Description : 로그인 : ARS_APPROVAL_LOGIN_POLICY, 파일접근 : ARS_APPROVAL_FILE_POLICY, 시스템종료 : ARS_APPROVAL_SHUTDOWN_POLICY
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2016.02.19
	 * @return List<UserAuthPolicyList>
	 */
	public List<EquipArsApprovalPolicyListTypeApprover> selectArsApprovalPolicyListTypeApproverForArsApprover(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("selectArsApprovalPolicyListTypeApproverForArsApprover", param);
	}
	
	/*******************************************************************************
	 * @Description : ARS 승인자 정책 리스트 총 갯수 (ARS 승인자)
	 * @Description : 파라미터 중 policyId를 3가지로 분류하여 가져올수 있음
	 * @Description : 로그인 : ARS_APPROVAL_LOGIN_POLICY, 파일접근 : ARS_APPROVAL_FILE_POLICY, 시스템종료 : ARS_APPROVAL_SHUTDOWN_POLICY
	 * @param param
	 * @author : LeeJungYoung
	 * @date : 2016.02.19
	 * @return List<UserAuthPolicyList>
	 */
	public int selectArsApprovalPolicyListCntTypeApproverForArsApprover(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("selectArsApprovalPolicyListCntTypeApproverForArsApprover", param);
	}
	
	/*******************************************************************************************************************
	 * ARS 승인자 정책 ARS 승인자 END
	 *******************************************************************************************************************/
	
	
	/*
	 * 장비 승인자 정책 삭제 - 데이터 가져오기 초기 등록시 일괄 삭제 후 입력해야 할 경우 사용
	 * 로그인, 파일접근, 시스템 종료가 사실상 같은 정책이기 때문에 policy_id로 선별적으로 삭제한다
	 * 2016.1.27 swcho   
	 */
	public void deleteAllEquipArsApprovalPolicy(String policyId){
		sqlSessionTemplate.delete("deleteAllEquipArsApprovalPolicy", policyId);
	}
}
