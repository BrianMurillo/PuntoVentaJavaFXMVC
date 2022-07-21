package com.ciencias.puntoventajavafxmvc.controller;

import com.ciencias.puntoventajavafxmvc.DAO.DataCBX;
import com.ciencias.puntoventajavafxmvc.DAO.MessageHandling;
import com.ciencias.puntoventajavafxmvc.DAO.UserDAO;
import com.ciencias.puntoventajavafxmvc.DTO.User;
import com.ciencias.puntoventajavafxmvc.MainApp;
import com.ciencias.puntoventajavafxmvc.validation.Validation;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ComboBox cbxRolUser;
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
    private ObservableList<User> listUsersData;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardContainer();
        clockRun();
        //DAOs initialized
        this.userDAO = new UserDAO();
        //Charge data users
        loadDataTableUsers();
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
        Callback<TableColumn<User, String>, TableCell<User, String>> cellFoctory = (TableColumn<User, String> param) -> {
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
        columnActionsUser.setCellFactory(cellFoctory);
        tableUsers.setItems(listUsersData);
    }

    private void chargeDataTableUsers() {
        tableUsers.getItems().clear();
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
        cbxRolUser.setPromptText(user.getRol());
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
        loadDataTableUsers();
    }
}
