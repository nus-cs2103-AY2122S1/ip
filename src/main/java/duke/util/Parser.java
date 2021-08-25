package duke.util;

import duke.Duke;

import duke.exceptions.ExceedListSizeException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.NoDescriptionException;
import duke.exceptions.UserInputError;

public class Parser {
    private final String input;
    private final String command;

    public Parser(String input) {
        this.input = input;
        command = getFirstWord(input);
    }

    public String[] parse() throws UserInputError {
            if (input.equals("list") || input.equals("bye")) {
                return new String[] {command};
            } else if (command.equals("done") || command.equals("delete")) {
                int index = getTaskNumber() - 1;
                checkIndexRange(index);
                return new String[] {command, Integer.toString(index)};
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                checkDescExist();
                return new String[] {command, getDesc(input)};
            } else {
                throw new InvalidInputException();
            }
    }

    private int getTaskNumber() {
        String[] result = input.split(" ");

        if (result[1].matches("\\d+")) {
            return Integer.parseInt(result[1]);
        }
        return -1;
    }

    private void checkIndexRange(int index) throws ExceedListSizeException {
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

    private String getDesc(String input) {
        return input.split(" ", 2)[1];
    }

    private void checkDescExist() throws NoDescriptionException {
        if (input.split(" ").length == 1) {
            throw new NoDescriptionException(
                    "Oops! Please add description for your command."
            );
        }
    }

    private String getFirstWord(String text) {
        int index = text.indexOf(' ');

        if (index > -1) { // Check if there is more than one word.
            return text.substring(0, index).trim(); // Extract first word.
        } else {
            return text; // Text is the first word itself.
        }
    }
}
