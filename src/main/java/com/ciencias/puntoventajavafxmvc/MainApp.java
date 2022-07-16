package com.ciencias.puntoventajavafxmvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Locale;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("FXMLLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    static Locale getLocaleSettingFromConfigurationFile(){
        return Locale.ENGLISH;
    }

    public static void main(String[] args) {
        Locale appLocale = getLocaleSettingFromConfigurationFile();
        Locale.setDefault(appLocale);
        launch();
    }
}