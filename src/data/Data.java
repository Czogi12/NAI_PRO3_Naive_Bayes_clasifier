package data;

import java.util.Arrays;

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

    public String[] getAttributes() {
        return attributes;
    }

    public String[] getX() {
        return Arrays.stream(attributes, 0, attributes.length - 1).toArray(String[]::new);
    }
}
