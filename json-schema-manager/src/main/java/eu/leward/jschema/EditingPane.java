package eu.leward.jschema;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import org.fxmisc.easybind.EasyBind;

import java.io.IOException;
import java.net.URL;

public class EditingPane extends TabPane {

    @FXML
    private TextArea schemaEditor;

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

        schemaEditor.textProperty().bind(
            EasyBind.monadic(schema).selectProperty(Schema::rawProperty)
        );
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
