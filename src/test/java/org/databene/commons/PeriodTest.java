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

import org.junit.Test;
import static junit.framework.Assert.*;

/**
 * Tests the Period class.<br/><br/>
 * Created at 02.05.2008 15:39:31
 * @since 0.4.3
 * @author Volker Bergmann
 */
public class PeriodTest {

	@Test
	public void testEquals() {
		Period second = Period.SECOND;
		assertFalse(second.equals(null));
		assertFalse(second.equals(""));
		assertTrue(second.equals(second));
		assertFalse(second.equals(Period.MINUTE));
	}
}
