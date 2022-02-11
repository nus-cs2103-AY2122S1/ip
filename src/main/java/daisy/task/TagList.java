package daisy.task;

import java.util.ArrayList;

/**
 * TagList class handles the tag list of a task.
 */
public class TagList {
    private ArrayList<String> tags = new ArrayList<>();
    /**
     * Adds tags to the task.
     *
     * @param tagStrings Tag strings.
     */
    public void addTags(String ... tagStrings) {
        if (tagStrings != null) {
            for (String tag : tagStrings) {
                tags.add(tag.trim());
            }
        }
    }
    @Override
    public String toString() {
        if (tags.isEmpty()) {
            return "";
        } else {
            StringBuilder result = new StringBuilder(" {");
            for (String tag : tags) {
                result.append(tag).append(", ");
            }
            return result.substring(0, result.length() - 2) + "}";
        }
    }
    /**
     * Returns the String representation of tags in file format.
     * File format is the format used to save tasks in a file.
     *
     * @return String representation of tags in file format.
     */
    public String convertToFileFormat() {
        if (tags.isEmpty()) {
            return "";
        } else {
            StringBuilder result = new StringBuilder();
            for (String tag : tags) {
                result.append(tag).append("/");
            }
            return result.substring(0, result.length() - 1);
        }
    }
}
