package duke.taskTypes;


import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.exception.EmptyTimeException;
import duke.exception.InvalidFormatException;

/**
 * Deadline Task class that sets description of task, date, time
 */
public class Deadline extends Task{

    /**
     * Deadline Constructor, main method that formats input and sets details of deadline
     *
     * @param input string from the user
     */
    public Deadline(String input, boolean isDone) throws DukeException {
        super(isDone);
        List<String> formattedInput = formatInput(input);
        boolean isMissingDescriptionTimestamp = formattedInput.size() == 0;
        boolean isMissingTimestamp = formattedInput.size() == 1;

        if (isMissingDescriptionTimestamp) {
            throw new InvalidFormatException("Missing description and timestamp");
        }

        if (isMissingTimestamp) {
            throw new EmptyTimeException("Invalid timestamp format");
        }

        setTaskDetails(getTaskType(), formattedInput);
    }

    // Deadline format Methods;
    /**
     * Formats the input into 2 parts : taskDetails, date together with time
     *
     * @param input user input
     * @return list
     */
    private List<String> formatInput(String input) {
        return Pattern.compile("/by")
                .splitAsStream(input)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * Returns string containing the task type of deadline
     *
     * @return String
     */
    private String getTaskType() {
        return "D";
    }

    // methods that returns formatted string for saving / displaying
    /**
     * Returns a string to be displayed to user
     *
     * @return String containing details of the task
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + super.getFormatDate() + ")";
    }

    /**
     * Returns a string that describes the instance for saving in  a TXT file
     *
     * @return String containing details of the task
     */
    @Override
    public String saveTaskTxt() {
        return super.saveTaskTxt() + " /by " + super.getSaveDate();
    }

    /**
     * Returns a string that describes the instance for saving in a CSV file
     *
     * @return
     */
    @Override
    public String saveTaskCsv() {
        return super.saveTaskCsv() + ",/by," + super.getSaveDateCsv();
    }
}
