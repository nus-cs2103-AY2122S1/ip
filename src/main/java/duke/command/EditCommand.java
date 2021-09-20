package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.Storage;
import duke.exception.EmptyDescriptionException;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidTaskCommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the user command when the user adds a deadline to the task list.
 */
public class EditCommand extends Command {
    private String command;
    private Storage storage;

    /**
     * Represents a constructor for the DeadlineCommand class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public EditCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Returns the string representation of the class.
     *
     * @return String description of the class.
     */
    public String toString() {
        return "This is an edit command";
    }

    /**
     * Executes the response when the user enters a deadline command and updates the task list and storage 
     * file (duke.txt)
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.
     * @return String representation of the new deadline task as well as the number of tasks in the task list.
     * @throws DukeException If user doesn't provide a description for the command or enters the command in invalid format.
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        if (command.trim().length() <= 4) {
            throw new EmptyDescriptionException();
        }

        String[] parts = command.split("/", 2);
        if (parts.length <= 1) {
            throw new InvalidTaskCommandException();
        }
        
        this.storage = storage;
        String str = parts[1];
        if (str.startsWith("description")) {
            return editDescription(parts, taskList);
        } else if (str.startsWith("task")) {
            return editTask(parts, taskList);
        } else if (str.startsWith("datetime")) {
            return editDateTime(parts, taskList);
        } else {
            throw new InvalidTaskCommandException();
        }
    }
    
    private String editTask(String[] parts, TaskList taskList) throws DukeException {
        String str = parts[1].substring(5);
        int index = Integer.parseInt(parts[0].substring(5).trim());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String[] furtherParts = str.split("/", 2);
        Task newTask = null;
        try {
            if (str.startsWith("deadline")) {
                LocalDateTime dateTime = LocalDateTime.parse(furtherParts[1].substring(3).trim(), dtf);
                newTask = new Deadline(furtherParts[0].substring(9), dateTime);
                taskList.modifyTask(newTask, index-1);
                
            } else if (str.startsWith("event")) {
                LocalDateTime dateTime = LocalDateTime.parse(furtherParts[1].substring(3).trim(), dtf);
                newTask = new Event(furtherParts[0].substring(6), dateTime);
                taskList.modifyTask(newTask, index-1);
                
            } else if (str.startsWith("todo")) {
                taskList.modifyTask(new Todo(str.substring(5)), index - 1);
                
            } else {
                throw new InvalidTaskCommandException();
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
        
        storage.writeToFile("./duke.txt", taskList);
        
        return "Task " + index + " in your task list has been updated! Here is your latest list: \n" 
                + taskList.printList();
    }

    private String editDescription(String[] parts, TaskList taskList) throws InvalidTaskCommandException {
        String str = parts[1];
        int index = Integer.parseInt(parts[0].substring(5).trim());
        Task oldTask = taskList.getTask(index-1);
        boolean isDone = oldTask.isDone();
        Task newTask = null;

        if (oldTask.toString().startsWith("[D]") || oldTask.toString().startsWith("[E]")) {
            String newDescription = str.substring(12);
            newTask = this.assignNewTask(oldTask, newDescription, oldTask.getDate());
            taskList.modifyTask(newTask, index-1);
            
        } else if (oldTask.toString().startsWith("[T]")) {
            taskList.modifyTask(new Todo(str.substring(12)), index-1);
            
        } else {
            throw new InvalidTaskCommandException();
        }

        if (isDone) {
            newTask.markAsDone();
        }

        storage.writeToFile("./duke.txt", taskList);
        
        return "The description of Task " + index + " in your task list has been updated! Here is your latest list: " 
                + "\n" + taskList.printList();
    }

    private String editDateTime(String[] parts, TaskList taskList) throws DukeException {
        String str = parts[1];
        int index = Integer.parseInt(parts[0].substring(5).trim());
        Task oldTask = taskList.getTask(index-1);
        boolean isDone = oldTask.isDone();
        Task newTask = null;

        if (oldTask.toString().startsWith("[D]") || oldTask.toString().startsWith("[E]")) {
            try {
                String newDateTime = str.substring(9);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(newDateTime.trim(), dtf);
                
                String description = oldTask.getDescription();
                newTask = this.assignNewTask(oldTask, description, dateTime);
                taskList.modifyTask(newTask, index-1);
                
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException();
            }
            
        } else if (oldTask.toString().startsWith("[T]")) {
            return "A todo task doesn't have a DateTime attribute!";
            
        } else {
            throw new InvalidTaskCommandException();
        }

        if (isDone) {
            newTask.markAsDone();
        }
        
        storage.writeToFile("./duke.txt", taskList);
        
        return "The DateTime of Task " + index + " in your task list has been updated! Here is your latest list: "
                + "\n" + taskList.printList();
    }
    
    private Task assignNewTask(Task oldTask, String description, LocalDateTime dateTime) {
        Task newTask = null;
        if (oldTask.toString().startsWith("[D]")) {
            newTask = new Deadline(description, dateTime);
            
        } else if (oldTask.toString().startsWith("[E]")) {
            newTask = new Event(description, dateTime);
            
        }
        return newTask;
    }
}
