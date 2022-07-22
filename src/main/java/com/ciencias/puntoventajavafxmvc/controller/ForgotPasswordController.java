package com.ciencias.puntoventajavafxmvc.controller;

import com.ciencias.puntoventajavafxmvc.DAO.MessageHandling;
import com.ciencias.puntoventajavafxmvc.DAO.UserDAO;
import com.ciencias.puntoventajavafxmvc.DTO.User;
import com.ciencias.puntoventajavafxmvc.MainApp;
import com.ciencias.puntoventajavafxmvc.validation.Validation;
import com.ciencias.puntoventajavafxmvc.validation.ValidationKeyPressed;
import com.ciencias.puntoventajavafxmvc.validation.ValidationUser;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtUser;

    @FXML
    private DatePicker datePickerBirthday;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtPassView;

    @FXML
    private ImageView imageSee;

    @FXML
    private ImageView imageNotSee;

    @FXML
    private ImageView imageMinimize;

    @FXML
    private ImageView imageClose;

    @FXML
    private Button btnReturn;

    @FXML
    private Button btnRecover;

    Alert msjConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    Alert msjInformation = new Alert(Alert.AlertType.INFORMATION);
    Alert msjError = new Alert(Alert.AlertType.ERROR);

    //Cont see
    private int cont=1;

    private UserDAO userDAO = new UserDAO();

    // Argon2Types.ARGON2id
    // salt 32 bytes
    // Hash length 64 bytes
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);


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
            Stage stage = (Stage) this.imageMinimize.getScene().getWindow();
            stage.setIconified(true);
        }

        if (event.getSource() == imageClose){
            Optional<ButtonType> result = MessageHandling.messageConfirmation(msjConfirmation,"Confirmation", "Do you want to exit the application?");
            if(result.get() == ButtonType.OK){
                Stage stage = (Stage) this.imageClose.getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    private void txtEmailOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            ValidationKeyPressed.validateTxtEmail(txtEmail,txtUser);
        }
    }

    @FXML
    private void txtUsernameOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            ValidationKeyPressed.validateTxtUsername(txtUser,txtPass);
        }
    }

    @FXML
    private void txtPasswordOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            ValidationKeyPressed.validateTxtPasswordLogin(txtPass);
        }
    }

    @FXML
    private void txtPasswordViewUserOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            ValidationKeyPressed.validateTxtPasswordLogin(txtPassView);
        }
    }

    @FXML
    private void txtOnKeyTyped(KeyEvent event){
        if(event.getSource() == txtUser){
            Validation.validateInputNumberText(txtUser);
        }
    }

    @FXML
    private void onActionEvents(ActionEvent event){
        if (event.getSource() == btnReturn){
            viewLogin();
        }

        if(event.getSource() == btnRecover){
            if(validationFieldsForgotPassword()){
                User user = recoverData();
                if(userDAO.forgotPasswordUser(user)){
                    if(userDAO.updatePasswordUser(user)){
                        MessageHandling.messagesInformation(msjInformation,"Information",null,"Updated password\n");
                        viewLogin();
                    } else {
                        MessageHandling.messagesError(msjError,"Error","Registration error\n","Server error\n");
                    }
                } else {
                    MessageHandling.messagesError(msjError,"Error","User does not exist\n","Incorrect data\n");
                }
            }
        }
    }

    private boolean validationFieldsForgotPassword() {
        boolean vEmail = ValidationUser.validationEmail(txtEmail);
        boolean vUserName = ValidationUser.validationUserName(txtUser);
        boolean vBirthday = ValidationUser.validationDatePicker(datePickerBirthday);
        boolean vPassword = ValidationUser.validationPassword(cont,txtPass,txtPassView);
        return vEmail && vUserName && vBirthday && vPassword;
    }

    private void viewLogin() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("FXMLLogin.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        Stage stageRegister = (Stage) txtUser.getScene().getWindow();
        stageRegister.close();
    }

    private User recoverData() {
        User user = new User();
        user.setEmail(txtEmail.getText());
        user.setUsername(txtUser.getText());
        user.setBirthday(datePickerBirthday.getValue().toString());
        String hash = argon2.hash(11, 32768, 1, txtPasswordSelect());
        user.setPassword(hash);
        return user;
    }

    private String txtPasswordSelect() {
        if(cont % 2 == 0){
            return txtPassView.getText();
        } else {
            return  txtPass.getText();
        }
    }
}
