package duke.commands;

import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Class that is a subclass of Command class
 * and handles the behaviour of the Command for deleting a task
 */
public class DeleteCommand extends Command{


    private String commandString;

    public DeleteCommand(String commandString){
        this.commandString = commandString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){

        String[] commandArr = commandString.split(" ");
        try {
            int taskNumberToDelete = Integer.parseInt(commandArr[1]);
            if (taskNumberToDelete > taskList.numberOfTasks()){
                System.out.println("Invalid number");
                return;
            } else {
                int taskToDeleteIndex = taskNumberToDelete-1;
                Task tasktoDel = taskList.getTask(taskToDeleteIndex);
                taskList.removeTask(taskToDeleteIndex);
                System.out.println("Noted. I've removed this task: ");
                System.out.println("  " + tasktoDel.toString());

            }
        } catch (Exception e){
            System.out.println("Invalid input");
        }

    }

    @Override
    public boolean isExit(){
        return false;
    }

}
