package duke.command;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {

    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String msg = "";
        if (input.length() > 7) {
            String[] stringArr = input.split(" ");
            if (Parser.isNumeric(stringArr[1])) {
                int taskId = Integer.parseInt(stringArr[1]) - 1;
                Task taskToBeDeleted = taskList.getTaskById(taskId);
                taskList.removeTaskById(taskId);
                msg = ui.taskRemovedMessage(taskToBeDeleted, taskList.getTotalNumberOfTask());
            }
        }
        return msg;
    }

}
