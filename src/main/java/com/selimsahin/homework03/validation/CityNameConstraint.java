package com.selimsahin.homework03.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author selimsahindev
 */
@Documented
@Constraint(validatedBy = { CityNameValidator.class })
@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface CityNameConstraint {

    String message() default "City name is not valid.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
