package com.ciencias.puntoventajavafxmvc.validation;

import com.ciencias.puntoventajavafxmvc.DAO.MessageHandling;
import javafx.scene.control.*;

import java.time.LocalDate;

public class ValidationUser {

    private static Alert msjInformation = new Alert(Alert.AlertType.INFORMATION);

    public static boolean validationName(TextField txtName){
        if(!ValidationRegularExpressions.validateName(txtName.getText())){
            MessageHandling.messagesInformation(msjInformation,"Information","Name incorrect","Example: Brian, Pedro, Juan");
            return false;
        }
        return true;
    }

    public static boolean validationPaternalSurname(TextField txtPaternalSurname){
        if(!ValidationRegularExpressions.validateSurname(txtPaternalSurname.getText())){
            MessageHandling.messagesInformation(msjInformation,"Information","Paternal surname incorrect","Example: Murillo, Suarez, Mejia");
            return false;
        }
        return true;
    }

    public static boolean validationMaternalSurname(TextField txtMaternalSurname){
        if(!ValidationRegularExpressions.validateSurname(txtMaternalSurname.getText())){
            MessageHandling.messagesInformation(msjInformation,"Information","Maternal surname incorrect","Example: Salas, Mendez, Hernandez");
            return false;
        }
        return true;
    }

    public static boolean validationEmail(TextField txtEmail){
        if(!ValidationRegularExpressions.validateEmail(txtEmail.getText())){
            MessageHandling.messagesInformation(msjInformation,"Information","Email incorrect","Example: \nmusovespo54@gmail.com\nporrovespo@yahoo.com");
            return false;
        }
        return true;
    }

    public static boolean validationUserName(TextField txtUsername){
        if(!ValidationRegularExpressions.validateUsername(txtUsername.getText())){
            MessageHandling.messagesInformation(msjInformation,"Information","Username incorrect","Example: muso54, admin54, root");
            return false;
        }
        return true;
    }

    public static boolean validationPassword(int cont,TextField txtPassword, TextField txtPasswordView){
        if(cont % 2 == 0) {
            if (!ValidationRegularExpressions.validatePassword(txtPasswordView.getText())) {
                MessageHandling.messagesInformation(msjInformation,"Information","Password incorrect", "Enter a password with of 5 length minimum");
                return false;
            }
        } else {
            if (!ValidationRegularExpressions.validatePassword(txtPassword.getText())) {
                MessageHandling.messagesInformation(msjInformation,"Information","Password incorrect", "Enter a password with of 5 length minimum");
                return false;
            }
        }
        return true;
    }

    public static boolean validationPhone(TextField txtPhone){
        if(!ValidationRegularExpressions.validatePhone(txtPhone.getText())){
            MessageHandling.messagesInformation(msjInformation,"Information","Phone incorrect","Enter phone with 10 digits");
            return false;
        }
        return true;
    }

    public static boolean validationGender(RadioButton rbMan,RadioButton rbWoman){
        if(rbMan.isSelected() || rbWoman.isSelected()){
            return true;
        } else {
            MessageHandling.messagesInformation(msjInformation,"Information",null,"Select your gender");
            return false;
        }
    }

    public static boolean validationBirthday(ComboBox cbxDay, ComboBox cbxMonth, ComboBox cbxYear) {
        if(cbxDay.getSelectionModel().getSelectedItem() != null && cbxMonth.getSelectionModel().getSelectedItem() != null && cbxYear.getSelectionModel().getSelectedItem() != null){
            return true;
        } else {
            MessageHandling.messagesInformation(msjInformation,"Information",null,"Select your birthday");
            return false;
        }
    }

    public static boolean validationDatePicker(DatePicker datePickerBirthday) {
        LocalDate myDate = datePickerBirthday.getValue();
        if(myDate != null){
            return true;
        } else {
            MessageHandling.messagesInformation(msjInformation,"Information",null,"Select your birthday");
            return false;
        }
    }

    public static boolean validationRol(ComboBox cbxRolUser) {
        if(cbxRolUser.getSelectionModel().getSelectedItem() != null){
            return true;
        } else {
            MessageHandling.messagesInformation(msjInformation,"Information",null,"Select rol");
            return false;
        }
    }

    public static boolean validationId(TextField txtidUser) {
        if(!"".equals(txtidUser.getText())){
            return true;
        } else {
            MessageHandling.messagesInformation(msjInformation,"Information",null,"Select a user");
            return false;
        }
    }
}
