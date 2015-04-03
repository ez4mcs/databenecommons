/*
 * Copyright (C) 2004-2014 Volker Bergmann (volker.bergmann@bergmann-it.de).
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.databene.commons.converter;

import org.databene.commons.ConversionException;
import org.databene.commons.Converter;

/**
 * {@link Converter} implementation that converts a null value to an empty {@link String}.
 * Created: 08.03.2011 14:59:34
 * @since 0.5.8
 * @author Volker Bergmann
 */
public class NullToEmptyStringConverter extends ThreadSafeConverter<String, String> {

	protected NullToEmptyStringConverter() {
		super(String.class, String.class);
	}

	@Override
	public String convert(String sourceValue) throws ConversionException {
		return (sourceValue == null ? "" : sourceValue);
	}

}
