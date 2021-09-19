package chad.command;

/**
 * Represents the different types of commands.
 *
 * @author Jay Aljelo Saez Ting
 */
public enum CommandType {

    EXIT("bye", "Exit"),
    ADD_TODO_TASK("todo", "Add To-do Task"),
    LIST_TASKS("list", "List Tasks"),
    MARK_TASK_DONE("done", "Mark Task Done"),
    ADD_DEADLINE_TASK("deadline", "Add Deadline Task"),
    ADD_EVENT_TASK("event", "Add Event Task"),
    DELETE_TASK("delete", "Delete Task"),
    FIND_TASKS("find", "Find Task"),
    UNDO("undo", "Undo");

    private final String commandName;
    private final String commandDescription;

    CommandType(String commandName, String commandDescription) {
        this.commandName = commandName;
        this.commandDescription = commandDescription;
    }

    String getCommandName() {
        return commandName;
    }

    String getCommandDescription() {
        return commandDescription;
    }
}
