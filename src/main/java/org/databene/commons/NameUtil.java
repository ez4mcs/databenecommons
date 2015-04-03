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
package org.databene.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Provides convenience methods for {@link Named} objects.
 * Created: 12.08.2010 09:21:46
 * @since 0.5.4
 * @author Volker Bergmann
 */
public class NameUtil {

	private NameUtil() { }
	
    public static String[] getNames(Named[] objects) {
    	String[] result = new String[objects.length];
    	for (int i = 0; i < objects.length; i++)
    		result[i] = objects[i].getName();
    	return result;
    }

    public static <T extends Collection<? extends Named>> List<String> getNames(T objects) {
    	List<String> result = new ArrayList<String>(objects.size());
    	for (Named object : objects)
    		result.add(object.getName());
    	return result;
    }

    public static <T extends Named> void orderByName(T[] objects) {
    	Arrays.sort(objects, new NameComparator());
    }

    public static <T extends Named> void orderByName(List<T> objects) {
    	Collections.sort(objects, new NameComparator());
    }

	public static int indexOf(String name, List<? extends Named> objects) {
		for (int i = 0; i < objects.size(); i++)
			if (name.equals(objects.get(i).getName()))
				return i;
		return -1;
	}

	public static int indexOf(String name, Named[] objects) {
		for (int i = 0; i < objects.length; i++)
			if (name.equals(objects[i].getName()))
				return i;
		return -1;
	}

	public static void sort(List<? extends Named> namedObjects) {
		Collections.sort(namedObjects, new NameComparator());
	}
	
	public static <T extends Named> List<T> find(List<T> list, Filter<String> filter) {
		List<T> result = new ArrayList<T>();
		for (T object : list)
			if (filter.accept(object.getName()))
				result.add(object);
		return result;
	}
	
}
