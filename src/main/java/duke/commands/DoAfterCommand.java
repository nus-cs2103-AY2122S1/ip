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
    private String extractPrevTaskDescription(String previousTask) throws DukeException {
        String taskType = previousTask.substring(1, 2);
        String des;
        int openBracket;
        switch (taskType) {
        case "T":
            des = previousTask.substring(7);
            break;
        case "E":
        case "D":
        case "A":
            openBracket = previousTask.indexOf('(');
            des = previousTask.substring(7, openBracket - 1);
            break;
        default:
            throw new DukeException("Unable to load DoAfter task into Duke as type of previous task cannot be read");
        }
        return des;
    }
}
