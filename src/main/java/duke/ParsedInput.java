package duke;

import java.io.Serializable;
import java.time.temporal.Temporal;

/**
 * Data class of parsed input with no behaviour.
 */
public class ParsedInput implements Serializable {

    final Duke.Commands command;
    final String description;
    @SuppressWarnings("checkstyle:VisibilityModifier")
    Temporal dateTime;
    @SuppressWarnings("checkstyle:VisibilityModifier")
    int index;
    @SuppressWarnings("checkstyle:VisibilityModifier")
    Duke.TaskTypes taskType;

    /* Search key for find method */
    @SuppressWarnings("checkstyle:VisibilityModifier")
    String searchKey;

    /**
     * Constructor for ParsedInput instance.
     *
     * @param command Command
     * @param description Description
     * @param dateTime Date-time if applicable
     * @param index Index of item if applicable
     * @param taskType Type of Task to create or find if applicable
     * @param searchKey Searchkey to find if applicable
     */
    public ParsedInput(
            Duke.Commands command,
            String description,
            Temporal dateTime,
            int index,
            Duke.TaskTypes taskType,
            String searchKey) {
        this.command = command;
        this.description = description;
        this.dateTime = dateTime;
        this.index = index;
        this.taskType = taskType;
        this.searchKey = searchKey;
    }
}
