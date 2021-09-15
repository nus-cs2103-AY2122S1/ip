package seedu.duke.command;

import seedu.duke.DateTimeManager;
import seedu.duke.DukeException;
import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a command. A <code>Command</code> describes
 * the action to be executed based on the command.
 */
public abstract class Command {

    public enum CommandType {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        DONE("done"),
        FIND("find"),
        GET("get"),
        BYE("bye"),
        LIST("list"),
        HELP("help"),
        REMINDER("remind"),
        INVALID("");

        private String type;

        CommandType(String type) {
            this.type = type;
        }

        //@@author Morrow1ndy-reused
        // with minor modifications
        public static CommandType parseTypeFromCommandWord(String word) {
            switch (word) {
            case "todo":
                return TODO;
            case "deadline":
                return DEADLINE;
            case "event":
                return EVENT;
            case "delete":
                return DELETE;
            case "done":
                return DONE;
            case "find":
                return FIND;
            case "get":
                return GET;
            case "bye":
                return BYE;
            case "list":
                return LIST;
            case "help":
                return HELP;
            case "reminder":
                return REMINDER;
            default:
                return INVALID;
            }
        }
    }

    protected Ui ui;
    protected TaskList taskList;

    /**
     * Public constructor for a <code>Command</code>.
     * @param ui Ui to handle user interactions.
     * @param taskList TaskList to be updated.
     */
    public Command(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return false if it is not.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Checks if the given command alters the task list.
     *
     * @return false if it does not update the task list.
     */
    public boolean isUpdatesTaskList() {
        return false;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Updates the hashmap if the command adds in tasks with dates.
     *
     * @param dateTasks Hashmap to be updated.
     * @param manager Manager that updates the hashmap.
     */
    public void updateDateTasks(HashMap<LocalDate, ArrayList<Task>> dateTasks,
                                DateTimeManager manager) {
        return;
    }

    /**
     * Returns the format on how to use the command.
     *
     * @return String representation of the help message.
     */
    public abstract String getUsageMessage();

    public abstract String execute() throws DukeException;

}
