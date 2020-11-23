module eu.leward.jschema {
    requires javafx.controls;
    requires javafx.fxml;

    opens eu.leward.jschema to javafx.graphics, javafx.fxml;
}