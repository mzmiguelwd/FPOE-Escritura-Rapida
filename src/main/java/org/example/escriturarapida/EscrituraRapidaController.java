package org.example.escriturarapida;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class EscrituraRapidaController {

    @FXML
    private Button buttonHelp;

    @FXML
    private Button buttonValidatePhrase;

    @FXML
    private ImageView imageMeteorite;

    @FXML
    private ImageView imageSun;

    @FXML
    private ImageView imageTime;

    @FXML
    private Label labelInfoText;

    @FXML
    private Label labelIterativePhrase;

    @FXML
    private Label labelLevelNum;

    @FXML
    private Label labelLevelTxt;

    @FXML
    private Label labelTime;

    @FXML
    private TextField textFieldPhrase;

    private String[] words = {
            "sol",     // Nivel 1
            "luz",     // Nivel 2
            "flor",    // Nivel 3
            "luna",    // Nivel 4
            "libro",   // Nivel 5
            "árbol",        // Nivel 6
            "camino",       // Nivel 7
            "espejo",       // Nivel 8
            "montaña",      // Nivel 9
            "computador",   // Nivel 10
            "el sol brilla",    // Nivel 11
            "un ave vuela",     // Nivel 12
            "la flor crece",    // Nivel 13
            "el perro ladra",   // Nivel 14
            "el gato maulla",   // Nivel 15
            "Aprender es crecer cada día",               // Nivel 16
            "La paciencia es una virtud importante",     // Nivel 17
            "El éxito depende del esfuerzo constante",   // Nivel 18
            "El bosque esconde secretos milenarios",     // Nivel 19
            "El tren avanza rápidamente por las vías",   // Nivel 20
            "El sol brilla intensamente en el cielo despejado de la mañana",                   // Nivel 21
            "La luna ilumina la noche con su luz plateada, creando un ambiente mágico",        // Nivel 22
            "Los pájaros cantan alegremente al amanecer, llenando el bosque de melodías",      // Nivel 23
            "El viento sopla con fuerza entre los árboles, moviendo las hojas suavemente",     // Nivel 24
            "El café de la mañana sabe mejor con tranquilidad, acompañado de un buen libro",   // Nivel 25
    };

    private int currentIndex = 0;
    private int currentLevel = 1;
    private int errorsCounter = 0;
    private int gameTime = 20;
    private Timeline timeline;
    private boolean playing = false;

    private void setGame() {
        stopTimer();

        this.currentIndex = 0; // Reinicia el indice de las palabras/frases
        this.currentLevel = 1; // Reinicia el nivel
        this.errorsCounter = 0; // Reinicia el contador de errores
        this.gameTime = 20; // Reinicia el tiempo

        // Actualiza la interfaz de usuario
        labelLevelNum.setText(String.valueOf(currentLevel));
        labelTime.setText(("00:" + String.valueOf(gameTime)));
    }

    private void startTimer() {
        stopTimer();

        // Crea un nuevo temporizador
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameTime--;

                // Actualiza el texto del labelTime
                if (gameTime <= 9) {
                    labelTime.setText(("00:0" + String.valueOf(gameTime)));
                } else {
                    labelTime.setText(("00:" + String.valueOf(gameTime)));
                }

                // Verifica si el tiempo se ha agotado
                if (gameTime <= 0) {
                    stopTimer();
                    validatePhrase();

                    // Carga la escena de estadísticas
                    try {
                        Stage currentStage = (Stage) labelInfoText.getScene().getWindow();
                        SceneLoader.loadSceneFromOther("stats-view.fxml", currentStage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE); // Repite indefinidamente el temporizador
        timeline.play(); // Inicia el temporizador
    }

    private void stopTimer() {
        if (timeline != null) {
            timeline.stop(); // Detiene el temporizador
            timeline = null; // Elimina la referencia al temporizador anterior
        }
    }

    private void startGame() {
        gameTime = 20;
        startTimer();
        labelInfoText.setContentDisplay(ContentDisplay.TEXT_ONLY);
        labelIterativePhrase.setText(words[currentIndex]);
    }

    private void increaseLevel() {
        currentLevel++;
        labelLevelNum.setText(String.valueOf(currentLevel));
    }

    private void validatePhrase() {
        String inputPhrase = textFieldPhrase.getText();
        String currentWord = words[currentIndex];
        currentIndex++; // Avanza al siguiente índice

        if (inputPhrase.equals(currentWord)) { // Si la palabra/frase ingresada coinciden
            textFieldPhrase.clear(); // Limpia el campo de texto

            if (currentIndex < words.length - 1) {
                increaseLevel(); // Aumenta el nivel

                gameTime = 20;
                stopTimer(); // Detiene el temporizador actual
                startTimer(); // Inicia el temporizador con el tiempo actualizado

                labelIterativePhrase.setText(words[currentIndex]); // Muestra la siguiente palabra/frase
                labelInfoText.setText("Escribe la siguiente frase:"); // Actualiza el mensaje de información
            } else {
                // Si no hay más palabras, el juego termina
                stopTimer();
                // Carga la escena de estadísticas
                try {
                    Stage currentStage = (Stage) labelInfoText.getScene().getWindow();
                    SceneLoader.loadSceneFromOther("stats-view.fxml", currentStage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                textFieldPhrase.setDisable(true);
                playing = false; // Restablece el estado del juego
            }
        } else { // Si la palabra/frase ingresada no coinciden
            errorsCounter++;
            labelIterativePhrase.setText(words[currentIndex]); // Muestra la siguiente palabra/frase
            imageSun.setLayoutX(imageSun.getLayoutX() + (48*0.25)); // 1/4 de 48
            imageMeteorite.setLayoutX(imageMeteorite.getLayoutX() - (48*0.25)); // 1/4 de 48
            gameTime = 20;
            stopTimer(); // Detiene el temporizador actual
            startTimer(); // Inicia el temporizador con el tiempo actualizado
            if (errorsCounter >= 4) { // 4 Intentos
                stopTimer();
                // Carga la escena de estadísticas
                try {
                    Stage currentStage = (Stage) labelInfoText.getScene().getWindow();
                    SceneLoader.loadSceneFromOther("stats-view.fxml", currentStage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                textFieldPhrase.clear(); // Limpia el campo de texto
                labelInfoText.setText("¡Las palabras/frases no coinciden! Intentalo de nuevo..."); // Si la palabra/frase ingresada no coinciden
            }
        }
    }

    @FXML
    private void onActionButtonValidatePhrase(ActionEvent event) {
        if (!playing) {
            setGame(); // Reinicia el juego
            startGame(); // Inicia el juego
            playing = true; // Cambia el estado del juego
            textFieldPhrase.setDisable(false);
            buttonValidatePhrase.setText("Validar frase");
        } else {
            validatePhrase(); // Valida la frase ingresada
        }
    }

    @FXML
    void onKeyPressedTextField(KeyEvent event) {
        if ((event.getCode() == KeyCode.ENTER) && playing) {
            validatePhrase();
        }
    }

    @FXML
    void onActionButtonHelp(ActionEvent event) throws IOException {
        SceneLoader.loadSceneFromNode("help-view.fxml", event);
    }

}