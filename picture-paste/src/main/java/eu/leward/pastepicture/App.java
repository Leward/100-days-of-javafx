package eu.leward.pastepicture;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    private StackPane pane;

    @Override
    public void start(Stage stage) {
        Label l = new Label("Paste your image here!");
        l.setTextFill(Color.valueOf("#2c5282"));
        l.setFont(Font.font(20));
        l.setStyle("-fx-text-fill: #2c5282;" +
                "-fx-font-size: 20;");

        pane = new StackPane(l);
        pane.setStyle("-fx-border-color: #2c5282; " +
                "-fx-border-width: 5;" +
                "-fx-border-style: dashed;" +
                "-fx-border-insets: 5;" +
                "-fx-padding: 5");

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

        KeyCodeCombination pastKeyCombination = new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (pastKeyCombination.match(keyEvent)) {
                pasteImageFromClipBoard();
            }
        });
    }

    private void pasteImageFromClipBoard() {
        Image image = Clipboard.getSystemClipboard().getImage();
        if (image != null) {
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            pane.getChildren().add(imageView);
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
