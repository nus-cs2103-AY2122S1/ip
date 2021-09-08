package duke.command;

import java.time.LocalDate;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 *  AddCommand class handles the commands that add tasks to the TaskList.
 */
public class AddCommand extends Command {

    static final String ADD_HEADER = "Alright! New task added:\n";
    private TaskType type;
    private String[] parameters;
    public enum TaskType { TODO, DEADLINE, EVENT }
    /**
     * Constructs the AddCommand object.
     *
     * @param type Task type.
     * @param parameters Strings of the different components of the task.
     */
    public AddCommand(TaskType type, String[] parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    /**
     * Adds task of TaskType type to the task list
     * and returns the response message.
     *
     * @param taskList The TaskList of Duke.
     * @param storage The Storage of Duke.
     * @return Response string.
     * @throws DukeException  If error occur during execution.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        switch (type) {
        case DEADLINE:
            assert parameters.length == 2 : "Wrong number of parameters";
            Deadline deadline = new Deadline(parameters[0].trim(), LocalDate.parse(parameters[1]));
            taskList.addTask(deadline);
            storage.saveList(taskList.convertToFileFormat());
            return ADD_HEADER + deadline + taskList.getListStatus();
        case EVENT:
            assert parameters.length == 2 : "Wrong number of parameters";
            Event event = new Event(parameters[0], LocalDate.parse(parameters[1]));
            taskList.addTask(event);
            storage.saveList(taskList.convertToFileFormat());
            return ADD_HEADER + event + taskList.getListStatus();
        case TODO:
            ToDo toDo = new ToDo(parameters[0]);
            taskList.addTask(toDo);
            storage.saveList(taskList.convertToFileFormat());
            return ADD_HEADER + toDo + taskList.getListStatus();
        default:
            throw new DukeException("Error");
        }
    }
}
