
package duke;

import java.util.Scanner;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DisplayCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.IncorrectCommand;


/**
 * To understand user's input.
 */
public class Parser {

    /**
     * Static method that allows input to be parsed into legitimate commands.
     *
     * @param input User's input string.
     * @return Most relevant command.
     */
    public static Command parse(String input) {
        Scanner sc = new Scanner(input);
        String keyword = sc.next();
        String msg = "";
        if (sc.hasNextLine()) {
            msg = sc.nextLine();
        }
        switch (keyword) {
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(msg);
        case (DoneCommand.COMMAND_WORD):
            return new DoneCommand(msg);
        case (ExitCommand.COMMAND_WORD):
            return new ExitCommand();
        case (DisplayCommand.COMMAND_WORD):
            return new DisplayCommand();
        case (AddCommand.ADD_EVENT):
            return new AddCommand(msg, "event");
        case (AddCommand.ADD_DEADLINE):
            return new AddCommand(msg, "deadline");
        case (AddCommand.ADD_TODO):
            return new AddCommand(msg, "todo");
        case (FindCommand.COMMAND_WORD):
            return new FindCommand(msg);
        default:
            return new IncorrectCommand();
        }
    }
}
