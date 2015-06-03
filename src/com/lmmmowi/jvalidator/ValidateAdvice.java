package com.lmmmowi.jvalidator;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.aop.MethodBeforeAdvice;

import com.lmmmowi.jvalidator.core.ValidateRule;
import com.lmmmowi.jvalidator.core.Violation;
import com.lmmmowi.jvalidator.exception.ValidateException;
import com.lmmmowi.jvalidator.parser.XmlParser;

public class ValidateAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] params, Object obj)
			throws Throwable {
		Validate validate = method.getAnnotation(Validate.class);
		
		if (validate != null) {
			String validateRulePath = validate.value();

			ValidateRule validateRule = ValidateRule.parse(validateRulePath,
					new XmlParser());
			JValidator validator = new JValidator(validateRule);
			validator.validate(params);

			List<Violation> violations = validator.getViolations();
			if (violations != null && violations.size() > 0) {
				throw new ValidateException(violations);
			}
		}
	}

}
