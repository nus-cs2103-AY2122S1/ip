package duke;

import commands.Command;
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
import tasks.TaskListHistory;

/**
 * A class responsible for reading and evaluating user inputs.
 */
public class Parser {

    /** User input keywords */
    private static final String KEYWORD_DONE = "done";
    private static final String KEYWORD_LIST = "list";
    private static final String KEYWORD_BYE = "bye";
    private static final String KEYWORD_TODO = "todo";
    private static final String KEYWORD_DEADLINE = "deadline";
    private static final String KEYWORD_EVENT = "event";
    private static final String KEYWORD_DELETE = "delete";
    private static final String KEYWORD_UNDO = "undo";
    private static final String KEYWORD_FIND = "find";

    /** A history of the changes made to the taskList */
    private final TaskListHistory taskListHistory;

    /** The current taskList */
    private TaskList currentTaskList;

    /** The previous taskList */
    private TaskList previousTaskList;

    protected Parser() {
        this.taskListHistory = new TaskListHistory();
        this.currentTaskList = new TaskList();
    }

    /**
     * Parses the user's input to find the relevant command.
     *
     * @param input The user's input.
     * @return The correct command for the user's input.
     */
    protected Command parseUserInput(String input) {
        assert input != null : "Unable to parse user input as user input is null";
        this.previousTaskList = TaskList.shallowCopyTaskList(this.currentTaskList);
        if (input.equalsIgnoreCase(KEYWORD_BYE)) {
            // Ends the chat
            return new ExitCommand();
        } else if (input.equalsIgnoreCase(KEYWORD_LIST)) {
            // Shows the task history
            return new ListCommand(this.currentTaskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_DONE)) {
            // Sets a task as done
            return new DoneCommand(input, this.currentTaskList, this.taskListHistory);
        } else if (input.toLowerCase().startsWith(KEYWORD_TODO)) {
            // Creates a todo task
            return new AddTodoCommand(input, this.currentTaskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_DEADLINE)) {
            //Creates a deadline task
            return new AddDeadlineCommand(input, this.currentTaskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_EVENT)) {
            // Creates an event task
            return new AddEventCommand(input, this.currentTaskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_DELETE)) {
            // Sets a task as done
            return new DeleteCommand(input, this.currentTaskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_FIND)) {
            // Search for a task in the taskList
            return new FindCommand(input, this.currentTaskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_UNDO)) {
            // Undo a previous change to the taskList
            return new UndoCommand(this.taskListHistory, this);
        } else {
            // Unrecognised input
            return new InvalidCommand();
        }
    }

    /**
     * Adds a new taskList version to the taskList history. This method is called
     * when a user enters a command that successfully change the taskList.
     *
     * @param taskStateChange True if the internal state of an individual task has
     *                        been changed from the previous iteration.
     */
    protected void updateTaskListHistory(boolean taskStateChange) {
        this.taskListHistory.addTaskListChanges(this.previousTaskList, taskStateChange);
    }

    /**
     * Reverts the current task list to the previous task list.
     *
     * @param previousTaskList The previous taskList to revert to.
     */
    public void undoCurrentTaskList(TaskList previousTaskList) {
        assert previousTaskList != null : "An error occurred while trying to undo your changes.";
        this.currentTaskList = previousTaskList;
    }

    /**
     * Returns the currently used taskList.
     */
    protected TaskList getCurrentTaskList() {
        assert this.currentTaskList != null : "An error occurred with the TaskList";
        return this.currentTaskList;
    }
}
