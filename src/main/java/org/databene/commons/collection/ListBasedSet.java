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
package org.databene.commons.collection;

import java.util.*;

/**
 * Implements a Set based on a List. This provides for ensuring element uniqueness
 * while maintaining the order in which elements were inserted.
 * Created: 03.08.2007 18:47:13
 */
public class ListBasedSet<E> implements Set<E> {

    private List<E> list;

    public ListBasedSet() {
        this.list = new ArrayList<E>();
    }

    public ListBasedSet(int initialCapacity) {
        this.list = new ArrayList<E>(initialCapacity);
    }

    public ListBasedSet(Collection<E> collection) {
        this.list = new ArrayList<E>(collection);
    }

    public ListBasedSet(E... elements) {
        this.list = new ArrayList<E>(elements.length);
        for (E element : elements)
        	this.list.add(element);
    }

    @Override
	public int size() {
        return list.size();
    }

    @Override
	public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
	public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
	public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
	public Object[] toArray() {
        return list.toArray();
    }

    @Override
	public <T>T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
	public boolean add(E o) {
        if (list.contains(o))
            return true;
        else
            return list.add(o);
    }

    @Override
	public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
	public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
	public boolean addAll(Collection<? extends E> c) {
        return list.addAll(c);
    }

    @Override
	public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
	public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
	public void clear() {
        list.clear();
    }

    // List interface --------------------------------------------------------------------------------------------------

    public E get(int index) {
        return list.get(index);
    }
    
    @Override
    public String toString() {
    	return list.toString();
    }
}
