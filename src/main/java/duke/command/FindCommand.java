package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {

    private String query;

    /**
     * Constructor for FindCommand
     * @param userInput String containing the user input
     */
    public FindCommand(String userInput) {
        super(userInput);
        assert userInput.split(" ", 2).length == 2
                : "User input should be in the format find (query)";
        this.query = userInput.split(" ", 2)[1];
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        StringBuilder outputList = new StringBuilder();
        outputList.append("Here are the matching tasks in your list:\n");
        int counter = 1;
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);

            if (currentTask.doesDescriptionContain(this.query)) {
                outputList.append(String.format("%d.%s\n", counter, currentTask));
                counter++;
            }
        }
        return ui.getDukeMessage(outputList.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
