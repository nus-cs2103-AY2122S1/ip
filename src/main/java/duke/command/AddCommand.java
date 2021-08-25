package duke.command;

import java.time.LocalDate;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class AddCommand extends Command {

    private TaskType type;
    private String[] parameters;

    public enum TaskType {TODO, DEADLINE, EVENT}

    public AddCommand(TaskType type, String[] parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String messageHeader = "Alright! New task added:\n";
        switch (type) {
            case DEADLINE: {
                Deadline task = new Deadline(parameters[0].trim(), LocalDate.parse(parameters[1]));
                taskList.addTask(task);
                String message = messageHeader + task + taskList.getListStatus();
                ui.printMessage(message);
                break;
            }
            case EVENT: {
                Event task = new Event(parameters[0], LocalDate.parse(parameters[1]));
                taskList.addTask(task);
                String message = messageHeader + task + taskList.getListStatus();
                ui.printMessage(message);
                break;
            }
            case TODO: {
                ToDo task = new ToDo(parameters[0]);
                taskList.addTask(task);
                String message = messageHeader + task + taskList.getListStatus();
                ui.printMessage(message);
                break;
            }
        }
        storage.saveList(taskList.getTasks());
    }
}
