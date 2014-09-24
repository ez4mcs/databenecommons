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

/**
 * Represents a log or failure level.<br/>
 * <br/>
 * Created at 18.09.2009 13:46:48
 * @since 0.5.0
 * @author Volker Bergmann
 */

public enum Level {
	ignore, 
	trace, 
	debug, 
	info, 
	warn, 
	error, 
	fatal
}
