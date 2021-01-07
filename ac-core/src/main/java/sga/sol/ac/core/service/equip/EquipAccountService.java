package sga.sol.ac.core.service.equip;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sga.sol.ac.core.dao.equip.EquipAccountDao;
import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.equip.EquipAccount;

/**
 * 장비 계정 서비스
 * 
 * @author JannyWang
 */
@Service
public class EquipAccountService {
	@Autowired
	private EquipAccountDao equipAccountDao;
	
	/**
	 * 장비 계정 목록 갯수 조회(장비 레코드 ID)
	 * 
	 * @param equipAccount
	 * <ul>
	 * <li>equipRecid: 장비 레코드ID</li>
	 * </ul>
	 * @return 장비 계정 개수
	 */
	public int getEquipAccountListCount(SearchParams params) {
		return equipAccountDao.getEquipAccountListCount(params);
	}
	
	/**
	 * 조회 조건으로 장비 계정을 조회
	 * 
	 * @param params 조회 조건
	 * <ul>
	 * <li>entity: EquipAccount 객체(옵션)</li>
	 * <li>page: PageParam 객체(옵션)</li>
	 * <li>sort: SortParam 객체(필수)</li>
	 * <li>extraParams: 추가 검색 조건(옵션)</li>
	 * </ul>
	 * @return EquipAccount 목록
	 * @author: surfree
	 * @date:	2016. 6. 1.
	 */
	public List<EquipAccount> selectEquipAccountList(SearchParams params) {
		return equipAccountDao.selectEquipAccountList(params);
	}
	
	/**
	 * 장비 계정 정보 조회(장비 계정 레코드ID)
	 * 
	 * @param recid
	 * @return
	 */
	public EquipAccount selectEquipAccountByRecid(int recid) {
		return equipAccountDao.selectEquipAccountByRecid(recid);
	}
	
	/**
	 * 장비 계정 목록 조회(장비 레코드ID)
	 * @param equipId
	 * @return
	 */
	public List<EquipAccount> selectEquipAccountListByEquipId(int equipId) {
		return equipAccountDao.selectEquipAccountListByEquipId(equipId);
	}
	
	/**
	 * 장비 계정 정보 조회
	 * <p>
	 * 장비 레코드ID와 장비 계정명으로 조회한다. 계정명으로 등록된 로컬 계정 및 도메인 계정이 모두 포함된다.<br/>
	 * </p>
	 * 
	 * @param equipId 장비 레코드ID
	 * @param account 장비 계정명
	 * @return EquipAccount 목록
	 * @author: surfree
	 * @since:	2016. 6. 14.
	 */
	public List<EquipAccount> selectEquipAccountListByAccount(int equipId, String account) {
		return equipAccountDao.selectEquipAccountListByAccount(equipId, account);
	}
	
	/**
	 * 장비 계정 정보 조회
	 * <p>
	 * 장비 레코드ID, 장비 계정명, 장비 계정 도메인으로 조회한다.
	 * </p>
	 * <p>
	 * getApplyEquipAccountByAccount과 다른 점은 domain까지 모두 일치하는 계정을 반환한다.
	 * </p>
	 * 
	 * @param equipId 장비 레코드ID
	 * @param account 장비 계정명
	 * @param domain 장비 계정 도메인
	 * </ul>
	 * @return	EquipAccount
	 * @author	surfree
	 * @since	2016. 5. 25.
	 */
	public EquipAccount getEquipAccountByAccount(int equipId, String account, String domain) {
		EquipAccount localAccount = null;
		EquipAccount selectedAccount = null;
		
		// 지정한 계정명의 모든 계정(도메인 계정 포함)이 반환된다.
		List<EquipAccount> accounts = equipAccountDao.selectEquipAccountListByAccount(equipId, account);
			
		for(EquipAccount oAccount: accounts) {
			if(StringUtils.isEmpty(oAccount.getDomain())) {
				localAccount = oAccount;
			}
				
			if(!StringUtils.isEmpty(domain) && 
				domain.equalsIgnoreCase(oAccount.getDomain())) {
				selectedAccount = oAccount;
				break;
			}
		}
		
		// 도메인 입력이 없는 경우 로컬 계정 정보 존재하면 반환된다.
		if(domain == null || domain.isEmpty()) {
			selectedAccount = localAccount;
		}
		
		return selectedAccount;
	}
	
	/**
	 * 장비 계정 조회
	 * <p>
	 * getEquipAccountByAccount과 다른 점은 지정한 도메인이 일치하지 않는 경우 로컬 계정을 반환한다.
	 * </p>
	 * <p>
	 * 지정한 도메인으로 계정이 등록되어 있는 경우에는 해당 계정을 반환하고,<br/>
	 * 지정한 도메인이 등록되어 있지 않고 로컬 계정(도메인이 없는 계정)이 등록되어 있다면 로컬 계정을 반환한다.<br/>
	 * 이차 인증 시에 RedCastle은 localhost 또는 호스트명으로 도메인명을 전달한다.
	 * </p>
	 * 
	 * @param equipId 장비 레코드ID
	 * @param account 장비 계정명
	 * @param domain 장비 계정 도메인명
	 * @return
	 * @author: surfree
	 * @since:	2016. 6. 14.
	 */
	public EquipAccount getApplyEquipAccountByAccount(int equipId, String account, String domain) {
		EquipAccount localAccount = null;
		EquipAccount selectedAccount = null;
		
		// 지정한 계정명의 모든 계정(도메인 계정 포함)이 반환된다.
		List<EquipAccount> accounts = equipAccountDao.selectEquipAccountListByAccount(equipId, account);
			
		for(EquipAccount oAccount: accounts) {
			if(oAccount.getDomain().isEmpty()) {
				localAccount = oAccount;
			}
				
			if((domain != null && !domain.isEmpty()) && domain.equalsIgnoreCase(oAccount.getDomain())) {
				selectedAccount = oAccount;
				break;
			}
		}
		
		/*
		 도메인까지 일치하는 계정이 있으면 도메인 계정을 반환하고, 로컬 계정만 등록되어 있다면
		 입력한 도메인(localhost, 호스트명)에 상관하지 않고 로컬 계정을 반환한다.
		 
		 2016.08.29 swcho
		 domain까지 일치하는 계정이 없으면, domain 입력 여부에 상관없이 localAccount를 리턴하도록 수정
		 RC 버전에 따라 null 또는 공백을 보내거나 localhost를 보내므로 domain의 null 판단을 넣으면 안됨  
		*/ 
		//if(null == selectedAccount && (domain != null && !domain.isEmpty())) {
		if(null == selectedAccount ) {
			selectedAccount = localAccount;
		}
		
		return selectedAccount;
	}
	
	/**
	 * 계정 추가
	 * 
	 * @param equipAccount EquipAccount 객체
	 * 
	 * @history:
	 * 		2015-12-08 surfree AddCmEquipAccount 통합(useGeneratedKey 사용)
	 */
	public int insertEquipAccount(EquipAccount equipAccount){
		return equipAccountDao.insertEquipAccount(equipAccount);
	}
	
	/**
	 * 계정 정보 수정 (계정 레코드ID)
	 * @param equipAccount EquipAccount 객체
	 * @return
	 */
	public int updateEquipAccountByRecid(EquipAccount equipAccount){
		return equipAccountDao.updateEquipAccountByRecid(equipAccount);
	}
	
	/**
	 * 단일 장비 계정 삭제(계정 레코드ID)
	 * 
	 * @param recid 장비 계정 레코드ID
	 * @return
	 */
	public int deleteEquipAccountByRecid(int recid){
		return equipAccountDao.deleteEquipAccountByRecid(recid);
	}
	
	/**
	 * 단일 장비 계정 삭제(장비 레코드ID)
	 * 
	 * @param equipId 장비 레코드ID
	 * @return
	 */
	public int deleteEquipAccountByEquipId(int equipId){
		return equipAccountDao.deleteEquipAccountByEquipId(equipId);
	}
	
	/**
	 * 다중 장비 계정 삭제(계정 레코드ID 목록)
	 * 
	 * @param list 장비 계정 레코드ID 목록
	 * @return
	 */
	public int deleteEquipAccountByRecidList(List<Integer> list) {
		return equipAccountDao.deleteEquipAccountByRecidList(list) ;
	}
	
	
	/**
	 * 다중 장비 계정 삭제(장비 레코드ID 목록)
	 * 
	 * @param equipIdList 장비 레코드ID 목록
	 * @return
	 * 
	 * TODO: DAO만 사용함
	 */
	public int deleteEquipAccountByEquipIdList(List<Integer> equipIdList){
		return equipAccountDao.deleteEquipAccountByEquipIdList(equipIdList);
	}
	
	/**
	 * 모든 장비계정 정보 삭제
	 * 
	 * @author hwbaek
	 * @date 2014. 9. 26.
	 */
	public int deleteEquipAccountAll(){
		return equipAccountDao.deleteEquipAccountAll();
	}
}
