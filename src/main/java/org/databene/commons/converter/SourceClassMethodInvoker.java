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

import java.lang.reflect.Method;

import org.databene.commons.BeanUtil;
import org.databene.commons.ConversionException;
import org.databene.commons.Converter;

/**
 * {@link Converter} implementation which invokes a no-arg method on the source object.
 * Created: 27.02.2010 06:53:27
 * @since 0.5.0
 * @author Volker Bergmann
 */
public class SourceClassMethodInvoker<S, T> extends ThreadSafeConverter<S, T> {
	
	private Method method;

	protected SourceClassMethodInvoker(Class<S> sourceType, Class<T> targetType, Method method) {
	    super(sourceType, targetType);
	    this.method = method;
    }

	@Override
	@SuppressWarnings("unchecked")
    public T convert(S sourceValue) throws ConversionException {
		return (T) BeanUtil.invoke(sourceValue, method, null);
	}

}
