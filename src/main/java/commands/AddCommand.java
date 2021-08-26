package commands;

import exceptions.DukeException;
import viper.*;
import tasks.Deadlines;
import tasks.Events;
import tasks.Todos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
                int posBy = splitLine[1].indexOf("by") + 3;
                int posSlash = splitLine[1].indexOf("/") - 1;
                String desc = splitLine[1].substring(0, posSlash).trim();
                String date = splitLine[1].substring(posBy).trim();
                try {
                    String formattedDate = LocalDate.parse(date).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    Deadlines addDeadline = new Deadlines(desc, formattedDate);
                    tasks.addTask(addDeadline);
                    storage.writeTask(addDeadline);
                    msg = new String[]{"Oh no!! A new deadline?! It's okay, you got this!!!!", addDeadline.toString(), 
                        "Now you have a total of " + tasks.getSize() + " task(s)!"};
                    ui.showMessage(msg);
                } catch (Exception e) {
                    msg = new String[]{"Oh no! " + e.getMessage(),
                            "Something went wrong......",
                            "Please make sure that your date format is in yyyy-MM-dd"};
                    ui.showMessage(msg);
                }
                break;
            case EVENT:
                int posAt = splitLine[1].indexOf("at") + 3;
                posSlash = splitLine[1].indexOf("/") - 1;
                desc = splitLine[1].substring(0, posSlash);
                String time = splitLine[1].substring(posAt);
                Events addEvent = new Events(desc, time);
                tasks.addTask(addEvent);
                storage.writeTask(addEvent);
                msg = new String[]{"Okie! I have added this event to your list:", addEvent.toString(),
                    "Now you have a total of " + tasks.getSize() + " task(s)!"};
                ui.showMessage(msg);
                break;
            case INVALID:
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
