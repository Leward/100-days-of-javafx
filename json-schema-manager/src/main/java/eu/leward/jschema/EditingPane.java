package eu.leward.jschema;

import eu.leward.jschema.highlighting.Json;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import org.fxmisc.richtext.CodeArea;

import java.io.IOException;
import java.net.URL;

public class EditingPane extends TabPane {

    @FXML
    private CodeArea schemaEditor;

    private final Json json = new Json();

    final private ObjectProperty<Schema> schema = new SimpleObjectProperty<>();

    public EditingPane() {
        URL fxmlResource = EditingPane.class.getResource("/editingpane.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlResource);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            // Component is broken, not much that can be recovered from.
            throw new RuntimeException(e);
        }

        schemaEditor.textProperty().addListener((obs, oldText, newText) -> {
            schemaEditor.setStyleSpans(0, json.highlight(newText));
        });

        schema.addListener((observable, oldValue, newValue) -> {
            schemaEditor.replaceText(newValue.getRaw());
        });
    }

    public Schema getSchema() {
        return schema.get();
    }

    public ObjectProperty<Schema> schemaProperty() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema.set(schema);
    }
}
