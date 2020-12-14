package eu.leward.jschema;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML
        URL fxmlResource = App.class.getResource("/app.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlResource);
        Pane pane = loader.load();

        // Assign the loaded view to the stage and show it
        Scene scene = new Scene(pane);
        stage.setScene(scene);

        // Configure CSS
        URL cssResource = App.class.getResource("/style.css");
        scene.getStylesheets().add(cssResource.toExternalForm());

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
