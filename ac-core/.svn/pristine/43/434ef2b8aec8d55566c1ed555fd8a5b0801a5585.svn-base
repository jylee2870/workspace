package sga.sol.ac.core.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sga.sol.ac.core.dao.user.UserCertificateDao;
import sga.sol.ac.core.entity.criteria.ExtraParams;
import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.user.UserCertificate;

/**
 * 사용자 인증서 서비스
 * 
 * @author	surfree
 * @date	2016.5.25.
 *
 */
@Service
public class UserCertificateService {
	@Autowired
	private UserCertificateDao userCertificateDao; 
	
	/**
	 * 인증서 목록 개수(전체)
	 * 
	 * @param param
	 * @return 인증서 목록 개수
	 */
	public int getUserCertificateCount(Map<String, Object> param){
		return userCertificateDao.getUserCertificateCount(param);
	}
	
	/**
	 * 인증서 목록 조회(페이지)
	 * 
	 * @param param
	 * <ul>
	 * <li>userRecid:	사용자 레코드ID</li>
	 * <li>sortFieldNm:	정렬할 대상 필드</li>
	 * <li>sortDir:		정렬 방향(ASC, DESC)</li>
	 * <li>startPage:	시작 레코드</li>
	 * <li>endPage:		종료 레코드</li>
	 * </ul>
	 * @return UserCertificate 객체 목록
	 */
	public List<UserCertificate> selectUserCertificateByPage(Map<String, Object> param) {
		return userCertificateDao.selectUserCertificateByPage(param);
	}
	
	/**
	 * 인증서 정보 추가
	 * 
	 * @param userCertificate
	 */
	public int insertUserCertificate(UserCertificate userCertificate) {
		return userCertificateDao.insertUserCertificate(userCertificate);
	}
	
	/**
	 * 발급자/시리얼 번호로 인증서 객체 조회
	 * 
	 * @param userCertificate
	 * @return
	 */
	public UserCertificate getUserCertificateByIssuerNSerial(UserCertificate userCertificate) {
		return userCertificateDao.getUserCertificateByIssuerNSerial(userCertificate);
	}

	/**
	 * 레코드ID로 인증서 객체 조회
	 * 
	 * @param recid
	 * @return
	 */
	public UserCertificate selectUserCertificateByRecid(int recid) {
		return userCertificateDao.selectUserCertificateByRecid(recid);
	}
	
	/**
	 * 레코드ID로 인증서 삭제
	 * 
	 * @param recid 인증서 레코드ID
	 * @return
	 */
	public int deleteUserCertificateByRecid(int recid) {
		return userCertificateDao.deleteUserCertificateByRecid(recid);
	}
	
	/**
	 * 레코드 ID 목록으로 인증서 삭제(관리용)
	 * 
	 * @param idsList 삭제할 인증서 레코드ID 목록
	 * @return
	 */
	public int deleteUserCertificateByRecids(List<Integer> idsList){
		return userCertificateDao.deleteUserCertificateByRecids(idsList);
	}
	
	/**
	 * 레코드 ID 목록으로 인증서 삭제 (개인용)
	 * 
	 * @param idsList 삭제할 인증서 레코드ID 목록
	 * 		  userReicd 사용자 recid	
	 * @return
	 */
	public int deleteUserPrivateCertificateByRecids(ExtraParams extra){
		return userCertificateDao.deleteUserPrivateCertificateByRecids(extra);
	}
	
	/*
	 * 	공인인증서 사용자 목록 리스트
	 * 
	 *  @param userName 사용자명  
	 *  	   userId 사용자아이디  
	 *  	   enabled 사용상태  
	 */
	public List<UserCertificate> getCertificateList(SearchParams param){
		return userCertificateDao.getCertificateList(param);
	}
	
	/*
	 * 	공인인증서 사용자 목록 리스트 카운트
	 * 
	 *  @param userName 사용자명  
	 *  	   userId 사용자아이디  
	 *  	   enabled 사용상태  
	 */
	public int getCertificateListCount(SearchParams param){
		return userCertificateDao.getCertificateListCount(param);
	}
}
