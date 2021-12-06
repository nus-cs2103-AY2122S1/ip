package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class PrintListCommand extends Command {

    public PrintListCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return printList(taskList, ui);

    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String printList(TaskList taskList, Ui ui) {
        StringBuilder outputList = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            outputList.append(String.format("%d.%s\n", i + 1, currentTask));
        }

        return ui.getDukeMessage(outputList.toString());
    }
}
