module com.ciencias.puntoventajavafxmvc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ciencias.puntoventajavafxmvc to javafx.fxml;
    exports com.ciencias.puntoventajavafxmvc;
    exports com.ciencias.puntoventajavafxmvc.controller;
    opens com.ciencias.puntoventajavafxmvc.controller to javafx.fxml;
}