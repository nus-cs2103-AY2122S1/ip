package commands;

import ui.Ui;
import storage.Storage;
import task.Task;
import task.TaskList;
import commands.TaskType;
import duke.DukeException;

public class FindCommand extends Command{
    private TaskType type;
    private String commands;

    public FindCommand(TaskType type, String commands) {
        this.type = type;
        this.commands = commands;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        switch (type) {
            case FIND_BY_DATE: {
                Task[] tasksOnDate = tasks.tasksOnDate(commands);
                for (Task task : tasksOnDate) {
                    System.out.println(task);
                }
                break;
            }
            default: {
                break;
            }
        }
    }
}
