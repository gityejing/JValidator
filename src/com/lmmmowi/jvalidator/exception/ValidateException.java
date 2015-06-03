package com.lmmmowi.jvalidator.exception;

import java.util.List;

import com.lmmmowi.jvalidator.core.Violation;

public class ValidateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<Violation> violations;

	public ValidateException(String message) {
		super(message);
	}

	public ValidateException(List<Violation> violations) {
		super("Validate failed.");
		this.violations = violations;
	}

	public List<Violation> getViolations() {
		return violations;
	}

}
