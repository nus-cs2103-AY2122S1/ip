package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.task.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * Represents the user command when the user wants to filter tasks that contain a common matching word.
 */
public class FindCommand extends Command {
    private String command;

    /**
     * Represents a constructor for the FindCommand class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public FindCommand(String command) {
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
     * Executes the response when the user enters a find command 
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.
     * @return String representation of the filtered tasks in the task list that contain the matching word 
     * entered by the user in the find command.
     * @throws DukeException If user doesn't provide a description for the find command.
     */
    public String execute(TaskList taskList, Storage storage) throws EmptyDescriptionException {
        if (command.trim().length() <= 4) {
            throw new EmptyDescriptionException();
        }
        
        String[] parts = this.command.split(" ", 2);
        String wordToFind = parts[1];
        
        Ui ui = new Ui(taskList, storage);
        String response = ui.findResponse(wordToFind, taskList);
        return response;
    }
}
