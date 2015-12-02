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
package org.databene.commons.validator;

import org.databene.commons.Resettable;
import org.databene.commons.Validator;

import java.util.Set;
import java.util.HashSet;

/**
 * Validator implementation that requires each argument 
 * of consecutive calls to the valid() method to be unique.
 * Created: 20.11.2007 09:55:16
 * @author Volker Bergmann
 */
public class UniqueValidator<E> implements Validator<E>, Resettable {

    private Set<E> instances = new HashSet<E>();

    @Override
	public boolean valid(E object) {
        if (instances.contains(object))
            return false;
        instances.add(object);
        return true;
    }
    
	@Override
	public void reset() {
	    instances.clear();
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
