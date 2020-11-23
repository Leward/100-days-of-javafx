package eu.leward.jschema;

public class Schema {

    private String name;
    private String raw;

    public Schema(String name, String raw) {
        this.name = name;
        this.raw = raw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }
}
