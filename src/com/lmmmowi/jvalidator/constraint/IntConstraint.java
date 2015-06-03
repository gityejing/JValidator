package com.lmmmowi.jvalidator.constraint;

import com.lmmmowi.jvalidator.core.Violation;

public class IntConstraint extends IConstraint {

	public static final String MIN = "min";
	public static final String MAX = "max";

	@Check(3)
	private Violation checkType(Object obj) {
		if (!(obj instanceof Integer)) {
			return new Violation(this, obj, "Type:[Integer]");
		}

		return null;
	}

	@Check
	private Violation checkMin(Object obj) {
		String condition = getConditions().get(MIN);

		if (condition != null) {
			int min = Integer.valueOf(condition);

			Integer value = (Integer) obj;
			if (value < min) {
				return new Violation(this, obj, "min:[" + min + "]");
			}
		}

		return null;
	}

	@Check
	private Violation checkMax(Object obj) {
		String condition = getConditions().get(MAX);

		if (condition != null) {
			int max = Integer.valueOf(condition);

			Integer value = (Integer) obj;
			if (value > max) {
				return new Violation(this, obj, "max:[" + max + "]");
			}
		}

		return null;
	}
}
