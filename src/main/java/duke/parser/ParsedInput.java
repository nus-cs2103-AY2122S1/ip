package duke.parser;

import java.io.Serializable;
import java.time.temporal.Temporal;

import duke.command.Command;
import duke.task.Task;

/**
 * Data class of parsed input with no behaviour.
 */
public class ParsedInput implements Serializable {

    public final Command.CommandTypes command;
    public final String description;
    public final Temporal dateTime;
    public final int index;
    public final Task.TaskTypes taskType;

    /* Search key for find method */
    public final String searchKey;

    /**
     * Constructor for ParsedInput instance.
     *
     * @param command Command
     * @param description Description
     * @param dateTime Date-time if applicable
     * @param index Index of item if applicable
     * @param taskType Type of Task to create or find if applicable
     * @param searchKey Search key to find if applicable
     */
    public ParsedInput(
            Command.CommandTypes command,
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
