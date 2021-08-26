package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

public class FilterTasksCommand extends Command {
    private String searchInput;

    public FilterTasksCommand(String arguments) throws Exception {
        if (arguments.length() == 0) {
            throw new Exception("Command `find` requires an argument");
        }
        this.searchInput = arguments;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        TaskList filteredTaskList = taskList.filterTasks(searchInput);

        StringBuilder builder = new StringBuilder();
        int numTasks = filteredTaskList.size();

        if (numTasks == 0) {
            ui.printMessage("Couldn't find any tasks matchin: " + searchInput);
        } else {
            builder.append("Here are the matching tasks:\n");

            for (int i = 0; i < numTasks; i++) {
                Task item = filteredTaskList.getTask(i);
                builder.append(i + 1);
                builder.append(". ");
                builder.append(item.toString());
                if (i < numTasks - 1) {
                    builder.append("\n");
                }
            }

            ui.printMessage(builder.toString());
        }
    }

    public boolean shouldExit() {
        return false;
    }
}
