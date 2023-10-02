package com.microsoft.germany.csu.appinno.vaiidation.vaiidation.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface VaiidationField {    
    String validationPrompt() default ""; 
}