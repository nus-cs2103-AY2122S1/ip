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
     * execute() method in DeadlineCommand to add a Deadline task to the TaskList.
     *
     * @param des   the user input into the Duke chat-box.
     * @param tList the TaskList object used to keep track of all tasks.
     * @return String object to describe execution of DeadlineCommand.
     * @throws DukeException if input for deadline command is not properly formatted.
     */
    @Override
    public String execute(String des, TaskList tList) throws DukeException {
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
            String description = des.substring(9, des.indexOf('/') - 1);
            LocalDate date = Storage.extractDate(des);
            LocalTime time = Storage.extractTimeDeadline(des);
            Deadline atHand = new Deadline(description, date, time);
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
            throw new DukeException("\"deadline\" command not correctly formatted");
        }
    }
}
