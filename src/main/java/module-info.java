module com.example.emailvalidator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;


    opens com.example.emailvalidator to javafx.fxml;
    exports com.example.emailvalidator;
}