module com.example.bank {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bank to javafx.fxml;
    exports com.example.bank;
    opens com.example.bank.model to javafx.fxml;
    exports com.example.bank.model;
}