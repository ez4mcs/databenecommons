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
 * Aggregates other (sub) converters and implements conversion by subsequent invocation of the sub converters,
 * each converting the result of the preceding converter.
 * Created: 13.05.2005 17:43:04
 * @since 0.1
 * @author Volker Bergmann
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ConverterChain<S, T> extends MultiConverterWrapper implements Converter<S, T> {

	public ConverterChain(Converter... components) {
		super(components);
    }
	
	// Converter interface implementation ------------------------------------------------------------------------------

	@Override
	public Class<S> getSourceType() {
		return (components.length > 0 ? components[0].getSourceType() : Object.class);
	}

	@Override
	public Class<T> getTargetType() {
        return (components.length > 0 ? components[components.length - 1].getTargetType() : Object.class);
    }

	@Override
	public T convert(Object source) throws ConversionException {
        Object tmp = source;
        for (Converter converter : components) {
            tmp = converter.convert(tmp);
        }
        return (T) tmp;
    }

}
