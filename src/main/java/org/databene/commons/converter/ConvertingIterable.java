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

import java.io.Closeable;

import org.databene.commons.Converter;
import org.databene.commons.HeavyweightIterator;
import org.databene.commons.HeavyweightTypedIterable;
import org.databene.commons.IOUtil;
import org.databene.commons.iterator.ConvertingIterator;

/**
 * Iterable that provides {@link ConvertingIterator}s.
 * Created: 28.08.2007 08:57:16
 * @author Volker Bergmann
 */
public class ConvertingIterable<S, T> implements HeavyweightTypedIterable<T> {

    protected Iterable<S> iterable;
    protected Converter<S, T> converter;

    public ConvertingIterable(Iterable<S> iterable, Converter<S, T> converter) {
        this.iterable = iterable;
        this.converter = converter;
    }
    
    // interface -------------------------------------------------------------------------------------------------------

    @Override
	public Class<T> getType() {
        return converter.getTargetType();
    }

    @Override
	public HeavyweightIterator<T> iterator() {
        return new ConvertingIterator<S, T>(this.iterable.iterator(), converter);
    }
    
	public void close() {
		 if (iterable instanceof Closeable)
			 IOUtil.close((Closeable) iterable);
	}

	// java.lang.Object overrides --------------------------------------------------------------------------------------
	
    @Override
    public String toString() {
    	return getClass().getSimpleName() + '[' + iterable + " -> " + converter + ']';
    }

}
