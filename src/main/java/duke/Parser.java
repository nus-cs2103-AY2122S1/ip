package duke;

import commands.Command;
import commands.CommandStack;
import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.DoneCommand;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.UndoCommand;

import tasks.TaskList;

/**
 * A class responsible for reading and evaluating user inputs.
 */
class Parser {

    /** User input keywords */
    private static final String KEYWORD_DONE = "done";
    private static final String KEYWORD_LIST = "list";
    private static final String KEYWORD_BYE = "bye";
    private static final String KEYWORD_TODO = "todo";
    private static final String KEYWORD_DEADLINE = "deadline";
    private static final String KEYWORD_EVENT = "event";
    private static final String KEYWORD_DELETE = "delete";
    private static final String KEYWORD_FIND = "find";
    private static final String KEYWORD_UNDO = "undo";

    /** A list of the tasks entered by the user */
    private final TaskList taskList;
    private final CommandStack commandStack;

    protected Parser(CommandStack commandStack) {
        this.taskList = new TaskList();
        this.commandStack = commandStack;
    }

    /**
     * Parses the user's input to find the relevant command.
     *
     * @param input The user's input.
     * @return The correct command for the user's input.
     */
    protected Command parseUserInput(String input) {
        assert input != null : "Unable to parse user input as user input is null";
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
        } else if (input.toLowerCase().startsWith(KEYWORD_FIND)) {
            // Search for a task in the taskList
            return new FindCommand(input, this.taskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_UNDO)) {
            return new UndoCommand(this.commandStack);
        }
        // Unrecognised input
        return new InvalidCommand();
    }

    protected TaskList getTaskList() {
        return taskList;
    }
}
