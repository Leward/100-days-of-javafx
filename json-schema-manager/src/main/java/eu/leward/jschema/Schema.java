package eu.leward.jschema;

//import com.fasterxml.jackson.databind.JsonNode;
//import com.github.fge.jackson.JsonLoader;
//import com.github.fge.jsonschema.core.exceptions.ProcessingException;
//import com.github.fge.jsonschema.main.JsonSchema;
//import com.github.fge.jsonschema.main.JsonSchemaFactory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.leadpony.justify.api.JsonSchema;
import org.leadpony.justify.api.JsonValidationService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Schema {

    private final StringProperty name;
    private final StringProperty raw;
    private ObjectProperty<JsonSchema> jsonSchema;

    public Schema(String name, String raw) {
        JsonValidationService service = JsonValidationService.newInstance();
        this.name = new SimpleStringProperty(name);
        this.raw = new SimpleStringProperty(raw);
        jsonSchema = new SimpleObjectProperty<>(readSchema(service, raw));

        this.raw.addListener((observable, oldValue, newValue) -> {
            jsonSchema.setValue(readSchema(service, newValue));
//            try {
//                JsonNode jsonNode = JsonLoader.fromString(newValue);
//                JsonSchemaFactory schemaFactory = JsonSchemaFactory.byDefault();
//                jsonSchema.setValue(schemaFactory.getJsonSchema(jsonNode));
//            } catch (IOException | ProcessingException e) {
//                // Schema will not get updated
//            }
        });
    }

    private JsonSchema readSchema(JsonValidationService jsonService, String schemaAsString) {
        InputStream inputStream = new ByteArrayInputStream(schemaAsString.getBytes());
        return jsonService.readSchema(inputStream);
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getRaw() {
        return raw.getValue();
    }

    public void setRaw(String raw) {
        this.raw.setValue(raw);
    }

    public StringProperty rawProperty() {
        return raw;
    }

    @Override
    public String toString() {
        return name.getValue();
    }

    public JsonSchema getJsonSchema() {
        return jsonSchema.get();
    }

    public ObjectProperty<JsonSchema> jsonSchemaProperty() {
        return jsonSchema;
    }
}
