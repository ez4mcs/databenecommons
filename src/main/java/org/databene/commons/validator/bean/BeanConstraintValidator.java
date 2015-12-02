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
package org.databene.commons.validator.bean;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;

import org.databene.commons.Validator;

/**
 * Wraps a JSR 303 {@link ConstraintValidator} with a databene commons {@link Validator}.
 * Created at 04.07.2009 08:30:13
 * @since 0.5.0
 * @author Volker Bergmann
 */

public class BeanConstraintValidator<E> implements Validator<E> {
	
	private ConstraintValidator<Annotation, E> constraintValidator;

    public BeanConstraintValidator(ConstraintValidator<Annotation, E> constraintValidator) {
	    this.constraintValidator = constraintValidator;
    }
    
    public void initialize(Annotation annotation) {
    	constraintValidator.initialize(annotation);
    }

    @Override
	public boolean valid(E object) {
	    return constraintValidator.isValid(object, null);
    }
	
    @Override
    public String toString() {
    	return constraintValidator.toString();
    }
    
}
