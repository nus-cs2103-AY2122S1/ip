package ponyo.parser;

import ponyo.data.exceptions.PonyoException;
import ponyo.commands.Command;
import ponyo.commands.ListCommand;
import ponyo.commands.AddCommand;
import ponyo.commands.DeleteCommand;
import ponyo.commands.DoneCommand;
import ponyo.commands.ExitCommand;

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
                    throw new PonyoException("Invalid command given!");
            }
        } catch (PonyoException e) {
            throw new PonyoException(MESSAGE_IDK);
        }

    }
}
