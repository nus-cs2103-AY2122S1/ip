package duke.io;

import duke.Duke;
import duke.command.Commands;

public class Ui {
    private StringBuilder message = new StringBuilder();

    /**
     * Prints the message stored in the StringBuilder between two line separators
     */
    public void print() {
        String linebreak = "_________________________________________\n";
        System.out.println(linebreak + message + '\n' + linebreak);
        resetMessage();
    }

    /**
     * Prints a message to prompt for user input
     */
    public void prompt() {
        System.out.println("How may I help you?");
    }

    /**
     * Add a message to be added to the queue to be printed.
     *
     * @param message The message to be added
     * @param color The color of the text of the message
     */
    public void addMessage(String message, TextColor color) {
        this.message.append(color);
        this.message.append(message);
        this.message.append(TextColor.DEFAULT);
    }

    /**
     * Clears the message queue
     */
    public void resetMessage() {
        message = new StringBuilder();
    }

    /**
     * Prints the startup message for when the user is determined to be new.
     * Prints a command list in addition to greeting.
     */
    public void greetNewUser() {
        addMessage("Hello, I'm iP Man! How may I help you?\n", TextColor.DEFAULT);
        addMessage("Supported commands: ", TextColor.DEFAULT);
        int lineLength = 20;
        int maxLineLength = 40;
        Commands[] commands = Commands.values();
        for (int i = 0; i < commands.length; i++) {
            String commandString = commands[i].getCommand().getCommandString();

            // ensure that line length doesn't get too long
            if (lineLength + commandString.length() > maxLineLength) {
                addMessage("\n  ", TextColor.DEFAULT);
                lineLength = commandString.length() + 2;
            } else {
                lineLength += commandString.length();
            }

            addMessage(commands[i].getCommand().getCommandString()
                    + (i == commands.length - 1 ? "" : ", "), TextColor.DEFAULT);
        }
        print();
        prompt();
    }

    /**
     * Prints the startup message for when the user is determined to have used the app before.
     * Prints the current list in addition to a greeting.
     */
    public void greetReturningUser() {
        addMessage("Welcome back! How may I help you?\n", TextColor.DEFAULT);
        addMessage("Tasks in list:\n", TextColor.DEFAULT);
        Duke.taskList.list();
        print();
        prompt();
    }
}
