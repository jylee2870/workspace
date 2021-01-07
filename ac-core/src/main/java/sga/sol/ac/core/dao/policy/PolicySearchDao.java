package sga.sol.ac.core.dao.policy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sga.sol.ac.core.entity.policy.Policy;
import sga.sol.ac.core.entity.policy.PolicyParamEquip;
import sga.sol.ac.core.entity.policy.PolicyParamUser;

@Repository
public class PolicySearchDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<Policy> policySearchByUserObject(PolicyParamUser object){
		return sqlSessionTemplate.selectList("policySearchByUserObject",object);
	}
	
	public List<Policy> policySearchByEquipObject(PolicyParamEquip object){
		return sqlSessionTemplate.selectList("policySearchByEquipObject",object);
	}
	
	public List<Policy> policySearchByUserObjectAndEquipTarget(PolicyParamUser object, PolicyParamEquip target){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("object", object);
		param.put("target", target);
		
		return sqlSessionTemplate.selectList("policySearchByUserObjectAndEquipTarget", param);  
	}
	
}
