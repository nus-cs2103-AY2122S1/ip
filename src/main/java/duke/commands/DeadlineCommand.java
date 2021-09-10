package duke.commands;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;

/**
 * DeadlineCommand class used to represent a Deadline Command.
 * Contains methods that
 * (i)    executes the DeadlineCommand
 */
public class DeadlineCommand extends Command {
    public DeadlineCommand() {
        super("deadline");
    }

    /**
     * Returns String object to describe execution of DeadlineCommand.
     *
     * @param des User input into the Duke chat-box.
     * @param tList TaskList object used to keep track of all tasks.
     * @return String representation of DeadlineCommand.
     * @throws DukeException If input for deadline command is not properly formatted.
     */
    @Override
    public String execute(String des, TaskList tList) throws DukeException {
        checkValidDes(des);
        if (des.equals("deadline")) {
            throw new DukeException("\"deadline\" command not correctly formatted \n"
                    + "Please insert task and due date arguments");
        }
        if (!des.contains("/by")) {
            throw new DukeException("\"deadline\" command not correctly formatted \n"
                    + "Please do not forget to include \"by\" and insert due date argument");
        }
        try {
            if (des.chars().filter(c -> c == '/').count() > 1) {
                throw new DukeException("Format Error. "
                        + "Do not use the special character \"/\" within your task description.");
            }
            Deadline atHand = addDeadlineFromDescriptionToTaskList(des, tList);
            Storage.createFile();
            Storage.writeToFile(tList);
            return "Sure. The following task has been added: \n"
                    + atHand.formatString()
                    + "\n"
                    + "\n"
                    + numberOfTasks(tList)
                    + "\n";

        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("\"deadline\" command not correctly formatted");
        }
    }

    /**
     * Returns Deadline object after adding it into the given task list.
     *
     * @param des User input into the Duke chat-box.
     * @param tList TaskList object used to keep track of all tasks.
     * @return Deadline task that is the most recent addition to tList.
     * @throws DukeException If user input, des, is not properly formatted for extractDate() or extractTimeDeadline().
     */
    private Deadline addDeadlineFromDescriptionToTaskList(String des, TaskList tList) throws DukeException {
        String description = des.substring(9, des.indexOf('/') - 1);
        LocalDate date = Storage.extractDate(des);
        LocalTime time = Storage.extractTimeDeadline(des);
        Deadline atHand = new Deadline(description, date, time);
        tList.add(atHand);
        return atHand;
    }
}
