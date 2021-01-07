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
import sga.sol.ac.core.entity.equip.EquipService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/applicationContext.xml")
public class EquipServiceDaoTest {
	@Autowired
	EquipServiceDao equipServiceDao;

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testCount() {
		SearchParams params = new SearchParams();
		EquipService equipService = new EquipService();
		
		params.setEntity(equipService);
		
		int count = equipServiceDao.getEquipServiceListCount(params);
		
		System.out.println("count: " + count);
	}
	
	@Test
	public void testSelectAll() {
		List<EquipService> equipServices = equipServiceDao.selectEquipServiceListAll();
		
		System.out.println("count: " + equipServices.size());
	}
	
	@Test
	public void testSelect() {
		SearchParams params = new SearchParams();
		EquipService equipService = new EquipService();
		
		params.setEntity(equipService);
		params.setSort(new SortParam("recid", SortParam.DIR_ASC));
		
		List<EquipService> equipServices = equipServiceDao.selectEquipServiceList(params);
		
		System.out.println("count: " + equipServices.size());
		
		for(EquipService service: equipServices) {
			System.out.println(service.getServiceName());
		}
	}

}
