package sga.sol.ac.core.entity.policy;

import java.util.Map;

public class UserPasswordPolicy extends PolicyDataItem {
	int enabled;
	int expiredPasswordDate;
	int validateNumber;
	int validateMixedCase;
	int validateSpecialChar;
	int validateLength;
	int validateMinLength;
	int validateMaxLength;

	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getExpiredPasswordDate() {
		return expiredPasswordDate;
	}
	public void setExpiredPasswordDate(int expiredPasswordDate) {
		this.expiredPasswordDate = expiredPasswordDate;
	}
	public int getValidateNumber() {
		return validateNumber;
	}
	public void setValidateNumber(int validateNumber) {
		this.validateNumber = validateNumber;
	}
	public int getValidateMixedCase() {
		return validateMixedCase;
	}
	public void setValidateMixedCase(int validateMixedCase) {
		this.validateMixedCase = validateMixedCase;
	}
	public int getValidateSpecialChar() {
		return validateSpecialChar;
	}
	public void setValidateSpecialChar(int validateSpecialChar) {
		this.validateSpecialChar = validateSpecialChar;
	}
	public int getValidateLength() {
		return validateLength;
	}
	public void setValidateLength(int validateLength) {
		this.validateLength = validateLength;
	}
	public int getValidateMinLength() {
		return validateMinLength;
	}
	public void setValidateMinLength(int validateMinLength) {
		this.validateMinLength = validateMinLength;
	}
	public int getValidateMaxLength() {
		return validateMaxLength;
	}
	public void setValidateMaxLength(int validateMaxLength) {
		this.validateMaxLength = validateMaxLength;
	}
	
	@Override
	protected Map<String, Object> createInternalDataMap() {
		Map<String, Object> result = createNewDataMap();
		
		result.put("enabled", getEnabled());
		result.put("expiredPasswordDate", getExpiredPasswordDate());
		result.put("validateNumber", getValidateNumber());
		result.put("validateMixedCase", getValidateMixedCase());
		result.put("validateSpecialChar", getValidateSpecialChar());
		result.put("validateLength", getValidateLength());
		result.put("validateMinLength", getValidateMinLength());
		result.put("validateMaxLength", getValidateMaxLength());
		
		return result;
	}
}
