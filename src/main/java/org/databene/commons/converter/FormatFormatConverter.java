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

import java.text.Format;

import org.databene.commons.ConversionException;

/**
 * Converts an object to a String by using a java.lang.Format object's format() method.<br/>
 * <br/>
 * Created: 30.08.2006 19:43:09
 * @since 0.1
 * @author Volker Bergmann
 */
public class FormatFormatConverter<S> extends FormatBasedConverter<S, String> {

    /**
     * Constructor that initializes the format object.
     * @param format the format object to use.
     */
    public FormatFormatConverter(Class<S> sourceType, Format format, boolean threadSafe) {
        super(sourceType, String.class, format, threadSafe);
    }

    /**
     * Converts an object to a String by using the format's format() method.
     * @see org.databene.commons.Converter
     */
	@Override
	public synchronized String convert(S source) {
        try {
        	return format.format(source);
        } catch (Exception e) {
        	throw new ConversionException("Conversion failed for value: " + source, e);
        }
    }

}
