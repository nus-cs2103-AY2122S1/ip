package duke.tag;

/**
 * A Tag class that handles the tagging of a task.
 * @author KelvinSoo
 * @version A-Tag
 */
public class Tag {
    private final String description;

    /**
     * Tag constructor.
     */
    public Tag(String description) {
        this.description = description;
    }

    /**
     * Get the tag description in a string format.
     * @return tag description.
     */
    public String toString() {
        return description;
    }
}
