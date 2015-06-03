package com.lmmmowi.jvalidator.constraint;

import com.lmmmowi.jvalidator.core.Violation;

public class FloatConstraint extends IConstraint {

	public static final String MIN = "min";
	public static final String MAX = "max";

	@Check(3)
	private Violation checkType(Object obj) {
		if (!(obj instanceof Float)) {
			return new Violation(this, obj, "Type:[Float]");
		}

		return null;
	}

	@Check
	private Violation checkMin(Object obj) {
		String condition = getConditions().get(MIN);

		if (condition != null) {
			float min = Float.valueOf(condition);

			Float value = (Float) obj;
			if (value < min) {
				return new Violation(this, obj, "Min:[" + min + "]");
			}
		}

		return null;
	}

	@Check
	private Violation checkMax(Object obj) {
		String condition = getConditions().get(MAX);

		if (condition != null) {
			float max = Float.valueOf(condition);

			Float value = (Float) obj;
			if (value > max) {
				return new Violation(this, obj, "Max:[" + max + "]");
			}
		}

		return null;
	}

}
