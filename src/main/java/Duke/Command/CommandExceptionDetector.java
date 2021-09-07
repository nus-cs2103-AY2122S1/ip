package duke.command;

import duke.exceptions.DukeException;

public class CommandExceptionDetector {
    private final String message;

    public CommandExceptionDetector(String message) {
        this.message = message;
    }

    public void detectGetTaskException() throws DukeException {
        boolean isCorrectType = message.startsWith("deadline") || message.startsWith("event")
                || message.startsWith("todo") || message.startsWith("find");

        boolean isInCorrectFormat = message.contains(" ");

        if (!isCorrectType) {
            return;
        }

        if (!isInCorrectFormat) {
            throw new DukeException("OOPS!!! The description of a " + message + " cannot be empty.");
        }
    }

    public void detectGetTimeException() throws DukeException {
        boolean isContainTime = message.startsWith("todo") || message.startsWith("deadline")
                || message.startsWith("event") || message.startsWith("tell");

        boolean isDeadlineFormat = message.contains("/") && message.contains("/by");

        boolean isEventFormat = message.contains("/") && message.contains("/at");

        boolean isTellFormat = message.contains(" ");

        //throw exceptions for deadline or events' format.
        if (!isContainTime) {
            return;
        }

        if (message.startsWith("deadline") && !isDeadlineFormat) {
            throw new DukeException("OOPS!!! I'm sorry, but the format of deadline is wrong :-(");
        }

        if (message.startsWith("event") && !isEventFormat) {
            throw new DukeException("OOPS!!! I'm sorry, but the format of event is wrong :-(");
        }

        if (message.startsWith("tell") && !isTellFormat)
            throw new DukeException("OOPS!!! I'm sorry, but the format of tell is wrong :-(");
        }
}