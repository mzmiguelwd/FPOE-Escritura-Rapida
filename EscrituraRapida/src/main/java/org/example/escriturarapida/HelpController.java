package org.example.escriturarapida;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class HelpController {

    @FXML
    private Button buttonPlay;

    @FXML
    void onActionButtonPlay(ActionEvent event) throws IOException {
        SceneLoader.loadSceneFromNode("escritura-rapida-view.fxml", event);
    }

}