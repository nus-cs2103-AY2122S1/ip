package pika.task;

/**
 * Tag class.
 */
public class Tag {
    private final String tagName;

    /**
     * Constructor for tags.
     * @param tagName Name of the tag.
     */
    public Tag(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "#" + this.tagName;
    }
}
