package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;

import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {

    private String query;

    public FindCommand(String userInput) {
        super(userInput);
        this.query = userInput.split(" ", 2)[1];
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        StringBuilder outputList = new StringBuilder();
        outputList.append("Here are the matching tasks in your list:\n");
        int counter = 1;
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);

            if(currentTask.doesDescriptionContainString(this.query)) {
                outputList.append(String.format("%d.%s\n", counter, currentTask));
                counter++;
            }
        }
        ui.printMessage(outputList.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
