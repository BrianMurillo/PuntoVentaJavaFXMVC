package com.ciencias.puntoventajavafxmvc.DAO;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MessageHandling {

    public static Optional<ButtonType> messageConfirmation(Alert alert, String titulo, String contenido){
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        return alert.showAndWait();
    }

    public static void messagesInformation(Alert alert, String title, String header,String content){
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void messagesError(Alert alert, String title, String header,String content){
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
