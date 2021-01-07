package sga.sol.ac.core.service.equip;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sga.sol.ac.core.dao.equip.EquipVendorDao;
import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.equip.EquipVendor;

/**
 * 장비 제조사 서비스
 * 
 * @author LEE JUNG YOUNG
 * @since	2015. 2. 26
 */
@Service
public class EquipVendorService {
	@Autowired
	private EquipVendorDao equipVendorDao;
	
	/**
	 * 장비 제조사 목록 개수 조회(조건)
	 * 
	 * @Date		: 2015. 9. 08. 
	 * @Author		: LEEJUNGYOUNG
	 */
	public int getEquipVendorListCount(SearchParams param){
		return equipVendorDao.getEquipVendorListCount(param); 
	}
	
	/**
	 * 장비 제조사 목록 조회
	 * 
	 * @return
	 */
	public List<EquipVendor> selectEquipVendorListAll() {
		return equipVendorDao.selectEquipVendorListAll();
	}
	
	/**
	 * 장비 제조사 목록 조회(조건, 페이징)
	 * 
	 * @Date		: 2015. 9. 08. 
	 * @Author		: LEEJUNGYOUNG
	 * @Description	: Vendor 리스트 조회
	 */
	public List<EquipVendor> selectEquipVendorList(SearchParams param){
		return equipVendorDao.selectEquipVendorList(param); 
	}
	
	
	/**
	 * 장비 제조사 정보 조회(레코드ID)
	 * 
	 * @param recid
	 * @return
	 */
	public EquipVendor getEquipVendorInfoByRecid(int recid) {
		return equipVendorDao.getEquipVendorInfoByRecid(recid);
	}
	
	/**
	 * 장비 제조사 정보 조회(제조사 이름)
	 * 
	 * @param companyName
	 * @return
	 */
	public EquipVendor getEquipVendorInfoByName(String companyName) {
		return equipVendorDao.getEquipVendorInfoByName(companyName);
	}
	
	/**
	 * 장비 제조사 추가
	 * 
	 * @Date		: 2015. 9. 08. 
	 * @Author		: LEEJUNGYOUNG
	 */
	public int insertEquipVendor(EquipVendor param){
		return equipVendorDao.insertEquipVendor(param); 
	}
	
	/**
	 * 장비 제조사 수정
	 * 
	 * @Date		: 2015. 9. 08. 
	 * @Author		: LEEJUNGYOUNG
	 */
	public int updateEquipVendor(EquipVendor param){
		return equipVendorDao.updateEquipVendor(param); 
	}
	
	/**
	 * 장비 제조사 삭제(레코드 ID 목록)
	 * 
	 * @Date		: 2015. 9. 08. 
	 * @Author		: LEEJUNGYOUNG
	 */
	public int deleteEquipVendor(String[] recidArr){
		int deleteCnt = 0;
		if(recidArr.length>0){
			for(int i=0; i<recidArr.length; i++){
				equipVendorDao.deleteEquipVendor(Integer.parseInt(recidArr[i]));
			}
			
			deleteCnt = 1;
		}
		return deleteCnt;
	}
	
	/**
	 * 장비 제조사 삭제(레코드 ID 목록)
	 * 
	 * @param recidList
	 * @return
	 */
	public int deleteEquipVendorByRecids(List<Integer> recidList) {
		return equipVendorDao.deleteEquipVendorByRecids(recidList);
	}
	
	public int updateEquipVendorNameForDelete(List<String> recidList) {
		return equipVendorDao.updateEquipVendorNameForDelete(recidList);
	}
}
