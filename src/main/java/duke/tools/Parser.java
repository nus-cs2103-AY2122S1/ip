package duke.tools;

import duke.Duke;
import duke.dukeException.DukeException;

import java.io.IOException;

/**
 * Parser is used to handle user inputs.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class Parser {
    /**
     * Method to handle the user inputs.
     * @param command Takes in a command from the user
     * @return A boolean value  to check if user terminated the bot
     * @throws DukeException Handles Duke Exception
     * @throws IOException Handles file errors
     */
    public static String parse(String command) throws DukeException, IOException {
        if (command.contains("bye")) {
            return "Bye. Hope to see you again soon!\n";
        }
        if (command.startsWith("list")) {
            return Duke.getTaskList();
        } else if (command.startsWith("done")) {
            int i = Integer.valueOf(command.substring(5));
            return Duke.markDone(i);
        } else if (command.contains("delete")) {
            int i = Integer.valueOf(command.substring(7));
            return Duke.deleteTask(i);
        } else if (command.startsWith("todo")) {
            return Duke.toDo(command);
        } else if (command.startsWith("deadline")) {
            return Duke.deadline(command);
        } else if (command.startsWith("event")) {
            return Duke.event(command);
        } else if (command.startsWith("find")) {
            return Duke.findTask(command);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n");
        }
    }
}