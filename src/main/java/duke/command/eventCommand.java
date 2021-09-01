package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Event;

import duke.Storage;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDateTimeException;

import duke.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the user command when the user enters an event.
 */
public class eventCommand extends Command {
    private String command;

    /**
     * Constructor for the eventCommand class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public eventCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String description of the class.
     */
    public String toString() {
        return "This is an event command";
    }

    /**
     * Executes the response when the user enters an 'event' command and updates the task list and storage 
     * file (duke.txt)
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.            
     */
    public void execute(TaskList taskList, Storage storage) {
        if (command.length() <= 6) {
            DukeException exp = new EmptyDescriptionException("OOPS!!! The description of an event cannot be empty.");
            System.out.println(exp);
        } else {
            String[] parts = command.split("/");
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(parts[1].substring(3).trim(), dtf);
                Task task = new Event(parts[0].substring(6), dateTime);
                taskList.addTask(task);
                Ui.taskResponse(task);
                storage.writeToFile("./duke.txt", taskList);
            } catch (DateTimeParseException e) {
                DukeException exp = new InvalidDateTimeException("The format of your command is incorrect! It should be event/at " 
                        + "<yyyy-mm-dd HHmm>");
                System.out.println(exp);
            }
        }
    }
}
