public class Parser {
    private static final String MESSAGE_IDK = "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    public static Command parse(String cmd) {
        String[] cmds = cmd.split(" ", 2);

        try {
            switch (cmds[0]) {
                case "list":
                    return new ListCommand();
                case "bye":
                    return new ExitCommand();
                case "done":
                    return new DoneCommand(Integer.parseInt(cmds[1]));
                case "todo":
                case "deadline":
                case "event":
                    return new AddCommand(cmds);
                case "delete":
                    return new DeleteCommand(Integer.parseInt(cmds[1]));
                default:
                    throw new DukeException("Invalid command given!");
            }
        } catch (DukeException e) {
            throw new DukeException(MESSAGE_IDK);
        }

    }
}
