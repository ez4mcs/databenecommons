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
package org.databene.commons.validator;

import org.databene.commons.Named;
import org.databene.commons.Validator;

/**
 * Combination of {@link Named} and {@link Validator} interfaces.<br/><br/>
 * Created: 21.12.2011 07:32:40
 * @since 0.5.14
 * @author Volker Bergmann
 */
public interface NamedValidator<E> extends Validator<E>, Named {

}
