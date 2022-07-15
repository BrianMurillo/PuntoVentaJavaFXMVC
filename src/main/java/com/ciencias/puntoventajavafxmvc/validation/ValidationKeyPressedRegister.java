package com.ciencias.puntoventajavafxmvc.validation;

import com.ciencias.puntoventajavafxmvc.DAO.MessageHandling;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ValidationKeyPressedRegister {

    private static final Alert msjInformation = new Alert(Alert.AlertType.INFORMATION);

    public static void validateTxtName(TextField  txtName, TextField txtPaternalSurname){
        if(!"".equals(txtName.getText())){
            if(ValidationRegularExpressions.validateName(txtName.getText())){
                txtPaternalSurname.requestFocus();
            } else {
                MessageHandling.messagesInformation(msjInformation,"Information","Enter a correct name","Example: Brian, Juan, George");
                txtName.setText("");
                txtName.requestFocus();
            }
        } else {
            MessageHandling.messagesInformation(msjInformation,"Information",null,"Enter a name");
        }
    }

    public static void validateTxtPaternalSurname(TextField  txtPaternalSurname, TextField txtMaternalSurname){
        if(!"".equals(txtPaternalSurname.getText())){
            if(ValidationRegularExpressions.validateSurname(txtPaternalSurname.getText())){
                txtMaternalSurname.requestFocus();
            } else {
                MessageHandling.messagesInformation(msjInformation,"Information","Enter a correct paternal surname","Example: Murillo, Meza, Garcia");
                txtPaternalSurname.setText("");
                txtPaternalSurname.requestFocus();
            }
        } else {
            MessageHandling.messagesInformation(msjInformation,"Information",null,"Enter a paternal surname");
        }
    }

    public static void validateTxtMaternalSurname(TextField  txtMaternalSurname, TextField txtEmail){
        if(!"".equals(txtMaternalSurname.getText())){
            if(ValidationRegularExpressions.validateSurname(txtMaternalSurname.getText())){
                txtEmail.requestFocus();
            } else {
                MessageHandling.messagesInformation(msjInformation,"Information","Enter a correct maternal surname","Example: Salas, Hernandez");
                txtMaternalSurname.setText("");
                txtMaternalSurname.requestFocus();
            }
        } else {
            MessageHandling.messagesInformation(msjInformation,"Information",null,"Enter a maternal surname");
        }
    }

    public static void validateTxtEmail(TextField txtEmail, TextField txtUsername){
        validationEmail(txtEmail, txtUsername);
    }

    public static void validateTxtUsername(TextField txtUsername, TextField txtPassword){
        if(!"".equals(txtUsername.getText())){
            if(ValidationRegularExpressions.validateUsername(txtUsername.getText())){
                txtPassword.requestFocus();
            } else {
                MessageHandling.messagesInformation(msjInformation,"Information","Enter a correct username","Example: B3060, BMS2019600741, BMUSO54");
                txtUsername.setText("");
                txtUsername.requestFocus();
            }
        } else {
            MessageHandling.messagesInformation(msjInformation,"Information",null,"Enter a username");
        }
    }

    public static void validateTxtPassword(TextField txtPassword,TextField txtPhone){
        if(!"".equals(txtPassword.getText())){
            if(ValidationRegularExpressions.validatePassword(txtPassword.getText())){
                txtPhone.requestFocus();
            } else {
                MessageHandling.messagesInformation(msjInformation,"Information",null,"Insecure Password");
                txtPassword.setText("");
                txtPassword.requestFocus();
            }
        } else {
            MessageHandling.messagesInformation(msjInformation,"Information",null,"Enter a password");
        }
    }

    public static void validateTxtPhone(TextField txtPhone){
        if(!"".equals(txtPhone.getText())){
            if(!ValidationRegularExpressions.validatePhone(txtPhone.getText())){
                MessageHandling.messagesInformation(msjInformation,"Information",null,"Enter a correct phone with 10 digits");
                txtPhone.setText("");
                txtPhone.requestFocus();
            }
        } else {
            MessageHandling.messagesInformation(msjInformation,"Information",null,"Enter a phone");
        }
    }

    public static void validateTxtEmailLogin(TextField txtEmail, TextField txtPassword){
        validationEmail(txtEmail, txtPassword);
    }

    private static void validationEmail(TextField txtEmail, TextField txtPassword) {
        if(!"".equals(txtEmail.getText())){
            if(ValidationRegularExpressions.validateEmail(txtEmail.getText())){
                txtPassword.requestFocus();
            } else {
                MessageHandling.messagesInformation(msjInformation,"Information","Enter a correct email","Example: \nmuso@gmail.com \nbmurillo@outlook.com");
                txtEmail.setText("");
                txtEmail.requestFocus();
            }
        } else {
            MessageHandling.messagesInformation(msjInformation,"Information",null,"Enter a email");
        }
    }

    public static void validateTxtPasswordLogin(TextField txtPassword){
        if(!"".equals(txtPassword.getText())){
            if(!ValidationRegularExpressions.validatePassword(txtPassword.getText())){
                MessageHandling.messagesInformation(msjInformation,"Information",null,"Password Incorrect");
                txtPassword.setText("");
                txtPassword.requestFocus();
            }
        } else {
            MessageHandling.messagesInformation(msjInformation,"Information",null,"Enter a password");
        }
    }
}
