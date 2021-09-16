package duke.util;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.CompleteCommand;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FalseCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.exceptions.UserInputError;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * The Parser class that makes sense of the user input.
 */
public class Parser {
    private final String input;
    private final String command;
    private final TaskList taskList;
    /**
     * Constructor to make a parser.
     *
     * @param input Details of the user input.
     */
    public Parser(String input, TaskList taskList) {
        this.input = input;
        this.command = getFirstWord(input).toLowerCase();
        this.taskList = taskList;
    }

    /**
     * Parses input to get the appropriate Command object. Throws a UserInputError if user command does not exist in
     * Duke.
     *
     * @return Command type according to input.
     * @throws UserInputError Throws error with bad user input.
     */
    public Command parse() throws UserInputError {
        Command cmd;
        int index;

        if (input.indexOf(' ') > -1) {
            switch(command) {
            case "deadline":
                checkDescExist();
                cmd = new AddCommand(getDesc(input), Task.Type.DEADLINE);
                break;
            case "todo":
                checkDescExist();
                cmd = new AddCommand(getDesc(input), Task.Type.TODO);
                break;
            case "event":
                checkDescExist();
                cmd = new AddCommand(getDesc(input), Task.Type.EVENT);
                break;
            case "find":
                checkDescExist();
                cmd = new FindCommand(getDesc(input));
                break;
            case "delete":
                index = getTaskNumber() - 1;
                checkIndexRange(index);
                cmd = new DeleteCommand(index);
                break;
            case "done":
                index = getTaskNumber() - 1;
                checkIndexRange(index);
                cmd = new CompleteCommand(index);
                break;
            default:
                cmd = new FalseCommand();
            }
        } else {
            switch (command) {
            case "bye":
                cmd = new ExitCommand();
                break;
            case "list":
                cmd = new ListCommand();
                break;
            default:
                cmd = new FalseCommand();
            }
        }
        return cmd;
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
     * @throws UserInputError Throws error with bad user input.
     */
    private void checkIndexRange(int index) throws UserInputError {
        if (index < 0) {
            throw new UserInputError(
                    "Invalid task reference!\nIndex should be more than 0."
            );
        }

        if (index > taskList.getNumOfTasks() - 1) {
            throw new UserInputError(
                    "Invalid task reference!\nYou currently have "
                            + taskList.getNumOfTasks()
                            + " tasks."
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
     * @throws UserInputError Throws error with bad user input.
     */
    private void checkDescExist() throws UserInputError {
        if (input.split(" ").length == 1) {
            throw new UserInputError(
                    "Oops! Please add description for your command :)"
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
