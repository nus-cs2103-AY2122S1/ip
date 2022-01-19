package duchess.command;

import duchess.main.DuchessList;

/**
 * This abstract class contains the logic to handle commands.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public abstract class Command {
    /** The name of the Command.*/
    private String name;
    /** Constructs a Command.*/
    public Command(String name) {
        this.name = name;
    }
    /** Construct either a ByeCommand or ListCommand.*/
    public static Command of(String name) {
        switch (name) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        default:
            assert false : "Code execution should not reach here. Something went wrong. Command does not match any"
                    + " known commands.";
            return null;
        }
    }
    /** Constructs one of the other commands.*/
    public static Command of(String name, String description) {
        switch (name) {
        case "done":
            return new DoneCommand(description);
        case "todo":
            return new TodoCommand(description);
        case "deadline":
            return new DeadlineCommand(description);
        case "event":
            return new EventCommand(description);
        case "delete":
            return new DeleteCommand(description);
        case "tasks":
            return new TasksCommand(description);
        case "find":
            return new FindCommand(description);
        default:
            assert false : "Code execution should not reach here. Something went wrong. Command does not match any"
                    + " known commands.";
            return null;
        }
    }

    public String getDescription() {
        return this.name;
    }

    /**
     * Handles the logic specific to the command.
     * @param duchessList The DuchessList to read or write tasks to.
     * @return The reply from Duchess to the user.
     */
    public abstract String handleLogic(DuchessList duchessList);
}
