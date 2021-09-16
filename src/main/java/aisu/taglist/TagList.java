package aisu.taglist;

import java.util.ArrayList;
import java.util.List;

import aisu.tag.Tag;

/**
 * The TagList class stores all the tags in a list.
 *
 * @author Liaw Xin Yan
 */
public class TagList {
    private final List<Tag> tags;

    /**
     * Constructor to initialise TagList.
     */
    public TagList() {
        this.tags = new ArrayList<>();
    }

    /**
     * Checks if the tag list contains the tag.
     * @param tagName the tag to be checked.
     * @return true if the tag list contains the tag.
     */
    public boolean contains(String tagName) {
        for (Tag currTag : this.tags) {
            if (currTag.toString().equals(tagName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the tag to the back of the tag list.
     * @param tagName the tag to be added.
     */
    public void add(String tagName) {
        this.tags.add(new Tag(tagName));
    }

    /**
     * Parses data in readable format to be stored in storage.
     *
     * @return Parsed data. xxx;;xxx;;xxx;;
     */
    public String parseData() {
        StringBuilder result = new StringBuilder();
        for (Tag tag : this.tags) {
            result.append(tag).append(";;");
        }
        return result.toString();
    }

    /**
     * Returns the string formatting of the tag list.
     * @return The string version of the list.
     */
    @Override
    public String toString() {
        if (!this.tags.isEmpty()) {
            StringBuilder result = new StringBuilder("\nTags: ");
            for (Tag tag : this.tags) {
                result.append(String.format("#%s ", tag));
            }
            return result.toString();
        }
        return "";
    }
}
