package duke;

import duke.command.*;
public class Parser {

    private enum CommandWord {
        TODO("todo"), DEADLINE("deadline"), EVENT("event"),
        DONE("done"), DELETE("delete"), BYE("bye"), LIST("list");
        private String value;
        private CommandWord(String value) {
            this.value = value;
        }
    }

    public static Command parse(String command) throws DukeException {
        String commandHolder = command.split(" ", 2)[0];

        for (Parser.CommandWord c : Parser.CommandWord.values()) {

            // Goes through the CommandWord Enum to check any matches with valid commands
            if (c.value.equals(commandHolder)) {
                switch (c) {
                case BYE: 
                    return new ExitCommand();
                    
                case LIST:
                    return new ListCommand(command);
                    
                case DONE:
                    return new DoneCommand(command);
                    
                case DELETE:
                    return new DeleteCommand(command);
                    
                case TODO:

                case DEADLINE:

                case EVENT:
                    return new AddCommand(command);
                }
            }
        }
        throw new DukeException("☹ oopsie!!! I'm sorry my dear but I can't do that for you");
    }
}
