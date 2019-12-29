package com.veselov.alex.racecar.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckSiteConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckSite {
    String[] value() default {"https://cars.av.by/"};

    String message() default "We can parse only: {CARS.AV.BY} sites";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
