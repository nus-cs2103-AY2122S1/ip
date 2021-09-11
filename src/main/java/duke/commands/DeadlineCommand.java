package duke.commands;

import duke.tasks.DeadlineTask;
import duke.tasks.TodoTask;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class DeadlineCommand extends Command{

    private String commandString;

    public DeadlineCommand(String commandString){
        this.commandString = commandString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        String[] dateArray = commandString.split("/");
        String date = "";
        for(int i=1; i< dateArray.length; i++){
            if (i==1){
                date+=dateArray[1].substring(3);
            } else {
                date += "/" + dateArray[i];
            }
        }
        DeadlineTask newDeadline = new DeadlineTask(commandString.substring(9).split("/")[0], date);
        taskList.addTask(newDeadline);

        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + newDeadline.toString());
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
