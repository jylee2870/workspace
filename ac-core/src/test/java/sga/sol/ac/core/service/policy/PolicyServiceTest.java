package sga.sol.ac.core.service.policy;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sga.sol.ac.core.entity.equip.EquipInfo;
import sga.sol.ac.core.entity.policy.PolicyTarget;
import sga.sol.ac.core.entity.user.UserInfo;
import sga.sol.ac.core.service.equip.EquipInfoService;
import sga.sol.ac.core.service.user.UserInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/applicationContext.xml")
public class PolicyServiceTest {
	@Autowired
	PolicyService policyService;
	
	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	EquipInfoService equipInfoService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		UserInfo userInfo = userInfoService.getUserInfoExByRecid(2);
		EquipInfo equipInfo = equipInfoService.selectEquipInfoByRecid(30);
		List<PolicyTarget> policyTargets = policyService.selectAppropriate2FactEquipPolices(userInfo, equipInfo, equipInfo.getGroupInfo(), null);
		
		System.out.println("size: " + policyTargets.size());
	}

}
