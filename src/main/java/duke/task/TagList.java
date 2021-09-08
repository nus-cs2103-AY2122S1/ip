package duke.task;

import java.util.ArrayList;

public class TagList {
    private ArrayList<String> tags = new ArrayList<>();
    /**
     * Returns the String representation of task in file format.
     * File format is the format used to save tasks in a file.
     *
     * @param tagStrings String representation of task in file format.
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
            StringBuilder result = new StringBuilder("{");
            for (String tag : tags) {
                result.append(tag).append(", ");
            }
            return result.substring(0, result.length() - 2) + "}";
        }
    }
    /**
     * Returns the String representation of task in file format.
     * File format is the format used to save tasks in a file.
     *
     * @return String representation of task in file format.
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
