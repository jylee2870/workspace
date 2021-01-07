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
import sga.sol.ac.core.entity.equip.EquipOsInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/applicationContext.xml")
public class EquipOsDaoTest {
	@Autowired
	EquipOsDao equipOsDao;

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testCount() {
		SearchParams params = new SearchParams();
		EquipOsInfo equipOs = new EquipOsInfo();
		
		params.setEntity(equipOs);
		
		int count = equipOsDao.getEquipOsListCount(params);
		
		System.out.println("count: " + count);
	}
	
	@Test
	public void testSelectAll() {
		List<EquipOsInfo> equipOses = equipOsDao.selectEquipOsListAll();
		
		System.out.println("count: " + equipOses.size());
	}
	
	@Test
	public void testSelect() {
		SearchParams params = new SearchParams();
		EquipOsInfo equipOs = new EquipOsInfo();
		
		params.setEntity(equipOs);
		params.setSort(new SortParam("recid", SortParam.DIR_ASC));
		
		List<EquipOsInfo> equipOses = equipOsDao.selectEquipOsList(params);
		
		System.out.println("count: " + equipOses.size());
	}

}
