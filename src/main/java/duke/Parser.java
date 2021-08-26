package duke;

import duke.command.*;

/**
 * Parses the input given by the user for the task manager application to
 * execute the necessary action.
 */
public class Parser {
    private String input;

    /**
     * Returns the command to be executed in response to the user's command.
     *
     * @param command Input by the user.
     * @return
     */
    public Command parse(String command) {
        if(command.equals("bye")) {
            return new ExitCommand(command);
        } else if(command.equals("list")) {
            return new ListCommand(command);
        } else if(command.equals("blah")) {
            return new BlahCommand(command);
        } else if(command.split(" ")[0].equals("done")) {
            String taskNumber = command.split(" ")[1];
            return new DoneCommand(taskNumber);
        } else if(command.split(" ")[0].equals("delete")) {
            String taskNumber = command.split(" ")[1];
            return new DeleteCommand(taskNumber);
        } else if(command.split(" ", 2)[0].equals("find")) {
            String input = command.split(" ", 2)[1];
            return new FindCommand(input);
        }
        else {
            return new AddCommand(command);
        }
    }

}
