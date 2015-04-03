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
package org.databene.commons.visitor;

import org.databene.commons.Element;
import org.databene.commons.Visitor;

import java.util.Collection;

/**
 * Implementation of the Element interface that supports navigating through sub elements.
 * Created: 04.02.2007 07:53:23
 * @author Volker Bergmann
 */
public abstract class AbstractElement<E> implements Element<E> {

    @Override
	public final void accept(Visitor<E> visitor) {
        acceptImpl(visitor);
        Collection<Element<E>> children = getChildren(visitor);
        if (children.size() > 0)
            for (Element<E> child : children)
                child.accept(visitor);
    }

    @SuppressWarnings("unchecked")
    protected void acceptImpl(Visitor<E> visitor) {
        visitor.visit((E) this);
    }

    protected abstract Collection<Element<E>> getChildren(Visitor<E> visitor);
    
}
