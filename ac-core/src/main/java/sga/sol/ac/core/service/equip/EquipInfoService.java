package sga.sol.ac.core.service.equip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sga.sol.ac.core.dao.equip.EquipAccountDao;
import sga.sol.ac.core.dao.equip.EquipInfoDao;
import sga.sol.ac.core.dao.equip.EquipGroupRelationDao;
import sga.sol.ac.core.dao.equip.EquipServiceDao;
import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.criteria.SortParam;
import sga.sol.ac.core.entity.equip.EquipInfo;
import sga.sol.ac.core.entity.equip.EquipGroupRelation;
import sga.sol.ac.core.entity.equip.EquipServiceRelation;

/**
 * 장비 정보 서비스
 * 
 * @author JannyWang 
 */
@Service
public class EquipInfoService{
	@Autowired
	private EquipInfoDao equipInfoDao;
	
	@Autowired
	private EquipGroupRelationDao equipGroupRelationDao;
	
	@Autowired
	private EquipServiceDao equipServiceDao;
	
	@Autowired
	private EquipAccountDao equipAccountDao;
	
	/**
	 * 모든 장비 목록을 조회
	 * <p>
	 * 장비 그룹 정보가 포함된다.<br/>
	 * 장비 그룹 경로 순으로 ASC 정렬되어 표시된다.
	 * </p>
	 * @return EquipInfo 목록
	 * @author: surfree
	 * @since:	2016. 6. 21.
	 */
	public List<EquipInfo> selectEquipInfoListAll(){
		SearchParams params = new SearchParams();
		params.setEntity(new EquipInfo());
		params.setSort(new SortParam("EG.group_path", SortParam.DIR_ASC));
		
		return equipInfoDao.selectEquipInfoList(params);
	}
	
	/**
	 * 장비 목록 개수
	 * 
	 * @param params
	 * @return
	 * @author: surfree
	 * @date:	2016. 6. 3.
	 */
	public int getEquipInfoListCount(SearchParams params) {
		return equipInfoDao.getEquipInfoListCount(params);
	}
	
	/**
	 * 장비 목록 조회
	 * 
	 * @param params
	 * @return
	 * @author: surfree
	 * @date:	2016. 6. 3.
	 */
	public List<EquipInfo> selectEquipInfoList(SearchParams params) {
		return equipInfoDao.selectEquipInfoList(params);
	}
	
	/**
	 * 장비 그룹 레코드ID 목록에 포함된 장비 목록 조회
	 * 
	 * @param list 장비 그룹 레코드ID 목록
	 * @return CmEquipInfo 객체 목록
	 * @author: surfree
	 * @date:	2016. 6. 3.
	 * <ul>
	 * <li>2016.5.24 swcho: selectCmEquipInfoListInGroupIds()에서 List형식으로 변경</li>
	 * </ul>
	 */
	public List<EquipInfo> selectEquipListByGroupList(List<Integer> list) {
		return equipInfoDao.selectEquipListByGroupList(list);
	}
	
	/**
	 * 장비 정보 조회(레코드ID)
	 * 
	 * @param recid
	 * @return
	 */
	public EquipInfo selectEquipInfoByRecid(int recid) {
		return equipInfoDao.selectEquipInfoByRecid(recid);
	}
	
	/**
	 * 장비 정보 조회 (장비 명)
	 * 
	 * @param equipName 장비명
	 * @return
	 */
	public EquipInfo selectEquipInfoByName(String equipName) {
		return equipInfoDao.selectEquipInfoByName(equipName);
	}
	
	/**
	 * 장비 정보 조회 (장비 주IP)
	 * 
	 * @param ipPrimary 장비 주 IP
	 * @return EquipInfo
	 * @author: surfree
	 * @since:	2016. 6. 10.
	 */
	public EquipInfo selectEquipInfoByIpPrimary(String ipPrimary) {
		return equipInfoDao.selectEquipInfoByIpPrimary(ipPrimary);
	}
	
	/**
	 * 장비 정보 추가
	 * 
	 * @param equipInfo 장비 정보
	 * @param equipServiceRelation 서비스 그룹 관계
	 * @param equipGroupRelation 장비 그룹 관계
	 * @return
	 * <ul>
	 * <li>성공: 추가된 장비 레코드ID</li>
	 * <li>실패: -1</li>
	 * </ul>
	 * @exception DataAccessException
	 * @since
	 * <ul> 
	 * <li>2015.12.8 surfree
	 * 		AddCmEquipInfo와 AddCmEquipInfo2 통합<br/>
	 * 		Equip추가 후 레코드 ID 자동 증감 사용<br/>
	 * 		이전 버전에서는 AssignuseragtArs 테이블 관련 ARS 승인자 재배치 코드가 포함되어 있었음<br/>
	 * </li>
	 * <li>2016.6.3 surfree: plEquipArs(장비 ARS 승인 정보)가 정책 구현 변경으로 인해 필요 없어짐</li>
	 * </ul>
	 */
	@Transactional
	public int insertEquipInfo(EquipInfo equipInfo, EquipServiceRelation equipServiceRelation, EquipGroupRelation equipGroupRelation) {
		int iRet = 0;
		
		equipInfoDao.insertEquipInfo(equipInfo);
		
		equipServiceRelation.setEquipRecid(equipInfo.getRecid());
		equipGroupRelation.setEquipRecid(equipInfo.getRecid());
		
		equipServiceDao.insertEquipServierRelation(equipServiceRelation);
		equipGroupRelationDao.insertEquipGroupRelation(equipGroupRelation);
		
		iRet = equipInfo.getRecid();
		 
		return iRet;
	}
	
	/**
	 * 장비 정보 수정
	 * 
	 * @param equipInfo 장비 정보
	 * @param equipServiceRelation 서비스 그룹 관계
	 * @param equipGroupRelation 장비 그룹 관계
	 * @return
	 * @exception DataAccessException
	 * @author: surfree
	 * @since:	2016. 6. 10.
	 * <ul>
	 * <li> 2016.6.10 surfree: plEquipArs(장비 ARS 승인 정보)가 정책 구현 변경으로 인해 필요 없어짐, 이름 변경 modifyCmEquipInfo2 -> updateEquipInfo</li>
	 * </ul>
	 */
	@Transactional
	public int updateEquipInfo(EquipInfo equipInfo, EquipServiceRelation equipServiceRelation, EquipGroupRelation equipGroupRelation) {
		
		int iRet = 0;
//		TODO: surfree 2016.6.10 아래 부분은 다시 활성화 해서 장비 부분과 관계를 맞출지 여부 결정
//		int equipId = 0;
//		equipId = equipInfo.getRecid();
		
		iRet = equipInfoDao.updateEquipInfoByRecid(equipInfo);
		
		equipServiceDao.updateEquipServiceRelation(equipServiceRelation);
		equipGroupRelationDao.updateEquipGroupRelationByEquipId(equipGroupRelation);
		
		return iRet;
	}
	
	/**
	 * 장비 정보 삭제(장비 레코드 ID 목록)
	 * <p>
	 * 연관 정책 및 관계 모두 삭제
	 * </p>
	 * 
	 * @param idsList 삭제할 장비 레코드ID 목록
	 * @return
	 * @exception DataAccessException
	 */
	@Transactional
	public int deleteEquipInfoByRecidList(List<Integer> idsList) {
		int iRet = 0;
		
		// 장비 관계 삭제 (장비그룹)
		equipGroupRelationDao.deleteEquipRelationByEquipIds(idsList);

		// 장비 계정 모두 삭제
		equipAccountDao.deleteEquipAccountByEquipIdList(idsList);
		
		// 장비 서비스 관계 삭제
		equipServiceDao.deleteEquipServiceRelationByEquipIdList(idsList);
		
		// 장비 정보 삭제
		equipInfoDao.deleteEquipInfoByRecidList(idsList);
		
		iRet = 1;
		 
		return iRet;
	}
	
	/**
	 * 모든 장비 정보 삭제
	 * <p>
	 * 연관 정책 및 관계 모두 삭제
	 * </p>
	 * 
	 * @author: swcho
	 * @since:	2016. 3. 2.
	 */
	public void deleteEquipInfoAll(){
		equipGroupRelationDao.deleteEquipRelation();
		equipAccountDao.deleteEquipAccountAll();
		equipServiceDao.deleteEquipServiceRelationAll();
		equipInfoDao.deleteEquipInfoAll();
	}
}
