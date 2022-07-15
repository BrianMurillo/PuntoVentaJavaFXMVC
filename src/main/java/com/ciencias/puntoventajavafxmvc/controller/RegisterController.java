package com.ciencias.puntoventajavafxmvc.controller;

import com.ciencias.puntoventajavafxmvc.DAO.DataCBXRegister;
import com.ciencias.puntoventajavafxmvc.DAO.MessageHandling;
import com.ciencias.puntoventajavafxmvc.DAO.UserDAO;
import com.ciencias.puntoventajavafxmvc.DTO.User;
import com.ciencias.puntoventajavafxmvc.validation.Validation;
import com.ciencias.puntoventajavafxmvc.MainApp;
import com.ciencias.puntoventajavafxmvc.validation.ValidationKeyPressedRegister;
import com.ciencias.puntoventajavafxmvc.validation.ValidationRegister;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private TextField txtName;

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
    private ComboBox cbxMonth;

    @FXML
    private ComboBox cbxYear;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnReturn;

    @FXML
    private ImageView imgMinimize;

    @FXML
    private ImageView imgClose;
    //Cont imageSee
    private int cont = 1;

    // Argon2Types.ARGON2id
    // salt 32 bytes
    // Hash length 64 bytes
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);

    private UserDAO userDAO = new UserDAO();

    Alert msjConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    Alert msjInformation = new Alert(Alert.AlertType.INFORMATION);
    Alert msjError = new Alert(Alert.AlertType.ERROR);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageSee.setVisible(false);
        txtPasswordView.setVisible(false);
        cbxDay.getItems().addAll(DataCBXRegister.arrayDays());
        cbxMonth.getItems().addAll(DataCBXRegister.arrayMouths());
        cbxYear.getItems().addAll(DataCBXRegister.arrayYears());
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

        if (event.getSource() == imgMinimize){
            Stage stage = (Stage) this.btnRegister.getScene().getWindow();
            stage.setIconified(true);
        }

        if (event.getSource() == imgClose){
            Optional<ButtonType> result = MessageHandling.messageConfirmation(msjConfirmation,"Confirmation", "Do you want to exit the application?");
            if(result.get() == ButtonType.OK){
                Stage stage = (Stage) this.btnRegister.getScene().getWindow();
                stage.close();
            }
        }
    }

    @FXML
    private void txtNameOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            ValidationKeyPressedRegister.validateTxtName(txtName,txtPaternalSurname);
        }
    }

    @FXML
    private void txtPSurnameOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            ValidationKeyPressedRegister.validateTxtPaternalSurname(txtPaternalSurname,txtMaternalSurname);
        }
    }

    @FXML
    private void txtMSurnameOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            ValidationKeyPressedRegister.validateTxtMaternalSurname(txtMaternalSurname,txtEmail);
        }
    }

    @FXML
    private void txtEmailOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            ValidationKeyPressedRegister.validateTxtEmail(txtEmail,txtUsername);
        }
    }

    @FXML
    private void txtUsernameOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            ValidationKeyPressedRegister.validateTxtUsername(txtUsername,txtPassword);
        }
    }

    @FXML
    private void txtPasswordOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            ValidationKeyPressedRegister.validateTxtPassword(txtPassword,txtPhone);
        }
    }

    @FXML
    private void txtPasswordViewUserOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            ValidationKeyPressedRegister.validateTxtPassword(txtPasswordView,txtPhone);
        }
    }

    @FXML
    private void txtPhoneOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            ValidationKeyPressedRegister.validateTxtPhone(txtPhone);
        }
    }

    @FXML
    private void txtOnKeyTyped(KeyEvent event){
        if(event.getSource() == txtName){
            Validation.validateInputText(txtName);
        }

        if(event.getSource() == txtPaternalSurname){
            Validation.validateInputText(txtPaternalSurname);
        }

        if(event.getSource() == txtMaternalSurname){
            Validation.validateInputText(txtMaternalSurname);
        }

        if(event.getSource() == txtPhone){
            Validation.validateInputNumber(txtPhone);
        }

        if(event.getSource() == txtUsername){
            Validation.validateInputNumberText(txtUsername);
        }
    }

    @FXML
    private void onActionEvents(ActionEvent event){
        if (event.getSource() == btnRegister){
            if(validationFields()){
                if(!userDAO.validationEmail(txtEmail.getText())){
                    User user = recuperarDatos();
                    if (userDAO.saveUser(user)) {
                        MessageHandling.messagesInformation(msjInformation,"Information",null,"Register Completed\n");
                        viewLogin();
                    } else {
                        MessageHandling.messagesError(msjError,"Error","Registration error\n","Server error\n");
                    }
                } else {
                     MessageHandling.messagesError(msjError,"Error","Sign up failed\n","Email already exists\n");
                }
            }
        }

        if (event.getSource() == btnReturn){
            viewLogin();
        }

        if (event.getSource() == rbMan){
            if(rbMan.isSelected()){
                rbWoman.setDisable(true);
                rbMan.setDisable(false);
            } else {
                rbWoman.setDisable(false);
                rbMan.setDisable(false);
            }
        }

        if (event.getSource() == rbWoman){
            if(rbWoman.isSelected()){
                rbWoman.setDisable(false);
                rbMan.setDisable(true);
            } else {
                rbWoman.setDisable(false);
                rbMan.setDisable(false);
            }
        }

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
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        Stage stageRegister = (Stage) txtUsername.getScene().getWindow();
        stageRegister.close();
    }

    private User recuperarDatos() {
        User user = new User();
        user.setName(txtName.getText());
        user.setPaternalSurname(txtPaternalSurname.getText());
        user.setMaternalSurname(txtMaternalSurname.getText());
        user.setEmail(txtEmail.getText());
        user.setUsername(txtUsername.getText());
        String hash = argon2.hash(11, 32768, 1, txtPassword.getText());
        user.setPassword(hash);
        user.setPhone(txtPhone.getText());
        user.setGender(selectionGender());
        user.setBirthday(selectionBirthday());
        return user;
    }

    private boolean validationFields() {
        boolean vName = ValidationRegister.validationName(txtName);
        boolean vPSurname = ValidationRegister.validationPaternalSurname(txtPaternalSurname);
        boolean vMSurname = ValidationRegister.validationMaternalSurname(txtMaternalSurname);
        boolean vEmail = ValidationRegister.validationEmail(txtEmail);
        boolean vUserName = ValidationRegister.validationUserName(txtUsername);
        boolean vPassword = ValidationRegister.validationPassword(cont,txtPassword,txtPasswordView);
        boolean vPhone = ValidationRegister.validationPhone(txtPhone);
        boolean vGender = ValidationRegister.validationGender(rbMan,rbWoman);
        boolean vBirthday = ValidationRegister.validationBirthday(cbxDay,cbxMonth,cbxYear);
        return vName && vPSurname && vMSurname && vEmail && vUserName && vPassword && vPhone && vGender &&vBirthday;
    }

    private char selectionGender() {
        if(rbMan.isSelected()){
            return 'M';
        } else {
            return 'W';
        }
    }

    private String selectionBirthday() {
        String day = cbxDay.getSelectionModel().getSelectedItem().toString();
        String mouth = cbxMonth.getSelectionModel().getSelectedItem().toString();
        String year = cbxYear.getSelectionModel().getSelectedItem().toString();
        return year+"-"+mouth+"-"+day;
    }
}