package duke;

import duke.command.DukeCommand;
import duke.exception.InvalidCommandException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents the UI interactions in the program.
 */
public class Ui {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final Scanner scanner;
    private final PrintStream outputStream;
    private boolean exit;

    public Ui(InputStream inputStream, PrintStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.outputStream = outputStream;
        this.exit = false;
    }

    /**
     * Prints a prompt for the next command, and returns the submitted user input.
     * @return the raw command input by the user
     */
    public String nextCommand() {
        outputStream.print("Duke> ");
        return scanner.nextLine();
    }

    /**
     * Outputs the given string with a newline.
     * @param output the string to output
     */
    public void outputLine(String output) {
        outputStream.println(output);
    }

    /**
     * Prints a welcome message greeting the user.
     */
    public void printWelcomeMessage() {
        outputLine("Hello from\n" + logo);
    }

    /**
     * Prints an exit message greeting the user.
     */
    public void printExitMessage() {
        outputLine("Goodbye from\n" + logo);
    }

    /**
     * Prints the list of commands available using the {@link DukeCommand#HELP} command.
     */
    public void printHelp() {
        try {
            DukeCommand.HELP.apply(null, this, null, "", Map.of());
        } catch (InvalidCommandException e) {
            e.printStackTrace();
        }
    }

    /**
     * Marks Duke as to be exited after the latest command is done processing.
     */
    public void markExit() {
        exit = true;
    }

    /**
     * Returns if Duke should continue accepting commands.
     * @return if Duke should continue accepting commands.
     */
    public boolean shouldContinue() {
        return !exit;
    }
}
