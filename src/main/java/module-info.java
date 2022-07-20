module com.ciencias.puntoventajavafxmvc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires de.mkammerer.argon2;
    requires com.jfoenix;


    opens com.ciencias.puntoventajavafxmvc to javafx.fxml;
    exports com.ciencias.puntoventajavafxmvc;
    exports com.ciencias.puntoventajavafxmvc.controller;
    exports com.ciencias.puntoventajavafxmvc.DTO;
    exports com.ciencias.puntoventajavafxmvc.DAO;
    opens com.ciencias.puntoventajavafxmvc.controller to javafx.fxml;
}