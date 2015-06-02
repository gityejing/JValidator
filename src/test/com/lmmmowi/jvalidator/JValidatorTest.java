package test.com.lmmmowi.jvalidator;

import org.junit.Test;

import com.lmmmowi.jvalidator.JValidator;
import com.lmmmowi.jvalidator.core.ValidateRule;
import com.lmmmowi.jvalidator.parser.XmlParser;

import junit.framework.TestCase;

public class JValidatorTest extends TestCase {

	@Test
	public void testValidate() {
		Object[] params = new Object[] { "22i", "asddas" };
		ValidateRule validateRules = ValidateRule.parse("classpath:valid.xml",
				new XmlParser());

		JValidator validator = new JValidator(validateRules);
		validator.validate(params);

		validator.printViolations();
		assertEquals(0, validator.getViolations().size());
	}

}
