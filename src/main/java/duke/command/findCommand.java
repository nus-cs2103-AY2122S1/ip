package duke.command;

import duke.task.TaskList;

import duke.Storage;

import duke.Ui;

/**
 * Represents the user command when the user wants to filter tasks that contain a matching word.
 */
public class findCommand extends Command {
    private String command;

    /**
     * Constructor for the findCommand class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public findCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String description of the class.
     */
    public String toString() {
        return "This is a find command";
    }

    /**
     * Executes the response when the user enters a 'find' command 
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.
     * @return String representation of the filtered tasks in the task list that contain the matching word 
     * entered by the user in the find command.
     */
    public String execute(TaskList taskList, Storage storage) {
        String[] parts = this.command.split(" ", 2);
        String wordToFind = parts[1];
        String response = Ui.findResponse(wordToFind, taskList);
        return response;
    }
}
