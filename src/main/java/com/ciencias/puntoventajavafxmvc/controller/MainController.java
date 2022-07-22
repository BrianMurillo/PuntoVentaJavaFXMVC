package com.ciencias.puntoventajavafxmvc.controller;

import com.ciencias.puntoventajavafxmvc.DAO.DataCBX;
import com.ciencias.puntoventajavafxmvc.DAO.LoginDAO;
import com.ciencias.puntoventajavafxmvc.DAO.MessageHandling;
import com.ciencias.puntoventajavafxmvc.DAO.UserDAO;
import com.ciencias.puntoventajavafxmvc.DTO.User;
import com.ciencias.puntoventajavafxmvc.MainApp;
import com.ciencias.puntoventajavafxmvc.validation.Validation;
import com.ciencias.puntoventajavafxmvc.validation.ValidationUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TabPane tpContainer;

    //Tabs
    @FXML
    private Tab tabDashboard;
    @FXML
    private Tab tabUsers;

    //navbar
    @FXML
    private JFXButton btnDashboard;
    @FXML
    private JFXButton btnUsers;

    //Users controls
    @FXML
    private TextField txtidUser;
    @FXML
    private TextField txtNameUser;
    @FXML
    private TextField txtPSurnameUser;
    @FXML
    private TextField txtMSurnameUser;
    @FXML
    private TextField txtEmailUser;
    @FXML
    private TextField txtUserNameUser;
    @FXML
    private TextField txtPhoneUser;
    @FXML
    private TextField txtSearchUser;
    @FXML
    private ImageView imageSearchUser;
    @FXML
    private DatePicker datePickerBirthdayUser;
    @FXML
    private JFXComboBox cbxRolUser;
    @FXML
    private JFXButton btnUpdateUser;
    @FXML
    private JFXButton btnCleanUser;
    @FXML
    private TableView<User> tableUsers;
    @FXML
    private TableColumn<User, Integer> columnIdUser;
    @FXML
    private TableColumn<User, String> columnNameUser;
    @FXML
    private TableColumn<User, String> columnPSurnameUser;
    @FXML
    private TableColumn<User, String> columnMSurnameUser;
    @FXML
    private TableColumn<User, String> columnEmailUser;
    @FXML
    private TableColumn<User, String> columnUsernameUser;
    @FXML
    private TableColumn<User, String> columnPhoneUser;
    @FXML
    private TableColumn<User, String> columnBirthdayUser;
    @FXML
    private TableColumn<User, String> columnRolUser;
    @FXML
    private TableColumn<User, String> columnActionsUser;
    private List<User> listUsers;
    private ObservableList<User> listUsersData = FXCollections.observableArrayList();
    private User user;

    //controls generals
    @FXML
    private ImageView imageMinimize;
    @FXML
    private ImageView imageMaximize;
    @FXML
    private ImageView imageClose;
    @FXML
    private AnchorPane anchorDashboard=null;
    @FXML
    private Label lblClock;

    //messages
    Alert msjConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    Alert msjInformation = new Alert(Alert.AlertType.INFORMATION);
    Alert msjError = new Alert(Alert.AlertType.ERROR);

    int contMaximize=2;

    //DAOs
    private UserDAO userDAO;
    private LoginDAO loginDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardContainer();
        clockRun();
        //DAOs initialized
        this.userDAO = new UserDAO();
        this.loginDAO = new LoginDAO();
        //Charge data users
        loadDataTableUsers();
        dynamicSearchUser();
        cbxRolUser.getItems().addAll(DataCBX.arrayRols());
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

        if(event.getSource() == imageSearchUser){

        }
    }

    @FXML
    public void onActionEvents(ActionEvent event){
        //Methods nav
        if(event.getSource() == btnDashboard){
            tpContainer.getSelectionModel().select(tabDashboard);
            dashboardContainer();
        }
        if(event.getSource() == btnUsers){
            tpContainer.getSelectionModel().select(tabUsers);
            loadDataTableUsers();
        }
        //Methods user
        if(event.getSource() == btnUpdateUser){
            if(!ValidationUser.validationId(txtidUser)){
                return;
            }
            if(validationFieldsUser()){
                user = recoverDataUser();
                if(userDAO.updateUser(user)){
                    MessageHandling.messagesInformation(msjInformation,"Information",null,"Update user completed\n");
                    cleanUsers();
                } else {
                    MessageHandling.messagesError(msjError,"Error","Update error\n","Server error\n");
                }
            }
        }
        if(event.getSource() == btnCleanUser){
            cleanUsers();
        }

    }

    private void runClock() {
        while (true) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Getting the system time in a string
                    String time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
                    // Setting the time in a label
                    lblClock.setText(time);
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void dashboardContainer(){
        Node[] nodes = new Node[1];
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("FXMLDashboard.fxml"));
            nodes[0] = fxmlLoader.load();
            anchorDashboard.getChildren().add(nodes[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clockRun(){
        Runnable clock = this::runClock;
        Thread newClock = new Thread(clock); //Creating new thread
        newClock.setDaemon(true); //Thread will automatically close on applications closing
        newClock.start(); //Starting Thread
    }

    private void loadDataTableUsers(){
        chargeDataTableUsers();
        Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory = (TableColumn<User, String> param) -> {
            final TableCell<User, String> cell = new TableCell<>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    } else {
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        editIcon.setSize("40px");
                        editIcon.setFill(Paint.valueOf("#00E676"));
                        editIcon.setCursor(Cursor.HAND);
                        deleteIcon.setSize("40px");
                        deleteIcon.setFill(Paint.valueOf("#ff1744"));
                        deleteIcon.setCursor(Cursor.HAND);

                        editIcon.setOnMouseClicked((MouseEvent event) ->{
                            chargeDataEditUser();
                        });

                        deleteIcon.setOnMouseClicked((MouseEvent) -> {
                            btnDeleteUser();
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:top-center;");
                        HBox.setMargin(deleteIcon, new Insets(5, 6, 5, 6));
                        HBox.setMargin(editIcon, new Insets(5, 6, 5, 6));

                        setGraphic(managebtn);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        columnActionsUser.setCellFactory(cellFactory);
        tableUsers.setItems(listUsersData);
    }

    private void btnDeleteUser() {
        Optional<ButtonType> result = MessageHandling.messageConfirmation(msjConfirmation,"Confirmation", "Do you want to delete the user?");
        if(result.get() == ButtonType.OK){
            user = tableUsers.getSelectionModel().getSelectedItem();
            if (!loginDAO.deleteUserLogin(user.getId_user())){
                MessageHandling.messagesError(msjError,"Error","Error when deleting login_user\n","Server error\n");
                return;
            }
            if(userDAO.deleteUser(user.getId_user())){
                MessageHandling.messagesInformation(msjInformation,"Information",null,"Delete user completed\n");
                loadDataTableUsers();
                cleanUsers();
            } else {
                MessageHandling.messagesError(msjError,"Error","Error when deleting user\n","Server error\n");
            }
        }
    }

    private void chargeDataTableUsers() {
        tableUsers.getColumns().clear();

        listUsers = userDAO.findAllUser();
        listUsersData = FXCollections.observableArrayList(listUsers);

        columnIdUser.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        columnNameUser.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPSurnameUser.setCellValueFactory(new PropertyValueFactory<>("paternalSurname"));
        columnMSurnameUser.setCellValueFactory(new PropertyValueFactory<>("maternalSurname"));
        columnEmailUser.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnUsernameUser.setCellValueFactory(new PropertyValueFactory<>("username"));
        columnPhoneUser.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnBirthdayUser.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        columnRolUser.setCellValueFactory(new PropertyValueFactory<>("rol"));
        tableUsers.setItems(listUsersData);
        tableUsers.getColumns().addAll(columnIdUser,columnNameUser,columnPSurnameUser,columnMSurnameUser,columnEmailUser,columnUsernameUser,columnPhoneUser,columnBirthdayUser,columnRolUser,columnActionsUser);
    }

    public void chargeDataEditUser(){
        user = tableUsers.getSelectionModel().getSelectedItem();
        txtidUser.setText(String.valueOf(user.getId_user()));
        txtNameUser.setText(user.getName());
        txtPSurnameUser.setText(user.getPaternalSurname());
        txtMSurnameUser.setText(user.getMaternalSurname());
        txtEmailUser.setText(user.getEmail());
        txtUserNameUser.setText(user.getUsername());
        txtPhoneUser.setText(user.getPhone());
        datePickerBirthdayUser.setValue(Validation.LOCAL_DATE(user.getBirthday()));
        cbxRolUser.getSelectionModel().select(user.getRol());
    }

    public void cleanUsers(){
        txtidUser.setText("");
        txtNameUser.setText("");
        txtPSurnameUser.setText("");
        txtMSurnameUser.setText("");
        txtEmailUser.setText("");
        txtUserNameUser.setText("");
        txtPhoneUser.setText("");
        datePickerBirthdayUser.setValue(null);
        cbxRolUser.getItems().clear();
        cbxRolUser.getItems().addAll(DataCBX.arrayRols());
        cbxRolUser.setPromptText("ROL");
        txtSearchUser.setText("");
        loadDataTableUsers();
        dynamicSearchUser();
    }

    private boolean validationFieldsUser() {
        boolean vId = ValidationUser.validationId(txtidUser);
        boolean vName = ValidationUser.validationName(txtNameUser);
        boolean vPSurname = ValidationUser.validationPaternalSurname(txtPSurnameUser);
        boolean vMSurname = ValidationUser.validationMaternalSurname(txtMSurnameUser);
        boolean vEmail = ValidationUser.validationEmail(txtEmailUser);
        boolean vUserName = ValidationUser.validationUserName(txtUserNameUser);
        boolean vPhone = ValidationUser.validationPhone(txtPhoneUser);
        boolean vBirthday = ValidationUser.validationDatePicker(datePickerBirthdayUser);
        boolean vRol = ValidationUser.validationRol(cbxRolUser);
        return vName && vPSurname && vMSurname && vEmail && vUserName && vPhone && vBirthday && vRol && vId;
    }

    private User recoverDataUser() {
        User user = new User();
        user.setId_user(Integer.parseInt(txtidUser.getText()));
        user.setName(txtNameUser.getText());
        user.setPaternalSurname(txtPSurnameUser.getText());
        user.setMaternalSurname(txtMSurnameUser.getText());
        user.setEmail(txtEmailUser.getText());
        user.setUsername(txtUserNameUser.getText());
        user.setPhone(txtPhoneUser.getText());
        user.setBirthday(datePickerBirthdayUser.getValue().toString());
        user.setRol(String.valueOf(cbxRolUser.getValue()));
        return user;
    }

    private void dynamicSearchUser() {
        //Search dynamic
        FilteredList<User> filteredDataUser = new FilteredList<>(listUsersData, b->true);
        txtSearchUser.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredDataUser.setPredicate(userSearchModel ->{

                if(newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                //lower case for search fast
                String searchKeyword = newValue.toLowerCase();

                if(String.valueOf(userSearchModel.getId_user()).indexOf(searchKeyword) > -1){
                    return true;
                } else if (userSearchModel.getName().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (userSearchModel.getUsername().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (userSearchModel.getBirthday().indexOf(searchKeyword) > -1) {
                    return true;
                } else {
                    return false; //no match found
                }

            });
        });
        //sorted list
        SortedList<User>  sortedUserData = new SortedList<>(filteredDataUser);
        //Bind sorted result
        sortedUserData.comparatorProperty().bind(tableUsers.comparatorProperty());

        tableUsers.setItems(sortedUserData);
    }
}
