module eu.leward.jschema {
    requires javafx.controls;
    requires javafx.fxml;
    requires easybind;

    opens eu.leward.jschema to javafx.graphics, javafx.fxml;
}