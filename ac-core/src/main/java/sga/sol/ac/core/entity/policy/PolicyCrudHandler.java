package sga.sol.ac.core.entity.policy;

public class PolicyCrudHandler {
	String policyName;
	String selectHandler;
	String insertHandler;
	String updateHandler;
	String deleteHandler;

	public PolicyCrudHandler() {
	}
	
	public PolicyCrudHandler(String policyName, String select, String insert, String update, String delete) {
		this.policyName = policyName;
		this.selectHandler = select;
		this.insertHandler = insert;
		this.updateHandler = update;
		this.deleteHandler = delete;
	}
	
	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getSelectHandler() {
		return selectHandler;
	}

	public void setSelectHandler(String selectHandler) {
		this.selectHandler = selectHandler;
	}

	public String getInsertHandler() {
		return insertHandler;
	}

	public void setInsertHandler(String insertHandler) {
		this.insertHandler = insertHandler;
	}

	public String getUpdateHandler() {
		return updateHandler;
	}

	public void setUpdateHandler(String updateHandler) {
		this.updateHandler = updateHandler;
	}

	public String getDeleteHandler() {
		return deleteHandler;
	}

	public void setDeleteHandler(String deleteHandler) {
		this.deleteHandler = deleteHandler;
	}
}
