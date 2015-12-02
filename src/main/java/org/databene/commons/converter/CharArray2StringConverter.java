/*
 * Copyright (C) 2004-2015 Volker Bergmann (volker.bergmann@bergmann-it.de).
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

/**
 * Combines the characters of a character array to a {@link String}.
 * Created: 27.02.2010 09:46:36
 * @since 0.5.0
 * @author Volker Bergmann
 */
public class CharArray2StringConverter extends ThreadSafeConverter<char[], String> {

	protected CharArray2StringConverter() {
	    super(char[].class, String.class);
    }

	@Override
	public String convert(char[] sourceValue) throws ConversionException {
	    return new String(sourceValue);
    }

}
