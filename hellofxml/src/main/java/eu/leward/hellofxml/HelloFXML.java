package eu.leward.hellofxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloFXML extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML
        URL fxmlResource = HelloFXML.class.getResource("/hello.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlResource);
        StackPane pane = loader.load();

        // Assign the loaded view to the stage and show it
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
