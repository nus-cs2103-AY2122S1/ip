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
     * Executes todo command
     * @param taskList The object that holds a list of Task
     * @param ui The object responsible for updating Ui response
     * @param storage The object responsible to save/load list of task to/from hard disk
     * @return string to be printed out to user
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
     * Check if command is exit command
     * @return true if exit command
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
