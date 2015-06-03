package com.lmmmowi.jvalidator.constraint;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lmmmowi.jvalidator.core.Violation;

public abstract class IConstraint {

	public static final String NULL = "null";

	protected Map<String, String> conditions;

	public Map<String, String> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, String> conditions) {
		this.conditions = conditions;
	}

	public Violation validate(Object obj) {
		Method[] methodArr = getClass().getDeclaredMethods();
		List<Method> methods = new ArrayList<Method>();

		// remove useless and sort
		for (Method method : methodArr) {
			Check check = method.getAnnotation(Check.class);
			if (check != null) {
				int pos = 0;
				int index = check.value();

				for (int i = 0; i < methods.size(); i++) {
					Method m = methods.get(i);
					Check mCheck = m.getAnnotation(Check.class);
					int mIndex = mCheck.value();

					if (mIndex < index) {
						break;
					} else {
						pos++;
					}
				}

				methods.add(pos, method);
			}
		}

		// check null
		if (obj == null && conditions.get(NULL) == null) {
			return new Violation(this, obj, "null");
		}

		// checks
		if (obj != null) {
			for (Method method : methods) {
				try {
					method.setAccessible(true);
					Object returnObj = method.invoke(this, obj);
					method.setAccessible(false);

					if (returnObj != null) {
						return (Violation) returnObj;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}
}
