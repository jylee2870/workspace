package sga.sol.ac.core.service.policy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import sga.sol.ac.core.entity.policy.PolicyCrudHandler;
import sga.sol.ac.core.entity.policy.PolicyId;

@Component
public class PolicyIdManager implements IPolicyIdManager, ApplicationContextAware {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected ApplicationContext applicationContext;
	
	private int index = 1;
	Map<String, PolicyId> policyMap = new HashMap<String, PolicyId>();
	Map<String, PolicyCrudHandler> handlerData = new HashMap<String, PolicyCrudHandler>();
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	@PostConstruct
	public void setDefaultData() {
		makePolicyList();
	}
	
	protected PolicyId makePolicyData(int recid, String policyId, String policyTitle, String type, String tableName) {
		PolicyId newPolicy = new PolicyId();
		
		newPolicy.setRecid(recid);
		newPolicy.setPolicyId(policyId);
		newPolicy.setPolicyTitle(policyTitle);
		newPolicy.setType(type);
		newPolicy.setTableName(tableName);
		
		return newPolicy;
	}
	
	protected void addPolicyDataToMap(int recid, String policyId, String policyTitle, String type, String tableName) {
		policyMap.put(policyId, makePolicyData(index, policyId, policyTitle, type, tableName));
		index++;
	}
	
	protected void addPolicyDataToMap(PolicyId policyId) {
		policyId.setRecid(index);
		policyMap.put(policyId.getPolicyId(), policyId);
		index++;
	}
	
	protected void makePolicyList() {
		addPolicyDataToMap(1, PolicyConstants.POLICY_WEB_LOGIN, "웹로그인 정책", "O", "user_web_login_policy");
		addPolicyDataToMap(2, PolicyConstants.POLICY_PASSWORD, "비밀번호 정책", "O", "user_password_policy");
		addPolicyDataToMap(3, PolicyConstants.POLICY_2FACT_AUTH, "사용자 2차인증 정책", "O", "user_2fact_auth_policy");
		addPolicyDataToMap(4, PolicyConstants.POLICY_2FACT_EQUIP, "사용자/장비 2차 인증 정책", "0", "equip_2fact_policy");
		addPolicyDataToMap(5, PolicyConstants.POLICY_ARS_APPROVAL_LOGIN, "ARS 승인자 로그인 정책", "T", "equip_account_ars_policy");
		addPolicyDataToMap(6, PolicyConstants.POLICY_ARS_APPROVAL_FILE, "ARS 승인자 파일접근 정책", "T", "equip_account_ars_policy");
		addPolicyDataToMap(7, PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN, "ARS 승인자 시스템종료 정책", "T", "equip_account_ars_policy");
		
		handlerData.put(PolicyConstants.POLICY_WEB_LOGIN, new PolicyCrudHandler(
				PolicyConstants.POLICY_WEB_LOGIN,
				"selectWebLoginPolicyInfo",
				"insertPolicyConfigForPolicyWebLogin",
				"updatePolicyConfigForPolicyWebLogin",
				"deleteWebLoginPolicyInfo"));
		
		handlerData.put(PolicyConstants.POLICY_PASSWORD, new PolicyCrudHandler(
				PolicyConstants.POLICY_PASSWORD,
				"selectPasswordPolicyInfo",
				"insertPolicyConfigForPolicyPassword",
				"updatePolicyConfigForPolicyPassword",
				"deletePasswordPolicyInfo"));
		
		handlerData.put(PolicyConstants.POLICY_2FACT_AUTH, new PolicyCrudHandler(
				PolicyConstants.POLICY_2FACT_AUTH,
				"select2FactAuthPolicyInfo",
				"insertPolicyConfigForPolicy2FactAuth",
				"updatePolicyConfigForPolicy2FactAuth",
				"delete2FactAuthPolicyInfo"));
		
		handlerData.put(PolicyConstants.POLICY_2FACT_EQUIP, new PolicyCrudHandler(
				PolicyConstants.POLICY_2FACT_EQUIP,
				"selectEquip2FactPolicyInfo",
				"insertPolicyConfigForPolicy2FactEquip",
				"updatePolicyConfigForPolicy2FactEquip",
				"deleteEquip2FactPolicyInfo"));
		
		handlerData.put(PolicyConstants.POLICY_ARS_APPROVAL_FILE, new PolicyCrudHandler(
				PolicyConstants.POLICY_ARS_APPROVAL_FILE,
				"selectEquipArsApprovalPolicyInfo",
				"insertPolicyConfigForPolicyArsApproval",
				"updatePolicyConfigForPolicyArsApproval",
				"deleteEquipArsApprovalPolicyInfo"));
		
		handlerData.put(PolicyConstants.POLICY_ARS_APPROVAL_LOGIN, new PolicyCrudHandler(
				PolicyConstants.POLICY_ARS_APPROVAL_LOGIN,
				"selectEquipArsApprovalPolicyInfo",
				"insertPolicyConfigForPolicyArsApproval",
				"updatePolicyConfigForPolicyArsApproval",
				"deleteEquipArsApprovalPolicyInfo"));
		
		handlerData.put(PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN, new PolicyCrudHandler(
				PolicyConstants.POLICY_ARS_APPROVAL_SHUTDOWN,
				"selectEquipArsApprovalPolicyInfo",
				"insertPolicyConfigForPolicyArsApproval",
				"updatePolicyConfigForPolicyArsApproval",
				"deleteEquipArsApprovalPolicyInfo"));
		
//		ApplicationContext context = ContextUtil.getContext();
		ApplicationContext context = applicationContext;
		
		Map<String, IPolicyRegister> beans = context.getBeansOfType(IPolicyRegister.class);
		
		logger.info("-------------------------------------------------");
		for(IPolicyRegister bean: beans.values()) {
			logger.info(bean.getClass().getName());
			try {
				bean.registerPolicy(this);
			}
			catch(Exception ex) {
				logger.debug(ex.getMessage(), ex);
			}
		}
		logger.info("-------------------------------------------------");
/*		
		logger.info("-------------------------------------------------");
		logger.info("ApplicationContext: " + context.getDisplayName());
		String registerBeans[] = context.getBeanNamesForType(IPolicyRegister.class);
		
		for(String bean: registerBeans) {
			logger.info("PolicyRegister Class: " + bean);
			Object beanObj = context.getBean(bean);
			if(beanObj instanceof IPolicyRegister) {
				IPolicyRegister policyRegister = (IPolicyRegister) beanObj;
				
				try {
					policyRegister.registerPolicy(this);
				}
				catch(Exception ex) {
					logger.debug(ex.getMessage(), ex);
				}
			}
			else {
				logger.error("PolicyRegister class invalid: " + bean);
			}
		}
		logger.info("-------------------------------------------------");
*/		
	}
	
	public Map<String, PolicyCrudHandler> getHandlerInfo() {
		return handlerData;
	}
	
	public List<PolicyId> getPolicyList() {
		List<PolicyId> policies = new ArrayList<PolicyId>(policyMap.values());
		
		Collections.sort(policies, new Comparator<PolicyId>() {
			@Override
			public int compare(PolicyId o1, PolicyId o2) {
				if(o1.getRecid() == o2.getRecid()) {
					return 0;
				}
				else if(o1.getRecid() > o2.getRecid()) {
					return 1;
				}
				else {
					return -1;
				}
			}
		});
		
		return policies;
	}
	
	public PolicyId getPolicyById(String policyId) {
		return policyMap.get(policyId);
	}

	@Override
	public void registPolicyId(String policyIdName, PolicyId policyId, PolicyCrudHandler handler) {
		addPolicyDataToMap(policyId);
		handlerData.put(handler.getPolicyName(), handler);
	}
}
