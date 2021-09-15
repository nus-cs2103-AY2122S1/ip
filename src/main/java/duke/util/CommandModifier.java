package duke.util;

import java.time.LocalDate;

public class CommandModifier {

    /**
     * Returns the tag array, if any.
     *
     * @param description The task description.
     * @return The tag array.
     */
    public static String[] getTags(String description) {
        String[] substringsWithTag = description.split("t/");
        int tagNumber = substringsWithTag.length - 1;
        String[] tags = new String[tagNumber];

        for (int i = 1; i < tagNumber + 1; i++) {
            String stringsWithTag = substringsWithTag[i];
            boolean isNeitherEmptyNorBlank = !stringsWithTag.isEmpty() && !stringsWithTag.isBlank();
            int firstSpaceIndex = stringsWithTag.indexOf(" ");
            boolean spaceNotFound = firstSpaceIndex == -1;
            String firstWord = spaceNotFound ? stringsWithTag : stringsWithTag.substring(0, firstSpaceIndex);
            boolean isFirstWordNeitherEmptyNorBlank = !firstWord.isEmpty() && !firstWord.isBlank();
            if (isNeitherEmptyNorBlank && isFirstWordNeitherEmptyNorBlank) {
                tags[i - 1] = firstWord;
            }
        }

        return tags;
    }

    /**
     * Returns description without any tag(s) inside.
     *
     * @param description The task description.
     */
    public static String removeTagsFrom(String[] tags, String description) {
        for (String tag : tags) {
            if (tag != null) {
                description = description.replaceAll("t/" + tag, "");
            }
        }
        description = description.replaceAll(" t/ ", "");
        description = description.replaceAll("t/ ", "");
        description = description.replaceAll(" t/", "");
        description = description.replaceAll("t/", "");

        return description;
    }

    /**
     * Returns the list of tags in String representation, for storage in data.
     */
    public static String toTagArrayDataString(String[] tags) {
        StringBuilder tagList = new StringBuilder();
        for (String tag : tags) {
            if (tag != null) {
                tagList.append(String.format("%s ", tag));
            }
        }

        return tagList.toString();
    }

    /**
     * Returns the list of tags in String representation.
     */
    public static String toTagArrayString(String[] tags) {
        StringBuilder tagList = new StringBuilder();
        for (String tag : tags) {
            if (tag != null) {
                tagList.append(String.format("#%s ", tag));
            }
        }

        return tagList.toString();
    }

    /**
     * Returns the LocalDate representation of the date specified in the description.
     *
     * @param description The task description.
     * @return The LocalDate representation of the date specified in the description.
     */
    public static LocalDate toLocalDate(String description, String delimiter) {
        String dateString = description.split(delimiter)[1];
        return LocalDate.parse(dateString);
    }

    /**
     * Returns the integer representation of the command description.
     *
     * @param description The command description.
     * @return The integer representation of the command description.
     */
    public static Integer toInt(String description) {
        return Integer.parseInt(description);
    }
}
