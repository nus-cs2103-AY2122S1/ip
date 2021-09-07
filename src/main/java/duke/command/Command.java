package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.ui.Ui;

/**
 * Command abstract class
 */
public abstract class Command {
    protected String commandName;
    protected String description;
    protected String[] arguments;

    public abstract String execute();

    protected static LocalDate getDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(date, formatter);
    }

    @Override
    public String toString() {
        assert commandName != null: "Command Name cannot be null";
        assert description != null: "Description cannot be null";
        assert arguments != null: "Arguments cannot be null";

        StringBuilder argString = new StringBuilder();
        for (String arg : arguments) {
            argString.append(Ui.OUTPUT_DISPLAY).append(arg).append('\n');
        }
        return commandName + " - " + description + '\n'
                + argString + '\n';
    }
}
