package com.lmmmowi.jvalidator.core;

import com.lmmmowi.jvalidator.constraint.IConstraint;

public class Violation {

	private IConstraint constraint;
	private Object value;
	private String error;

	public Violation(IConstraint constraint, Object value, String error) {
		super();
		this.constraint = constraint;
		this.value = value;
		this.error = error;
	}

	public String getMessage() {
		StringBuilder builder = new StringBuilder();

		builder.append("##########\n");
		builder.append(constraint.getClass().getSimpleName()).append(":");
		builder.append("[" + value + "]\n");
		builder.append("Error:").append(error);

		return builder.toString();
	}

}
