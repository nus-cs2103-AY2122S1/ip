package duke;

public class Parser {
    public static Command parse(String input) throws DukeException {
        input = input.strip();
        String[] inputs = input.split(" ");
        String action = inputs[0];
        switch (action.toLowerCase()) {
        case "bye":
            return new Command.ExitCommand();
        case "list":
            return new Command.ListCommand();
        case "done":
            if (inputs.length == 1) {
                throw new DukeException("Insufficient input received! " +
                        "Please indicate the task number of the completed task.");
            } else {
                try {
                    int index = Integer.parseInt(inputs[1]);
                    return new Command.DoneCommand(index);
                } catch (NumberFormatException e) {
                    throw new DukeException("Invalid input! Please enter the task number after 'done'.");
                }
            }
        case "delete":
            if (inputs.length == 1) {
                throw new DukeException("Insufficient input received! "
                        + "Please indicate the task number of the task you wish to delete.");
            }
            try {
                int index = Integer.parseInt(inputs[1]);
                return new Command.DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid input! Please enter the task number after 'delete'.");
            }
        case "todo":
            return new Command.AddCommand(input, 0);
        case "deadline":
            return new Command.AddCommand(input, 1);
        case "event":
            return new Command.AddCommand(input, 2);
        default:
            return new Command.UnknowCommand();
        }
    }
}
