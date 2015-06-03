package com.lmmmowi.jvalidator.core;

import com.lmmmowi.jvalidator.constraint.IConstraint;

public class Violation {

	private IConstraint constraint;
	private Object object;
	private String key;
	private Object value;
	private String error;
	private int paramIndex;

	public Violation(IConstraint constraint, Object value, String error) {
		super();
		this.constraint = constraint;
		this.value = value;
		this.error = error;
	}

	public void setField(Object object, String key) {
		this.object = object;
		this.key = key;
	}

	public void setParamIndex(int paramIndex) {
		this.paramIndex = paramIndex;
	}

	public String getMessage() {
		StringBuilder builder = new StringBuilder();

		builder.append("#####").append(constraint.getClass().getSimpleName())
				.append("#####\n");
		builder.append("ParamIndex:[" + paramIndex + "]\n");
		builder.append("Value:[" + value + "]\n");
		if (object != null) {
			builder.append("Object:[" + object + "]\n");
			builder.append("Key:[" + key + "]\n");
		}
		builder.append("Error:[" + error + "]");

		return builder.toString();
	}

}
