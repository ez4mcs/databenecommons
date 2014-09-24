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

import static junit.framework.Assert.*;

import org.databene.commons.ConversionException;
import org.databene.commons.bean.BeanToPropertyArrayConverter;
import org.junit.Test;

import java.util.Arrays;

/**
 * Tests the {@link BeanToPropertyArrayConverter}.
 * Created: 21.07.2007 16:29:23
 * @author Volker Bergmann
 */
public class BeanToPropertyArrayConverterTest {

	@Test
    public void test() throws ConversionException {
        Bean bean = new Bean(42, "foobar");
        BeanToPropertyArrayConverter<Bean> converter 
        	= new BeanToPropertyArrayConverter<Bean>(Bean.class, "number", "text");
        Object[] expectedResult = new Object[]{42, "foobar"};
        assertTrue(Arrays.equals(expectedResult, converter.convert(bean)));
    }
	
}
