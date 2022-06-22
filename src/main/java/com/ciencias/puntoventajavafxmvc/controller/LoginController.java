package com.ciencias.puntoventajavafxmvc.controller;

import com.ciencias.puntoventajavafxmvc.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    private ImageView imageMinimize;

    @FXML
    private ImageView imageClose;

    //Controller Register
    private RegisterController registerController;

    //Messages for user
    Alert msjConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
    Alert msjInformacion = new Alert(Alert.AlertType.INFORMATION);

    //Cont see
    private int cont=1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageSee.setVisible(false);
        txtPassView.setVisible(false);
    }

    @FXML
    public void onMouseClicked(MouseEvent event){

        if (event.getSource() == imageNotSee){
            cont++;
            if(cont%2 == 0){
                imageSee.setVisible(true);
                imageNotSee.setVisible(false);
                txtPassView.setVisible(true);
                txtPassView.setText(txtPass.getText());
                txtPass.setVisible(false);
            }
        }

        if (event.getSource() == imageSee){
            cont++;
            if(cont%2 != 0){
                imageNotSee.setVisible(true);
                imageSee.setVisible(false);
                txtPass.setVisible(true);
                txtPass.setText(txtPassView.getText());
                txtPassView.setVisible(false);
            }
        }

        if (event.getSource() == imageMinimize){
            Stage stage = (Stage) this.btnLogin.getScene().getWindow();
            stage.setIconified(true);
        }

        if (event.getSource() == imageClose){
            Optional<ButtonType> result =messagesConfirmation(msjConfirmacion,"Confirmation", "Do you want to exit the application?");
            if(result.get() == ButtonType.OK){
                Stage stage = (Stage) this.btnLogin.getScene().getWindow();
                stage.close();
            }
        }

        if (event.getSource() == lblForgotPassword){

        }

        if (event.getSource() == lblRegister){
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("FXMLRegister.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e);
            }
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();

            Stage stageLogin = (Stage) txtUser.getScene().getWindow();
            stageLogin.close();
        }
    }

    @FXML
    private void txtUserOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){

        }
    }

    @FXML
    private void txtPassOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){

        }
    }

    @FXML
    private void txtPassViewUserOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){

        }
    }

    @FXML
    private void onActionEvents(ActionEvent event){
        if (event.getSource() == btnLogin){

        }
    }

    public Optional<ButtonType> messagesConfirmation(Alert alert, String title, String content){
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert.showAndWait();
    }

    public void messagesInformation(Alert alert, String title, String content){
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}