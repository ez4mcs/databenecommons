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
package org.databene.commons.accessor;

import org.databene.commons.Accessor;

/**
 * Accessor that always returns the same value, independent of the object it is applied to.<br/>
 * <br/>
 * Created: 06.03.2006 08:59:02
 * @author Volker Bergmann
 */
public class ConstantAccessor<V> implements Accessor<Object, V> {

    /**
     * the value to return; null is allowed
     */
    private V value;

    public ConstantAccessor() {
        this(null);
    }

    public ConstantAccessor(V value) {
        this.value = value;
    }

    @Override
	public V getValue(Object item) {
        return this.value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final ConstantAccessor that = (ConstantAccessor) o;
        return (value != null ? value.equals(that.value) : that.value == null);
    }

    @Override
    public int hashCode() {
        return (value != null ? value.hashCode() : 0);
    }
}
