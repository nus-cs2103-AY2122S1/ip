package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.ui.UiInterface;

/**
 * Class that handles the Sort command
 */
public class Sort extends Command {

    private final String[] words;

    /**
     * Constructs a Sort instance.
     *
     * @param words Array of strings representing the flag options
     */
    public Sort(String[] words) {
        this.words = words;
    }

    /**
     * Executes the Sort command.
     *
     * @param taskList Current list of tasks
     * @param ui Ui to interact with user
     * @param storage Storage that allows loading/saving
     * @throws DukeException if an error is encountered
     */
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

    /**
     * Returns if the command is an exit.
     *
     * @return boolean indicating if command is exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
