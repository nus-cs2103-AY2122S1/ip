package jackie;

import jackie.command.Command;

/**
 * A class with method to interpret the commands from user input.
 * The parse method will return the interpretation in the form of a Command.
 *
 * @author Gu Geng
 */
public class Parser {

    /**
     * A collection of command words that is used to specify the user input command.
     *
     * @author Gu Geng
     */
    private enum CommandWord {
        TODO("todo"), DEADLINE("deadline"), EVENT("event"), FIND("find"),
        DONE("done"), DELETE("delete"), BYE("bye"), LIST("list"), UNDO("undo");
        private String value;
        private CommandWord(String value) {
            this.value = value;
        }
    }

    /**
     * Interprets the user input in terms of a String and returns the information in the form of a Command.
     *
     * @param command A String that contains the user input.
     * @return A Command that is corresponding to the specific user input of command.
     * @throws JackieException Will be thrown should the user input command are not in the correct formats.
     */
    public static Command parse(String... command) throws JackieException {
        String commandHolder = command[0];
        for (Parser.CommandWord c : Parser.CommandWord.values()) {
            // Goes through the CommandWord Enum to check any matches with valid commands
            if (c.value.equals(commandHolder)) {
                switch (c) {
                case BYE:
                    return new jackie.command.ExitCommand();
                case LIST:
                    return new jackie.command.ListCommand(command);
                case DONE:
                    return new jackie.command.DoneCommand(command);
                case DELETE:
                    return new jackie.command.DeleteCommand(command);
                case FIND:
                    return new jackie.command.FindCommand(command);
                case UNDO:
                    return new jackie.command.UndoCommand(command);
                case TODO:
                case DEADLINE:
                case EVENT:
                    return new jackie.command.AddCommand(command);
                default:
                    continue;
                }
            }
        }
        throw new JackieException("D: oopsie!!! I'm sorry my dear but I can't do that for you");
    }
}
