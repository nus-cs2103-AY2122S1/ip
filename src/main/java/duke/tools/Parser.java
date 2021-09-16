package duke.tools;

import duke.Duke;
import duke.dukeException.DukeException;

import java.io.IOException;

/**
 * A Parser program that is used to handle user inputs.
 *
 * @author Erwin Quek
 * @version CS2103 AY21/22 Sem 1
 */
public class Parser {
    /**
     * Returns a String message that corresponds to the user's commands.
     * @param command Takes in a command from the user.
     * @return A String message that associates with the input command.
     * @throws DukeException Handles Duke Exception.
     * @throws IOException Handles file errors.
     */
    public static String parse(String command) throws DukeException, IOException {
        if (command.contains("bye")) {
            assert command.contains("bye");
            return "Bye. Hope to see you again soon!";
        }
        if (command.startsWith("list")) {
            assert command.contains("list");
            return Duke.getTaskList();
        } else if (command.startsWith("done")) {
            assert command.contains("done");
            if (command.trim().equals("done")) {
                throw new DukeException("OOPS!!! The description of done cannot be empty.\n "
                        + "Please follow this format: \n" + "    done {task index}");
            }
            int i = Integer.valueOf(command.substring(5));
            return Duke.markDone(i);
        } else if (command.contains("delete")) {
            assert command.contains("delete");
            if (command.trim().equals("delete")) {
                throw new DukeException("OOPS!!! The description of delete cannot be empty.\n "
                        + "Please follow this format: \n" + "    delete {task index}");
            }
            int i = Integer.valueOf(command.substring(7));
            return Duke.deleteTask(i);
        } else if (command.startsWith("todo")) {
            assert command.contains("todo");
            return Duke.toDo(command);
        } else if (command.startsWith("deadline")) {
            assert command.contains("deadline");
            return Duke.deadline(command);
        } else if (command.startsWith("event")) {
            assert command.contains("event");
            return Duke.event(command);
        } else if (command.startsWith("find")) {
            assert command.contains("find");
            if (command.trim().equals("find")) {
                throw new DukeException("OOPS!!! The description of find cannot be empty.\n "
                        + "Please follow this format: \n" + "    find {key}");
            }
            return Duke.findTask(command);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}