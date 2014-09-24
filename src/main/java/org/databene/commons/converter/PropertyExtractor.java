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
import org.databene.commons.bean.PropertyAccessor;
import org.databene.commons.bean.PropertyAccessorFactory;

/**
 * {@link Converter} implementation that reads a property form a JavaBean.<br/>
 * <br/>
 * Created at 15.07.2009 23:19:18
 * @since 0.5.0
 * @author Volker Bergmann
 */

public class PropertyExtractor<B, P> extends ThreadSafeConverter<B, P> {
	
	PropertyAccessor<B, P> accessor;

    @SuppressWarnings("unchecked")
    public PropertyExtractor(Class<B> beanType, String propertyName, Class<P> targetType) {
	    super(beanType, targetType);
	    this.accessor = PropertyAccessorFactory.getAccessor(sourceType, propertyName, true);
    }

    @Override
	public P convert(B sourceValue) throws ConversionException {
    	return accessor.getValue(sourceValue);
    }

}
