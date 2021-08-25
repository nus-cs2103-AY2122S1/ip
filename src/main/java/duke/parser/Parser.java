package duke.parser;

import duke.exception.TaskNotFoundException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import duke.exception.DukeException;
import duke.exception.EmptyCommandException;
import duke.exception.IncorrectFormatException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateTimeException;
import duke.exception.MessageEmptyException;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Handles and interprets user commands for Duke.
 */

public class Parser {

    /** List of tasks */
    private final TaskList taskList;

    /**
     * Constructor for Parser.
     *
     * @param taskList list of tasks.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Logic for handling different commands and executing the appropriate methods for the inputted command.
     * Throws appropriate exceptions for its respective error.
     *
     * @param input The entire user input.
     */

    public void handleCommands(String input) {
        // isolates the command word
        String[] words = input.split(" ");
        String command = words[0];

        try {
            switch (command) {
            case "list":
                taskList.displayList();
                break;
            case "done":
                if (words.length == 1) {
                    // throws an error if there is no message input after the command word
                    throw new MessageEmptyException();
                }

                // extracts index of task to mark as done
                String doneTaskIndex = words[words.length - 1];
                taskList.markDone(doneTaskIndex);
                break;
            case "deadline":
                if (words.length == 1) {
                    // throws an error if there is no message input after the command word
                    throw new MessageEmptyException();
                }
                try {
                    // excludes command "deadline " from the string
                    taskList.addDeadline(input.substring(9));
                } catch (InvalidDateTimeException | MessageEmptyException | IncorrectFormatException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "todo":
                if (words.length == 1) {
                    // throws an error if there is no message input after the command word
                    throw new MessageEmptyException();
                }

                // excludes command "todo" from the string
                taskList.addTodo(input.substring(5));
                break;
            case "event":
                if (words.length == 1) {
                    // throws an error if there is no message input after the command word
                    throw new MessageEmptyException();
                }
                // excludes command "event" from the string
                taskList.addEvent(input.substring(6));
                break;
            case "delete":
                if (words.length == 1) {
                    // throws an error if there is no message input after the command word
                    throw new MessageEmptyException();
                }

                // extracts index of task to delete
                String deleteTaskIndex = words[words.length - 1];
                taskList.deleteTask(deleteTaskIndex);
                break;
            case "find":
                if (words.length == 1) {
                    throw new MessageEmptyException();
                }

                ArrayList<String> isolateCommand = new ArrayList<>(Arrays.asList(words));

                // remove "find"
                isolateCommand.remove(0);

                String query = String.join(" ", isolateCommand);

                ArrayList<Task> matchedTasks = taskList.findTask(query);

                if (matchedTasks.size() == 0) {
                    throw new TaskNotFoundException();
                }

                Ui.printList(matchedTasks);
                break;
            case "":                                // empty user input
                throw new EmptyCommandException();
            default:                                // all other inputs that are not supported
                throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            Ui.printMessage(e.getMessage());        // prints only error message out for user
        }
    }
}
