package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DoneCommand extends Command {
    public DoneCommand() {
        super("done");
    }

    /**
     * The execute() method in doneCommand marks a given Task as completed.
     *
     * @param des the user input into the Duke chat-box.
     * @throws DukeException if done command is not formatted properly
     *                       or if task has already been completed.
     */
    public void execute(String des, TaskList tList) throws DukeException {
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
                System.out.println("I see that you have completed a task. Keep up the good work!");
                System.out.println();
                System.out.println("This task has now been marked as completed");
                System.out.println(atHand.getStatusIcon() + " " + atHand.getDescription());
            }
        }
        Storage.createFile();
        Storage.writeToFile(tList);
    }
}
