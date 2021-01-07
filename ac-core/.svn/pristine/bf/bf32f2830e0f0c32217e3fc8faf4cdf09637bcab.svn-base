package sga.sol.ac.core.service.equip;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sga.sol.ac.core.dao.equip.EquipGroupInfoDao;
import sga.sol.ac.core.dao.equip.EquipGroupRelationDao;
import sga.sol.ac.core.entity.equip.EquipGroupInfo;

/**
 * 장비 그룹 정보 서비스 
 *
 * @author		: swcho 
 * @date		: 2014. 9. 29.
 * <ul>
 * <li>2016.6.1 surfree: 필요없는 메소드 정리</li>
 * </ul>
 */
@Service
public class EquipGroupInfoService{
	public final static String PATH_SEPARATOR = "/";
	
	@Autowired
	private EquipGroupInfoDao equipGroupInfoDao;
	
	@Autowired
	private EquipGroupRelationDao equipGroupRelationDao;
	
	/**
	 * 모든 장비 그룹 정보 조회
	 * 
	 * @return
	 * @author: surfree
	 * @date:	2016. 5. 26.
	 */
	public List<EquipGroupInfo> selectEquipGroupListAll() {
		return equipGroupInfoDao.selectEquipGroupListAll();
	}
	
	/**
	 * 장비 그룹 정보 조회(레코드ID)
	 * @param recid
	 * @return
	 */
	public EquipGroupInfo selectEquipGroupInfoByRecid(int recid) {
		return equipGroupInfoDao.selectEquipGroupInfoByRecid(recid);
	}
	
	/**
	 * 장비 그룹 정보 조회(장비 레코드ID)
	 * @param equipid
	 * @return
	 */
	public EquipGroupInfo selectEquipGroupInfoByEquipId(int equipid) {
		return equipGroupInfoDao.selectEquipGroupInfoByEquipId(equipid);
	}
	
	/**
	 * 장비 그룹 정보 조회(장비 그룹 경로)
	 * 
	 * @param groupPath 장비 그룹 경로
	 * @return EquipGroupInfo
	 * @author: surfree
	 * @date:	2016. 5. 26.
	 */
	public EquipGroupInfo selectEquipGroupInfoByGroupPath(String groupPath){
		return equipGroupInfoDao.selectEquipGroupInfoByGroupPath(groupPath);
	}
	
	/**
	 * 지정한 장비 그룹의 하위 장비 그룹 목록을 조회
	 * <p>
	 * parentGroupId 레코드 객체 제외
	 * </p>
	 * 
	 * @param parentGroupId 장비그룹 레코드ID
	 * @return EquipGroupInfo 객체 목록
	 * @author surfree
	 * @since 2016. 5. 26.
	 */
	public List<EquipGroupInfo> selectChildEquipGroupList(int parentGroupId) {
		return equipGroupInfoDao.selectChildEquipGroupList(parentGroupId);
	}
	
	/**
	 * 지정한 장비 그룹과 동일 레벨의 장비 그룹 목록을 조회
	 * <p>
	 * recid 레코드 객체 포함
	 * </p>
	 * 
	 * @param recid 장비그룹 레코드ID
	 * @return EquipGroupInfo 객체 목록
	 * @author surfree
	 * @since 2016. 5. 26.
	 */
	public List<EquipGroupInfo> selectSiblingEquipGroupList(int recid) {
		return equipGroupInfoDao.selectSiblingEquipGroupList(recid);
	}
	
	/**
	 * 지정한 장비 그룹을 포함한 모든 상위 장비 그룹 목록을 조회
	 * <p>
	 * recid 레코드 객체 포함
	 * </p>
	 * @param recid 장비 그룹 레코드ID
	 * @return EquipGroupInfo 객체 목록
	 * @author: surfree
	 * @since:	2016. 6. 21.
	 */
	public List<EquipGroupInfo> selectAllParentEquipGroupList(int recid) {
		return equipGroupInfoDao.selectAllParentEquipGroupList(recid);
	}
	
	/**
	 * 지정한 장비 그룹을 포함한 모든 하위 장비 그룹 목록을 조회한다.
	 * <p>
	 * recid 레코드 객체 포함
	 * </p>
	 * 
	 * @param recid 장비 그룹 레코드ID
	 * @return EquipGroupInfo 객체 목록
	 * @author: surfree
	 * @since:	2016. 6. 21.
	 */
	public List<EquipGroupInfo> selectAllChildEquipGroupList(int recid) {
		return equipGroupInfoDao.selectAllChildEquipGroupList(recid);
	}
	
	/**
	 * 장비 그룹 정보 추가
	 * 
	 * @param equipGroupInfo
	 * @return
	 * @author: surfree
	 * @date:	2016. 5. 26.
	 */
	public int insertEquipGroupInfo(EquipGroupInfo equipGroupInfo) {
		return equipGroupInfoDao.insertEquipGroupInfo(equipGroupInfo);
	}
	
	/**
	 * 장비 그룹 정보 수정
	 * <p>
	 * 	장비 이름 변경 시 장비 경로가 연동되어 변경되지 않으므로 내부에서 사용해야 함.
	 * </p>
	 * 
	 * @param equipGroupInfo 
	 * @return
	 * @author: surfree
	 * @date:	2016. 6. 10.
	 */
	public int updateEquipGroupInfoByRecid(EquipGroupInfo equipGroupInfo) {
		Validate.isTrue(equipGroupInfo.getRecid() > 0, "recid value must be greater than zero");
		
		return equipGroupInfoDao.updateEquipGroupInfoByRecid(equipGroupInfo);
	}
	
	/**
	 * 장비 그룹 정보 수정 및 그룹 경로 변경
	 * 
	 * @param userGroupInfo
	 * @param currentGroupPath
	 * @param newGroupPath
	 * @return
	 */
	@Transactional
	public int updateEquipGroupName(EquipGroupInfo equipGroupInfo, String currentGroupPath, String newGroupPath) {
		equipGroupInfoDao.updateEquipGroupInfoByGroupPath(equipGroupInfo.getRecid(), currentGroupPath, newGroupPath);
		
		return equipGroupInfoDao.updateEquipGroupInfoByRecid(equipGroupInfo);
	}
	
	/**
	 * 장비 그룹간의 이동을 위하여 정의
	 * @param equipGroupInfo
	 * @author LeeJungYoung
	 * @Date 2015.09.16
	 * @return
	 */
	public int updateEquipGroupInfoByRecidForMoveGroup(EquipGroupInfo equipGroupInfo) {
		return equipGroupInfoDao.updateEquipGroupInfoByRecidForMoveGroup(equipGroupInfo);
	}
	
	/**
	 * 장비 그룹 삭제
	 * 
	 * @param recid
	 * @return
	 * @author: surfree
	 * @date:	2016. 5. 26.
	 */
	@Transactional
	public boolean deleteEquipGroupInfoByRecid(int recid) {
		List<EquipGroupInfo> equipGroups = this.selectAllChildEquipGroupList(recid);
		List<Integer> ids = this.collectionToIntegerList(equipGroups);
		
		equipGroupRelationDao.clearEquipGroupRelationByGroupIds(ids);
		equipGroupInfoDao.deleteEquipGroupInfoByIds(ids);
		
		return true;
	}
	
	public String getNewGroupPath(int recid, String groupName) {
		String path = "";
		
		if(recid == 0) {
			path = PATH_SEPARATOR + groupName;
		}
		else {
			EquipGroupInfo group = selectEquipGroupInfoByRecid(recid);
			if(group != null) {
				path = group.getGroupPath() + PATH_SEPARATOR + groupName;
			}
		}
		return path;
	}
	
	public String getCurrentGroupPath(int recid) {
		String path = "";
		
		if(recid == 0) {
			path = PATH_SEPARATOR;
		}
		else {
			EquipGroupInfo group = selectEquipGroupInfoByRecid(recid);
			if(group != null) {
				path = group.getGroupPath();
			}
		}
		return path;
	}
	
	public List<Integer> collectionToIntegerList(List<EquipGroupInfo> colls) {
		List<Integer> result = new ArrayList<Integer>(colls.size());
		
		for(EquipGroupInfo equip: colls) {
			result.add(equip.getRecid());
		}
		
		return result;
	}
}
