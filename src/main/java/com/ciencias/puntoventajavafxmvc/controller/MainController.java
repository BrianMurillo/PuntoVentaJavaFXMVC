package com.ciencias.puntoventajavafxmvc.controller;

import com.ciencias.puntoventajavafxmvc.DAO.MessageHandling;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ImageView imageMinimize;

    @FXML
    private ImageView imageMaximize;

    @FXML
    private ImageView imageClose;

    //messages
    Alert msjConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    Alert msjInformation = new Alert(Alert.AlertType.INFORMATION);
    Alert msjError = new Alert(Alert.AlertType.ERROR);

    int contMaximize=2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void onMouseClicked(MouseEvent event){
        if (event.getSource() == imageMinimize){
            Stage stage = (Stage) this.imageMinimize.getScene().getWindow();
            stage.setIconified(true);
        }

        if (event.getSource() == imageMaximize){
            Stage stage = (Stage) this.imageMaximize.getScene().getWindow();
            if (contMaximize % 2 == 0) {
                stage.setMaximized(true);
            } else {
                stage.setMaximized(false);
            }
            contMaximize++;
        }

        if (event.getSource() == imageClose){
            Optional<ButtonType> result = MessageHandling.messageConfirmation(msjConfirmation,"Confirmation", "Do you want to exit the application?");
            if(result.get() == ButtonType.OK){
                Stage stage = (Stage) this.imageClose.getScene().getWindow();
                stage.close();
            }
        }

    }
}
