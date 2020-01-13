package com.veselov.alex.racecar.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class CheckSiteConstraintValidator implements ConstraintValidator<CheckSite, String> {
    private String[] sites;

    @Override
    public void initialize(CheckSite constraintAnnotation) {
        this.sites = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String query, ConstraintValidatorContext context) {
        return query != null && Arrays.stream(this.sites)
                .anyMatch(query::startsWith);
    }
}
