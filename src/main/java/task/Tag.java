package task;

public class Tag {

    private String tagName;

    public Tag(String tagName) {
        this.tagName = tagName.trim();
    }

    public boolean compareTagName(String key) {
        return tagName.equals(key.trim());
    }

    @Override
    public String toString() {
        return this.tagName;
    }
}
