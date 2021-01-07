package sga.sol.ac.core.service.equip;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sga.sol.ac.core.dao.equip.EquipGroupInfoDao;
import sga.sol.ac.core.dao.equip.EquipGroupRelationDao;
import sga.sol.ac.core.entity.equip.EquipGroupInfo;
import sga.sol.ac.core.entity.equip.EquipGroupRelation;
import sga.sol.ac.core.entity.equip.EquipMoveRecids;

@Service
public class EquipGroupRelationService {
	@Autowired
	private EquipGroupRelationDao equipGroupRelationDao;
	
	@Autowired
	private EquipGroupInfoDao equipGroupInfoDao;
	
	/**
	 * 장비 그룹ID로 장비와 그룹 관계 조회
	 * 
	 * @param grpid
	 * @return
	 */
	public List<EquipGroupRelation> selectEquipGroupRelationByGrpid(int grpid){
		return equipGroupRelationDao.selectEquipGroupRelationByGrpid(grpid);
	}
	
	public void getRecursiveGroupPathData(int parentGroupRecid, String groupPath, EquipGroupInfo resultItem){
		
		if(parentGroupRecid != 0){
			EquipGroupInfo equipGroupInfo = equipGroupInfoDao.selectEquipGroupInfoByRecid(parentGroupRecid);
			groupPath = "/"+equipGroupInfo.getGroupName() + groupPath;
			resultItem.setGroupPath(groupPath);
			getRecursiveGroupPathData(equipGroupInfo.getParentGroupRecId(), groupPath, resultItem);
		}
	}
	
	@Transactional
	public void updateRecursiveChildGroupPath(EquipGroupInfo resultItem, int groupRecid){
		List<EquipGroupInfo> childList = equipGroupInfoDao.selectChildEquipGroupList(groupRecid);
		
		if(childList.size()>0){
			for(EquipGroupInfo childRowData : childList){
				childRowData.setGroupPath(resultItem.getGroupPath() + "/" + childRowData.getGroupName());
				childRowData.setUpdateIp(resultItem.getUpdateIp());
				childRowData.setUpdateUser(resultItem.getUpdateUser());
				
				equipGroupInfoDao.updateEquipGroupInfoByRecid(childRowData);
				
				updateRecursiveChildGroupPath(childRowData, childRowData.getRecid());
			}
		}
	}
	
	/**
	 * 장비 그룹 관계 정보 추가
	 * 
	 * @param equipGroupRelation
	 * @return
	 */
	public int insertEquipGroupRelation(EquipGroupRelation equipGroupRelation) {
		return equipGroupRelationDao.insertEquipGroupRelation(equipGroupRelation);
	}

	/**
	 * 장비 그룹 관계 정보 수정
	 * 
	 * @param equipGroupRelation
	 * @return
	 */
	public int updateEquipGroupRelationByEquipId(EquipGroupRelation equipGroupRelation) {
		return equipGroupRelationDao.updateEquipGroupRelationByEquipId(equipGroupRelation);
	}
	
	@Transactional
	public int moveEquipGroupService(EquipMoveRecids equipMoveRecids){
		
		int resultCnt = 0;
		
		if(equipMoveRecids!=null){
			int groupRecid = equipMoveRecids.getGroupRecid();
			List<Integer> equipRecids = equipMoveRecids.getEquipRecids();
			String updateUser = equipMoveRecids.getUpdateUser();
			String updateIp = equipMoveRecids.getUpdateIp();
			
			if(equipRecids.size()>0){
				for(int equipRecid : equipRecids){
					EquipGroupRelation equipGroupRelation = new EquipGroupRelation();
					equipGroupRelation.setEquipRecid(equipRecid);
					equipGroupRelation.setEquipGrpId(groupRecid);
					equipGroupRelation.setUpdateIp(updateIp);
					equipGroupRelation.setUpdateUser(updateUser);
					
					equipGroupRelationDao.updateEquipGroupRelationByEquipId(equipGroupRelation);
				}
				
				resultCnt = 1;
			}
		}
		return resultCnt;
	}
	
	/**
	 * 장비그룹(tree) --> 그룹(tree) 서비스
	 * @param params
	 * @author LeeJungYoung
	 * @return
	 */
	@Transactional
	public int moveEquipGroupToParentGroup(Map params){
		int resultCnt = 0;
		
		int parentGroupRecid =Integer.parseInt((String)params.get("parentGroupRecid"));
		
		String groupPath = "";
		EquipGroupInfo updateGroupData = equipGroupInfoDao.selectEquipGroupInfoByRecid(Integer.parseInt((String)params.get("groupRecid")));
		
		getRecursiveGroupPathData(parentGroupRecid,groupPath, updateGroupData);
		
		if(parentGroupRecid == 0){
			updateGroupData.setGroupPath("/" + updateGroupData.getGroupName());
		}else{
			updateGroupData.setGroupPath(updateGroupData.getGroupPath() + "/"+updateGroupData.getGroupName());
		}
		
		updateGroupData.setParentGroupRecId(parentGroupRecid);
		updateGroupData.setUpdateUser((String)params.get("updateUser"));
		updateGroupData.setUpdateIp((String)params.get("updateIp"));
		
		int recidCnt = equipGroupInfoDao.updateEquipGroupInfoByRecidForMoveGroup(updateGroupData);
		
		if(recidCnt>0){
			updateRecursiveChildGroupPath(updateGroupData, updateGroupData.getRecid());
			resultCnt = 1;
		}
		
		return resultCnt;
	}

	/**
	 * 장비 그룹 관계 정보 삭제
	 * 
	 * @param equipId
	 * @return
	 */
	public int deleteEquipGroupRelationByEquipId(int equipId) {
		return equipGroupRelationDao.deleteEquipGroupRelationByEquipId(equipId);
	}
}
