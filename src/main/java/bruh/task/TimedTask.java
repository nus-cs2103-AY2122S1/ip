package bruh.task;

import bruh.exception.BruhException;
import bruh.exception.InvalidArgumentException;
import bruh.parser.Parser;

/**
 * Represents a task with an associated date and time.
 */
public abstract class TimedTask extends Task {
    private static final String DEADLINE_FLAG = "by";
    private static final String EVENT_FLAG = "at";
    private static final String DOAFTER_FLAG = "after";

    private static final String SPLIT_DELIMITTER = " /%s ";
    private static final String FORMAT = "%s (%s: %s)";

    private static final String MISSING_DATE_ERROR_MSG = "Please specify a valid date & time.";

    private final LocalDateTimeOrString dateTimeOrString;
    private final String flag;

    /**
     * Constructor for a task with an associated date and time.
     *
     * @param description   The description of the task.
     * @param symbol        The symbol representing the task type.
     * @param dateTimeInput The date and time associated with the task.
     */
    public TimedTask(String description, char symbol, String dateTimeInput, String flag) {
        super(description, symbol);
        this.dateTimeOrString = new LocalDateTimeOrString(dateTimeInput);
        this.flag = flag;
    }

    /**
     * Returns the appropriate timed task, as specified by the keyword in the input.
     *
     * @param  inputs        An array of the string input, divided into the first word and the rest
     *                       of the input.
     * @return               The timed task as specified by the input.
     * @throws BruhException If an error occurs in creating the task.
     */
    public static TimedTask createTimedTask(String[] inputs) throws BruhException {
        String keyword = inputs[0];

        switch (keyword) {
        case "deadline":
            inputs = inputs[1].split(String.format(SPLIT_DELIMITTER, DEADLINE_FLAG), 2);
            Parser.checkMissingArguments(inputs, MISSING_DATE_ERROR_MSG);
            return new DeadlineTimedTask(inputs[0], inputs[1], DEADLINE_FLAG);
        case "event":
            inputs = inputs[1].split(String.format(SPLIT_DELIMITTER, EVENT_FLAG), 2);
            Parser.checkMissingArguments(inputs, MISSING_DATE_ERROR_MSG);
            return new EventTimedTask(inputs[0], inputs[1], EVENT_FLAG);
        case "doafter":
            inputs = inputs[1].split(String.format(SPLIT_DELIMITTER, DOAFTER_FLAG), 2);
            Parser.checkMissingArguments(inputs, MISSING_DATE_ERROR_MSG);
            return new DoAfterTimedTask(inputs[0], inputs[1], DOAFTER_FLAG);
        default:
            throw new InvalidArgumentException();
        }
    }

    @Override
    public boolean isAtDateTime(LocalDateTimeOrString dateTimeOrString) {
        return this.dateTimeOrString.equals(dateTimeOrString);
    }

    @Override
    public String toString() {
        return String.format(FORMAT, super.toString(), flag, dateTimeOrString.toString());
    }
}
