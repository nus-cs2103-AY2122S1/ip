package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * DeleteCommand class used to represent a Delete Command.
 * Contains methods that
 * (i)    executes the DeleteCommand
 */
public class DeleteCommand extends Command {
    public DeleteCommand() {
        super("delete");
    }

    /**
     * execute() method in DeleteCommand to Delete a task from the TaskList.
     *
     * @param des   the user input into the Duke chat-box.
     * @param tList the TaskList object used to keep track of all tasks.
     * @return String object to describe execution of DeleteCommand.
     * @throws DukeException if input for Delete command is not properly formatted.
     */
    @Override
    public String execute(String des, TaskList tList) throws DukeException {
        ArrayList<Task> tasks = tList.getTaskList();
        String sNum = des.substring(des.lastIndexOf(' ') + 1);
        int num = Integer.parseInt(sNum);
        if (num <= 0 || num > tasks.size()) {
            throw new DukeException("The input number is not a valid task number \n"
                    + "Please refer to the task list using the \"list\" command");
        } else if (this.countSpaces(des) > 1) {
            throw new DukeException("Too many arguments being provided to \"delete\" \n"
                    + "Please refer to proper usage of duke.commands with \"allCmd\"");
        } else {
            tList.remove(num);
            Storage.createFile();
            Storage.writeToFile(tList);
            return "Successfully removed task " + num + "\n";
        }
    }
}
