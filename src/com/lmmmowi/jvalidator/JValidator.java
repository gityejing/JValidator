package com.lmmmowi.jvalidator;

import java.util.ArrayList;
import java.util.List;

import com.lmmmowi.jvalidator.core.ValidateItem;
import com.lmmmowi.jvalidator.core.ValidateRule;
import com.lmmmowi.jvalidator.core.Violation;
import com.lmmmowi.jvalidator.exception.ValidateException;

public class JValidator {

	private ValidateRule validateRule;
	private List<Violation> violations = new ArrayList<Violation>();

	public JValidator(ValidateRule validateRule) {
		super();
		this.validateRule = validateRule;
	}

	public void validate(Object[] params) {
		if (params.length != validateRule.size()) {
			throw new ValidateException("Rule can not match params.");
		}

		for (int i = 0; i < params.length; i++) {
			ValidateItem validateItem = validateRule.getValidateItem(i);
			List<Violation> list = validateItem.validate(params[i]);

			if (list != null) {
				violations.addAll(list);
			}
		}
	}

	public List<Violation> getViolations() {
		return this.violations;
	}

	public void printViolations() {
		if (violations != null && violations.size() > 0) {
			for (Violation violation : violations) {
				System.out.println(violation.getMessage());
			}
		}
	}
}
