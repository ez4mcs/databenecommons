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
package org.databene.commons.comparator;

import java.util.Comparator;

/**
 * Compares values of type Long or long.
 * Created: 05.04.2005 19:25:25
 * @author Volker Bergmann
 */
public class LongComparator implements Comparator<Long> {

    @Override
	public int compare(Long l1, Long l2) {
        return l1.compareTo(l2);
    }

    public static int compare(long n1, long n2) {
        return (n1 < n2 ? -1 : (n1 > n2 ? 1 : 0));
    }

}
