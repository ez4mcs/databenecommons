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

import static org.junit.Assert.*;

import org.databene.commons.version.VersionInfo;
import org.junit.Test;

/**
 * Tests the {@link VersionInfo}.
 * Created: 23.03.2011 11:17:36
 * @since 0.5.8
 * @author Volker Bergmann
 */
public class VersionInfoTest {

	@SuppressWarnings("null")
	@Test
	public void testCommonsVersionInfo() {
		VersionInfo version = VersionInfo.getInfo("commons");
		String versionNumber = version.getVersion();
		assertFalse(versionNumber == null || versionNumber.length() == 0);
		assertFalse(versionNumber.startsWith("${"));
		System.out.println(version);
	}
	
	@Test
	public void testCommonsDependencies() {
		VersionInfo version = VersionInfo.getInfo("commons");
		version.verifyDependencies();
	}
	
	@Test
	public void testCustomInfo() {
		VersionInfo version = VersionInfo.getInfo("com.my");
		String versionNumber = version.getVersion();
		assertEquals("1.2.3", versionNumber);
	}
	
	@Test
	public void testVersionInfoOnIDE() {
		VersionInfo version = VersionInfo.getInfo("com.ide");
		String versionNumber = version.getVersion();
		assertEquals("path.resolved.externally", versionNumber);
	}
	
}
