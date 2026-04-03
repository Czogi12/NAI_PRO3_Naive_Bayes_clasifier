package data;

public class Data {
    private final String[] attributes;

    public Data(String[] attributes) {
        this.attributes = attributes;
    }

    public int getDimension() {
        return attributes.length;
    }
}
