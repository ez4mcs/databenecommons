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
package org.databene.commons.context;

import org.databene.commons.Context;

/**
 * Simple implementation of the {@link ContextAware} interface.
 * Created: 16.02.2010 11:59:53
 * @since 0.6.0
 * @author Volker Bergmann
 */
public abstract class ContextHolder implements ContextAware {

	protected Context context;
	
	protected ContextHolder() {
		this(null);
	}
	
	public ContextHolder(Context context) {
		this.context = context;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

}
