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
     * execute() method in DoneCommand to mark a task as done in the TaskList.
     *
     * @param des   the user input into the Duke chat-box.
     * @param tList the TaskList object used to keep track of all tasks.
     * @return String object to describe execution of DoneCommand.
     * @throws DukeException if input for Delete command is not properly formatted.
     */
    public String execute(String des, TaskList tList) throws DukeException {
        ArrayList<Task> tasks = tList.getTaskList();
        String sNum = des.substring(des.lastIndexOf(' ') + 1);
        int num = Integer.parseInt(sNum);
        if (num <= 0 || num > tasks.size()) {
            throw new DukeException("The input number is not a valid task number \n"
                    + "Please refer to the task list using the \"list\" command");
        } else if (this.countSpaces(des) > 1) {
            throw new DukeException("Too many arguments being provided to \"done\" \n"
                    + "Please refer to proper usage of commands with \"allCmd\"");
        } else {
            Task atHand = tasks.get(num - 1);
            if (atHand.getIsDone()) {
                throw new DukeException("You have already completed this task.");
            } else {
                atHand.markAsDone();
                Storage.createFile();
                Storage.writeToFile(tList);
                return "I see that you have completed a task. Keep up the good work!\n"
                        + "\n"
                        + "This task has now been marked as completed\n"
                        + atHand.getStatusIcon()
                        + " " + atHand.getDescription()
                        + "\n";
            }
        }
    }
}
