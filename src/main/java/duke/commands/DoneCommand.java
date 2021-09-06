package duke.commands;

import duke.tasks.Task;
import duke.utils.*;

public class DoneCommand extends Command{

    String commandString;

    public DoneCommand(String commandString){
        this.commandString = commandString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){

        String[] commandArr = commandString.split(" ");
        try {
            int taskNumberDone = Integer.parseInt(commandArr[1]);
            if (taskNumberDone > taskList.numberOfTasks()){
                System.out.println("Invalid number");
                return;
            } else {
                int taskDoneIndex = taskNumberDone-1;
                taskList.getTask(taskDoneIndex).makeDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(taskList.getTask(taskDoneIndex).toString());
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
