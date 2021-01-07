package sga.sol.ac.core.service.equip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sga.sol.ac.core.dao.equip.EquipOsDao;
import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.equip.EquipOsInfo;

/**
 * 장비 OS 서비스
 * 
 * @author LEE JUNG YOUNG
 * @since 2015. 02. 26.
 *
 */
@Service
public class EquipOsService {
	@Autowired
	private EquipOsDao equipOsDao;
	
	/**
	 * @Date		: 2015. 9. 08. 
	 * @Author		: LEEJUNGYOUNG
	 * @Description	: OS 리스트 조회 토탈 카운트
	 */
	public int getEquipOsListCount(SearchParams params){
		return equipOsDao.getEquipOsListCount(params); 
	}
	
	/**
	 * 장비 OS 목록 조회
	 * 
	 * @return
	 */
	public List<EquipOsInfo> selectEquipOsListAll(){
		return equipOsDao.selectEquipOsListAll();
	}
	
	/**
	 * 장비 OS 목록
	 * @Date		: 2015. 9. 08. 
	 * @Author		: LEEJUNGYOUNG
	 * @Description	: OS 리스트 조회
	 */
	public List<EquipOsInfo> selectEquipOsList(SearchParams param){
		return equipOsDao.selectEquipOsList(param); 
	}
	
	
	public EquipOsInfo getEquipOsInfoByRecid(int recid) {
		return equipOsDao.getEquipOsInfoByRecid(recid);
	}
	
	public EquipOsInfo getEquipOsInfoByName(String name) {
		return equipOsDao.getEquipOsInfoByName(name);
	}
	
	/**
	 * 장비 OS 추가
	 * 
	 * @Date		: 2015. 9. 08. 
	 * @Author		: LEEJUNGYOUNG
	 */
	public int insertEquipOs(EquipOsInfo equipOs){
		return equipOsDao.insertEquipOs(equipOs); 
	}
	
	/**
	 * 장비 OS 수정
	 * 
	 * @Date		: 2015. 9. 08. 
	 * @Author		: LEEJUNGYOUNG
	 */
	public int updateEquipOs(EquipOsInfo equipOs){
		return equipOsDao.updateEquipOs(equipOs); 
	}
	
	/**
	 * 장비 OS 삭제(레코드 ID 목록)
	 * 
	 * @param recidList
	 * @return
	 */
	public int deleteEquipOsByRecidList(List<Integer> recidList) {
		equipOsDao.releaseEquipOsByIds(recidList);
		return equipOsDao.deleteEquipOsByRecidList(recidList);
	}
	
}
