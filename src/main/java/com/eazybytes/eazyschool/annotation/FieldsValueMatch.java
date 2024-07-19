package com.eazybytes.eazyschool.annotation;

import com.eazybytes.eazyschool.validations.FieldsValueMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {FieldsValueMatchValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "Fields values do not match ";
    String field();
    String fieldMatch();
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }
}
//only use list interface when 2 or more variables are about to annotated at onece like confirm password in 2 fields