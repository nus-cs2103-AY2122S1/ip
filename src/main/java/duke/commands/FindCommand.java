package duke.commands;

import duke.exceptions.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.Locale;

/**
 * Class that is a subclass of Command class
 * and handles the behaviour of the Command for
 * finding tasks with keywords
 */
public class FindCommand extends Command{

    String commandString;

    public FindCommand(String commandString){
        this.commandString = commandString;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        String[] commandArr = commandString.split(" ");

        boolean foundAnyMatch = false;
        int numberofMatches = 0;

        for(int i = 0; i < taskList.numberOfTasks(); i++){

            boolean currTaskPrinted = false;

            // loop again for each keyword in input
            for(int word = 1; word < commandArr.length; word++){
                if(taskList.getTask(i).getName().toLowerCase().contains(commandArr[word].toLowerCase())) {
                    if (!foundAnyMatch) {
                        ui.printResponse("Here are the matching tasks:");
                    }
                    foundAnyMatch = true;
                    numberofMatches++;
                    if(!currTaskPrinted){
                        ui.printResponse((numberofMatches) + "." + taskList.getTask(i).toString());
                        currTaskPrinted = true;
                    }

                }
            }
        }
        if (!foundAnyMatch){
            ui.printResponse("Sorry, we are unable to find any matching tasks");
        }


    }

    @Override
    public boolean isExit(){
        return false;
    }
}
