package duke.command;

import duke.UI;

import duke.task.TaskList;

public class DoneCommand extends Command{
    
    private final String userInput;
    private final TaskList tasks;

    public DoneCommand(String userInput, TaskList tasks) {
        this.userInput = userInput;
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        String[] inputs = this.userInput.split(" ");
        int ind = Integer.valueOf(inputs[1]) - 1;
        tasks.markDone(ind);
        String result = "Nice! I've marked this task as done:\n  " +
                tasks.get(ind).toString();
        System.out.println(UI.tabAndFormat(result));
    }
}
