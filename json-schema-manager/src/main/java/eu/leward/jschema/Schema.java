package eu.leward.jschema;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Schema {

    private final StringProperty name;
    private final StringProperty raw;

    public Schema(String name, String raw) {
        this.name = new SimpleStringProperty(name);
        this.raw = new SimpleStringProperty(raw);
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

}
