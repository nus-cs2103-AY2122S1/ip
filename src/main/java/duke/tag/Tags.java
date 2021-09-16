package duke.tag;

import java.util.HashMap;

/**
 * A Tags class to manage all tags.
 * @author KelvinSoo
 */
public abstract class Tags {

    private static final HashMap<String, Tag> tags = new HashMap<>();

    /**
     * Tag method to tag a task.
     * @param description the description of the tag
     * @return  a tag that fits the description
     */
    public static Tag tag(String description) {
        if (tags.containsKey(description)) {
            return tags.get(description);
        } else {
            Tag newTag = new Tag(description);
            tags.put(description, newTag);
            return newTag;
        }
    }
}