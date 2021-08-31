package commands;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Event;

public class AddEventCommand extends AddTaskCommand {

    String time;

    public AddEventCommand(String desc, Boolean isDone, String time) {
        super(desc, isDone);
        this.time = time;
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            Event newEvent = new Event(this.desc, this.isDone, this.time);
            taskList.addTask(newEvent);
            storage.writeTasksToFile(taskList, storage.getTaskFile());
            return ui.getAddTaskResponse(newEvent);
        } catch (IOException e) {
            return ui.getFileWriteFailResponse(storage.getTaskFile());
        }
    }
}
