package sga.sol.ac.core.dao.equip;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.criteria.SortParam;
import sga.sol.ac.core.entity.equip.EquipVendor;

/**
 * 장비 제조사 관리 DAO
 * 
 * @author LEE JUNG YOUNG
 * @since 2015. 9. 9
 *
 */
@Repository
public class EquipVendorDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getEquipVendorListCount(SearchParams param) {
		return sqlSessionTemplate.selectOne("getEquipVendorListCount", param);
	}
	
	public List<EquipVendor> selectEquipVendorListAll() {
		SearchParams params = new SearchParams();
		EquipVendor equipVendor = new EquipVendor();
		
		params.setEntity(equipVendor);
		params.setSort(new SortParam("recid", SortParam.DIR_DESC));
		
		return sqlSessionTemplate.selectList("selectEquipVendorList", params);
	}
	
	public List<EquipVendor> selectEquipVendorList(SearchParams param) {
		return sqlSessionTemplate.selectList("selectEquipVendorList", param);
	}
	
	public EquipVendor getEquipVendorInfoByRecid(int recid) {
		EquipVendor equipVendor = new EquipVendor();
		equipVendor.setRecid(recid);
		
		return sqlSessionTemplate.selectOne("selectEquipVendorByParam", equipVendor);
	}
	
	public EquipVendor getEquipVendorInfoByName(String companyName) {
		EquipVendor equipVendor = new EquipVendor();
		equipVendor.setCompanyName(companyName);
		
		return sqlSessionTemplate.selectOne("selectEquipVendorByParam", equipVendor);
	}
	
	public int insertEquipVendor(EquipVendor param) {
		return sqlSessionTemplate.insert("insertEquipVendor", param);
	}
	
	public int updateEquipVendor(EquipVendor param) {
		return sqlSessionTemplate.update("updateEquipVendor", param);
	}
	
	public int deleteEquipVendor(int recid) {
		return sqlSessionTemplate.delete("deleteEquipVendor", recid);
	}
	
	public int deleteEquipVendorByRecids(List<Integer> recidList) {
		return sqlSessionTemplate.delete("deleteEquipVendorByRecidList", recidList);
	}
	
	public int updateEquipVendorNameForDelete(List<String> recidList) {
		return sqlSessionTemplate.update("updateEquipVendorNameForDelete", recidList);
	}
}
