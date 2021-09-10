package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * DoneCommand class used to represent a Done Command.
 * Contains methods that
 * (i)    executes the DoneCommand
 */
public class DoneCommand extends Command {
    public DoneCommand() {
        super("done");
    }

    /**
     * Returns String object to describe a specific task being marked as done.
     *
     * @param des   User input into the Duke chat-box.
     * @param tList TaskList object used to keep track of all tasks.
     * @return String representation of DoneCommand.
     * @throws DukeException If input for Delete command is not properly formatted.
     */
    @Override
    public String execute(String des, TaskList tList) throws DukeException {
        checkValidDes(des);
        Task atHand = markTaskAsDone(des, tList);
        Storage.createFile();
        Storage.writeToFile(tList);
        return "I see that you have completed a task. Keep up the good work!\n"
                + "\n"
                + "This task has now been marked as completed\n"
                + atHand.getStatusIcon()
                + " " + atHand.getDescription()
                + "\n";
    }

    /**
     * Returns Task object that is marked as done.
     *
     * @param des User input into the Duke chat-box.
     * @param tList TaskList object used to keep track of all tasks.
     * @return Task object marked as done.
     * @throws DukeException If task has been completed, too many arguments are provided,
     * or if input number is not valid.
     */
    private Task markTaskAsDone(String des, TaskList tList) throws DukeException {
        ArrayList<Task> tasks = tList.getTaskList();
        String stringNumber = des.substring(des.lastIndexOf(' ') + 1);
        int num = Integer.parseInt(stringNumber);
        if (num <= 0 || num > tasks.size()) {
            throw new DukeException("The input number is not a valid task number \n"
                    + "Please refer to the task list using the \"list\" command");
        }
        if (this.countSpaces(des) > 1) {
            throw new DukeException("Too many arguments being provided to \"done\" \n"
                    + "Please refer to proper usage of commands with \"allCmd\"");
        }
        Task atHand = tasks.get(num - 1);
        if (atHand.getIsDone()) {
            throw new DukeException("You have already completed this task.");
        }
        atHand.markAsDone();
        return atHand;
    }
}
