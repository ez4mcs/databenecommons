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
package org.databene.commons.filter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests the {@link AndFilter}.<br/><br/>
 * Created: 08.06.2012 21:00:24
 * @since 0.5.16
 * @author Volker Bergmann
 */
@SuppressWarnings("unchecked")
public class AndFilterTest {

	private static final ConstantFilter<Integer> TRUE = new ConstantFilter<Integer>(true);
	private static final ConstantFilter<Integer> FALSE = new ConstantFilter<Integer>(false);
	
	@Test
	public void testTrueAndTrue() {
		assertTrue(new AndFilter<Integer>(TRUE, TRUE).accept(0));
	}
	
	@Test
	public void testTrueAndFalse() {
		assertFalse(new AndFilter<Integer>(TRUE, FALSE).accept(0));
	}
	
	@Test
	public void testFalseAndTrue() {
		assertFalse(new AndFilter<Integer>(FALSE, TRUE).accept(0));
	}
	
	@Test
	public void testFalseAndFalse() {
		assertFalse(new AndFilter<Integer>(FALSE, FALSE).accept(0));
	}
	
}
