package duke;

import duke.command.*;
import java.util.Scanner;

public class Parser {

    public Parser() {
    }

    public static Command parse(String input) {
        Scanner sc = new Scanner(input);
        String keyword = sc.next();
        String msg = "";
        if (sc.hasNextLine()) {
            msg = sc.nextLine();

        }

        switch (keyword) {
            case (DeleteCommand.COMMAND_WORD):
                return new DeleteCommand(msg);
            case (DoneCommand.COMMAND_WORD):
                return new DoneCommand(msg);
            case (ExitCommand.COMMAND_WORD):
                return new ExitCommand();
            case (DisplayCommand.COMMAND_WORD):
                return new DisplayCommand();
            case ("event"):
                return new AddCommand(msg, "event");
            case ("deadline"):
                return new AddCommand(msg, "deadline");
            case ("todo"):
                return new AddCommand(msg, "todo");
        }

        return new IncorrectCommand();

    }
}