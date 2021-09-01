package duke;

/**
 * Deals with making sense of the user's command.
 * It takes in user inputs and filters it to its respective command.
 */
public class Parser {
    public Parser() {

    }

    /**
     * @param command Command is the user input.
     * @param taskArr List of tasks in a TaskList.
     * @throws DukeException If input is unexpected or invalid.
     */
    public Command parse(String command, TaskList taskArr) throws DukeException {
        String[] commandArr = command.split(" ");
        if (command.equals("list")) {
            return new ListCommand();
        } else if (commandArr[0].equals("done")) {
            int taskArrRef = Integer.parseInt(commandArr[1]) - 1;
            return new DoneCommand(taskArrRef);
        } else if (commandArr[0].equals("delete")) {
            int taskArrRef = Integer.parseInt(commandArr[1]) - 1;
            return new DeleteCommand(taskArrRef);
        } else if (commandArr[0].equals("find")) {
            try {
                String keyword = commandArr[1];
                return new FindCommand(keyword);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please insert a word after \"done\"");
            }

        } else {
            boolean wrongArrayLength = commandArr.length <= 1;
            String taskAdded = "Got it. I've added this task: ";
            if (commandArr[0].equals("todo")) {
                if (wrongArrayLength) {
                    throw new DukeException("The description of a todo cannot be empty!");
                } else {
                    System.out.println(taskAdded);
                    int spaceIndex = command.indexOf(" ");
                    Task task = new Todo(command.substring(spaceIndex + 1));
                    return new TodoCommand(task);
                }

            } else if (commandArr[0].equals("deadline")) {
                if (wrongArrayLength) {
                    throw new DukeException("The description of a deadline cannot be empty!");
                } else if (!command.contains("/by ")) {
                    throw new DukeException("Remember to type input in this format:\"[deadline] [task] /by [date]\"");
                } else {
                    System.out.println(taskAdded);
                    int spaceIndex = command.indexOf(" ");
                    int slashIndex = command.indexOf("/by ");
                    Task task = new Deadline(command.substring(spaceIndex + 1, slashIndex - 1),
                            command.substring(slashIndex + 4));
                    return new DeadlineCommand(task);
                }

            } else if (commandArr[0].equals("event")) {
                if (wrongArrayLength) {
                    throw new DukeException("The description of an event cannot be empty!");

                } else if (!command.contains("/at ")) {
                    throw new DukeException("Remember to enter event in this format:\"[event] [task] /at [date]\"");
                } else {
                    System.out.println(taskAdded);
                    int spaceIndex = command.indexOf(" ");
                    int slashIndex = command.indexOf("/at ");
                    Task task = new Event(command.substring(spaceIndex + 1, slashIndex - 1),
                            command.substring(slashIndex + 4));
                    return new EventCommand(task);
                }

            } else {
                throw new DukeException("I'm sorry, but I don't know what that means!");
            }

        }
        throw new DukeException("I'm sorry, but I don't know what that means!");
    }


}
