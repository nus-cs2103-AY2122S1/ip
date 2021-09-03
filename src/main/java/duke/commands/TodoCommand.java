package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Represents command to create todo
 */
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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //TaskList
        Task task = new Todo(this.description);
        taskList.addTask(task);

        //Storage
        storage.save(taskList);

        //Ui
        String output = "";
        output += ui.showAddTask(task) + "\n";
        output += ui.showNumTask(taskList.getNumTask());
        return output;
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
