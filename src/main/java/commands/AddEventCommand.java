package commands;

import java.io.IOException;

import duke.*;
import tasks.*;
import exceptions.*;

public class AddEventCommand extends AddTaskCommand {

    String time;

    public AddEventCommand(String desc, Boolean isDone, String time) {
        super(desc, isDone);
        this.time = time;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        Event newEvent = new Event(this.desc, this.isDone, this.time);
        taskList.addTask(newEvent);
        ui.printAddTask(newEvent);
        try {
            storage.writeTasksToFile(taskList, storage.getTaskFile());
        } catch (IOException e) {
            ui.printFileWriteFail(storage.getTaskFile());
        }
    }
}
