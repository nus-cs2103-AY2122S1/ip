package taskMan.taskTypes;


import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import taskMan.exception.DukeException;
import taskMan.exception.EmptyTimeException;
import taskMan.exception.InvalidFormatException;

/**
 * Event Task class that sets description of task, date, time
 */
public class Event extends Task{

    /**
     * Takes in a string and splits msg into based on /at pattern. Set the eventType and time of the instance
     *
     * @param input string from the user
     */
    public Event(String input, boolean isDone) throws DukeException {
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

        super.setTaskDetails(getTaskType(), formattedInput);
    }

    /**
     * Formats the input into 2 parts : taskDetails, date together with time
     *
     * @param input user input
     * @return list
     */
    private List<String> formatInput (String input) {
        return Pattern.compile("/at")
                .splitAsStream(input)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * Returns string containing the task type of event
     *
     * @return String
     */
    private String getTaskType() {
        return "E";
    }

    /**
     * Returns a string to be displayed to user
     *
     * @return String containing details of the task
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + super.getFormatDate() + ")";
    }

    /**
     * Returns a string that describes the instance for saving in  a TXT file
     *
     * @return String containing details of the task
     */
    @Override
    public String saveTaskTxt() {
        return super.saveTaskTxt() + " /at " + super.getSaveDate();
    }

    /**
     * Returns a string that describes the instance for saving in  a CSV file
     *
     * @return String containing details of the task
     */
    @Override
    public String saveTaskCsv() {
        return super.saveTaskCsv() + ",/at," + super.getSaveDateCsv();
    }
}
