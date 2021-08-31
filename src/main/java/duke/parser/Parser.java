package duke.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import duke.exception.DukeException;
import duke.exception.EmptyCommandException;
import duke.exception.IncorrectFormatException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateTimeException;
import duke.exception.MessageEmptyException;
import duke.exception.TaskNotFoundException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;


/**
 * Handles and interprets user commands for Duke.
 */

public class Parser {

    /** List of tasks. */
    private final TaskList tasks;

    /** UI to handle user inputs. */
    private final Ui ui;

    /** Storage that handles saving and loading of the data file. */
    private final Storage storage;

    /**
     * Constructor for Parser.
     *
     * @param tasks list of tasks.
     */
    public Parser(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Logic for handling different commands and executing the appropriate methods for the inputted command.
     * Throws appropriate exceptions for its respective error.
     *
     * @param input The entire user input.
     */

    public String handleCommands(String input) {
        // isolates the command word
        String[] words = input.split(" ");
        String command = words[0];

        try {
            switch (command) {
            case "bye": // only applicable to GUI Duke
                storage.save(tasks.getTaskList());
                return ui.exit();
            case "list":
                return tasks.displayList();
            case "done":
                if (words.length == 1) {
                    // throws an error if there is no message input after the command word
                    throw new MessageEmptyException();
                }
                // extracts index of task to mark as done
                String doneTaskIndex = words[words.length - 1];
                return tasks.markDone(doneTaskIndex);
            case "deadline":
                if (words.length == 1) {
                    // throws an error if there is no message input after the command word
                    throw new MessageEmptyException();
                }
                try {
                    // excludes command "deadline " from the string
                    return tasks.addDeadline(input.substring(9));
                } catch (InvalidDateTimeException | MessageEmptyException | IncorrectFormatException e) {
                    return e.getMessage();
                }
            case "todo":
                if (words.length == 1) {
                    // throws an error if there is no message input after the command word
                    throw new MessageEmptyException();
                }
                // excludes command "todo" from the string
                return tasks.addTodo(input.substring(5));
            case "event":
                if (words.length == 1) {
                    // throws an error if there is no message input after the command word
                    throw new MessageEmptyException();
                }
                // excludes command "event" from the string
                return tasks.addEvent(input.substring(6));
            case "delete":
                if (words.length == 1) {
                    // throws an error if there is no message input after the command word
                    throw new MessageEmptyException();
                }
                // extracts index of task to delete
                String deleteTaskIndex = words[words.length - 1];
                return tasks.deleteTask(deleteTaskIndex);
            case "find":
                ArrayList<Task> matchedTasks = findCommand(words);
                return Ui.printList(matchedTasks);
            case "": // empty user input
                throw new EmptyCommandException();
            default: // all other inputs that are not supported
                throw new InvalidCommandException();
            }
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Processes the find command.
     *
     * @param words words to be found.
     * @return ArrayList of Tasks that match the words to be found.
     * @throws MessageEmptyException if no words are given after the command.
     * @throws TaskNotFoundException if no tasks of the specified query can be found.
     */
    private ArrayList<Task> findCommand(String[] words) throws MessageEmptyException, TaskNotFoundException {
        if (words.length == 1) {
            throw new MessageEmptyException();
        }

        ArrayList<String> isolateCommand = new ArrayList<>(Arrays.asList(words));

        // remove "find"
        isolateCommand.remove(0);

        String query = String.join(" ", isolateCommand);

        ArrayList<Task> matchedTasks = tasks.findTask(query);

        if (matchedTasks.size() == 0) {
            throw new TaskNotFoundException();
        }

        return matchedTasks;
    }
}
