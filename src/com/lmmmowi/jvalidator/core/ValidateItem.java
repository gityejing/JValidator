package com.lmmmowi.jvalidator.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ValidateItem {

	protected String name;
	protected String constraint;
	protected Map<String, String> conditions = new HashMap<String, String>();

	public abstract List<Violation> validate(Object obj);

	public Map<String, String> getConditions() {
		return conditions;
	}

	public void addConditions(String key, String value) {
		this.conditions.put(key, value);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

}
