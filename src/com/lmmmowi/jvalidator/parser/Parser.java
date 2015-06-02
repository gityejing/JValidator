package com.lmmmowi.jvalidator.parser;

import java.io.File;
import java.util.List;

import com.lmmmowi.jvalidator.core.ValidateItem;

public interface Parser {

	abstract List<ValidateItem> parse(File file) throws Throwable;

}
