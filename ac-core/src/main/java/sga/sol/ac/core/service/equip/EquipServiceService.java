package sga.sol.ac.core.service.equip;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sga.sol.ac.core.dao.equip.EquipServiceDao;
import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.equip.EquipService;
import sga.sol.ac.core.entity.equip.EquipServiceRelation;

/**
 * 장비 서비스 관리 서비스
 * 
 * @author surfree
 *
 */
@Service
public class EquipServiceService {
	@Autowired
	private EquipServiceDao equipServiceDao;
	
	/**
	 * 장비 서비스 목록 개수 조회(조건)
	 * 
	 * @param param
	 * @return
	 */
	public int getEquipServiceListCount(SearchParams param) {
		return equipServiceDao.getEquipServiceListCount(param);
	}
	
	/**
	 * 장비 서비스 목록 조회(조건, 페이징)
	 * 
	 * @param param
	 * @return
	 */
	public List<EquipService> selectEquipServiceList(SearchParams param) {
		return equipServiceDao.selectEquipServiceList(param);
	}
	
	/**
	 * 장비 서비스 목록 조회
	 * 
	 * @return
	 */
	public List<EquipService> selectEquipServiceListAll() {
		return equipServiceDao.selectEquipServiceListAll();
	}
	
	/**
	 * 장비 서비스 정보 조회(레코드ID)
	 * 
	 * @param recid
	 * @return
	 */
	public EquipService getEquipServiceInfoByRecid(int recid) {
		return equipServiceDao.getEquipServiceInfoByRecid(recid);
	}
	
	/**
	 * 장비 서비스 조회(서비스 이름)
	 * 
	 * @param serviceName
	 * @return
	 */
	public EquipService getEquipServiceInfoByName(String serviceName) {
		return equipServiceDao.getEquipServiceInfoByName(serviceName);
	}
	
	/**
	 * 장비 서비스 추가
	 * 
	 * @param equipServiceInfo
	 * @return
	 */
	public int insertEquipServiceInfo(EquipService equipServiceInfo) {
		return equipServiceDao.insertEquipServiceInfo(equipServiceInfo);
	}
	
	/**
	 * 장비 서비스 수정
	 * 
	 * @param equipServiceInfo
	 * @return
	 */
	public int updateEquipServiceInfoByRecid(EquipService equipServiceInfo) {
		Validate.isTrue(equipServiceInfo.getRecid() > 0, "recid value must be greater than zero");
		
		return equipServiceDao.updateEquipServiceInfoByRecid(equipServiceInfo);
	}

	/**
	 * 장비 서비스 삭제(레코드ID)
	 * 
	 * @param recid
	 * @return
	 */
	public int deleteEquipServiceInfoByRecid(int recid) {
		// TODO: 연관 관계를 먼저 삭제해야 함
		return equipServiceDao.deleteEquipServiceInfoByRecid(recid);
	}
	
	/**
	 * 장비 레코드ID로 장비 서비스 관계 조회
	 * 
	 * @param equipId
	 * @return
	 */
	public EquipServiceRelation getEquipServiceRelationByEquipId(int equipId){
		return equipServiceDao.getEquipServiceRelationByEquipId(equipId);
	}
	
	/**
	 * 장비 레코드ID 목록으로 장비 서비스 삭제
	 * 
	 * @param equipIdList 장비 레코드ID 목록
	 * @return
	 */
	public int deleteEquipServiceRelationByEquipIdList(List<Integer> equipIdList) {
		return equipServiceDao.deleteEquipServiceRelationByEquipIdList(equipIdList);
	}

	/**
	 * 장비 서비스 레코드ID 목록으로 장비 서비스 삭제
	 * 
	 * @param idsList 장비 서비스 레코드ID 목록
	 * @return
	 */
	public int deleteEquipServiceInfoByRecidList(List<Integer> idsList) {
		equipServiceDao.updateEquipServiceRecidForDelete(idsList);
		return equipServiceDao.deleteEquipServiceByRecidList(idsList);
	}
}
