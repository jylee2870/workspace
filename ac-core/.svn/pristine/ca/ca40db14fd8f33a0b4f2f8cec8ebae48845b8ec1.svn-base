package sga.sol.ac.core.dao.equip;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.criteria.SortParam;
import sga.sol.ac.core.entity.equip.EquipOsInfo;

/**
 * 장비 운영체제 DAO
 * 
 * @author LEE JUNG YOUNG
 * @since 2015. 9. 9.
 */
@Repository
public class EquipOsDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getEquipOsListCount(SearchParams params){
		return sqlSessionTemplate.selectOne("getEquipOsListCount", params);
	}
	
	public List<EquipOsInfo> selectEquipOsList(SearchParams params){
		return sqlSessionTemplate.selectList("selectEquipOsList", params);
	}
	
	public List<EquipOsInfo> selectEquipOsListAll(){
		SearchParams params = new SearchParams();
		EquipOsInfo equipOs = new EquipOsInfo();
		
		params.setEntity(equipOs);
		params.setSort(new SortParam("recid", SortParam.DIR_DESC));
		
		return sqlSessionTemplate.selectList("selectEquipOsList", params);
	}
	
	public EquipOsInfo getEquipOsInfoByRecid(int recid){
		EquipOsInfo equipOs = new EquipOsInfo();
		equipOs.setRecid(recid);
		
		return sqlSessionTemplate.selectOne("selectEquipOsInfoByParam", equipOs);
	}
	
	public EquipOsInfo getEquipOsInfoByName(String name) {
		EquipOsInfo equipOs = new EquipOsInfo();
		equipOs.setOsName(name);
		
		return sqlSessionTemplate.selectOne("selectEquipOsInfoByParam", equipOs);
	}
	
	public int insertEquipOs(EquipOsInfo equipOs){
		return sqlSessionTemplate.insert("insertEquipOs", equipOs);
	}
	
	public int updateEquipOs(EquipOsInfo equipOs){
		Validate.isTrue(equipOs.getRecid() > 0, "recid value must be greater than zero");
		return sqlSessionTemplate.update("updateEquipOs", equipOs);
	}
	
	public int deleteEquipOs(int recid){
		return sqlSessionTemplate.delete("deleteEquipOs", recid);
	}
	
	public int deleteEquipOsByRecidList(List<Integer> recidList) {
		return sqlSessionTemplate.delete("deleteEquipOsByRecidList", recidList);
	}
	
	public int releaseEquipOsByIds(List<Integer> recidList){
		return sqlSessionTemplate.update("releaseEquipOsByIds", recidList);
	}
}
