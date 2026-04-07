package data;

public class Data {
    private final String[] attributes;

    public Data(String[] attributes) {
        this.attributes = attributes;
    }

    public String getY() {
        return attributes[attributes.length - 1];
    }

    public int getDimension() {
        return attributes.length;
    }

    public String getAttribute(int index) {
        return attributes[index];
    }
}
