package duke.commands;

import duke.tasks.TodoTask;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;


/**
 * Class that is a subclass of Command class
 * and handles the behaviour of the Command for todotasks
 */
public class TodoCommand extends Command {

    private String commandString;

    public TodoCommand(String commandString) {
        this.commandString = commandString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TodoTask newTodo = new TodoTask(commandString.substring(5));
        //duke.tasks.add(newTask);
        taskList.addTask(newTodo);
        //taskCounter++;
        ui.printResponse("Got it. I've added this task: ");
        ui.printResponse("  " + newTodo.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
