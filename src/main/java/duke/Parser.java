package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * Represents a class that deals with making sense of the user command.
 *
 * @author botr99
 */
public class Parser {
    /**
     * Parses the user input of a date task creation and returns the date task
     * corresponding to the user input.
     *
     * @param descriptionAndDate The user input.
     * @param command The type of date task to be created.
     * @return Null if the command is not an appropriate date task type;
     *         the new date task created otherwise;
     * @throws DukeException When the description and date string cannot be split in 2;
     *                       when the date task cannot be constructed due to invalid date string.
     */
    public static Task parseDateTask(String descriptionAndDate, String command) throws DukeException {
        String[] splitDescriptionAndDate;
        Task task = null;

        try {
            if (command.equals("deadline")) {
                splitDescriptionAndDate = descriptionAndDate.split(" /by ");
                task = new Deadline(splitDescriptionAndDate[0].trim(), splitDescriptionAndDate[1].trim());
            } else if (command.equals("event")) {
                splitDescriptionAndDate = descriptionAndDate.split(" /at ");
                task = new Event(splitDescriptionAndDate[0].trim(), splitDescriptionAndDate[1].trim());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Oops!!! Deadlines or events should contain a description, followed by " +
                    "a /by or /at respectively, followed by a date.");
        }

        return task;
    }

}
