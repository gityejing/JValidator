package com.lmmmowi.jvalidator.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.lmmmowi.jvalidator.core.ValidateItem;
import com.lmmmowi.jvalidator.core.ValidateObject;
import com.lmmmowi.jvalidator.core.ValidateValue;
import com.lmmmowi.jvalidator.exception.ValidateException;

public class XmlParser implements Parser {

	private static final String ROOT_ELEMENT = "validate";
	private static final String VALUE_ELEMENT = "value";
	private static final String OBJECT_ELEMENT = "object";

	private static final String NAME_ATTRIBUTE = "name";
	private static final String CONSTRAINT_ATTRIBUTE = "constraint";

	@SuppressWarnings("unchecked")
	@Override
	public List<ValidateItem> parse(File file) throws DocumentException {
		List<ValidateItem> items = new ArrayList<ValidateItem>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);

		/* get root */
		Element root = document.getRootElement();
		if (!ROOT_ELEMENT.equals(root.getName())) {
			throw new ValidateException("Illegal rule xml file.");
		}

		/* get items */
		List<Element> itemElements = root.elements();
		if (itemElements != null && itemElements.size() > 0) {
			for (Element element : itemElements) {
				ValidateItem item = parseItem(element);

				items.add(item);
			}
		}

		return items;
	}

	@SuppressWarnings("unchecked")
	private ValidateItem parseItem(Element element) {
		ValidateItem validateItem = null;

		if (VALUE_ELEMENT.equals(element.getName())) {
			validateItem = new ValidateValue();
			Iterator<Attribute> iterator = element.attributeIterator();
			while (iterator.hasNext()) {
				Attribute attribute = iterator.next();
				String key = attribute.getName();

				if (NAME_ATTRIBUTE.equals(key)) {
					validateItem.setName(attribute.getValue());
				} else if (CONSTRAINT_ATTRIBUTE.equals(key)) {
					validateItem.setConstraint(attribute.getValue());
				} else {
					validateItem.addConditions(key, attribute.getValue());
				}
			}
		} else if (OBJECT_ELEMENT.equals(element.getName())) {
			validateItem = new ValidateObject();
			ValidateObject validateObject = (ValidateObject) validateItem;

			List<Element> valueElements = element.elements();
			if (valueElements != null && valueElements.size() > 0) {
				for (Element valueElement : valueElements) {
					ValidateValue validateValue = (ValidateValue) parseItem(valueElement);
					validateObject.addValidateValue(validateValue);
				}
			}
		}
		return validateItem;
	}
}