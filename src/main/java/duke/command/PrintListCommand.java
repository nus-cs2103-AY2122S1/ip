package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;

import duke.task.Task;
import duke.task.TaskList;

public class PrintListCommand extends Command {

    public PrintListCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        printList(taskList, ui);

    }

    @Override
    public boolean isExit() {
        return false;
    }

    private void printList(TaskList taskList, Ui ui) {
        StringBuilder outputList = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            outputList.append(String.format("%d.%s\n", i + 1, currentTask));
        }
        ui.printMessage(outputList.toString());
    }
}
