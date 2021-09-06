package duke.commands;

import duke.tasks.EventTask;
import duke.tasks.TodoTask;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

public class EventCommand extends Command{


    private String commandString;

    public EventCommand(String commandString){
        this.commandString = commandString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){

        String[] dateArr = commandString.split("/");
        String date = "";
        for(int i=1; i< dateArr.length; i++){
            if (i==1){
                date+=dateArr[1].substring(3);
            } else {
                date += "/" + dateArr[i];
            }
        }

        String at = commandString.split("/")[1].substring(3);
        EventTask newEvent = new EventTask(commandString.substring(6).split("/")[0], date);
        taskList.addTask(newEvent);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + newEvent.toString());

    }

    @Override
    public boolean isExit(){
        return false;
    }
}
