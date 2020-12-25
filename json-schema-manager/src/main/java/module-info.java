module eu.leward.jschema {
    requires javafx.controls;
    requires javafx.fxml;

    requires easybind;
    requires org.fxmisc.richtext;
    requires flowless;
    requires com.fasterxml.jackson.core;
    requires org.leadpony.justify;

    opens eu.leward.jschema to javafx.graphics, javafx.fxml;
}