package com.ciencias.puntoventajavafxmvc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPaternalSurname;

    @FXML
    private TextField txtMaternalSurname;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPasswordView;

    @FXML
    private ImageView imageSee;

    @FXML
    private ImageView imageNotSee;

    @FXML
    private TextField txtPhone;

    @FXML
    private RadioButton rbMan;

    @FXML
    private RadioButton rbWoman;

    @FXML
    private ComboBox cbxDay;

    @FXML
    private ComboBox cbxMouth;

    @FXML
    private ComboBox cbxYear;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnReturn;

    @FXML
    private ImageView imgMinimizar;

    @FXML
    private ImageView imgCerrar;
    //Contador boton ver
    private int cont = 1;
    //Contador radio button
    private int contRbMan = 1;
    private int contRbWoman = 1;
    //Mensaje confirmacion
    Alert msjConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageSee.setVisible(false);
        txtPasswordView.setVisible(false);
    }

    @FXML
    private void onMouseClicked(MouseEvent event){

        if (event.getSource() == imageNotSee){
            cont++;
            if(cont%2 == 0){
                imageSee.setVisible(true);
                imageNotSee.setVisible(false);
                txtPasswordView.setVisible(true);
                txtPasswordView.setText(txtPassword.getText());
                txtPassword.setVisible(false);
            }
        }

        if (event.getSource() == imageSee){
            cont++;
            if(cont%2 != 0){
                imageNotSee.setVisible(true);
                imageSee.setVisible(false);
                txtPassword.setVisible(true);
                txtPassword.setText(txtPasswordView.getText());
                txtPasswordView.setVisible(false);
            }
        }

        if (event.getSource() == imgMinimizar){
            Stage stage = (Stage) this.btnRegister.getScene().getWindow();
            stage.setIconified(true);
        }

        if (event.getSource() == imgCerrar){
            Optional<ButtonType> result =mensajeConfirmacion(msjConfirmacion,"Confirmación", "¿Desea salir de la aplicación?");
            if(result.get() == ButtonType.OK){
                Stage stage = (Stage) this.btnRegister.getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    private void txtNameOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){

        }
    }

    @FXML
    private void txtPSurnameOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){

        }
    }

    @FXML
    private void txtMSurnameOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){

        }
    }

    @FXML
    private void txtEmailOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){

        }
    }

    @FXML
    private void txtUsernameOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){

        }
    }

    @FXML
    private void txtPasswordOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){

        }
    }

    @FXML
    private void txtPasswordViewUserOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){

        }
    }

    @FXML
    private void txtPhoneOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){

        }
    }

    @FXML
    private void onActionEvents(ActionEvent event){
        if (event.getSource() == btnRegister){

        }

        if (event.getSource() == btnReturn){

        }

        if (event.getSource() == rbMan){
            contRbMan++;
            if(contRbMan%2 == 0){
                rbWoman.setDisable(true);
                rbMan.setDisable(false);
            } else {
                rbWoman.setDisable(false);
                rbMan.setDisable(false);
            }
        }

        if (event.getSource() == rbWoman){
            contRbWoman++;
            if(contRbWoman%2 == 0){
                rbWoman.setDisable(false);
                rbMan.setDisable(true);
            } else {
                rbWoman.setDisable(false);
                rbMan.setDisable(false);
            }
        }

    }

    public Optional<ButtonType> mensajeConfirmacion(Alert alert, String titulo, String contenido){
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        return alert.showAndWait();
    }
}
