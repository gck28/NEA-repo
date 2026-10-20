module com.example.nea {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.xml;
    requires java.desktop;
    requires java.sql;


    opens com.example.nea to javafx.fxml;
    exports com.example.nea;
}