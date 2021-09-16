package aisu.tag;

/**
 * The tag class.
 * A tag can be added to a task for organizational purposes.
 *
 * @author Liaw Xin Yan
 */
public class Tag {
    private final String name;

    /**
     * A constructor to initialise the Tag class.
     * @param name The name of the tag.
     */
    public Tag(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the tag.
     * @return name of the tag.
     */
    @Override
    public String toString() {
        return this.name;
    }
}
