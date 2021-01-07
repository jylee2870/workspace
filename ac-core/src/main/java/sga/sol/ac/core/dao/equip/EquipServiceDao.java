package sga.sol.ac.core.dao.equip;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.criteria.SortParam;
import sga.sol.ac.core.entity.equip.EquipService;
import sga.sol.ac.core.entity.equip.EquipServiceRelation;

/**
 * 장비 서비스 DAO
 * 
 * @author surfree
 *
 */
@Repository
public class EquipServiceDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getEquipServiceListCount(SearchParams param) {
		return sqlSessionTemplate.selectOne("getEquipServiceListCount", param);
	}
	
	public List<EquipService> selectEquipServiceList(SearchParams param) {
		return sqlSessionTemplate.selectList("selectEquipServiceList", param);
	}
	
	public List<EquipService> selectEquipServiceListAll() {
		SearchParams params = new SearchParams();
		EquipService equipService = new EquipService();
		
		params.setEntity(equipService);
		params.setSort(new SortParam("recid", SortParam.DIR_DESC));
		
		return sqlSessionTemplate.selectList("selectEquipServiceList", params);
	}
	
	public EquipService getEquipServiceInfoByRecid(int recid) {
		EquipService equipService = new EquipService();
		equipService.setRecid(recid);
		
		return sqlSessionTemplate.selectOne("selectEquipServiceByParam", equipService);
	}

	public EquipService getEquipServiceInfoByName(String serviceName) {
		EquipService equipService = new EquipService();
		equipService.setServiceName(serviceName);
		
		return sqlSessionTemplate.selectOne("selectEquipServiceByParam", equipService);
	}

	public int insertEquipServiceInfo(EquipService equipServiceInfo) {
		return sqlSessionTemplate.insert("insertEquipServiceInfo", equipServiceInfo);
	}

	public int updateEquipServiceInfoByRecid(EquipService equipServiceInfo) {
		return sqlSessionTemplate.update("updateEquipServiceInfoByRecid", equipServiceInfo);
	}

	public int deleteEquipServiceInfoByRecid(int recid) {
		return sqlSessionTemplate.delete("deleteEquipServiceInfoByRecid", recid);
	}

	public int deleteEquipServiceByRecidList(List<Integer> recidList) {
		return sqlSessionTemplate.delete("deleteEquipServiceByRecidList", recidList);
	}
	
	/**
	 * 삭제시 연관 장비 service_recid = 1 
	 * @param recidList
	 * @return
	 */
	public int updateEquipServiceRecidForDelete(List<Integer> recidList) {
		return sqlSessionTemplate.update("updateEquipServiceRecidForDelete", recidList);
	}

	public EquipServiceRelation getEquipServiceRelationByEquipId(int equipId) {
		return sqlSessionTemplate.selectOne("getEquipServiceRelationByEquipId", equipId);
	}

	public int insertEquipServierRelation(EquipServiceRelation equipServiceRelation) {
		return sqlSessionTemplate.insert("insertEquipServiceRelation", equipServiceRelation);
	}
	
	public int updateEquipServiceRelation(EquipServiceRelation equipServiceRelation) {
		return sqlSessionTemplate.update("updateEquipServiceRelation",equipServiceRelation);
	}
	
	public int deleteEquipServiceRelationByEquipId(int equipId) {
		return sqlSessionTemplate.delete("deleteEquipServiceRelationByEquipId", equipId);
	}
	
	public int deleteEquipServiceRelationByEquipIdList(List<Integer> equipIdList) {
		return sqlSessionTemplate.delete("deleteEquipServiceRelationByEquipIdList", equipIdList);
	}
	
	/*
	 * 2016.3.2 swcho 모든 장비 서비스 삭제
	 */
	public int deleteEquipServiceRelationAll() {
		return sqlSessionTemplate.delete("deleteEquipServiceRelationByEquipIdList");
	}
}
