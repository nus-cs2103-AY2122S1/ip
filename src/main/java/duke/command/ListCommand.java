package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ListCommand extends Command {

    /**
     * Constructor for class ListCommand
     * @param userInput  user's input
     */
    public ListCommand(String userInput) {
        super(userInput);
    }

    /**
     * Generates the list of tasks
     *
     * @param tasks contains the task list
     * @param ui deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     */
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

    /**
     * Generates the list of tasks in a readable format
     *
     * @param pos current task position in the list
     * @param toDoListToPrint prior list of tasks to concat to
     * @param tasks list of tasks
     * @return list of tasks
     */
    public String addToStringToPrint(int pos, String toDoListToPrint, TaskList tasks) {
        Task currentTask = tasks.getTasks().get(pos);
        return toDoListToPrint + (pos + 1) + "." + currentTask.toString();
    }
}
