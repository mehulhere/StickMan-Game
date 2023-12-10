module com.example.first_fx_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires java.desktop;


    opens com.example.first_fx_project to javafx.fxml;
    exports com.example.first_fx_project;
}