package commands;

import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import main.Ui;
import main.Storage;
import tasks.Task;
import main.TaskList;
import tasks.Todo;


public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String userInput) throws EmptyDescriptionException {
        super(userInput);
        this.description = this.getDescription();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        //TaskList
        Task task = new Todo(this.description);
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

    private String getDescription() throws EmptyDescriptionException {
        //Get description
        String description = String.join(" ",
                userInputList.subList(1, userInputList.size()));
        if (description.equals("")) {
            throw new EmptyDescriptionException("todo");
        } else {
            return description;
        }
    }
}
