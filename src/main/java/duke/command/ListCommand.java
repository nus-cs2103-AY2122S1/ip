package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ListCommand extends Command {

    public ListCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTasks().isEmpty()) {
            ui.reply("There are no tasks to complete!");
        } else {
            String toDoListToPrint = "";
            for (int pos = 0; pos < tasks.getTasks().size(); pos++) {
                if (pos == tasks.getTasks().size() - 1) {
                    toDoListToPrint = addToStringToPrint(pos, toDoListToPrint, tasks);
                } else {
                    toDoListToPrint = addToStringToPrint(pos, toDoListToPrint, tasks) + "\n";
                }
            }
            ui.reply(toDoListToPrint);
        }
    }

    public String addToStringToPrint(int pos, String toDoListToPrint, TaskList tasks) {
        Task currentTask = tasks.getTasks().get(pos);
        return toDoListToPrint + (pos + 1) + "." + currentTask.toString();
    }
}
