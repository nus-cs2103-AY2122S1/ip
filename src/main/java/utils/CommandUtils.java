package utils;

import exception.DukeException;
import task.Operation;

/**
 * The is the CommandUtils class that extracts contents from command.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class CommandUtils {
    private static final String INDENTATION = "     ";

    /**
     * Extract operation from command.
     *
     * @return operation from command if exists, else throw exception
     * @throws DukeException if operation is empty
     */
    public static Operation extractOperation(String command) throws DukeException {
        String[] contents = command.split(" ");
        if (contents.length == 0) {
            throw new DukeException(INDENTATION + "☹ OOPS!!! The operation cannot be empty.");
        }
        String operation = contents[0];
        if (operation.equals("")) {
            throw new DukeException(INDENTATION + "☹ OOPS!!! The operation cannot be empty.");
        } else {
            if (operation.equals(Operation.TODO.getValue())) {
                return Operation.TODO;
            } else if (operation.equals(Operation.DEADLINE.getValue())) {
                return Operation.DEADLINE;
            } else if (operation.equals(Operation.EVENT.getValue())) {
                return Operation.EVENT;
            } else if (operation.equals(Operation.LIST.getValue())) {
                return Operation.LIST;
            } else if (operation.equals(Operation.DONE.getValue())) {
                return Operation.DONE;
            } else if (operation.equals(Operation.BYE.getValue())) {
                return Operation.BYE;
            } else {
                throw new DukeException(INDENTATION + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    /**
     * Extract task number from command.
     *
     * @return task number from command if it exists and is a positive integer,
     *         else throw exception
     * @throws DukeException if task number is empty or not a positive integer
     */
    public static int extractTaskNumber(String command) throws DukeException {
        String[] contents = command.split(" ", 2);
        if (contents.length == 1) {
            throw new DukeException(INDENTATION + "☹ OOPS!!! The task number cannot be empty.");
        }
        int number = 0;
        try {
            number = Integer.parseInt(contents[1]);
        } catch (Exception e) {
            throw new DukeException(INDENTATION + "☹ OOPS!!! The task number is not an integer.");
        }
        if (number < 1) {
            throw new DukeException(INDENTATION + "☹ OOPS!!! The task number is not a positive integer.");
        }
        return number;
    }

    /**
     * Extract task description from command.
     *
     * @return task description from command if it exists, else throw exception
     * @throws DukeException if task description is empty
     */
    public static String extractTaskDescription(String command) throws DukeException {
        String[] contents = command.split(" ", 2);
        String operation = contents[0];
        if (contents.length == 1) {
            throw new DukeException(INDENTATION + "☹ OOPS!!! The description of a " + operation + " cannot be empty.");
        }
        String description = contents[1];
        if (description.equals("")) {
            throw new DukeException(INDENTATION + "☹ OOPS!!! The description of a " + operation + " cannot be empty.");
        }
        return description;
    }

    /**
     * Extract task details that contains task name and task (at/by) time from task description.
     *
     * @param  description extracted from task command
     * @param  regex " /at " or " /by "
     * @return task details from description if they are not empty and can be extracted properly,
     *         else throw exception
     * @throws DukeException if task details are empty or cannot be extracted properly
     */
    public static String[] extractTaskDetails(String description, String regex) throws DukeException {
        String[] details = description.split(regex, 2);
        if (details.length != 2) {
            throw new DukeException(INDENTATION + "☹ OOPS!!! The task details cannot be extracted properly.");
        }
        for (String detail: details) {
            if (detail.equals("")) {
                throw new DukeException(INDENTATION + "☹ OOPS!!! The task details cannot be empty.");
            }
        }
        return details;
    }
}
