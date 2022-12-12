module com.example.fpcashier {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.fpcashier to javafx.fxml;
    exports com.example.fpcashier;
    exports com.example.fpcashier.controller;
    opens com.example.fpcashier.controller to javafx.fxml;
    exports com.example.fpcashier.product;
    opens com.example.fpcashier.product to javafx.fxml;
}