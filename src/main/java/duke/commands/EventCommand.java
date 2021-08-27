package commands;

import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import exceptions.EmptyTimeException;
import main.Ui;
import main.Storage;
import tasks.Event;
import tasks.Task;
import main.TaskList;

public class EventCommand extends Command {
    private int byIndex;
    private String time;
    private String description;


    public EventCommand(String userInput) throws EmptyDescriptionException, EmptyTimeException {
        super(userInput);
        this.byIndex = this.getAtIndex();
        this.time = this.getTime();
        this.description = this.getDescription();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        //TaskList
        Task task = new Event(this.description, this.time);
        taskList.addTask(task);

        //Storage
        storage.save(taskList);

        //Ui
        ui.showAddTask(task);
        ui.showNumTask(taskList.getNumTask());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String getTime() throws EmptyTimeException {
        String time = String.join(" ", userInputList.subList(this.byIndex + 1, userInputList.size()));

        if (time.equals("")) {
            throw new EmptyTimeException("event");
        } else {
            return time;
        }
    }

    private String getDescription() throws EmptyDescriptionException {
        //Get description
        String description = String.join(" ", userInputList.subList(1, byIndex));
        if (description.equals("")) {
            throw new EmptyDescriptionException("event");
        } else {
            return description;
        }
    }

    private int getAtIndex() {
        return userInputList.indexOf("/at");
    }
}
