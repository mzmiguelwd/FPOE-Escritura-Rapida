package org.example.escriturarapida;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneLoader {

    public static void loadSceneFromNode(String fxmlPath, Event event) throws IOException {
        // Carga la nueva escena desde el archivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(SceneLoader.class.getResource(fxmlPath));
        Parent newScene = fxmlLoader.load();

        // Obtiene la ventana actual a partir del evento
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Establece la nueva escena en la ventana
        currentStage.setScene(new Scene(newScene));
    }

    public static void loadSceneFromOther(String fxmlPath, Stage currentStage) throws IOException {
        // Carga la nueva escena desde el archivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(SceneLoader.class.getResource(fxmlPath));
        Parent newScene = fxmlLoader.load();

        // Establece la nueva escena en la ventana
        currentStage.setScene(new Scene(newScene));
    }

}