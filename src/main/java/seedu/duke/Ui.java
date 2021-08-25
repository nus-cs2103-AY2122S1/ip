package seedu.duke;

import seedu.duke.command.Commands;

/**
 * Represents a Ui object. A <code>Ui</code> object
 * outputs to the user based on given commands.
 */
class Ui {
    /**
     * Outputs the given command to the console.
     *
     * @param command Command to be printed to user.
     */
    public void outputMessage(Commands command) {
        String message = command.getCommand();
        System.out.println(String.format("%4s%s", " ", message));
    }

}
