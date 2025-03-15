package org.example.escriturarapida;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;

public class StatsController {

    @FXML
    private Button buttonPlayAgain;

    @FXML
    private Label labelCongratulations;

    @FXML
    void onActionButtonPlayAgain(ActionEvent event) throws IOException {
        SceneLoader.loadSceneFromNode("escritura-rapida-view.fxml", event);
    }

}
