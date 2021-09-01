package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import duke.Storage;

import duke.Ui;

/**
 * Represents the user command when the user is done with a task.
 */
public class doneCommand extends Command {
    private String command;

    /**
     * Constructor for the doneCommand class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public doneCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String description of the class.
     */
    public String toString() {
        return "This is a done command";
    }

    /**
     * Executes the response when the user enters a 'done' command and updates the task list and storage 
     * file (duke.txt)
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.            
     */
    public void execute(TaskList taskList, Storage storage) {
        int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
        if (value > taskList.getSize()) {
            System.out.println("Sorry the task doesn't exist yet, please try again!");
        } else {
            Task t = taskList.getTask(value - 1);
            t.markAsDone();
            Ui.doneResponse(t);
            storage.writeToFile("./duke.txt", taskList);
        }
    }
}
