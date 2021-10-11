package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parser class to make sense of user's input
 */
public class Parser {
    /**
     * Converts the letters to lowercase to accept commands with uppercase letters
     *
     * @param command takes in a String representing user's command
     * @return a String with only lower case letters representing user's command
     */
    public static String parseCommand(String command) {
        return command.toLowerCase();
    }

    /**
     * A helper function to abstract out checking of exceptions and creating a todo Task during
     * a todo command
     *
     * @param todoDescription takes in a String describing the todo Task
     * @param ui takes in a Ui for error message
     * @return a todo Task that was created with todoDescription
     * @throws DukeException throws an emptyDescriptionError
     */
    public static Task todoHelper(String todoDescription, Ui ui) throws DukeException {
        if (todoDescription.isEmpty()) {
            throw new DukeException(ui.emptyDescriptionError());
        }
        Task todo = new Todo(todoDescription);
        return todo;
    }

    /**
     * A helper function to abstract out checking of exceptions and creating a Deadline Task during
     * a deadline command
     *
     * @param deadlineArr takes in a String array describing the deadline Task
     * @param ui takes in a Ui for error message
     * @return a Deadline Task that was created with deadlineArr
     * @throws DukeException throws an emptyDescriptionError
     */
    public static Task deadlineHelper(String[] deadlineArr, Ui ui) throws DukeException {
        if (deadlineArr[0].strip().isEmpty()) {
            throw new DukeException(ui.emptyDescriptionError());
        }
        LocalDate d1 = LocalDate.parse(deadlineArr[1].trim());
        Task deadline = new Deadline(deadlineArr[0].trim(),
                d1.format(DateTimeFormatter.ofPattern("MMM dd YYYY")));
        return deadline;
    }

    /**
     * A helper function to abstract out checking of exceptions and creating a event Task during
     * an event command
     *
     * @param eventArr takes in a String array describing the event Task
     * @param ui takes in a Ui for error message
     * @return a Event Task that was created with eventArr
     * @throws DukeException throws an emptyDescriptionError
     */
    public static Task eventHelper(String[] eventArr, Ui ui) throws DukeException {
        if (eventArr[0].strip().isEmpty()) {
            throw new DukeException(ui.emptyDescriptionError());
        }
        Task event = new Event(eventArr[0].trim(), eventArr[1].trim());
        return event;
    }

    /**
     * A helper function to abstract out checking of exceptions and creating a doAfter Task during
     * a doafter command
     *
     * @param afterArr takes in a String array describing the doAfter Task
     * @param ui takes in a Ui for error message
     * @return a doAfter Task that was created with afterArr
     * @throws DukeException throws an emptyDescriptionError
     */
    public static Task doAfterHelper(String[] afterArr, Ui ui) throws DukeException {
        if (afterArr[0].strip().isEmpty()) {
            throw new DukeException(ui.emptyDescriptionError());
        }
        Task doAfter = new DoAfter(afterArr[0].trim(), afterArr[1].trim());
        return doAfter;
    }

}
