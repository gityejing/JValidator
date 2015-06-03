package com.lmmmowi.jvalidator.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.lmmmowi.jvalidator.exception.ValidateException;
import com.lmmmowi.jvalidator.parser.Parser;

public class ValidateRule {

	private List<ValidateItem> items = new ArrayList<ValidateItem>();

	private ValidateRule() {
	}

	public static ValidateRule parse(String path, Parser parser) {
		path = path.replace("classpath:", ValidateRule.class.getResource("/")
				.getFile());

		return parse(new File(path), parser);
	}

	public static ValidateRule parse(File file, Parser parser) {
		try {
			ValidateRule validateRule = new ValidateRule();
			validateRule.items = parser.parse(file);
			return validateRule;
		} catch (Throwable e) {
			throw new ValidateException("Fail to parse rule file.");
		}
	}

	public int size() {
		return items.size();
	}

	public ValidateItem getValidateItem(int index) {
		return items.get(index);
	}
}
