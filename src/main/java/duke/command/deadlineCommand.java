package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

import duke.Storage;

import duke.exception.EmptyDescriptionException;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeException;

import duke.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the user command when the user enters a deadline.
 */
public class deadlineCommand extends Command {
    private String command;

    /**
     * Constructor for the deadlineCommand class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public deadlineCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String description of the class.
     */
    public String toString() {
        return "This is a deadline command";
    }

    /**
     * Executes the response when the user enters a 'deadline' command and updates the task list and storage 
     * file (duke.txt)
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.
     * @return String representation of the new deadline task as well as the number of tasks in the task list.
     */
    public String execute(TaskList taskList, Storage storage) {
        if (command.length() <= 9) {
            DukeException exp = new EmptyDescriptionException("OOPS!!! The description of a deadline cannot be empty.");
            return exp.toString();
        } else {
            String[] parts = command.split("/", 2);
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(parts[1].substring(3).trim(), dtf);
                Task task = new Deadline(parts[0].substring(9), dateTime);
                taskList.addTask(task);
                storage.writeToFile("./duke.txt", taskList);
                String response = Ui.taskResponse(task);
                return response;
            } catch (DateTimeParseException e) {
                DukeException exp = new InvalidDateTimeException("The format of your command is incorrect! It should be deadline/by " 
                        + "<yyyy-mm-dd HHmm>");
                return exp.toString();
            }
        }
    }
}
