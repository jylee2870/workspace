package sga.sol.ac.core.dao.equip;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.equip.EquipGroupRelation;

/**
 * 장비 그룹과 장비 관계 DAO
 * 
 * @author surfree
 */
@Repository
public class EquipGroupRelationDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<EquipGroupRelation> selectEquipGroupRelationByGrpid(int grpid) {
		return sqlSessionTemplate.selectList("selectEquipGroupRelationByGrpid",grpid);
	}
	
	public int insertEquipGroupRelation(EquipGroupRelation equipGroupRelation) {
		return sqlSessionTemplate.insert("insertEquipGroupRelation", equipGroupRelation);
	}

	public int updateEquipGroupRelationByEquipId(EquipGroupRelation cmEquipEquipGroup) {
		return sqlSessionTemplate.update("updateEquipGroupRelationByEquipId", cmEquipEquipGroup);
	}
	
	public int clearEquipGroupRelationByGroupIds(List<Integer> ids) {
		return sqlSessionTemplate.update("clearEquipGroupRelationByGroupIds", ids);
	}
	
	public int deleteEquipGroupRelationByEquipId(int equipId) {
		return sqlSessionTemplate.delete("deleteEquipGroupRelationByEquipId", equipId);
	}
	
	public int deleteEquipRelationByEquipIds(List<Integer> ids) {
		return sqlSessionTemplate.delete("deleteEquipRelationByEquipIds", ids);
	}
	
	/*
	 * 2016.3.2 모든 장비 그룹 관계 삭제
	 */
	public int deleteEquipRelation(){
		return sqlSessionTemplate.delete("deleteEquipRelationByEquipIds");
	}
}
