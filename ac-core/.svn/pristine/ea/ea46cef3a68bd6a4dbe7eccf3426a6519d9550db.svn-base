package sga.sol.ac.core.dao.equip;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.equip.EquipInfo;

/**
 * 장비 정보 DAO
 * 
 * @author JannyWang
 *
 */
@Repository
public class EquipInfoDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int getEquipInfoListCount(SearchParams params) {
		Validate.notNull(params, "params must not be null");
		
		return sqlSessionTemplate.selectOne("getEquipInfoListCount", params);
	}
	
	public List<EquipInfo> selectEquipInfoList(SearchParams params) {
		Validate.notNull(params, "params must not be null");
		
		return sqlSessionTemplate.selectList("selectEquipInfoList", params);
	}
	
	public List<EquipInfo> selectEquipListByGroupList(List<Integer> list) {
		Validate.notNull(list, "list must not be null");
		Validate.notEmpty(list, "list must not be empty");
		
		return sqlSessionTemplate.selectList("selectEquipListByGroupList", list);
	}
	
	public EquipInfo selectEquipInfoByRecid(int recid) {
		EquipInfo param = new EquipInfo();
		param.setRecid(recid);
		
		return sqlSessionTemplate.selectOne("selectEquipInfoByParam", param);
	}
	
	public EquipInfo selectEquipInfoByName(String equipName) {
		EquipInfo param = new EquipInfo();
		param.setEquipName(equipName);
		
		return sqlSessionTemplate.selectOne("selectEquipInfoByParam", param);
	}
	
	public EquipInfo selectEquipInfoByIpPrimary(String ipPrimary) {
		EquipInfo param = new EquipInfo();
		param.setIpPrimary(ipPrimary);
		
		return sqlSessionTemplate.selectOne("selectEquipInfoByParam", param);
	}
	
	public int insertEquipInfo(EquipInfo equipInfo) {
		return sqlSessionTemplate.insert("insertEquipInfo", equipInfo);
	}
	
	public int updateEquipInfoByRecid(EquipInfo equipInfo) {
		return sqlSessionTemplate.update("updateEquipInfoByRecid", equipInfo);
	}
	
	public int deleteEquipInfoByRecid(int recid) {
		return sqlSessionTemplate.delete("deleteEquipInfoByRecid", recid);
	}
	
	public int deleteEquipInfoByRecidList(List<Integer> recidList) {
		Validate.notNull(recidList, "recidList must not be null");
		Validate.notEmpty(recidList, "recidList must not be empty");
		
		return sqlSessionTemplate.delete("deleteEquipInfoByRecidList", recidList);
	}
	
	/*
	 * 2016.3.2 모든 장비 정보 삭제
	 */
	public int deleteEquipInfoAll() {
		return sqlSessionTemplate.delete("deleteEquipInfoByRecidList");
	}
}