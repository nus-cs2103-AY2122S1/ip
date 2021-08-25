package bot;

import java.util.Scanner;

import commands.Command;

/**
 * The InputManager handles reading user input/commands
 */
public class Parser {

    private static final Scanner reader;

    static {
        reader = new Scanner(System.in);
    }

    /**
     * Gets and returns user input
     *
     * @return input string entered by user
     */
    public static String getInput() {
        return reader.nextLine();
    }

    /**
     * Converts input string to Command
     *
     * @param input user input
     * @return Command matching input
     */
    public static Command commandFromString(String input) {
        String[] inputArr = input.split(" ", 2);
        Command cmd = CommandType.getCommandFromName(inputArr[0]);
        cmd.setArgs(new String[]{inputArr.length >= 2 ? inputArr[1] : ""});
        return cmd;
    }

    /**
     * Combines receiving user input and converting
     * it to a command
     *
     * @return Command corresponding to user input
     */
    public static Command getCommand() {
        return commandFromString(getInput());
    }

    /**
     * Close scanner for cleanup
     */
    public static void closeScanner() {
        reader.close();
    }

}
