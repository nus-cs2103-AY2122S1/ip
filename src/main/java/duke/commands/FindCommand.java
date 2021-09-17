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
                return findByDate(tasks);
            }
            case FIND: {
                return findByKeyword(tasks);
            }
            default: {
                return "";
            }
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }

    }

    private String findByDate(TaskList tasks) throws DukeException {
        String response = "Here are the tasks on that date:\n";
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

    private String findByKeyword(TaskList tasks) {
        String response = "Here are the matching results:\n";
        Task[] searchResults = tasks.findByKeyword(commands);
        for (Task task : searchResults) {
            response += task + "\n";
        }
        return response;
    }
}
