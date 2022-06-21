package com.ciencias.puntoventajavafxmvc.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtPassView;

    @FXML
    private Button btnLogin;

    @FXML
    private ImageView imageSee;

    @FXML
    private ImageView imageNotSee;

    @FXML
    private CheckBox ckbRemember;

    @FXML
    private Label lblForgotPassword;

    @FXML
    private Label lblRegister;

    @FXML
    private ImageView imageMinimizar;

    @FXML
    private ImageView imageClose;

    //Contador ver
    private int cont=1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageNotSee.setVisible(false);
        txtPassView.setVisible(false);
    }

    @FXML
    public void onMouseClicked(MouseEvent event){

        if (event.getSource() == imageSee){
            cont++;
            if(cont%2 == 0){
                imageNotSee.setVisible(true);
                imageSee.setVisible(false);
                txtPassView.setVisible(true);
                txtPassView.setText(txtPass.getText());
                txtPass.setVisible(false);
            }
        }

        if(event.getSource() == imageNotSee){
            cont++;
            if(cont%2 != 0){
                imageSee.setVisible(true);
                imageNotSee.setVisible(false);
                txtPass.setVisible(true);
                txtPass.setText(txtPassView.getText());
                txtPassView.setVisible(false);
            }
        }
    }
}
