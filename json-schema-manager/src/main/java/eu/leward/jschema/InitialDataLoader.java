package eu.leward.jschema;

import java.io.IOException;
import java.util.List;

public class InitialDataLoader {
  public List<Schema> load() throws IOException {
    Schema person = new Schema("person", loadExampleFile("person.schema.json"));
    Schema geo = new Schema("geo", loadExampleFile("geo.schema.json"));
    return List.of(person, geo);
  }

  private String loadExampleFile(String fileName) throws IOException {
    return new String(
      getClass().getResource("/examples/" + fileName)
        .openStream()
        .readAllBytes()
    );
  }
}
