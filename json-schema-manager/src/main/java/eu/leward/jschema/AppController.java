package eu.leward.jschema;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.util.List;

public class AppController {

  @FXML
  private TreeItem<String> schemasTreeRoot;

  @FXML
  public void initialize() throws IOException {
    // Load data
    InitialDataLoader dataLoader = new InitialDataLoader();
    List<Schema> schemas = dataLoader.load();

    // Replace TreeView content with loaded data
    schemasTreeRoot.getChildren().clear();

    schemas.stream()
      .map(schema -> new TreeItem<String>(schema.getName()))
      .forEach(stringTreeItem -> {
        schemasTreeRoot.getChildren().add(stringTreeItem);
      });
  }
}
