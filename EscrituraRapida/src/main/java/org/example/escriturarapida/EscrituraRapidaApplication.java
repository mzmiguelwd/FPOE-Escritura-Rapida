package org.example.escriturarapida;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class EscrituraRapidaApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EscrituraRapidaApplication.class.getResource("escritura-rapida-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene gameScene = new Scene(root, 650, 380);
        primaryStage.setTitle("Escritura Rápida - Game");
        primaryStage.setScene(gameScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}