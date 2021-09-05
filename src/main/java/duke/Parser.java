package duke;

/**
 * Deals with making sense of the user's command.
 * It takes in user inputs and filters it to its respective command.
 */
public class Parser {

    /**
     * @param command Command is the user input.
     * @param taskArr List of tasks in a TaskList.
     * @throws DukeException If input is unexpected or invalid.
     */
    public Command parse(String command, TaskList taskArr) throws DukeException {
        String[] commandArr = command.split(" ");
        int commandArrLength = commandArr.length;
        if (command.equals("save")) {
            return new SaveCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (commandArr[0].equals("done")) {
            if (commandArrLength <= 1) {
                return new ErrorCommand("Please enter the task number after \"done\"");
            } else {
                int taskArrRef = Integer.parseInt(commandArr[1]) - 1;
                return new DoneCommand(taskArrRef);
            }
        } else if (commandArr[0].equals("delete")) {
            if (commandArrLength <= 1) {
                return new ErrorCommand("Please enter the task number after \"delete\"");
            } else {
                int taskArrRef = Integer.parseInt(commandArr[1]) - 1;
                return new DeleteCommand(taskArrRef);
            }
        } else if (commandArr[0].equals("find")) {
            if (commandArrLength <= 1) {
                return new ErrorCommand("Please enter a keyword after \"find\"");
            } else {
                String keyword = commandArr[1];
                return new FindCommand(keyword);
            }
        } else {
            boolean wrongArrayLength = commandArr.length <= 1;
            if (commandArr[0].equals("todo")) {
                if (wrongArrayLength) {
                    return new ErrorCommand("The description of a todo cannot be empty!");
                } else {
                    int spaceIndex = command.indexOf(" ");
                    Task task = new Todo(command.substring(spaceIndex + 1));
                    return new TodoCommand(task);
                }

            } else if (commandArr[0].equals("deadline")) {
                if (wrongArrayLength) {
                    return new ErrorCommand("The description of a deadline cannot be empty!");
                } else if (!command.contains("/by ")) {
                    return new ErrorCommand("Remember to type input in this format:\"[deadline] [task] /by [date]\"");
                } else {
                    int spaceIndex = command.indexOf(" ");
                    int slashIndex = command.indexOf("/by ");
                    Task task = new Deadline(command.substring(spaceIndex + 1, slashIndex - 1),
                            command.substring(slashIndex + 4));
                    return new DeadlineCommand(task);
                }

            } else if (commandArr[0].equals("event")) {
                if (wrongArrayLength) {
                    return new ErrorCommand("The description of an event cannot be empty!");

                } else if (!command.contains("/at ")) {
                    return new ErrorCommand("Remember to enter event in this format:\"[event] [task] /at [date]\"");
                } else {
                    int spaceIndex = command.indexOf(" ");
                    int slashIndex = command.indexOf("/at ");
                    Task task = new Event(command.substring(spaceIndex + 1, slashIndex - 1),
                            command.substring(slashIndex + 4));
                    return new EventCommand(task);
                }

            } else {
                return new ErrorCommand("I'm sorry, but I don't know what that means!");
            }

        }
    }


}
