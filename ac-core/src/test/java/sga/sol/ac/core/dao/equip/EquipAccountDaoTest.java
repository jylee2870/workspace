package sga.sol.ac.core.dao.equip;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sga.sol.ac.core.entity.criteria.SearchParams;
import sga.sol.ac.core.entity.criteria.SortParam;
import sga.sol.ac.core.entity.equip.EquipAccount;
import sga.sol.ac.core.entity.equip.EquipInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/applicationContext.xml")
public class EquipAccountDaoTest {
	@Autowired
	EquipAccountDao equipAccountDao;
	
	int equipRecid = 0;
	int equipAccountRecid = 0;

	@Before
	public void setUp() throws Exception {
		equipRecid = 30;
		equipAccountRecid = 1;
	}
	
	@Test
	public void testSelectEquipAccountByRecid() {
		EquipAccount equipAccount = equipAccountDao.selectEquipAccountByRecid(equipAccountRecid);
		assertNotNull(equipAccount);
		
		assertNotEquals(equipAccount.getAccount(), "");
		System.out.println("account name:" + equipAccount.getAccount());
		
		EquipInfo equipInfo = equipAccount.getEquipInfo();
		assertNotNull(equipInfo);
		assertNotEquals(equipInfo.getEquipName(), "");
		System.out.println("equip name: " + equipInfo.getEquipName());
	}
	
	@Test
	public void testSelectEquipAccountMain() {
		SearchParams params = new SearchParams();
		SortParam sort = new SortParam("EA.recid", "DESC");
		
		EquipAccount entity = new EquipAccount();
		
		entity.setEquipRecid(equipRecid);
		
		params.setEntity(entity);
		params.setSort(sort);
		
		int count = equipAccountDao.getEquipAccountListCount(params);
		
		List<EquipAccount> equipAccounts = equipAccountDao.selectEquipAccountList(params);
		assertNotNull(equipAccounts);
		
		assertEquals(count, equipAccounts.size());
		
		if(equipAccounts.size() > 0) {
			assertNotEquals(equipAccounts.get(0).getAccount(), "");
			System.out.println("account name:" + equipAccounts.get(0).getAccount());
			
			EquipInfo equipInfo = equipAccounts.get(0).getEquipInfo();
			assertNotNull(equipInfo);
			assertNotEquals(equipInfo.getEquipName(), "");
			System.out.println("equip name: " + equipInfo.getEquipName());
		}
	}

}
