package com.ciencias.puntoventajavafxmvc.validation;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Validation {

    public static void validateInputText(TextField text){
        text.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            char input = event.getCharacter().charAt(0);
            if(!Character.isLetter(input)){
                event.consume();
            }
        });
    }

    public static void validateInputNumber(TextField text){
        text.addEventFilter(KeyEvent.KEY_TYPED, event ->{
            char input = event.getCharacter().charAt(0);
            if(!Character.isDigit(input)){
                event.consume();
            }
        });
    }

    public static void validateInputNumberText(TextField text){
        text.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            char input = event.getCharacter().charAt(0);
            if(!Character.isLetterOrDigit(input)){
                event.consume();
            }
        });
    }

}
