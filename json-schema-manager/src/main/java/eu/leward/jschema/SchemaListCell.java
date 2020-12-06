package eu.leward.jschema;

import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;

public class SchemaListCell extends TextFieldListCell<Schema> {

    public SchemaListCell() {
        super();
        refreshConverter();
    }

    private void refreshConverter() {
        StringConverter<Schema> converter = new StringConverter<>() {
            @Override
            public String toString(Schema schema) {
                return schema.getName();
            }

            @Override
            public Schema fromString(String string) {
                if (isEmpty()) {
                    return new Schema(string, "{}");
                }
                Schema schema = getItem();
                schema.setName(string);
                return schema;
            }
        };
        setConverter(converter);
    }

    @Override
    public void updateItem(Schema item, boolean empty) {
        super.updateItem(item, empty);
        refreshConverter();
    }
}
