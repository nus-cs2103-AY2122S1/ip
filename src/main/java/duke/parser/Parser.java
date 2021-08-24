/**
 * This class handles interaction with the user: interpreting user command and logs
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.parser;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.NotDoneRightException;
import java.time.LocalDate;

public class Parser {

    String command;
    String[] commandWords;

    public Parser(String command) {
        this.command = command;
        this.commandWords = command.split(" ");
    }

    /**
     * This function returns the command (the first word) given.
     *
     * @return The first word (aka the command) from the user
     * @throws EmptyDescriptionException
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
     * This function checks if the second integer is viable for the done and delete commands
     * and returns it.
     *
     * @param size this is the length of the taskList
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
     * This function retrieves the information of a Todo task.
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
     * This function retrieves information about a Deadline task
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
     * This function retrieves the date of a Deadline task.
     *
     * @return The date of a todo task
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
     * This function retrieves the information of an Event task.
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
     * This function retrieves the location/additional information of an Event task.
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
