package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


public class FindCommand extends Command {
    private TaskType type;
    private String commands;

    /**
     * Constructor for FindCommand class
     *
     * @param type type of task.
     * @param commands command input by user.
     */
    public FindCommand(TaskType type, String commands) {
        this.type = type;
        this.commands = commands;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            switch (type) {
            case FIND_BY_DATE: {
                return findByDate(tasks, storage);
            }
            case FIND: {
                return findByKeyword(tasks, storage);
            }
            default: {
                return "";
            }
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }

    }

    private String findByDate(TaskList tasks, Storage storage) throws DukeException {
        String response = "";
        try {
            Task[] tasksOnDate = tasks.tasksOnDate(commands);
            for (Task task : tasksOnDate) {
                response += task + "\n";
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        return response;
    }

    private String findByKeyword(TaskList tasks, Storage storage) {
        String response = "";
        Task[] searchResults = tasks.findByKeyword(commands);
        for (Task task : searchResults) {
            response += task + "\n";
        }
        return response;
    }
}
