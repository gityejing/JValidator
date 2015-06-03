package com.lmmmowi.jvalidator.core;

import java.util.ArrayList;
import java.util.List;

import com.lmmmowi.jvalidator.exception.ValidateException;

public class ValidateObject extends ValidateItem {

	private IFiledGetter filedGetter = new DefaultFieldGetter();
	private List<ValidateValue> validateValues = new ArrayList<ValidateValue>();

	public List<Violation> validate(Object obj) {
		List<Violation> violations = new ArrayList<Violation>();

		for (ValidateValue validateValue : validateValues) {
			String key = validateValue.name;
			Object value = filedGetter.getField(obj, key);

			if (value == null) {
				throw new ValidateException("Unable to get field from object.");
			}

			List<Violation> list = validateValue.validate(value);
			if (list != null && list.size() > 0) {
				Violation violation = list.get(0);
				violation.setField(obj, key);
				violations.add(violation);
			}
		}

		return violations;
	}

	public void addValidateValue(ValidateValue validateValue) {
		this.validateValues.add(validateValue);
	}

	public void setFiledGetter(IFiledGetter filedGetter) {
		this.filedGetter = filedGetter;
	}

}
