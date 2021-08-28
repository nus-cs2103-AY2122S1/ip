package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Parser;
import duke.util.ToDoList;

/**
 * This class encapsulates the command dealing with finding tasks matching a keyword.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class FindCommand extends Command {
    private final ToDoList list;

    public FindCommand(ToDoList list) {
        this.list = list;
    }

    @Override
    public String getResponse(String input) {
        String keyword;
        try {
            keyword = Parser.extractKeyword(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
        ArrayList<Task> matchingTasks = list.searchList(keyword);
        String output = "Here are the matching tasks in your list:" + System.lineSeparator();

        for (int i = 1; i <= matchingTasks.size(); i++) {
            output = output.concat(String.format("  %d. %s", i, matchingTasks.get(i - 1)));
            output = output.concat(System.lineSeparator());
        }

        return output;
    }
}
