/**
 * This class handles interaction with the user.
 * Interprets user command and creates logs
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.parser;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.exceptions.DateNotAcceptedException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidIntegerInput;

public class Parser {

    private final String command;
    private final String[] commandWords;

    /**
     * Constructs a Parser object.
     *
     * @param command The command issued by the user.
     */
    public Parser(String command) {
        assert command != null : "Command to be parsed cannot be null";
        this.command = command;
        this.commandWords = command.toLowerCase().split(" ");
    }

    /**
     * Returns the first word (the command) given.
     *
     * @return The first word from the user, which is the command
     */
    public String getFirstWord() {
        return commandWords[0];
    }

    /**
     * Returns the second word given.
     *
     * @return The second word from the user.
     */
    public String getSecondWord() throws EmptyDescriptionException {
        if (commandWords.length == 1) {
            throw new EmptyDescriptionException("todo");
        }
        return commandWords[1];
    }

    /**
     * Checks if the second integer is viable for the done and delete commands and returns it.
     *
     * @param size The length of the taskList
     * @return The second word (aka the number) from the user
     * @throws InvalidIntegerInput If there are issues with extracting the second integer from user input.
     */
    public int getSecondInteger(int size) throws InvalidIntegerInput {
        int secondInteger;

        try {
            secondInteger = Integer.parseInt(commandWords[1]);
        } catch (NumberFormatException e) {
            throw new InvalidIntegerInput("1", String.valueOf(size));
        }

        if (commandWords.length == 1
                || secondInteger < 1
                || secondInteger > size) {
            throw new InvalidIntegerInput("1", String.valueOf(size));
        }

        return secondInteger;
    }

    /**
     * Returns the information of a Todo task.
     *
     * @return The description of a Todo task
     * @throws EmptyDescriptionException If the user input is missing extra information after the command.
     */
    public String getTodoInfo() throws EmptyDescriptionException {

        if (commandWords.length == 1) {
            throw new EmptyDescriptionException("todo");
        }

        return this.command.split(" ", 2)[1];
    }

    /**
     * Returns information about a Deadline task.
     *
     * @return The description of a Deadline task
     * @throws EmptyDescriptionException If the user input is missing extra information after the command.
     */
    public String getDeadlineInfo() throws EmptyDescriptionException {

        if (commandWords.length == 1 || this.command.split(" ", 2).length == 1) {
            throw new EmptyDescriptionException("deadline");
        }

        String allDetails = this.command.split(" ", 2)[1];
        return allDetails.split("/by", 2)[0];
    }

    /**
     * Returns the date of a Deadline task.
     *
     * @return The date and time of a Deadline task
     * @throws EmptyDescriptionException If the user input is missing extra information after the command.
     * @throws DateNotAcceptedException If there is an error with the date inputted.
     */
    public LocalDateTime getDeadlineDateTime() throws EmptyDescriptionException, DateNotAcceptedException {

        if (commandWords.length == 1 || this.command.split(" ", 2).length == 1) {
            throw new EmptyDescriptionException("deadline");
        }

        String allDetails = this.command.split(" ", 2)[1];
        try {
            return LocalDateTime.parse(allDetails.split("/by ", 2)[1].replace(" ", "T"));

        } catch (DateTimeParseException e) {
            throw new DateNotAcceptedException("Please recheck your date and time inputs!");

        } catch (IndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("There isn't enough information :(");
        }
    }

    /**
     * Returns the information of an Event task.
     *
     * @return The description of an Event task
     * @throws EmptyDescriptionException If the user input is missing extra information after the command.
     */
    public String getEventInfo() throws EmptyDescriptionException {

        if (commandWords.length == 1 || this.command.split(" ", 2).length == 1) {
            throw new EmptyDescriptionException("event");
        }

        String allDetails = this.command.split(" ", 2)[1];
        return allDetails.split("/at", 2)[0];
    }

    /**
     * Returns the starting date and time of an Event task.
     *
     * @return The starting time and date of an Event task.
     * @throws EmptyDescriptionException If the user input is missing extra information after the command.
     */
    public LocalDateTime getEventStartDateTime() throws EmptyDescriptionException, DateNotAcceptedException {
        if (commandWords.length == 1 || this.command.split(" ", 2).length == 1) {
            throw new EmptyDescriptionException("event");
        }

        String allDetails = this.command.split(" ", 2)[1];

        try {
            return LocalDateTime.parse(allDetails.split("/at ", 2)[1]
                    .split("~", 2)[0].replace(" ", "T"));

        } catch (DateTimeParseException e) {
            throw new DateNotAcceptedException("Please recheck your date and time inputs!");

        } catch (IndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("There isn't enough information :(");
        }
    }

    /**
     * Returns the end time information of an Event task.
     *
     * @return The end time of an Event task
     * @throws EmptyDescriptionException If the user input is missing extra information after the command.
     */
    public LocalTime getEventEndTime() throws EmptyDescriptionException, DateNotAcceptedException {
        if (commandWords.length == 1 || this.command.split(" ", 2).length == 1) {
            throw new EmptyDescriptionException("event");
        }

        String allDetails = this.command.split(" ", 2)[1];
        try {
            return LocalTime.parse(allDetails.split("/at ", 2)[1]
                    .split("~", 2)[1]);

        } catch (DateTimeParseException e) {
            throw new DateNotAcceptedException("Please recheck your date and time inputs!");

        } catch (IndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("There isn't enough information :(");
        }
    }

    /**
     * Returns the information of a Tag.
     *
     * @return The information of a Tag
     * @throws EmptyDescriptionException If the user input is missing extra information after the command.
     */
    public String getTagInfo() throws EmptyDescriptionException {

        if (commandWords.length == 1 || this.command.split(" ", 3).length < 3) {
            throw new EmptyDescriptionException("event");
        }

        return this.command.split(" ", 3)[2];
    }
}
