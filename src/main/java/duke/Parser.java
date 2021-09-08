package duke;

/**
 * Translates the user input string into a command object.
 */
public class Parser {

    /**
     * Takes in the user input and converts it into a command.
     *
     * @param input the user input string.
     * @return the command object that represents the action that the user intends to make.
     */
    public static Command parse(String input) {
        if (input.equals("bye")) {
            return new ExitCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        }
        if (input.startsWith("done ")) {
            return parseDone(input);
        }
        if (input.startsWith("delete ")) {
            return parseDelete(input);
        }
        if (input.startsWith("find ")) {
            return parseFind(input);
        }
        return comprehendTask(input);
    }

    private static DeleteCommand parseDelete(String input) {
        int deletedTaskNumber;
        try {
            deletedTaskNumber = Integer.parseInt(input.substring(7)) - 1;
            assert deletedTaskNumber >= 0;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number provided.");
        }

        return new DeleteCommand(deletedTaskNumber);
    }

    private static DoneCommand parseDone(String input) {
        int completedTaskNumber;
        try {
            completedTaskNumber = Integer.parseInt(input.substring(5)) - 1;
            assert completedTaskNumber >= 0;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number provided.");
        }

        return new DoneCommand(completedTaskNumber);
    }

    private static AddCommand comprehendTask(String input) {
        Task newTask;
        if (input.startsWith("todo ")) {
            int todoFirstIndex = 5;
            newTask = new Todo(input.substring(todoFirstIndex));
        } else if (input.startsWith("deadline ")) {
            int location = input.indexOf("/by ");
            if (location < 0) {
                throw new DukeException("Please provide a deadline.");
            }
            int deadlineFirstIndex = 9;
            assert location > 9;
            newTask = new Deadline(input.substring(deadlineFirstIndex, location - 1), input.substring(location + 4));
        } else if (input.startsWith("event ")) {
            int location = input.indexOf("/at ");
            if (location < 0) {
                throw new DukeException("Please provide an event time.");
            }
            int eventFirstIndex = 6;
            assert location > 6;
            newTask = new Event(input.substring(eventFirstIndex, location - 1), input.substring(location + 4));
        } else {
            throw new DukeException("Please specify a type of task.");
        }

        return new AddCommand(newTask);
    }

    private static FindCommand parseFind(String input) {
        return new FindCommand(input.substring(5));
    }
}
