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
     * Returns String object to describe deletion of a task from the TaskList.
     *
     * @param des   User input into the Duke chat-box.
     * @param tList TaskList object used to keep track of all tasks.
     * @return String representation of DeleteCommand.
     * @throws DukeException If input for Delete command is not properly formatted.
     */
    @Override
    public String execute(String des, TaskList tList) throws DukeException {
        if (this.countSpaces(des) > 1) {
            throw new DukeException("Too many arguments being provided to \"delete\" \n"
                    + "Please refer to proper usage of duke.commands with \"allCmd\"");
        }
        checkValidDes(des);
        int taskRemoved = removeCommandByDescriptionFromTaskList(des, tList);
        Storage.createFile();
        Storage.writeToFile(tList);
        return "Successfully removed task " + taskRemoved + "\n";
    }

    /**
     * Returns int object to represent index of the command being deleted from a given task list.
     *
     * @param des User input into the Duke chat-box.
     * @param tList TaskList object used to keep track of all tasks.
     * @return Integer object representing index of command being deleted.
     * @throws DukeException If input number is not valid.
     */
    private int removeCommandByDescriptionFromTaskList(String des, TaskList tList) throws DukeException {
        ArrayList<Task> tasks = tList.getTaskList();
        String sNum = des.substring(des.lastIndexOf(' ') + 1);
        int num = Integer.parseInt(sNum);
        if (num <= 0 || num > tasks.size()) {
            throw new DukeException("The input number is not a valid task number \n"
                    + "Please refer to the task list using the \"list\" command");
        }
        tList.remove(num);
        return num;
    }
}
