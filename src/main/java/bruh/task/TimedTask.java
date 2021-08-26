package bruh.task;

import bruh.exception.DukeException;
import bruh.exception.InvalidArgumentException;
import bruh.parser.Parser;

public abstract class TimedTask extends Task {
    private final LocalDateTimeOrString dateTimeOrString;

    public TimedTask(String description, char symbol, String dateTimeInput) {
        super(description, symbol);
        this.dateTimeOrString = new LocalDateTimeOrString(dateTimeInput);
    }

    public static TimedTask createTimedTask(String[] inputs) throws DukeException {
        String keyword = inputs[0];

        switch (keyword) {
        case "deadline":
            inputs = inputs[1].split(" /by ", 2);
            Parser.checkMissingArguments(inputs, "Please specify the date & time of your deadline.");
            return new Deadline(inputs[0], inputs[1]);
        case "event":
            inputs = inputs[1].split(" /at ", 2);
            Parser.checkMissingArguments(inputs, "Please specify the date & time of your event.");
            return new Event(inputs[0], inputs[1]);
        default:
            throw new InvalidArgumentException();
        }
    }

    @Override
    public boolean isAtTime(LocalDateTimeOrString dateTimeOrString) {
        return this.dateTimeOrString.equals(dateTimeOrString);
    }

    public String getDateTimeDesc() {
        return dateTimeOrString.getDesc();
    }
}
