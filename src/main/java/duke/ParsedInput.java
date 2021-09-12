package duke;

import java.io.Serializable;
import java.time.temporal.Temporal;

/**
 * Data class of parsed input with no behaviour.
 */
public class ParsedInput implements Serializable {

    final Commands.CommandTypes command;
    final String description;
    final Temporal dateTime;
    final int index;
    final Task.TaskTypes taskType;

    /* Search key for find method */
    final String searchKey;

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
            Commands.CommandTypes command,
            String description,
            Temporal dateTime,
            int index,
            Task.TaskTypes taskType,
            String searchKey) {
        this.command = command;
        this.description = description;
        this.dateTime = dateTime;
        this.index = index;
        this.taskType = taskType;
        this.searchKey = searchKey;
    }
}
