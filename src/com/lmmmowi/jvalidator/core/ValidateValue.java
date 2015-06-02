package com.lmmmowi.jvalidator.core;

import java.util.ArrayList;
import java.util.List;

import com.lmmmowi.jvalidator.constraint.ConstraintFactory;
import com.lmmmowi.jvalidator.constraint.IConstraint;
import com.lmmmowi.jvalidator.exception.ValidateException;

public class ValidateValue extends ValidateItem {

	@Override
	public List<Violation> validate(Object obj) {
		IConstraint constraint = ConstraintFactory
				.getConstraint(this.constraint);

		if (constraint != null) {
			constraint.setConditions(this.conditions);

			Violation violation = constraint.validate(obj);
			if (violation != null) {
				List<Violation> violations = new ArrayList<Violation>();
				violations.add(violation);
				return violations;
			}
		} else {
			throw new ValidateException("Unable to find constraint.");
		}

		return null;
	}

}
