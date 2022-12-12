package com.example.fpcashier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        File f = new File("src\\main\\java\\com\\example\\fpcashier\\fxml\\CashierUI.fxml");
        URL url = new File(f.getAbsolutePath().toString()).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Cashier");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}