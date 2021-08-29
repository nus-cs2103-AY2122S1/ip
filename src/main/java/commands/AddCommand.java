package commands;

import exceptions.DukeException;
import viper.*;
import tasks.Deadlines;
import tasks.Events;
import tasks.Todos;

import java.io.IOException;

/**
 * for TODO, DEADLINE and EVENT commands
 */

public class AddCommand extends Command {
    String line;

    public AddCommand(String line) {
        this.line = line;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] splitLine = line.split(" ", 2);
        try {
            if (splitLine[1].isBlank()) {
                throw new DukeException("Oh no!! Something is missing from this " + splitLine[0] + " !!! ☹");
            }
            switch (Instruction.comparesTo(splitLine[0])) {
            case TODO:
                Todos addTodo = new Todos(splitLine[1].trim());
                tasks.addTask(addTodo);
                storage.writeTask(addTodo);
                String[] msg = {"Ok! I have added this task to your list:", addTodo.toString(),
                    "Now you have a total of " + tasks.getSize() + " task(s)!"};
                ui.showMessage(msg);
                break;
            case DEADLINE:
                String[] descDateArray = splitLine[1].split("/by ");
                
                Deadlines addDeadline = new Deadlines(descDateArray[0].trim(), descDateArray[1]);
                tasks.addTask(addDeadline);
                storage.writeTask(addDeadline);
                
                msg = new String[]{"Oh no!! A new deadline?! It's okay, you got this!!!!", addDeadline.toString(),
                        "Now you have a total of " + tasks.getSize() + " task(s)!"};
                ui.showMessage(msg);
                break;
            case EVENT:
                String[] descTimeArray = splitLine[1].split("/at ");
                String[] dateTimeArray = descTimeArray[1].split(" ");
                
                Events addEvent = new Events(descTimeArray[0].trim(), dateTimeArray[0], dateTimeArray[1]);
                tasks.addTask(addEvent);
                storage.writeTask(addEvent);
                
                msg = new String[]{"Okie! I have added this event to your list:", addEvent.toString(),
                    "Now you have a total of " + tasks.getSize() + " task(s)!"};
                ui.showMessage(msg);
                break;
            default:
                ui.showInvalidTypeError();
                break;
            }
        } catch (DukeException | IOException e) {
            e.printStackTrace();
        } 
        
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
