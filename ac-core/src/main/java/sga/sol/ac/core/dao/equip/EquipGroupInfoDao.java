package sga.sol.ac.core.dao.equip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.equip.EquipGroupInfo;

/**
 * 장비 그룹 DAO
 *
 * @author surfree
 */
@Repository
public class EquipGroupInfoDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<EquipGroupInfo> selectEquipGroupListAll() {
		return sqlSessionTemplate.selectList("selectEquipGroupListAll");
	}
	
	public EquipGroupInfo selectEquipGroupInfoByRecid(int recid) {
		return sqlSessionTemplate.selectOne( "selectEquipGroupInfoByRecid", recid);
	}
	
	public EquipGroupInfo selectEquipGroupInfoByEquipId(int equipid) {
		return sqlSessionTemplate.selectOne( "selectEquipGroupInfoByEquipId", equipid);
	}
	
	public EquipGroupInfo selectEquipGroupInfoByGroupPath(String groupPath) {
		return sqlSessionTemplate.selectOne("selectEquipGroupInfoByGroupPath",groupPath);
	}
	
	public List<EquipGroupInfo> selectChildEquipGroupList(int parentGroupId) {
		return sqlSessionTemplate.selectList("selectChildEquipGroupList", parentGroupId);
	}
	
	public List<EquipGroupInfo> selectSiblingEquipGroupList(int recid) {
		return sqlSessionTemplate.selectList("selectSiblingEquipGroupList", recid);
	}
	
	public List<EquipGroupInfo> selectAllParentEquipGroupList(int recid) {
		return sqlSessionTemplate.selectList("selectAllParentEquipGroupList", recid);
	}
	
	public List<EquipGroupInfo> selectAllChildEquipGroupList(int recid) {
		return sqlSessionTemplate.selectList("selectAllChildEquipGroupList", recid);
	}
	
	public int insertEquipGroupInfo(EquipGroupInfo equipGroupInfo) {
		return sqlSessionTemplate.insert("insertEquipGroupInfo", equipGroupInfo);
	}
	
	public int updateEquipGroupInfoByRecid(EquipGroupInfo equipGroupInfo) {
		return sqlSessionTemplate.update("updateEquipGroupInfoByRecid", equipGroupInfo);
	}
	
	/**
	 * 장비 그룹간의 이동을 위하여 정의
	 * 
	 * @param equipGroupInfo
	 * @author LeeJungYoung
	 * @Date 2015.09.16
	 * @return
	 */
	public int updateEquipGroupInfoByRecidForMoveGroup(EquipGroupInfo equipGroupInfo) {
		return sqlSessionTemplate.update("updateEquipGroupInfoByRecidForMoveGroup", equipGroupInfo);
	}
	
	public int updateEquipGroupInfoByGroupPath(int groupRecid, String currentGroupPath, String newGroupPath) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("recid", groupRecid);
		map.put("oldGroupPath", currentGroupPath);
		map.put("newGroupPath", newGroupPath);
		
		return sqlSessionTemplate.update("updateEquipGroupInfoByGroupPath", map);
	}

	public int deleteEquipGroupInfoByRecid(int recid) {
		return sqlSessionTemplate.delete("deleteEquipGroupInfoByRecid", recid);
	}
	
	/**
	 * 그룹 레코드ID로 해당 그룹 삭제
	 * 
	 * @param ids
	 * @return
	 * @author: surfree
	 * @date:	2016. 6. 1.
	 */
	public int deleteEquipGroupInfoByIds(List<Integer> ids) {
		Validate.isTrue(ids.size() > 0, "ids valud size must be greater than zero");
		
		return sqlSessionTemplate.delete("deleteEquipGroupInfoByIds", ids);
	}
}
