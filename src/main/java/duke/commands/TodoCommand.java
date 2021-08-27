package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.main.Ui;
import duke.main.Storage;
import duke.tasks.Task;
import duke.main.TaskList;
import duke.tasks.Todo;


public class TodoCommand extends Command {
    private String description;

    /**
     * {@inheritDoc}
     */
    public TodoCommand(String userInput) throws DukeException {
        super(userInput);
        this.description = this.getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        //TaskList
        Task task = new Todo(this.description);
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
