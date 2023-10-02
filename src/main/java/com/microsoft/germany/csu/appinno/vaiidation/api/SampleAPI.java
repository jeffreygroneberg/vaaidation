package com.microsoft.germany.csu.appinno.vaiidation.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.germany.csu.appinno.vaiidation.dto.CustomDTO;
import com.microsoft.germany.csu.appinno.vaiidation.vaiidation.errors.ErrorResponse;

import org.springframework.validation.Errors;
import jakarta.validation.Valid;

@RestController
public class SampleAPI {

    @PostMapping("/sendCustomDTO")
    public ResponseEntity<?> sendCustomDTO(@Valid @RequestBody CustomDTO customDTO, Errors errors) {

        // can be replaced with custom exception mapper!
        if (errors.hasErrors()) {
            List<String> errorMessages = errors.getAllErrors()
                    .stream()
                    .map(ObjectError::getCode)
                    .collect(Collectors.toList());

            ErrorResponse errorResponse = new ErrorResponse(errorMessages);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // ... processing if validation passes
        return new ResponseEntity<>(HttpStatus.OK);       

    }

}