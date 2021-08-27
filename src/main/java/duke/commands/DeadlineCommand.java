package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.main.Ui;
import duke.main.Storage;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.main.TaskList;

public class DeadlineCommand extends Command {
    private int byIndex;
    private String time;
    private String description;

    public DeadlineCommand(String userInput) throws EmptyDescriptionException, EmptyTimeException {
        super(userInput);
        this.byIndex = this.getByIndex();
        this.time = this.getTime();
        this.description = this.getDescription();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        //TaskList
        Task newTask = new Deadline(this.description, this.time);
        taskList.addTask(newTask);

        //Storage
        storage.save(taskList);

        //Ui
        ui.showAddTask(newTask);
        ui.showNumTask(taskList.getNumTask());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String getTime() throws EmptyTimeException {
        String time = String.join(" ", userInputList.subList(this.byIndex + 1, userInputList.size()));

        if (time.equals("")) {
            throw new EmptyTimeException("deadline");
        } else {
            return time;
        }
    }

    private String getDescription() throws EmptyDescriptionException {
        //Get description
        String description = String.join(" ", userInputList.subList(1, byIndex));
        if (description.equals("")) {
            throw new EmptyDescriptionException("deadline");
        } else {
            return description;
        }
    }

    private int getByIndex() {
        return userInputList.indexOf("/by");
    }
}
