package meow;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser that parses the command
 * input by the user.
 */
public class Parser {

    /**
     * A public constructor to initialize a meow.Parser object.
     */
    public Parser() {

    }

    /**
     * Parses the input by the user, converts and returns
     * the date in a LocalDate representation.
     *
     * @param inputDate The date input by the user.
     * @return The command word using enum.
     * @throws InvalidDateTimeException If the date is invalid.
     */
    public LocalDate convertToLocalDate(String inputDate) throws InvalidDateTimeException {
        try {
            String modifiedDate = "";
            String[] values = inputDate.split("/");
            for (int i = values.length - 1; i >= 0; i--) {
                String addedValue;
                if (Integer.parseInt(values[i]) < 10) {
                    addedValue = "0" + values[i];
                } else {
                    addedValue = values[i];
                }

                if (i == 0) {
                    modifiedDate = modifiedDate + addedValue;
                } else {
                    modifiedDate = modifiedDate + addedValue + "-";
                }
            }
            LocalDate date = LocalDate.parse(modifiedDate);
            return date;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Parses the input by the user, interprets and returns a boolean indicating
     * whether the user input is valid date and valid time.
     *
     * @param inputDate The date input by the user.
     * @param inputTime The time input by the user.
     * @return A boolean indicating whether the user input is valid date and valid time.
     */
    public boolean isLocalDateTime(String inputDate, String inputTime) {
        System.out.println("input date: " + inputDate);
        String validator = "[0-9]{1,2}(/|-)[0-9]{1,2}(/|-)[0-9]{4}";
        boolean dateValidated = inputDate.matches(validator);
        int time = Integer.parseInt(inputTime);
        boolean timeValidated = time >= 0 && time <= 2359;
        return dateValidated && timeValidated;
    }

    /**
     * Parses the command input by the user, interprets and returns
     * the description of the task in a String representation.
     *
     * @param input      The input command from the user.
     * @param typeOfTask The type of task in the command.
     * @return The description of the task in a String representation.
     * @throws MeowException If the user input is incomplete.
     */
    public String getTask(String input, Meow.Command typeOfTask) throws MeowException {
        try {
            return input.trim().substring(typeOfTask.toString().length() + 1);
        } catch (StringIndexOutOfBoundsException exception) {
            if (typeOfTask.equals(Meow.Command.TODO)) {
                throw new EmptyTodoDescriptionException();
            } else if (typeOfTask.equals(Meow.Command.DEADLINE)) {
                throw new EmptyDeadlineDescriptionException();
            } else {
                throw new EmptyEventDescriptionException();
            }
        }
    }

    /**
     * Parses the command input by the user, interprets and returns
     * an array containing the description of the task as well as the
     * date/time of the task to be completed (only for meow.Deadline and meow.Event).
     *
     * @param task The description of the task in the command.
     * @param typeOfTask The type of task in the command.
     * @return An array containing the description of the task as well as the
     * date/time of the task to be completed (only for meow.Deadline and meow.Event).
     */
    public String[] getTaskAndDate(String task, Meow.Command typeOfTask) {
        String[] split;
        if (typeOfTask.equals(Meow.Command.DEADLINE)) {
            split = task.split(" /by ");
        } else {
            split = task.split(" /at ");
        }
        return split;
    }

    /**
     * Parses the command input by the user, interprets and returns
     * the index of the task in a String representation.
     *
     * @param input The input command from the user.
     * @return The command word using enum.
     * @throws MeowException If the user input is invalid.
     */
    public Meow.Command checkCommand(String input) throws MeowException {
        try {
            String[] commandWord = input.split(" ");
            Meow.Command userCommand = Meow.Command.valueOf(commandWord[0].trim().toUpperCase());
            assert(userCommand != null);
            return userCommand;
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException();
        }
    }

    /**
     * Parses the command input by the user, interprets and returns
     * the index of the task in a String representation.
     *
     * @param input The command input by the user.
     * @return The index of the task in a String representation.
     * @throws MeowException If the user input is invalid.
     */
    public String getTaskNumber(String input) throws MeowException {
        try {
            String[] commandWord = input.split(" ");
            assert(commandWord[1].trim() != null);
            return commandWord[1].trim();
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException();
        }

    }
}
