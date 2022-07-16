package com.ciencias.puntoventajavafxmvc.controller;

import com.ciencias.puntoventajavafxmvc.DAO.LoginDAO;
import com.ciencias.puntoventajavafxmvc.DAO.MessageHandling;
import com.ciencias.puntoventajavafxmvc.DAO.UserDAO;
import com.ciencias.puntoventajavafxmvc.DTO.User;
import com.ciencias.puntoventajavafxmvc.MainApp;
import com.ciencias.puntoventajavafxmvc.validation.ValidationKeyPressed;
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
import javafx.scene.paint.Color;
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

    Alert msjConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    Alert msjInformation = new Alert(Alert.AlertType.INFORMATION);
    Alert msjError = new Alert(Alert.AlertType.ERROR);

    //Cont see
    private int cont=1;

    // Argon2Types.ARGON2id
    // salt 32 bytes
    // Hash length 64 bytes
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);


    private UserDAO userDAO = new UserDAO();
    private LoginDAO loginDAO = new LoginDAO();
    User userLoginCheckBoxActive = new User();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageSee.setVisible(false);
        txtPassView.setVisible(false);
        checkboxValidation();
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
            Optional<ButtonType> result = MessageHandling.messageConfirmation(msjConfirmation,"Confirmation", "Do you want to exit the application?");
            if(result.get() == ButtonType.OK){
                Stage stage = (Stage) this.btnLogin.getScene().getWindow();
                stage.close();
            }
        }

        if (event.getSource() == lblForgotPassword){
            viewScene("ForgotPassword.fxml");
        }

        if (event.getSource() == lblRegister){
            viewScene("FXMLRegister.fxml");
        }
    }

    @FXML
    private void txtUserOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            if(cont % 2 == 0) {
                ValidationKeyPressed.validateTxtEmailLogin(txtUser, txtPassView);
            } else {
                ValidationKeyPressed.validateTxtEmailLogin(txtUser,txtPass);
            }
        }
    }

    @FXML
    private void txtPassOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            ValidationKeyPressed.validateTxtPasswordLogin(txtPass);
        }
    }

    @FXML
    private void txtPassViewUserOnKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            ValidationKeyPressed.validateTxtPasswordLogin(txtPassView);
        }
    }

    @FXML
    private void onActionEvents(ActionEvent event){
        if (event.getSource() == btnLogin){
            if(validationFields()){
                if(!"".equals(userDAO.recuperationPass(txtUser.getText()))){
                    String passHash = userDAO.recuperationPass(txtUser.getText());
                    if(argon2.verify(passHash, txtPasswordSelect())){
                        System.out.println("Bienvenido");
                        verifyCheckBoxSave();
                    } else {
                        MessageHandling.messagesError(msjError,"Error","Sign In failed\n","Password Incorrect\n");
                    }
                } else {
                    MessageHandling.messagesError(msjError,"Error","Sign In failed\n","Email Incorrect\n");
                }
            }
        }
    }

    private String txtPasswordSelect() {
        if(cont % 2 == 0){
            return txtPassView.getText();
        } else {
            return  txtPass.getText();
        }
    }

    private boolean validationFields() {
        boolean vEmail = ValidationRegister.validationEmail(txtUser);
        boolean vPassword = ValidationRegister.validationPassword(cont,txtPass,txtPassView);
        return  vEmail && vPassword;
    }

    public void viewScene(String view){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(view));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        Stage stageLogin = (Stage) txtUser.getScene().getWindow();
        stageLogin.close();
    }

    private void checkboxValidation() {
        userLoginCheckBoxActive = loginDAO.getUserLogin();
        if(userLoginCheckBoxActive.getActive() == 1){
            txtUser.setText(userLoginCheckBoxActive.getEmail());
            txtPass.setText(userLoginCheckBoxActive.getPassword());
            ckbRemember.setSelected(true);
        }
    }

    private void verifyCheckBoxSave() {
        userLoginCheckBoxActive.setEmail(txtUser.getText());
        userLoginCheckBoxActive.setPassword(txtPasswordSelect());
        userLoginCheckBoxActive.setId_user(userDAO.recuperationIdUser(txtUser.getText()));
        if(ckbRemember.isSelected()){
            userLoginCheckBoxActive.setActive(1);
        } else{
            userLoginCheckBoxActive.setActive(0);
        }
        loginDAO.saveUserLogin(userLoginCheckBoxActive);
    }
}