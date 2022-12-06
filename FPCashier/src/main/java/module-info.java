module com.example.fpcashier {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.fpcashier to javafx.fxml;
    exports com.example.fpcashier;
}