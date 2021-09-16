package taskman.tasktypes;


import java.util.Arrays;
import java.util.List;

import taskman.exception.DukeException;
import taskman.exception.InvalidFormatException;

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

    /**
     * Formats the input into 2 parts : taskDetails, empty date time
     *
     * @param input user input
     * @return list
     */
    private List<String> formatInput(String input) throws DukeException {
        checkIfHaveTimeIncluded(input.trim());
        return Arrays.asList(input.trim(), getEmptyTime());
    }

    /**
     * Returns empty date time placeholder
     *
     * @return
     */
    private String getEmptyTime() {
        return null;
    }

    /**
     * Check if time format mistakenly placed
     *
     * @param input
     * @throws DukeException
     */
    private void checkIfHaveTimeIncluded(String input) throws DukeException {
        String[] userInputArray = input.split(" ");
        for (String word : userInputArray) {
            if (word.equals("/by")) {
                throw new InvalidFormatException("todo does not have /by");
            }
            if (word.equals("/at")) {
                throw new InvalidFormatException("todo does not have /at");
            }
        }
    }


    // Start methods that returns formatted string for saving / displaying
    /**
     * Returns a string to be displayed to user
     *
     * @return String containing details of the task
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Returns a string that describes the instance for saving in  a CSV file
     *
     * @return String containing details of the task
     */
    @Override
    public String saveTaskCsv() {
        String state = isDone ? "T" : "F";
        return taskType + "," + state + "," + description + ",,,";
    }
}
