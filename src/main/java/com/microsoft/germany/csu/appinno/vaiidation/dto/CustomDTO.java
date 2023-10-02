package com.microsoft.germany.csu.appinno.vaiidation.dto;

import com.microsoft.germany.csu.appinno.vaiidation.vaiidation.annotations.Vaiidation;
import com.microsoft.germany.csu.appinno.vaiidation.vaiidation.annotations.VaiidationField;

@Vaiidation()
public class CustomDTO {

    @VaiidationField(validationPrompt = "Must be a valid address format")
    private String address;

    @VaiidationField(validationPrompt = "Must be a city name")
    private String city;

    @VaiidationField(validationPrompt = "Must be a game released on a Nintendo console, but does not need to be exclusive to a Nintendo console.")
    private String nintendo;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNintendo() {
        return nintendo;
    }

    public void setNintendo(String nintendo) {
        this.nintendo = nintendo;
    }

}
