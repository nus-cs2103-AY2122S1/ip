package duke.io;

import duke.command.Commands;
import duke.Duke;

public class Ui {
    private StringBuilder message = new StringBuilder();

    // prints the message between two lines and resets the message
    public void print() {
        String linebreak = "_________________________________________\n";
        System.out.println(linebreak + message + '\n' + linebreak);
        resetMessage();
    }

    public void prompt() {
        System.out.println("How may I help you?");
    }

    // adds message to be printed in the specified color
    public void addMessage(String message, TextColor color) {
        this.message.append(color);
        this.message.append(message);
        this.message.append(TextColor.DEFAULT);
    }

    public void resetMessage() {
        message = new StringBuilder();
    }

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

    public void greetReturningUser() {
        addMessage("Welcome back! How may I help you?\n", TextColor.DEFAULT);
        addMessage("Tasks in list:\n", TextColor.DEFAULT);
        Duke.taskList.list();
        print();
        prompt();
    }
}
