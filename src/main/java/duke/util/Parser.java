package duke.util;

import duke.Duke;
import duke.exceptions.ExceedListSizeException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.NoDescriptionException;
import duke.exceptions.UserInputError;

/**
 * The Parser class that makes sense of the user input.
 */
public class Parser {
    private final String input;
    private final String command;

    /**
     * Constructor to make a parser.
     *
     * @param input Details of the user input.
     */
    public Parser(String input) {
        this.input = input;
        command = getFirstWord(input);
    }

    /**
     * Parse the input to make input more readable.
     *
     * @return Array of string sectioned into separate parts.
     * @throws UserInputError
     */
    public String[] parse() throws UserInputError {
            if (input.equals("list") || input.equals("bye")) {
                return new String[] {command};
            } else if (command.equals("done") || command.equals("delete")) {
                int index = getTaskNumber() - 1;
                checkIndexRange(index);
                return new String[] {command, Integer.toString(index)};
            } else if (command.equals("todo") || command.equals("deadline") ||
                    command.equals("event") || command.equals("find")) {
                checkDescExist();
                return new String[] {command, getDesc(input)};
            } else {
                throw new InvalidInputException();
            }
    }

    /**
     * Get index of the task the user is referring to.
     *
     * @return Index of wanted task.
     */
    private int getTaskNumber() {
        String[] result = input.split(" ");
        if (result[1].matches("\\d+")) {
            return Integer.parseInt(result[1]);
        }
        return -1;
    }

    /**
     * Check that input index is valid.
     *
     * @param index Input index proposed by user.
     * @throws ExceedListSizeException
     */
    private void checkIndexRange(int index)
            throws ExceedListSizeException {
        if (index < 0) {
            throw new ExceedListSizeException(
                    "Invalid task reference!\nIndex should be more than 0."
            );
        }

        if (index > Duke.taskList.length() - 1) {
            throw new ExceedListSizeException(
                    "Invalid task reference!\nYou currently have " +
                            Duke.taskList.length() +
                            " tasks."
            );
        }
    }

    /**
     * Separate command and description from input.
     *
     * @param input User input.
     * @return Description of the task.
     */
    private String getDesc(String input) {
        return input.split(" ", 2)[1];
    }

    /**
     * Check user input contains needed description.
     *
     * @throws NoDescriptionException
     */
    private void checkDescExist()
            throws NoDescriptionException {
        if (input.split(" ").length == 1) {
            throw new NoDescriptionException(
                    "Oops! Please add description for your command."
            );
        }
    }

    /**
     * Get the command from the input.
     *
     * @param text User input.
     * @return Command for Duke.
     */
    private String getFirstWord(String text) {
        int index = text.indexOf(' ');
        if (index > -1) { // Check if there is more than one word.
            return text.substring(0, index).trim(); // Extract first word.
        } else {
            return text; // Text is the first word itself.
        }
    }
}
