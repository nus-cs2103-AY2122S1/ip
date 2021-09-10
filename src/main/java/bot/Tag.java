package bot;

/**
 * A class that encapsulates a single tag for tasks.
 */
public class Tag {

    private String tagName;

    /**
     * Constructor for the Tag class.
     *
     * @param tagName The input given by the user.
     */
    public Tag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Returns a string representation of the Tag object.
     *
     * @return A String representation of the name of the Tag.
     */
    @Override
    public String toString() {
        return this.tagName;
    }
}
