package duke;

import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.AddCommand;
import duke.command.AddNoteCommand;
import duke.command.ListNoteCommand;
import duke.command.DeleteNoteCommand;
import duke.command.BlahCommand;
import duke.command.HelpCommand;

/**
 * Parses the input given by the user for the task manager application to execute the necessary action.
 */
public class Parser {

    /**
     * Returns the command to be executed in response to the user's command.
     *
     * @param command Input by the user.
     * @return Parsed command.
     */
    public static Command parse(String command) {
        String firstWord = command.split(" ")[0];
        if(command.equals("bye")) {
            return new ExitCommand(command);
        } else if(command.equals("list")) {
            return new ListCommand(command);
        } else if(firstWord.equals("done")) {
            String taskNumber = command.split(" ")[1];
            return new DoneCommand(taskNumber);
        } else if(firstWord.equals("delete")) {
            String taskNumber = command.split(" ")[1];
            return new DeleteCommand(taskNumber);
        } else if(firstWord.equals("find")) {
            String keyword = command.split(" ", 2)[1];
            return new FindCommand(keyword);
        } else if(firstWord.equals("todo") || firstWord.equals("event") || firstWord.equals("deadline")) {
            return new AddCommand(command);
        } else if(firstWord.equals("note")) {
            return new AddNoteCommand(command);
        } else if(command.equals("n/list")) {
            return new ListNoteCommand(command);
        } else if(firstWord.equals("n/delete")){
            String noteNumber = command.split(" ")[1];
            return new DeleteNoteCommand(noteNumber);
        } else if(command.equals("help")) {
            return new HelpCommand(command);
        } else {
            return new BlahCommand(command);
        }
    }
}
