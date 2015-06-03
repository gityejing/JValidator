package com.lmmmowi.jvalidator.constraint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lmmmowi.jvalidator.core.Violation;

public class StringConstraint extends IConstraint {

	public static final String MIN_LEN = "minLen";
	public static final String MAX_LEN = "maxLen";
	public static final String REGEX = "regex";

	@Check(3)
	private Violation checkType(Object obj) {
		if (!(obj instanceof String)) {
			return new Violation(this, obj, "Type:[String]");
		}

		return null;
	}

	@Check
	private Violation checkMinLen(Object obj) {
		String condition = getConditions().get(MIN_LEN);

		if (condition != null) {
			int minLen = Integer.valueOf(condition);

			String string = (String) obj;
			if (string.length() < minLen) {
				return new Violation(this, obj,
						"String length shoul be more than " + minLen);
			}
		}

		return null;
	}

	@Check
	private Violation checkMaxLen(Object obj) {
		String condition = getConditions().get(MAX_LEN);

		if (condition != null) {
			int maxLen = Integer.valueOf(condition);

			String string = (String) obj;
			if (string.length() > maxLen) {
				return new Violation(this, obj,
						"String length shoul be less than " + maxLen);
			}
		}

		return null;
	}

	@Check
	private Violation checkRegex(Object obj) {
		String condition = getConditions().get(REGEX);

		if (condition != null) {
			String regex = condition;

			String string = (String) obj;
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(string);
			if (!matcher.matches()) {
				return new Violation(this, obj, "Can not match regex " + regex);
			}
		}

		return null;
	}

}
