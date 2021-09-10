package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.DoAfter;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class DoAfterCommand extends Command {
    public DoAfterCommand() {
        super("doafter");
    }

    /**
     * Returns String object to describe execution of DoAfterCommand.
     *
     * @param des User input into the Duke chat-box.
     * @param tList TaskList object used to keep track of all tasks.
     * @return String representation of DoAfterCommand.
     * @throws DukeException If input for doafter command is not properly formatted.
     */
    @Override
    public String execute(String des, TaskList tList) throws DukeException {
        checkValidDes(des);
        if (des.equals("doafter")) {
            throw new DukeException("\"doafter\" command not correctly formatted \n"
                    + "Please insert task description and previous task number");
        }
        if (des.chars().filter(c -> c == '/').count() > 1) {
            throw new DukeException("Format Error. "
                    + "Do not use the special character \"/\" within your task description.");
        }
        try {
            DoAfter atHand = addDoAfterFromDescriptionToTaskList(des, tList);
            Storage.createFile();
            Storage.writeToFile(tList);
            return "Sure. The following task has been added: \n"
                    + atHand.formatString()
                    + "\n"
                    + "\n"
                    + numberOfTasks(tList)
                    + "\n";
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("\"doafter\" command not correctly formatted");
        }
    }

    /**
     * Returns description of previous task based on its String representation.
     *
     * @param previousTask String representation of previous task.
     * @return String representing description of previous task.
     * @throws DukeException If the taskType belongs to none of the cases.
     */
    private String extractPrevTaskDescription(String previousTask) throws DukeException {
        String taskType = previousTask.substring(1, 2);
        String des;
        int openBracket;
        switch (taskType) {
        case "T":
            des = previousTask.substring(7);
            break;
        case "E": //Fallthrough
        case "D": //Fallthrough
        case "A":
            openBracket = previousTask.indexOf('(');
            des = previousTask.substring(7, openBracket - 1);
            break;
        default:
            throw new DukeException("Unable to load DoAfter task into Duke as type of previous task cannot be read");
        }
        return des;
    }

    /**
     * Returns DoAfter object after adding it into the given task list.
     *
     * @param des User input into the Duke chat-box.
     * @param tList TaskList object used to keep track of all tasks.
     * @return DoAfter task that is the most recent addition to tList.
     * @throws DukeException If input number is not valid.
     */
    private DoAfter addDoAfterFromDescriptionToTaskList(String des, TaskList tList) throws DukeException {
        String description = des.substring(8, des.indexOf('/') - 1);
        String stringNumber = des.substring(des.lastIndexOf(' ') + 1);
        int num = Integer.parseInt(stringNumber);
        if (num <= 0 || num > tList.getTaskList().size()) {
            throw new DukeException("The input number is not a valid task number \n"
                    + "Please refer to the task list using the \"list\" command");
        }
        ArrayList<Task> tasks = tList.getTaskList();
        String previousTask = tasks.get(num - 1).toString();
        DoAfter atHand = new DoAfter(description, extractPrevTaskDescription(previousTask));
        tList.add(atHand);
        return atHand;
    }
}
