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

import org.databene.commons.Validator;

/**
 * Abstract implementation of the {@link Validator} interface. 
 * This serves as parent class for deriving custom {@link Validator}
 * implementations. If the {@link Validator} interface will change in 
 * future versions, the AbstractValidator class will try to compensate 
 * the change for its child classes.<br/>
 * <br/>
 * Created at 08.11.2008 07:52:34
 * @since 0.4.6
 * @author Volker Bergmann
 */
public abstract class AbstractValidator<E> implements Validator<E> {

}
