package duke;//duke.Parser: deals with making sense of the user command

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
    public static boolean parse(String command) throws DukeException, IOException {
        if (command.contains("bye")) {
            return true;
        }
        if (command.startsWith("list")) {
            Duke.getTaskList();
        } else if (command.startsWith("done")) {
            int i = Integer.valueOf(command.substring(5));
            Duke.markDone(i);
        } else if (command.contains("delete")) {
            int i = Integer.valueOf(command.substring(7));
            System.out.println(i);
            Duke.deleteTask(i);
        } else if (command.startsWith("todo")) {
            Duke.toDo(command);
        } else if (command.startsWith("deadline")) {
            Duke.deadline(command);
        } else if (command.startsWith("event")) {
            Duke.event(command);
        } else if (command.startsWith("find")) {
            Duke.findTask(command);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n");
        }
        return false;
    }
}