/**
 * This class handles interaction with the user.
 * Interprets user command and creates logs
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.parser;

import duke.exceptions.DateNotAcceptedException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.NotDoneRightException;
import java.time.LocalDate;
import java.util.zip.DataFormatException;

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
        this.commandWords = command.split(" ");
    }

    /**
     * Returns the first word (the command) given.
     *
     * @return The first word from the user, which is the command
     */
    public String getFirstWord() {
        return commandWords[0].toLowerCase();
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
        return commandWords[1].toLowerCase();
    }

    /**
     * Checks if the second integer is viable for the done and delete commands and returns it.
     *
     * @param size The length of the taskList
     * @return The second word (aka the number) from the user
     * @throws NotDoneRightException If there are issues with extracting the second integer from user input.
     */
    public int getSecondInteger(int size) throws NotDoneRightException {

        if (command.toLowerCase().split(" ").length == 1
                || Integer.parseInt(command.split(" ")[1]) < 1
                || Integer.parseInt(command.split(" ")[1]) > size) {
            throw new NotDoneRightException("1", String.valueOf(size));

        } else {
            return Integer.parseInt(commandWords[1]);
        }
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
     * @return The date of a Deadline task
     * @throws EmptyDescriptionException If the user input is missing extra information after the command.
     * @throws DateNotAcceptedException If there is an error with the date inputted.
     */
    public LocalDate getDeadlineDate() throws EmptyDescriptionException, DateNotAcceptedException {

        if (commandWords.length == 1 || this.command.split(" ", 2).length == 1) {
            throw new EmptyDescriptionException("deadline");
        }

        String allDetails = this.command.split(" ", 2)[1];
        try {
            return LocalDate.parse(allDetails.split("/by", 2)[1].strip());

        } catch (Exception e) {
            throw new DateNotAcceptedException(e.getMessage());
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
     * Returns the location/additional information of an Event task.
     *
     * @return The location/context of an Event task
     * @throws EmptyDescriptionException If the user input is missing extra information after the command.
     */
    public String getEventLocation() throws EmptyDescriptionException {
        if (commandWords.length == 1 || this.command.split(" ", 2).length == 1) {
            throw new EmptyDescriptionException("event");
        }

        String allDetails = this.command.split(" ", 2)[1];
        return allDetails.split("/at", 2)[1];
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
