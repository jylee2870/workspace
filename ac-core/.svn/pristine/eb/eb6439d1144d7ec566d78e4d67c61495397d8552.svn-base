package sga.sol.ac.core.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.user.User2FactStatus;
import sga.sol.ac.core.entity.user.UserArs;
import sga.sol.ac.core.entity.user.UserInfo;
import sga.sol.ac.core.entity.user.UserStatus;
import sga.sol.ac.core.entity.user.UserWebStatus;
import sga.sol.ac.core.listener.annotation.DeleteUser;
import sga.sol.ac.core.listener.annotation.DeleteUserByLevel;

/**
 * 사용자 정보 DAO
 * @author surfree
 * @history
 * 		2015-12-08 surfree UserGroupRelation 관련 메소드 UserGroupInfoDao로 위치 변경
 */
@Repository
public class UserInfoDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 조건에 맞는 사용자 목록 개수 조회 
	 * 2016.6.15 swcho SearchParams 모델 적용
	 * 2016.8.23 swcho 조건에서 사용자레벨 제거 (특정 레벨만을 검색하는 방식에서 벗어나기 위해 예) 최고관리자, 관리자 동시 조회.... )
	 * 2016.10.17 swcho SeaechParams에서 sort null 확인 추가
	 *
	 * @param  searchParam 검색조건
	 * @return int 사용자 개수
	 */
	/*
	public int getUserInfoExCount(Map<String, Object> inParamMap) {
		return sqlSessionTemplate.selectOne("getUserInfoExCount", inParamMap);
	}
	*/
	public int getUserInfoExCount(SearchParams searchParams) {
		Validate.notNull(searchParams, "searchParams must not be null");
		Validate.notNull(searchParams.getSort(), "params.sort must not be null");
		Validate.notNull(searchParams.getEntity(), "params.entity must not be null");
		Validate.isTrue(searchParams.getEntity() instanceof UserInfo, "searcharams.entity must be instance of UserInfo");
		
		/*
		UserInfo userInfo = (UserInfo)searchParams.getEntity();
		int userLevelCd = userInfo.getUserLevelCd();
		
		Validate.isTrue(userLevelCd >= Constants.USER_LEVEL_MIN && userLevelCd <= Constants.USER_LEVEL_MAX, "invalid userLevelCd value");
		*/
		
		return sqlSessionTemplate.selectOne("getUserInfoExCount", searchParams);
	}
	
	/**
	 * 조건에 맞는 사용자 목록 조회
	 * 2016.6.15 swcho SearchParams 모델 적용
	 *
	 * @param searchParams 검색 조건
	 * @return UserInfo 리스트(연결 객체 포함)
	 */
	/*
	public List<UserInfo> selectUserInfoListExByPage(Map<String, Object> inParamMap) {
		return sqlSessionTemplate.selectList("selectUserInfoListExByPage", inParamMap);
	}
	*/
	public List<UserInfo> selectUserInfoListExByPage(SearchParams searchParams) {
		Validate.notNull(searchParams, "searchParams must not be null");
		Validate.notNull(searchParams.getSort(), "params.sort must not be null");
		Validate.notNull(searchParams.getEntity(), "params.entity must not be null");
		Validate.isTrue(searchParams.getEntity() instanceof UserInfo, "searcharams.entity must be instance of UserInfo");
		
		return sqlSessionTemplate.selectList("selectUserInfoListExByPage", searchParams);
	}
	
	/**
	 * 조건에 맞는 사용자 목록 조회(엑셀 내보내기)
	 * 
	 * @param inParamMap
	 * @return
	 */
	public List<UserInfo> selectUserInfoListExForDown(Map<String, Object> inParamMap) {
		return sqlSessionTemplate.selectList("selectUserInfoListExForDown", inParamMap);
	}
	
	
	
	/**
	 * 사용자 정보 조회(사용자ID)
	 * 
	 * @param recid	사용자ID
	 * @return UserInfo 객체(연결 객체 포함)
	 */
	public UserInfo getUserInfoExByRecid(int recid) {
		return sqlSessionTemplate.selectOne("getUserInfoExByRecid", recid);
	}
	
	/**
	 * 사용자 정보 조회(사용자 레코드ID)
	 * 
	 * @param recid	사용자 레코드ID
	 * @return UserInfo 객체(연결 객체 포함)
	 */
	public UserInfo getUserInfoExByUserId(String userId) {
		return sqlSessionTemplate.selectOne("getUserInfoExByUserId", userId);
	}
	
	/**
	 * 사용자 정보 조회(사용자 레코드ID)
	 * 
	 * @param recid	사용자 레코드ID
	 * @return UserInfo 객체(연결 객체 포함)
	 */
	public List<UserInfo> getUserInfoExByParam(UserInfo param) {
		return sqlSessionTemplate.selectList("getUserInfoExByParam", param);
	}
	
	/**
	 * 사용자 정보 단순 조회(사용자 ID)
	 * 
	 * @param userId	조회할 사용자ID
	 * @return UserInfo 객체
	 */
	public UserInfo getUserInfoByUserId(String userId) {
		return sqlSessionTemplate.selectOne("getUserInfoByUserId", userId);
	}
	
	/**
	 * 사용자 정보 단순 조회(레코드 ID)
	 * 
	 * @param userId	조회할 사용자 레코드ID
	 * @return UserInfo 객체
	 */
	public UserInfo getUserInfoByRecid(int recid) {
		return sqlSessionTemplate.selectOne("getUserInfoByRecid", recid);
	}
	
	/**
	 * 사용자 ARS 정보 조회(사용자 레코드ID)
	 * 
	 * @param userRecid
	 * @return UserArs 객체
	 */
	public UserArs getUserArsInfo(int userRecid) {
		return sqlSessionTemplate.selectOne("getUserArsInfo", userRecid);
	}
	
	public UserWebStatus getUserWebStatus(int userRecid) {
		return sqlSessionTemplate.selectOne("getUserWebStatus", userRecid);
	}
	
	public User2FactStatus getUser2FactStatus(int userRecid) {
		return sqlSessionTemplate.selectOne("getUser2FactStatus", userRecid);
	}
	
	public int insertUserInfo(UserInfo userInfo) {
		return sqlSessionTemplate.insert("insertUserInfo", userInfo);
	}

	public int insertUserStatusInfo(UserStatus userStatus) {
		return sqlSessionTemplate.insert("insertUserStatus", userStatus);
	}
	
	public int insertUserWebStatusInfo(UserWebStatus userWebStatus) {
		return sqlSessionTemplate.insert("insertUserWebStatus", userWebStatus);
	}
	
	public int insertUser2FactStatusInfo(User2FactStatus user2FactStatus) {
		return sqlSessionTemplate.insert("insertUser2FactStatus", user2FactStatus);
	}
	
	public int insertUserArsInfo(UserArs arsInfo) {
		return sqlSessionTemplate.insert("insertUserArsInfo", arsInfo);
	}
	
	public int modifyUserInfo(UserInfo userInfo) {
		return sqlSessionTemplate.update("modifyUserInfo", userInfo);
	}

	public int modifyUserStatusInfo(UserStatus userStatus) {
		return sqlSessionTemplate.update("modifyUserStatusInfo", userStatus);
	}
	
	public int modifyUserWebStatusInfo(UserWebStatus userWebStatus) {
		return sqlSessionTemplate.update("modifyUserWebStatusInfo", userWebStatus);
	}
	
	public int modifyUser2FactStatusInfo(User2FactStatus user2FactStatus) {
		return sqlSessionTemplate.update("modifyUser2FactStatusInfo", user2FactStatus);
	}
	
	public int modifyUserArsInfo(UserArs arsInfo) {
		return sqlSessionTemplate.update("modifyUserArsInfo", arsInfo);
	}
	
	/**
	 * 사용자 암호 변경
	 * 
	 * @param userInfo 사용자 정보 객체
	 * @return
	 */
	public int updateUserPwdByRecid(UserInfo userInfo) {
		return sqlSessionTemplate.update("updateUserPwdByRecid", userInfo);
	}
	
	/**
	 * 사용자 ARS 암호 변경
	 * 
	 * @param arsInfo
	 * @return
	 */
	public int updateUserArsPwdByRecid(UserArs arsInfo) {
		return sqlSessionTemplate.update("updateUserArsPwdByRecid", arsInfo);
	}
	
	/**
	 * 웹 로그인 실패 기록
	 * 
	 * @param userWebStatus
	 * @return
	 */
	public int updateWebLoginFailure(UserWebStatus userWebStatus) {
		return sqlSessionTemplate.update("updateWebLoginFailure", userWebStatus);
	}
	
	public int updateWebLoginSuccess(UserWebStatus userWebStatus) {
		return sqlSessionTemplate.update("updateWebLoginSuccess", userWebStatus);
	}
	
	/**
	 * 이차 인증 실패 기록
	 * 
	 * @param user2FactStatus 사용자 2차 인증 객체
	 * @return
	 * @author: surfree
	 * @date:	2016. 5. 24.
	 */
	public int update2FactAuthFailure(User2FactStatus user2FactStatus) {
		return sqlSessionTemplate.update("update2FactAuthFailure", user2FactStatus);
	}
	
	/**
	 * 이차 인증 성공 기록
	 * 
	 * @param user2FactStatus 사용자 2차 인증 객체
	 * @return
	 * @author: surfree
	 * @date:	2016. 5. 24.
	 */
	public int update2FactAuthSuccess(User2FactStatus user2FactStatus) {
		return sqlSessionTemplate.update("update2FactAuthSuccess", user2FactStatus);
	}

	@DeleteUser
	public int deleteApproverByRecids(List<Integer> userRecids) {
		return sqlSessionTemplate.delete("deleteApproverByRecids", userRecids);
	}
	
	@DeleteUser
	public int deleteUserStatusByRecids(List<Integer> userRecids) {
		return sqlSessionTemplate.delete("deleteUserStatusByRecids", userRecids);
	}
	
	@DeleteUser
	public int deleteUserWebStatusByRecids(List<Integer> userRecids) {
		return sqlSessionTemplate.delete("deleteUserWebStatusByRecids", userRecids);
	}
	
	@DeleteUser
	public int deleteUser2FactStatusByRecids(List<Integer> userRecids) {
		return sqlSessionTemplate.delete("deleteUser2FactStatusByRecids", userRecids);
	}
	
	@DeleteUser
	public int deleteUserArsByRecids(List<Integer> userRecids) {
		return sqlSessionTemplate.delete("deleteUserArsByRecids", userRecids);
	}
	
	@DeleteUser
	public int deleteUserCertificateByUserRecids(List<Integer> userRecids) {
		return sqlSessionTemplate.delete("deleteUserCertificateByUserRecids", userRecids);
	}
	
	public int deleteUserInfoByRecids(List<Integer> userRecids) {
		return sqlSessionTemplate.delete("deleteUserInfoByRecids", userRecids);
	}

	@DeleteUserByLevel
	public int deleteApproverByUserLevel(int userLevelCd) {
		return sqlSessionTemplate.delete("deleteApproverByUserLevel", userLevelCd);
	}
	
	@DeleteUserByLevel
	public int deleteAllUserStatusByUserLevel(int userLevelCd) {
		return sqlSessionTemplate.delete("deleteAllUserStatusByUserLevel", userLevelCd);
	}
	
	@DeleteUserByLevel
	public int deleteAllUserWebStatusByUserLevel(int userLevelCd) {
		return sqlSessionTemplate.delete("deleteAllUserWebStatusByUserLevel", userLevelCd);
	}
	
	@DeleteUserByLevel
	public int deleteAllUser2FactStatusByUserLevel(int userLevelCd) {
		return sqlSessionTemplate.delete("deleteAllUser2FactStatusByUserLevel", userLevelCd);
	}
	
	@DeleteUserByLevel
	public int deleteAllUserArsByUserLevel(int userLevelCd) {
		return sqlSessionTemplate.delete("deleteAllUserArsByUserLevel", userLevelCd);
	}
	
	@DeleteUserByLevel
	public int deleteAllUserCertificateByUserLevel(int userLevelCd) {
		return sqlSessionTemplate.delete("deleteAllUserCertificateByUserLevel", userLevelCd);
	}
	
	public int deleteAllUserInfoByUserLevel(int userLevelCd) {
		return sqlSessionTemplate.delete("deleteAllUserInfoByUserLevel", userLevelCd);
	}
	
	public int deleteFidoUserByRecids(List<Integer> userRecids){
		return sqlSessionTemplate.delete("deleteFidoUserByRecids", userRecids);
	}
	
	public int deleteFidoUserAppStatusByRecid(List<Integer> userRecids){
		return sqlSessionTemplate.delete("deleteFidoUserAppStatusByRecid", userRecids);
	}
	
	public int deleteFidoUserRegiStatusByRecid(List<Integer> userRecids){
		return sqlSessionTemplate.delete("deleteFidoUserRegiStatusByRecid", userRecids);
	}
	
	public int deleteFidoUserByUserLevel(int userLevelCd){
		return sqlSessionTemplate.delete("deleteFidoUserByUserLevel", userLevelCd);
	}
	
	public int deleteFidoUserAppStatusByUserLevel(int userLevelCd){
		return sqlSessionTemplate.delete("deleteFidoUserAppStatusByUserLevel", userLevelCd);
	}
	
	public int deleteFidoUserRegiStatusByUserLevel(int userLevelCd){
		return sqlSessionTemplate.delete("deleteFidoUserRegiStatusByUserLevel", userLevelCd);
	}
}
