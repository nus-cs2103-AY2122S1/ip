/**
 * A parser to parse Duke commands
 */
public class Parser {
    /**
     * Get the action specified by the command
     *
     * A valid action can be list, done, todo, event, deadline, delete
     * @param command The command to be parsed
     * @return The action of the command
     */
    public String getCommandAction(String command) {
        return command.split(" ", 2)[0];
    }

    /**
     * Get the task index of done and delete action
     *
     * @param command The command to be parsed
     * @return The index of the task of the command, -1 if is invalid action
     */
    public int getCommandActionIndex(String command) throws DukeException {
        switch (getCommandAction(command)) {
        case "done":
            return Integer.parseInt(command.substring(5));
        case "delete":
            return Integer.parseInt(command.substring(7));
        default:
            throw new DukeException("Invalid action. Index cannot be parsed from the command.");
        }
    }

    /**
     * Get the relavant task information from the command
     *
     * @param command The command to be parsed
     * @return A map of task information from the command
     */
    public Task commandToTask(String command) throws DukeException {
        switch (getCommandAction(command)) {
        case "todo":
            String[] todoSplit = command.split(" ", 2);
            String todoDescription;
            try {
                todoDescription = todoSplit[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                // no description
                String message = "☹ OOPS!!! The description of a todo cannot be empty.";
                throw new DukeException(message);
            }
            return new Todo(todoDescription);
        case "event":
            String[] eventSplit = command.split(" /at ");
            String at;
            try {
                at = eventSplit[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                // no /at found in command
                String message = "☹ OOPS!!! The time of an event cannot be empty.";
                throw new DukeException(message);
            }
            String eventDescription;
            try {
                eventDescription = eventSplit[0].split("event ")[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                // no event description
                String message = "☹ OOPS!!! The description of an event cannot be empty.";
                throw new DukeException(message);
            }
            return new Event(eventDescription, at);
        case "deadline":
            String[] ddlSplit = command.split(" /by ");
            String by;
            try {
                by = ddlSplit[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                // no /by found in command
                String message = "☹ OOPS!!! The time of a deadline cannot be empty.";
                throw new DukeException(message);
            }
            String ddlDescription;
            try {
                ddlDescription = ddlSplit[0].split("deadline ")[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                // no deadline description
                String message= "☹ OOPS!!! The description of a deadline cannot be empty.";
                throw new DukeException(message);
            }
            return new Deadline(ddlDescription, by);
        default:
            throw new DukeException("Invalid action. Task cannot be parsed from the command.");
        }
    }
}
