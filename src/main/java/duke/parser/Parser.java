/**
 * This class handles interaction with the user.
 * Interprets user command and creates logs
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.parser;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.NotDoneRightException;
import java.time.LocalDate;

public class Parser {

    private final String command;
    private final String[] commandWords;

    public Parser(String command) {
        this.command = command;
        this.commandWords = command.split(" ");
    }

    /**
     * Returns the command (the first word) given.
     *
     * @return Command (which is the first word) from the user
     */
    public String getFirstWord() {
        return commandWords[0].toLowerCase();
    }

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
     * @throws NotDoneRightException
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
     * @throws EmptyDescriptionException
     */
    public String getTodoInfo() throws EmptyDescriptionException {

        if (commandWords.length == 1) {
            throw new EmptyDescriptionException("todo");
        }

       return this.commandWords[1];
    }

    /**
     * Returns information about a Deadline task.
     *
     * @return The description of a Deadline task
     * @throws EmptyDescriptionException
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
     * @throws EmptyDescriptionException
     */
    public LocalDate getDeadlineDate() throws EmptyDescriptionException {

        if (commandWords.length == 1 || this.command.split(" ", 2).length == 1) {
            throw new EmptyDescriptionException("deadline");
        }

        String allDetails = this.command.split(" ", 2)[1];
        return LocalDate.parse(allDetails.split("/by", 2)[1].strip());
        // TODO add DateNotAcceptedException
    }

    /**
     * Returns the information of an Event task.
     *
     * @return The description of an Event task
     * @throws EmptyDescriptionException
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
     * @throws EmptyDescriptionException
     */
    public String getEventLocation() throws EmptyDescriptionException {
        if (commandWords.length == 1 || this.command.split(" ", 2).length == 1) {
            throw new EmptyDescriptionException("event");
        }

        String allDetails = this.command.split(" ", 2)[1];
        return allDetails.split("/at", 2)[1];
    }
}
