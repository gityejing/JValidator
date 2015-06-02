package com.lmmmowi.jvalidator.constraint;

public class ConstraintFactory {

	public static final String STRING = "string";
	public static final String INT = "int";
	public static final String LONG = "long";
	public static final String FLOAT = "float";

	public static IConstraint getConstraint(String name) {
		if (STRING.equals(name)) {
			return new StringConstraint();
		}

		return null;
	}
}
