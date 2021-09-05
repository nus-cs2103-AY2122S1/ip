package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UiInterface;
import duke.exception.DukeException;
import duke.exception.InvalidFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

/**
 * Class that handles the Sort command
 */
public class Sort extends Command {

    private final String[] words;

    public Sort(String[] words) {
        this.words = words;
    }

    @Override
    public void execute(TaskList taskList, UiInterface ui, Storage storage) throws DukeException {
        if (this.words.length > 2) {
            throw new InvalidFormatException("`sort [Opt]${flag}`");
        }

        // If sort, then group sort Tasks
        // If sort -T, then sort Todos and likewise
        // If sort -Td, then sort Todos in descending and likewise
        String res;

        if (this.words.length == 1) {
            res = taskList.stringifyTasksForSort(i -> true, 0);
        } else {
            int order = 0;

            if (this.words[1].length() > 2 && this.words[1].charAt(2) == 'd') {
                order = 1;
            }

            switch (this.words[1].substring(1, 2)) {
            case "T":
                res = taskList.stringifyTasksForSort(i -> taskList.getTask(i) instanceof ToDo, order);
                break;
            case "E":
                res = taskList.stringifyTasksForSort(i -> taskList.getTask(i) instanceof Event, order);
                break;
            case "D":
                res = taskList.stringifyTasksForSort(i -> taskList.getTask(i) instanceof Deadline, order);
                break;
            default:
                throw new InvalidFormatException("allowed flags: T, E, D, Td, Ed, Dd");
            }
        }

        ui.print(res);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
