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
package org.databene.commons.bean;

import org.databene.commons.BeanUtil;
import org.databene.commons.ConfigurationError;
import org.databene.commons.ConversionException;
import org.databene.commons.UpdateFailedException;
import org.databene.commons.converter.AnyConverter;

import java.lang.reflect.Method;
import java.beans.PropertyDescriptor;

/**
 * Mutates the value of a JavaBean property with knowledge of the property type.<br/>
 * <br/>
 * Created: 21.07.2007 08:58:49
 * @author Volker Bergmann
 */
public class TypedPropertyMutator extends AbstractNamedMutator {

    private boolean required;
    private boolean autoConvert;
    private Method writeMethod;

    public TypedPropertyMutator(Class<?> beanClass, String propertyName, boolean required, boolean autoConvert) {
        super(propertyName);
        this.required = required;
        this.autoConvert = autoConvert;
        PropertyDescriptor propertyDescriptor = BeanUtil.getPropertyDescriptor(beanClass, propertyName);
        if (propertyDescriptor == null) {
            if (required)
                throw new ConfigurationError("No property '" + propertyName + "' found in " + beanClass);
            else
                writeMethod = null;
        } else {
            writeMethod = propertyDescriptor.getWriteMethod();
            if (writeMethod == null)
                throw new ConfigurationError("No write method found for property '" + propertyName + "' in class " + beanClass.getName());
        }
    }

    @Override
	public void setValue(Object bean, Object value) throws UpdateFailedException {
        if (bean == null)
            if (required)
                throw new IllegalArgumentException("Cannot set a property on null");
            else
                return;
        if (writeMethod == null)
            return;
        if (autoConvert && value != null) {
            Class<?> sourceType = value.getClass();
            Class<?> targetType = writeMethod.getParameterTypes()[0];
            try {
                if (!targetType.isAssignableFrom(sourceType))
                    value = AnyConverter.convert(value, targetType);
            } catch (ConversionException e) {
                throw new ConfigurationError(e);
            }
        }
        BeanUtil.invoke(bean, writeMethod, new Object[] { value });
    }
    
}
