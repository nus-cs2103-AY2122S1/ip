package duke;

import commands.*;
import tasks.TaskList;

import java.util.Scanner;

/**
 * A class responsible for reading and evaluating user inputs.
 */
class Parser {

    /** User input keywords */
    private final static String KEYWORD_DONE = "done";
    private final static String KEYWORD_LIST = "list";
    private final static String KEYWORD_BYE = "bye";
    private final static String KEYWORD_TODO = "todo";
    private final static String KEYWORD_DEADLINE = "deadline";
    private final static String KEYWORD_EVENT = "event";
    private final static String KEYWORD_DELETE = "delete";
    private final static String KEYOWORD_FIND = "find";

    /** A list of the tasks entered by the user */
    private final TaskList taskList;

    protected Parser() {
        taskList = new TaskList();
    }

    /**
     * Parses the user's input to find the relevant command.
     *
     * @param input The user's input.
     * @return The correct command for the user's input.
     */
    protected Command parseUserInput(String input) {
        if (input.equalsIgnoreCase(KEYWORD_BYE)) {
            // Ends the chat
            return new ExitCommand();
        } else if (input.equalsIgnoreCase(KEYWORD_LIST)) {
            // Shows the task history
            return new ListCommand(this.taskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_DONE)) {
            // Sets a task as done
            return new DoneCommand(input, this.taskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_TODO)) {
            // Creates a todo task
            return new AddTodoCommand(input, this.taskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_DEADLINE)) {
            //Creates a deadline task
            return new AddDeadlineCommand(input, this.taskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_EVENT)) {
            // Creates an event task
            return new AddEventCommand(input, this.taskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_DELETE)) {
            // Sets a task as done
            return new DeleteCommand(input, this.taskList);
        } else if (input.toLowerCase().startsWith(KEYOWORD_FIND)) {
            return new FindCommand(input, this.taskList);
        }
        // Unrecognised input
        return new InvalidCommand();
    }

    protected TaskList getTaskList() {
        return taskList;
    }
}
