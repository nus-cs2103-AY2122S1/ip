package duke.task;

import java.util.Arrays;
import java.util.List;

import duke.parser.storage.StorageParser;
import duke.storage.Storage;

/**
 * This class encapsulates a todo task.
 */
public class Todo extends Task {
    private static final String TASK_TYPE = "T";

    /**
     * Constructor of the duke.task.Todo class
     *
     * @param description description of this todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the savable string format of this task.
     *
     * @return Formatted string to be saved into storage.
     */
    @Override
    public String toSavableFormat() {
        String isDone = StorageParser.parseIsDoneToString(this.isDone());
        List<String> stringList = Arrays.asList(TASK_TYPE, isDone, this.getDescription());
        return String.join(Storage.DELIMITER, stringList);
    }

    @Override
    protected String getTaskType() {
        return TASK_TYPE;
    }
}
