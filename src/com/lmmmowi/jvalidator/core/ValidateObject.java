package com.lmmmowi.jvalidator.core;

import java.util.ArrayList;
import java.util.List;

public class ValidateObject extends ValidateItem {

	private List<ValidateValue> validateValues = new ArrayList<ValidateValue>();

	public List<Violation> validate(Object obj) {
		List<Violation> violations = new ArrayList<Violation>();

		return violations;
	}

	public void addValidateValue(ValidateValue validateValue) {
		this.validateValues.add(validateValue);
	}

}
