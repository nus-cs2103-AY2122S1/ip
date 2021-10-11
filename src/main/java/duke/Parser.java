package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.WrongCommand;


public class Parser {
    private final TaskList dukeList;
    private final Data data;

    /**
     * Constructor for Parser
     * @param dukeList Lists of Tasks taken in.
     * @param data Data object.
     */
    public Parser(TaskList dukeList, Data data) {
        this.dukeList = dukeList;
        this.data = data;
    }

    /**
     * Interpret what the user has entered as an input and categorises it into a Command.
     * @param input User's input
     * @return The correct command that is interpreted from the user input.
     */
    public Command inputToCommand(String input) {
        String[] inputs = input.split(" ", 2);
        String command = inputs[0].toLowerCase();
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand(dukeList);
        case "delete":
            return new DeleteCommand(dukeList);
        case "done":
            return new DoneCommand(dukeList);
        case "find":
            return new FindCommand(dukeList);
        case "deadline":
        case "event":
        case "todo":
            return new AddCommand(dukeList, data, command);
        default:
            //If no cases above are entered,Duke will not understand the command and prompt the user.
            return new WrongCommand();
        }
    }

}
