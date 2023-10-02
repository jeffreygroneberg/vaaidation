package com.microsoft.germany.csu.appinno.vaiidation.conf;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.microsoft.germany.csu.appinno.vaiidation.vaiidation.GPTValidation;

@ControllerAdvice
public class VaaidationControllerAdvice {

    // constructor injection
    public VaaidationControllerAdvice(GPTValidation validator) {
        this.validator = validator;
    }

    private GPTValidation validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
    
}
