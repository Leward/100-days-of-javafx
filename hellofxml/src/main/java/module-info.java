module eu.leward.hellofxml {
    requires javafx.controls;
    requires javafx.fxml;

    opens eu.leward.hellofxml to javafx.graphics, javafx.fxml;
}