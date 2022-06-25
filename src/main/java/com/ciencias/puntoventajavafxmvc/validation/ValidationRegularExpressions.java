package com.ciencias.puntoventajavafxmvc.validation;

public class ValidationRegularExpressions {

    public static boolean validateName(String text){
        return text.matches("^[A-Z]{1}[a-z]{2,}([ ]{1}[A-Z]{1}[a-z]{2,})*$");
    }

    public static boolean validateSurname(String text){
        return text.matches("^[A-Z]{1}[a-z]{2,30}$");
    }

    public static boolean validateEmail(String text){
        return text.matches("^\\w+[@]{1}[a-z]{2,7}\\.[a-z]{2,3}(\\.[a-z]{2}){0,1}$");
    }

    public static boolean validateUsername(String text){
        return text.matches("^[A-Za-z0-9]{4,15}$");
    }

    public static  boolean validatePassword(String text){
        return text.matches("^\\w{5,}$");
    }

    public static boolean validatePhone(String text){
        return text.matches("^[0-9]{10}$");
    }
}
