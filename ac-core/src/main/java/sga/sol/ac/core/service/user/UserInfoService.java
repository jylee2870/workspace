package sga.sol.ac.core.service.user;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sga.sol.ac.core.Constants;
import sga.sol.ac.core.dao.user.UserGroupInfoDao;
import sga.sol.ac.core.dao.user.UserInfoDao;
import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.user.User2FactStatus;
import sga.sol.ac.core.entity.user.UserArs;
import sga.sol.ac.core.entity.user.UserInfo;
import sga.sol.ac.core.entity.user.UserWebStatus;
import sga.sol.ac.core.listener.annotation.DeleteUser;
import sga.sol.ac.core.listener.annotation.DeleteUserByLevel;
import sga.sol.ac.core.listener.impl.UserDeleteByLevelListener;
import sga.sol.ac.core.listener.impl.UserDeleteListener;

/**
 * 사용자 서비스
 * 
 * @author		: swcho
 * @date		: 2014. 9. 29.
 */
@Service
public class UserInfoService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private UserGroupInfoDao userGroupInfoDao;

	@Autowired
	private UserDeleteListener userDeleteListener;
	
	@Autowired
	private UserDeleteByLevelListener userDeleteByLevelListener;
	
	/**
	 * 조건에 맞는 사용자 목록 개수 조회
	 * 2016.6.15 swcho SearchParams 모델 적용
	 * 
	 * @param  searchParam 검색조건
	 * @return int 사용자 개수
	 */
	/*
	public int getUserInfoExCount(Map<String, Object> inParamMap) {
		Integer userLevelCd = (Integer) inParamMap.get("userLevelCd");
		if(null == userLevelCd || (userLevelCd < Constants.USER_LEVEL_MIN && userLevelCd > Constants.USER_LEVEL_MAX)) {
			throw new IllegalArgumentException("userLevelCd required.");
		}
		return userInfoDao.getUserInfoExCount(inParamMap);
	}
	*/
	public int getUserInfoExCount(SearchParams searchParams) {
		return userInfoDao.getUserInfoExCount(searchParams);
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
		return userInfoDao.selectUserInfoListExByPage(inParamMap);
	}
	*/
	public List<UserInfo> selectUserInfoListExByPage(SearchParams searchParams) {
		return userInfoDao.selectUserInfoListExByPage(searchParams);
	}
	
	/**
	 * 사용자 정보 조회(사용자 레코드ID)
	 * 
	 * @param recid	사용자 레코드ID
	 * @return UserInfo 객체(연결 객체 포함)
	 */
	public UserInfo getUserInfoExByRecid(int recid) {
		return userInfoDao.getUserInfoExByRecid(recid);
	}
	
	public UserInfo getUserInfoExByUserId(String userId) {
		return userInfoDao.getUserInfoExByUserId(userId);
	}
	
	/**
	 * 사용자 정보 단순 조회(사용자 ID)
	 * 
	 * @param userId	조회할 사용자ID
	 * @return UserInfo 객체
	 */
	public UserInfo getUserInfoByUserId(String userId) {
		return userInfoDao.getUserInfoByUserId(userId);
	}
	
	/**
	 * 사용자 정보 조회
	 * 
	 * @param param UserInfo 객체
	 * <ul>
	 * <li>userId: 사용자 ID</li>
	 * <li>userName: 사용자 이름</li>
	 * <li>mobile: 사용자 핸드폰 번호</li>
	 * </ul>
	 * @return UserInfo 객체 목록
	 * @author: surfree
	 * @date:	2016. 5. 27.
	 */
	public List<UserInfo> getUserInfoExByParam(UserInfo param) {
		return userInfoDao.getUserInfoExByParam(param);
	}
	
	/**
	 * 사용자 정보 단순 조회(사용자 ID)
	 * 
	 * @param userId	조회할 사용자ID
	 * @return UserInfo 객체
	 */
	public UserInfo getUserInfoByRecid(int recid) {
		return userInfoDao.getUserInfoByRecid(recid);
	}
	
	/**
	 * 사용자 ARS 정보 조회(사용자 레코드ID)
	 * 
	 * @param userRecid
	 * @return UserArs 객체
	 */
	public UserArs getUserArsInfo(int userRecid) {
		UserArs resultObj = userInfoDao.getUserArsInfo(userRecid);
//		try {
//			String pwd = "";
//			if(resultObj.getArsPassword() != null){
//				
//				pwd = DataEncryptor.decryptString(resultObj.getArsPassword());
//			}
//			
//			resultObj.setArsPassword(pwd);
//			
//		} catch (GeneralSecurityException e) {
//			e.printStackTrace();
//		}
		
		return resultObj;
	}
	
	/**
	 * 사용자 2차 인증 상태 조회(사용자 레코드ID)
	 * @param userRecid 사용자 레코드ID
	 * @return
	 * @author: surfree
	 * @date:	2016. 5. 24.
	 */
	public User2FactStatus getUser2FactStatus(int userRecid) {
		return userInfoDao.getUser2FactStatus(userRecid);
	}
	
	/**
	 * 사용자 정보 추가(연결 객체 포함)
	 * 
	 * @param userInfo 사용자 정보
	 * @param arsInfo 사용자 ARS 정보(NULL 허용)
	 * @return
	 */
	@Transactional
	public boolean insertUserInfo(UserInfo userInfo, UserArs arsInfo) {
		boolean bResult = true;
			
		// 2015-07-29 surfree 패스워드 저장 방식 변경
//		String pwd = userInfo.getUserId() + userInfo.getPwd();
//		userInfo.setPwd(PasswordDigest.digestString(pwd));
//			
//		userInfoDao.insertUserInfo(userInfo);
//		
//		// 추가된 recid로 하위 객체 설정
//		userInfo.applyInfo();
//		
//		// group
//		userGroupInfoDao.inserUserGroupRelation(userInfo.getUserGroupRelation());
//		
//		userInfoDao.insertUserStatusInfo(userInfo.getUserStatus());
//		userInfoDao.insertUserWebStatusInfo(userInfo.getUserWebStatus());
//		
//		if(null != arsInfo) {
//			// 2016-02-23 LeeJungYoung 패스워드 저장 방식 변경
//			try {
//				String arsPassword = null;
//				if(arsInfo.getArsPassword() != null) arsPassword = DataEncryptor.encryptString(arsInfo.getArsPassword());
//				arsInfo.setArsPassword(arsPassword);
//			} catch (GeneralSecurityException e) {
//				e.printStackTrace();
//			}
//			arsInfo.setUserRecid(userInfo.getRecid());
//		}
//		
//		switch(userInfo.getUserLevelCd()) {
//		case Constants.USER_LEVEL_MANAGE:
//			// 관리
//			break;
//		case Constants.USER_LEVEL_USER_ARS:
//			// ars
//			if(null == arsInfo) {
//				throw new IllegalArgumentException("arsInfo required.");
//			}
//			userInfoDao.insertUserArsInfo(arsInfo);
//			break;
//		case Constants.USER_LEVEL_USER:
//			// ars
//			if(null != arsInfo) {
//				userInfoDao.insertUserArsInfo(arsInfo);
//			}
//			userInfoDao.insertUser2FactStatusInfo(userInfo.getUser2FactStatus());
//			break;
//		}
		
		// TODO: login policy
		
		return bResult;
	}
	
	/**
	 * 사용자 정보 수정(연결 객체)
	 * 
	 * @param userInfo 사용자 정보
	 * @param arsInfo 사용자 ARS 정보(NULL 허용)
	 * @return
	 */
	@Transactional
	public boolean modifyUserInfo(UserInfo userInfo, UserArs arsInfo) {
		boolean bResult = true;
		
		userInfoDao.modifyUserInfo(userInfo);
		
		userInfo.applyInfo();
		
		// group
		userGroupInfoDao.updateUserGroupRelation(userInfo.getUserGroupRelation());

		// status
		if(null != userInfo.getUserStatus()) {
			userInfoDao.modifyUserStatusInfo(userInfo.getUserStatus());
		}
		// web status
		if(null != userInfo.getUserWebStatus()) {
			userInfoDao.modifyUserWebStatusInfo(userInfo.getUserWebStatus());
		}

		// ars
		// 2fact
		switch(userInfo.getUserLevelCd()) {
		case Constants.USER_LEVEL_USER:
			userInfoDao.modifyUserArsInfo(arsInfo);
			if(null != userInfo.getUser2FactStatus()) {
				userInfoDao.modifyUser2FactStatusInfo(userInfo.getUser2FactStatus());
			}
			break;
		case Constants.USER_LEVEL_USER_ARS:
			userInfoDao.modifyUserArsInfo(arsInfo);
			break;
		}
		
		return bResult;
	}
	
	/**
	 * 사용자 암호 검사
	 * 
	 * @param userInfo
	 * @param password
	 * @return boolean
	 */
	public boolean checkUserPwd(UserInfo userInfo, String password) {
		// 2015-07-29 surfree 패스워드 저장 방식 변경
//		if(null == userInfo.getUserId() || userInfo.getUserId().isEmpty()) {
//			logger.error("userid is empty.");
//			return false;
//		}
//		
//		String storedPassword = userInfo.getPwd();
//		String hashedPassword = PasswordDigest.digestString(userInfo.getUserId() + password);
//		
//		return hashedPassword.equals(storedPassword);
		return false;
	}
	
	/**
	 * 사용자 암호 변경
	 * @param userInfo 사용자 정보
	 * @return
	 */
	public boolean updateUserPwdByRecid(UserInfo userInfo) {
		// 2015-07-29 surfree 패스워드 저장 방식 변경
//		if(null == userInfo.getUserId() || userInfo.getUserId().isEmpty()) {
//			logger.error("userid is empty.");
//			return false;
//		}
//		
//		String pwd = userInfo.getUserId() + userInfo.getPwd();
//		userInfo.setPwd(PasswordDigest.digestString(pwd));
//		
//		return userInfoDao.updateUserPwdByRecid(userInfo) > 0 ? true: false;
		return false;
	}
	
	public int updateUserArsPwdByRecid(UserArs arsInfo) {
		// 2016-02-23 LeeJungYoung 패스워드 저장 방식 변경
//		if(null == arsInfo.getArsPassword()) {
//			logger.error("ARS password is empty.");
//			return 0;
//		}
//		try {
//			arsInfo.setArsPassword(DataEncryptor.encryptString(arsInfo.getArsPassword()));
//		} catch (GeneralSecurityException e) {
//			e.printStackTrace();
//		}
//		
//		return userInfoDao.updateUserArsPwdByRecid(arsInfo);
		return 0;
	}
	
	/**
	 * 사용자 정보 삭제(목록)
	 * 
	 * 2016.11.1 swcho 참조 데이터 삭제를 리스너 등록 방식으로 변경
	 * 
	 * @param userRecids 사용자 레코드 ID 목록
	 * @return
	 */
	@Transactional
	public boolean deleteUserInfoByRecids(List<Integer> userRecids) {
		/*
		userGroupInfoDao.deleteUserGroupRelationByRecids(userRecids);
		
		userInfoDao.deleteApproverByRecids(userRecids); // 이정용 추가 작업 승인자 제거 
		userInfoDao.deleteUserStatusByRecids(userRecids);
		userInfoDao.deleteUserWebStatusByRecids(userRecids);
		userInfoDao.deleteUser2FactStatusByRecids(userRecids);
		userInfoDao.deleteUserArsByRecids(userRecids);
		userInfoDao.deleteUserCertificateByUserRecids(userRecids);
		
		*/
		
		// DeleteUser를 사용하여 기본 정보 UserInfo를 삭제하기 전에 userRecid를 참조하는 데이터를 먼저 지운다
		userDeleteListener.chainExecute(userRecids);
		
		// userRecid에 해당하는 사용자 기본 정보를 삭제한다
		userInfoDao.deleteUserInfoByRecids(userRecids);
		return true;
	}
	/**
	 * 사용자 레벨로 사용자 정보 일괄 삭제
	 * 
	 * 2017.4.27 swcho 참조 데이터 삭제를 리스너 등록 방식으로 변경
	 */
	@Transactional
	public int deleteAllUserInfoByUserLevel(int userLevelCd) {
		// TODO: 정책 관련 데이터 삭제
		
		/*
		userGroupInfoDao.deleteAllUserGroupRelationByUserLevel(userLevelCd);
		userInfoDao.deleteAllUserStatusByUserLevel(userLevelCd);
		userInfoDao.deleteAllUserWebStatusByUserLevel(userLevelCd);
		userInfoDao.deleteAllUser2FactStatusByUserLevel(userLevelCd);
		userInfoDao.deleteAllUserArsByUserLevel(userLevelCd);
		userInfoDao.deleteAllUserCertificateByUserLevel(userLevelCd);
		*/
		
		userDeleteByLevelListener.chainExecute(userLevelCd);
		
		return userInfoDao.deleteAllUserInfoByUserLevel(userLevelCd);
	}
	
	/**
	 * 사용자의 웹 상태 잠금
	 * 
	 * @param userRecid 사용자 레코드ID
	 * @return
	 */
	public int lockUserWebStatus(int userRecid) {
		UserWebStatus userWebStatus = new UserWebStatus();
		userWebStatus.setUserRecid(userRecid);
		userWebStatus.setLocked(1);
		
		return userInfoDao.modifyUserWebStatusInfo(userWebStatus);
	}
	
	public int updateWebLoginFailure(UserWebStatus userWebStatus) {
		return userInfoDao.updateWebLoginFailure(userWebStatus);
	}
	
	public int updateWebLoginSuccess(UserWebStatus userWebStatus) {
		return userInfoDao.updateWebLoginSuccess(userWebStatus);
	}
	
	/**
	 * 사용자의 이차 인증 상태 잠금
	 * 
	 * @param userRecid 사용자 레코드ID
	 * @return
	 */
	public int lockUser2FactAuthStatus(int userRecid) {
		User2FactStatus user2FactStatus = new User2FactStatus();
		user2FactStatus.setUserRecid(userRecid);
		user2FactStatus.setLocked(1);
		
		return userInfoDao.modifyUser2FactStatusInfo(user2FactStatus);
	}
	
	/**
	 * 사용자 이차 인증 실패 기록
	 * 
	 * @param user2FactStatus 사용자 2차 인증 상태 객체
	 * @return
	 * @author: surfree
	 * @date:	2016. 5. 24.
	 */
	public int update2FactAuthFailure(User2FactStatus user2FactStatus) {
		return userInfoDao.update2FactAuthFailure(user2FactStatus);
	}
	
	/**
	 * 사용자 이차 인증 성공 기록
	 * 
	 * @param user2FactStatus 사용자 2차 인증 상태 객체
	 * @return
	 * @author: surfree
	 * @date:	2016. 5. 24.
	 */
	public int update2FactAuthSuccess(User2FactStatus user2FactStatus) {
		return userInfoDao.update2FactAuthSuccess(user2FactStatus);
	}
	
	public List<UserInfo> selectUserInfoListExForDown(Map<String, Object> inParamMap) {
		return userInfoDao.selectUserInfoListExForDown(inParamMap);
	}
	
	@DeleteUser
	public int deleteFidoUserByRecids(List<Integer> userRecids){
		int info = userInfoDao.deleteFidoUserByRecids(userRecids);
		int app  = userInfoDao.deleteFidoUserAppStatusByRecid(userRecids);
		int regi = userInfoDao.deleteFidoUserRegiStatusByRecid(userRecids);
		
		return info; 
	}
	
	@DeleteUserByLevel
	public int deleteFidoUserByUserLevel(int userLevelCd){
		int info = userInfoDao.deleteFidoUserByUserLevel(userLevelCd);
		int app  = userInfoDao.deleteFidoUserAppStatusByUserLevel(userLevelCd);
		int regi = userInfoDao.deleteFidoUserRegiStatusByUserLevel(userLevelCd);
		
		return info;
	}
}
