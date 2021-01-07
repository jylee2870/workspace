package sga.sol.ac.core.service.policy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sga.sol.ac.core.dao.policy.PolicySearchDao;
import sga.sol.ac.core.entity.policy.Policy;
import sga.sol.ac.core.entity.policy.PolicyParam;
import sga.sol.ac.core.entity.policy.PolicyParamEquip;
import sga.sol.ac.core.entity.policy.PolicyParamUser;

@Service
public class PolicySearchService {
	
	@Autowired
	private PolicySearchDao policySearchDao;
	
	public List<Policy> search(PolicyParam object) throws Exception{
		int type = object.getType();
		int recid = object.getRecid();
		if (type == 1 || type == 2){
			PolicyParamUser param = new PolicyParamUser();
			param.setPolicyId(object.getPolicyId());
			if(type == 1 && recid != 0){
				param.setUserGroupRecid(recid);
			}else if(type == 2 && recid != 0){
				param.setUserRecid(recid);
			}else{
				throw new Exception("Recid of PolicyParam is incorrected!");
			}
			return search(param);
			
		}else if (type == 3 || type == 4 || type == 5){
			PolicyParamEquip param = new PolicyParamEquip();
			param.setPolicyId(object.getPolicyId());
			if(type == 3 && recid != 0){
				param.setEquipGroupRecid(recid);
			}else if(type == 4 && recid != 0){
				param.setEquipRecid(recid);
			}else if(type == 5 && recid != 0){
				param.setEquipAccountRecid(recid);
			}else{
				throw new Exception("Recid of PolicyParam is incorrected!");
			}
			
			return search(param);
		}else{
			throw new Exception("Type of PolicyParam is incorrected!");
		}
	}
	
	public List<Policy> search(PolicyParam object, PolicyParam target) throws Exception{
		int type = object.getType();
		int recid = object.getRecid();
		
		int type2 = target.getType();
		int recid2 = target.getRecid();
 
		if (type == 1 || type == 2){
			PolicyParamUser param = new PolicyParamUser();
			param.setPolicyId(object.getPolicyId());
			if(type == 1 && recid != 0){
				param.setUserGroupRecid(recid);
			}else if(type == 2 && recid != 0){
				param.setUserRecid(recid);
			}else{
				throw new Exception("Recid of PolicyParam is incorrected!");
			}
			
			if(type2 == 1 || type2 ==2){
				PolicyParamUser param2 = new PolicyParamUser();
				if(type2 == 1 && recid2 != 0){
					param2.setUserGroupRecid(recid2);
				}else if(type2 == 2 && recid2 != 0){
					param2.setUserRecid(recid2);
				}else{
					throw new Exception("Recid of PolicyParam is incorrected!");
				}
				
				return search(param, param2);
			}else if (type2 == 3 || type2 == 4 || type2 == 5){
				PolicyParamEquip param2 = new PolicyParamEquip();
				if(type2 == 3 && recid2 != 0){
					param2.setEquipGroupRecid(recid2);
				}else if(type2 == 4 && recid != 0){
					param2.setEquipRecid(recid2);
				}else if(type2 == 5 && recid != 0){
					param2.setEquipAccountRecid(recid2);
				}else{
					throw new Exception("Recid of PolicyParam is incorrected!");
				}
				return search(param, param2);
			}else{
				throw new Exception("Type of PolicyParam is incorrected!");
			}
		}else if (type == 3 || type == 4 || type == 5){
			PolicyParamEquip param = new PolicyParamEquip();
			param.setPolicyId(object.getPolicyId());
			if(type == 3 && recid != 0){
				param.setEquipGroupRecid(recid);
			}else if(type == 4 && recid != 0){
				param.setEquipRecid(recid);
			}else if(type == 5 && recid != 0){
				param.setEquipAccountRecid(recid);
			}else{
				throw new Exception("Recid of PolicyParam is incorrected!");
			}
			
			if(type2 == 1 || type2 ==2){
				PolicyParamUser param2 = new PolicyParamUser();
				if(type2 == 1 && recid2 != 0){
					param2.setUserGroupRecid(recid2);
				}else if(type2 == 2 && recid2 != 0){
					param2.setUserRecid(recid2);
				}else{
					throw new Exception("Recid of PolicyParam is incorrected!");
				}
				
				return search(param, param2);
			}else if (type2 == 3 || type2 == 4 || type2 == 5){
				PolicyParamEquip param2 = new PolicyParamEquip();
				if(type2 == 3 && recid2 != 0){
					param2.setEquipGroupRecid(recid);
				}else if(type == 4 && recid != 0){
					param2.setEquipRecid(recid);
				}else if(type == 5 && recid != 0){
					param2.setEquipAccountRecid(recid);
				}else{
					throw new Exception("Recid of PolicyParam is incorrected!");
				}
				return search(param, param2);
			}else{
				throw new Exception("Type of PolicyParam is incorrected!");
			}
		}else{
			throw new Exception("Type of PolicyParam is incorrected!");
		}
	}

	public List<Policy> search(PolicyParamUser object){
		return policySearchDao.policySearchByUserObject(object);
	}
	
	public List<Policy> search(PolicyParamEquip object){
		return policySearchDao.policySearchByEquipObject(object);
	}
	
	public List<Policy> search(PolicyParamUser object, PolicyParamUser target){
		return null;
	}
	
	public List<Policy> search(PolicyParamUser object, PolicyParamEquip target){
		return policySearchDao.policySearchByUserObjectAndEquipTarget(object, target);
	}
	
	public List<Policy> search(PolicyParamEquip object, PolicyParamUser target){
		return null;
	}
	
	public List<Policy> search(PolicyParamEquip object, PolicyParamEquip target){
		return null;
	}
}
