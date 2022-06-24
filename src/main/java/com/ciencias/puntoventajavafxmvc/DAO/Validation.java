package com.ciencias.puntoventajavafxmvc.DAO;

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

}
