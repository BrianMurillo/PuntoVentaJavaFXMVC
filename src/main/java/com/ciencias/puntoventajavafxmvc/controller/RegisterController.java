package com.ciencias.puntoventajavafxmvc.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
