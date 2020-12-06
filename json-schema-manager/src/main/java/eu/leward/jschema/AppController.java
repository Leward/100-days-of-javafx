package eu.leward.jschema;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextArea;
import org.fxmisc.easybind.EasyBind;

import java.io.IOException;

public class AppController {

    @FXML
    private ListView<Schema> schemaListView;
    @FXML
    private TextArea schemaEditor;

    private final ObjectProperty<Schema> selectedSchemaProperty = new SimpleObjectProperty<>();

    @FXML
    public void initialize() throws IOException {
        ObservableList<Schema> schemas = FXCollections.observableArrayList();
        schemaListView.setEditable(true);
        schemaListView.setCellFactory(param -> new SchemaListCell());
        schemaListView.setItems(schemas);

        // When the selected schema changes
        selectedSchemaProperty.bind(schemaListView.getSelectionModel().selectedItemProperty());
        schemaEditor.textProperty().bind(
            EasyBind.monadic(selectedSchemaProperty)
                .selectProperty(Schema::rawProperty)
        );

        // Load data
        InitialDataLoader dataLoader = new InitialDataLoader();
        schemas.addAll(dataLoader.load());
    }
}
