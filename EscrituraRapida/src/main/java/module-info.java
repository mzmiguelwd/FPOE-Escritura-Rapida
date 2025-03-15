module org.example.escriturarapida {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.escriturarapida to javafx.fxml;
    exports org.example.escriturarapida;
}