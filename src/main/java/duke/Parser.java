package duke;

public class Parser {
    public static Command parse(String input) {
        if (input.equals("bye")) return new ExitCommand();
        if (input.equals("list")) return new ListCommand();
        if (input.startsWith("done ")) return parseDone(input);
        if (input.startsWith("delete ")) return parseDelete(input);
        return comprehendTask(input);
    }

    public static DeleteCommand parseDelete(String input) {
        int deletedTaskNumber;
        try {
            deletedTaskNumber = Integer.parseInt(input.substring(7)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number provided.");
        }

        return new DeleteCommand(deletedTaskNumber);
    }

    public static DoneCommand parseDone(String input) {
        int completedTaskNumber;
        try {
            completedTaskNumber = Integer.parseInt(input.substring(5)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number provided.");
        }

        return new DoneCommand(completedTaskNumber);
    }

    public static AddCommand comprehendTask(String input) {
        Task newTask;
        if (input.startsWith("todo ")) {
            newTask = new Todo(input.substring(5));
        } else if (input.startsWith("deadline ")) {
            int location = input.indexOf("/by ");
            if (location < 0) throw new DukeException("Please provide a deadline.");
            newTask = new Deadline(input.substring(9, location - 1), input.substring(location + 4));
        } else if (input.startsWith("event ")) {
            int location = input.indexOf("/at ");
            if (location < 0) throw new DukeException("Please provide an event time.");
            newTask = new Event(input.substring(6, location - 1), input.substring(location + 4));
        } else {
            throw new DukeException("Please specify a type of task!");
        }

        return new AddCommand(newTask);
    }
}
