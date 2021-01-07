package sga.sol.ac.core.dao.equip;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.equip.EquipAccount;

/**
 * 장비 계정 DAO
 * 
 */
@Repository
public class EquipAccountDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 장비 계정의 전체 개수
	 * 
	 * FIXME: SearchParams로 매개변수 변경 필요
	 *  
	 * @param equipAccount
	 * @return
	 * @author: surfree
	 * @date:	2016. 6. 1.
	 */
	public int getEquipAccountListCount(SearchParams params) {
		Validate.notNull(params, "params must not be null");
		Validate.notNull(params.getEntity(), "params.entity must not be null");
		Validate.isTrue(params.getEntity() instanceof EquipAccount, "params.entity must be instance of EquipAccount");
		
		return sqlSessionTemplate.selectOne("getEquipAccountListCount", params);
	}
	
	public List<EquipAccount> selectEquipAccountList(SearchParams params) {
		Validate.notNull(params, "params must not be null");
		Validate.notNull(params.getEntity(), "params.entity must not be null");
		Validate.isTrue(params.getEntity() instanceof EquipAccount, "params.entity must be instance of EquipAccount");
		Validate.notNull(params.getSort(), "params.sort must not be null");
		
		return sqlSessionTemplate.selectList("selectEquipAccountList", params);
	}
	
	public EquipAccount selectEquipAccountByRecid(int recid) {
		EquipAccount equipAccount = new EquipAccount();
		equipAccount.setRecid(recid);
		
		return sqlSessionTemplate.selectOne("selectEquipAccountByParam", equipAccount);
	}
	
	public List<EquipAccount> selectEquipAccountListByEquipId(int equipRecid) {
		EquipAccount equipAccount = new EquipAccount();
		equipAccount.setEquipRecid(equipRecid);
		
		return sqlSessionTemplate.selectList("selectEquipAccountByParam", equipAccount);
	}
	
	public EquipAccount getEquipAccountByAccount(int equipId, String account, String domain) {
		EquipAccount equipAccount = new EquipAccount();
		equipAccount.setEquipRecid(equipId);
		equipAccount.setAccount(account);
		equipAccount.setDomain(domain);
		
		return sqlSessionTemplate.selectOne("selectEquipAccountByParam", equipAccount);
	}
	
	public List<EquipAccount> selectEquipAccountListByAccount(int equipId, String account) {
		EquipAccount equipAccount = new EquipAccount();
		equipAccount.setEquipRecid(equipId);
		equipAccount.setAccount(account);
		
		return sqlSessionTemplate.selectList("selectEquipAccountByParam", equipAccount);
	}
	
	public int insertEquipAccount(EquipAccount equipAccount){
		return sqlSessionTemplate.insert("insertEquipAccount", equipAccount);
	}

	public int updateEquipAccountByRecid(EquipAccount equipAccount) {
		Validate.isTrue(equipAccount.getRecid() > 0, "recid value must be greater than zero");
		return sqlSessionTemplate.update("updateEquipAccountByRecid", equipAccount);
	}
	
	/**
	 * 장비 계정 삭제
	 * <p>
	 * 직접 호출은 없지만 예비용으로 함수 구현
	 * </p>
	 * 
	 * @param recid 장비 계정 레코드ID
	 * @return
	 * @author: surfree
	 * @since:	2016. 6. 15.
	 */
	public int deleteEquipAccountByRecid(int recid) {
		return sqlSessionTemplate.delete("deleteEquipAccountByRecid", recid);
	}
	
	/**
	 * 지정한 장비에 해당하는 모든 장비 계정 삭제
	 * <p>
	 * 직접 호출은 없지만 예비용으로 함수 구현
	 * </p>
	 * 
	 * @param equiId 장비 레코드ID
	 * @return
	 * @author: surfree
	 * @since:	2016. 6. 15.
	 */
	public int deleteEquipAccountByEquipId(int equipId) {
		return sqlSessionTemplate.delete("deleteEquipAccountByEquipId", equipId);
	}
	
	public int deleteEquipAccountByRecidList(List<Integer> recidList) {
		return sqlSessionTemplate.delete("deleteEquipAccountByRecidList", recidList);
	}
	
	public int deleteEquipAccountByEquipIdList(List<Integer> equipIdList) {
		return sqlSessionTemplate.delete("deleteEquipAccountByEquipIdList", equipIdList);
	}
	
	/**
	 * 모든 장비 계정 삭제
	 * 
	 * @return
	 * @author: swcho
	 * @since:	2015. 3. 2.
	 */
	public int deleteEquipAccountAll() {
		return sqlSessionTemplate.delete("deleteEquipAccountByEquipIdList");
	}
}