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

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the {@link MaxNumberStringOperation}.
 * Created: 26.02.2010 09:53:34
 * @since 0.5.0
 * @author Volker Bergmann
 */
public class MaxNumberStringOperationTest {

	private MaxNumberStringOperation max;

	@Before
	public void setUp() {
		max = new MaxNumberStringOperation();
	}
	
	@Test
	public void testInt() {
		assertEquals("3", max.perform("1", "2", "3"));
	}
	
	@Test
	public void testDouble() {
		assertEquals("3.", max.perform("1.", "2.", "3."));
	}
	
	@Test
	public void testFormatSustained() {
		assertEquals("3.", max.perform("1.000", "2.00", "3."));
	}
	
	@Test
	public void testMixed() {
		assertEquals("2", max.perform("1.000", "2"));
	}
	
}
