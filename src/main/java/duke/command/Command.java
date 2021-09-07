package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.ui.Ui;

/**
 * Command abstract class.
 */
public abstract class Command {
    protected String commandName;
    protected String description;
    protected String[] arguments;

    public abstract String execute();

    /**
     * Takes in a String date and returns its corresponding LocalDate object.
     *
     * @param date String date in format DD/MM/YYYY, with 1-2 digits from Day and Month
     * @return LocalDate object with the corresponding day, month and year
     * @throws DateTimeParseException Thrown if date passed is an invalid one
     */
    protected static LocalDate getDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(date, formatter);
    }

    /**
     * String representation of a Command.
     *
     * @return Command name, description, as well as its arguments on newlines
     */
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
