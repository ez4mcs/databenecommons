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
package org.databene.commons.operation;

import org.databene.commons.Operation;

/**
 * Returns the first non-null argument as the operation result.
 * Created: 03.08.2007 07:17:31
 * @param <E> the argument and result type of the operation
 * @author Volker Bergmann
 */
public class FirstNonNullSelector<E> implements Operation<E, E> {

    @SuppressWarnings("unchecked")
	@Override
	public E perform(E ... args) {
        if (args.length == 0)
            return null;
        for (E arg : args)
            if (arg != null)
                return arg;
        return null;
    }
}
