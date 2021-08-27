package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.main.Ui;
import duke.main.Storage;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.main.TaskList;

public class EventCommand extends Command {
    private int byIndex;
    private String time;
    private String description;

    /**
     * {@inheritDoc}
     */
    public EventCommand(String userInput) throws DukeException {
        super(userInput);
        this.byIndex = this.getAtIndex();
        this.time = this.getTime();
        this.description = this.getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        //TaskList
        Task task = new Event(this.description, this.time);
        taskList.addTask(task);

        //Storage
        storage.save(taskList);

        //Ui
        ui.showAddTask(task);
        ui.showNumTask(taskList.getNumTask());
    }

    /**
     * {@inheritDoc}
     */
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
