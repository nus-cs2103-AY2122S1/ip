package duke.tag;

import java.util.HashMap;

/**
 * A Tags class.
 *
 * @author KelvinSoo
 */
public abstract class Tags {

    private static final HashMap<String, Tag> tags = new HashMap<>();

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