package sga.sol.ac.core.dao.user;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.criteria.ExtraParams;
import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.user.UserCertificate;

/**
 * 사용자 인증서 DAO 객체
 * 사용자와 1:N 관계임
 * 
 * @author surfree
 */
@Repository
public class UserCertificateDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getUserCertificateCount(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("getUserCertificateCount", param);
	}

	public List<UserCertificate> selectUserCertificateByPage(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("selectUserCertificateByPage", param);
	}

	public UserCertificate getUserCertificateByIssuerNSerial(UserCertificate userCertificate) {
		return sqlSessionTemplate.selectOne("getUserCertificateByIssuerNSerial", userCertificate);
	}

	public int insertUserCertificate(UserCertificate userCertificate) {
		return sqlSessionTemplate.insert("insertUserCertificate", userCertificate);
	}
	
	public int deleteUserCertificateByRecid(int recid) {
		return sqlSessionTemplate.delete("deleteUserCertificateByRecid", recid);
	}

	public int deleteUserCertificateByRecids(List<Integer> recidList) {
		return sqlSessionTemplate.delete("deleteUserCertificateByRecids", recidList);
	}
	
	public int deleteUserPrivateCertificateByRecids(ExtraParams extra) {
		return sqlSessionTemplate.delete("deleteUserPrivateCertificateByRecids", extra);
	}

	public UserCertificate selectUserCertificateByRecid(int recId) {
		return sqlSessionTemplate.selectOne("selectUserCertificateByRecid", recId);
	}
	
	// 공인인증서 사용자 목록 리스트
	public List<UserCertificate> getCertificateList(SearchParams param){
		return sqlSessionTemplate.selectList("getCertificateList", param);
	}
	
	// 공인인증서 사용자 목록 리스트 카운트 
	public int getCertificateListCount(SearchParams param){
		return sqlSessionTemplate.selectOne("getCertificateListCount", param);
	}
}