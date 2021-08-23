package duke.command;

import duke.UI;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    private final String userInput;
    private final TaskList tasks;

    public DeleteCommand(String userInput, TaskList tasks) {
        this.userInput = userInput;
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        String[] inputs = this.userInput.split(" ");
        int ind = Integer.valueOf(inputs[1]) - 1;

        String result = "Noted. I've removed this task:\n  " + tasks.get(ind).toString() + "\n";
        tasks.delete(ind);
        result += "Now you have " + tasks.size() + " tasks in the list.";
        System.out.println(UI.tabAndFormat(result));
    }

}
