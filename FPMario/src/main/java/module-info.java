module com.example.fpmario {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.fpmario to javafx.fxml;
    exports com.example.fpmario;
}