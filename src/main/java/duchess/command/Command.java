package duchess.command;

import duchess.main.Duchess;

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
        }
        // Should not reach here
        return null;
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
        }
        // Should not reach here
        return null;
    }

    public String getName() {
        return this.name;
    }

    /** Handles the logic specific to the command.*/
    public abstract boolean handleLogic(Duchess duchess);
}
