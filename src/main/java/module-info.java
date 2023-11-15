module com.example.zoo_management_system {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zoo_management_system to javafx.fxml;
    exports com.example.zoo_management_system;
}