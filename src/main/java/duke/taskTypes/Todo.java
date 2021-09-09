package duke.taskTypes;

import duke.exception.DukeException;

import java.util.Arrays;
import java.util.List;

/**
 * Task class that sets description of task, date, time
 */
public class Todo extends Task{


    // Constructor
    /**
     * Takes in a string Set the eventType and description of the instance
     *
     * @param input
     */
    public Todo(String input, boolean isDone) throws DukeException {
        super(isDone);
        List<String> formattedInput = formatInput(input);
        super.setTaskDetails(getTaskType(), formattedInput);

    }


    // toDoo format method
    private String getTaskType() {
        return "T";
    }

    private List<String> formatInput(String input) {
        return Arrays.asList(input.trim(), getEmptyTime());
    }

    private String getEmptyTime() {
        return null;
    }


    // Start methods that returns formatted string for saving / displaying
    /**
     * Returns a string that describes the instance for display
     *
     * @return String containing details of the task
     */
    @Override
    public String toString() {
        return super.toString();
    }

}
