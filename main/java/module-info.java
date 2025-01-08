module com.example.museum_new {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.museum_new to javafx.fxml;
    exports com.example.museum_new;
}