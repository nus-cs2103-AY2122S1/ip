package duke;

/**
 * A tag is simply an item that contains a string (the tag description / name).
 * A tag is used on tasks.
 *
 */
public class Tag {
    private final String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Checks to see if the tag name matches the query
     * @param query The query string to compare with
     * @return whether the tag matches the query
     */
    public boolean matchesTag(String query) {
        return this.tagName.equals(query);
    }

    @Override
    public String toString() {
        return this.tagName;
    }
}
