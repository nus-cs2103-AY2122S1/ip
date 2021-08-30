package duke.utility;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * <h2>Parser</h2>
 * Auxiliary class that parses commands from the user and hands the parsed commands and details to
 * {@link duke.utility.TaskList} to track.
 */

public class Parser {
    
    private final TaskList taskList;
    private final Storage storage;
    
    Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Parses a given command from the user.
     * @param command command from the user.
     * @return a string that updates the user of the outcome of the command.
     */
    String parseCommand(String command) {
        if (command.trim().length() == 0) {
            return "Please type some commands!";
        }
        String[] commandTokens = command.split(" ");
        // parse command
        try {
            switch (commandTokens[0]) {
            case "bye":
                return "TERMINATE";
            case "done":
                return this.parseDoneTask(command.substring(5).trim());
            case "list":
                return this.taskList.getAllTasks();
            case "delete":
                return this.parseDeleteTask(command.substring(7));
            case "find":
                return this.taskList.search(command.substring(5).trim());
            default:
                return this.parseNewTask(command.trim()); // default is add new duke.task
            }
        } catch (DukeException ex) {
            return ex.getMessage();
        } catch (DateTimeParseException ex) {
            return "Invalid task description: "  
                    + "invalid date/time\nPlease use [command type] [task name] / [dd-mm-yyyy] [time (in 24hr "
                    + "format)]\ne.g. event lecture / 21-02-2021 1500";
        } catch (IOException ex) {
            return "Unable to log task.";
        } catch (StringIndexOutOfBoundsException ex) {
            return "Invalid task description: missing details!";
        } catch (NumberFormatException ex) {
            return "Invalid input for delete command. Please enter [delete] followed by the number of the line to "
                    + "delete\ne.g. delete 2";
        } catch (Exception ex) {
            return "Unexpected error occurred. Please check input.";
        }
    }
    
    private String parseDeleteTask(String taskNum) throws DukeException.InvalidTaskNumException, IOException {
        String message = this.taskList.deleteTask(Integer.parseInt(taskNum.trim()));
        if (this.storage != null) {
            this.storage.deleteTaskLogEntry(Integer.parseInt(taskNum.trim()) - 1);
        }
        return message;
    }

    private String parseDoneTask(String taskName) throws DukeException.NoSuchTaskException,
            DukeException.TaskAlreadyCompleteException, IOException {
        String message = this.taskList.markAsCompleted(taskName);
        if (this.storage != null) { // log
            int taskIdx = this.taskList.getTaskIndex(taskName);
            this.storage.changeTaskLogToCompleted(taskIdx);
        }
        return message;
    }
    
    private String parseNewTask(String command) throws DukeException.DuplicateTaskException,
            DukeException.InvalidTaskDescriptionException, DukeException.InvalidCommandException, IOException {
        if (command.length() == 0) {
            throw new DukeException.InvalidCommandException("Please type something!");
        }
        String[] commandTokens = command.split(" ");
        String taskType = commandTokens[0];
        if (taskType.equals("todo") || taskType.equals("event") || taskType.equals("deadline")) { // valid task
            // remove tasktype to get taskName (+datetime) only
            String details = command.substring(taskType.length() + 1).trim(); 
            if (details.length() == 0) {
                throw new DukeException.InvalidTaskDescriptionException("Missing task details!");
            }
            // if task is todo, then name = details, else name will be before the "/"
            String taskName = details.split("/")[0].trim(); 
            if (this.taskList.existingTasks.contains(taskName)) { // task already in list
                throw new DukeException.DuplicateTaskException("Task already in list!");
            }
            Task task = null;
            String message = null;
            if ("todo".equals(taskType)) {
                task = new ToDo(taskName);
                message = this.taskList.add(task);
                if (this.storage != null) {
                    this.storage.append("T", "F", taskName);
                }
            } else { 
                String[] tokens = details.split("/");  // event or deadline
                // tokens has the structure [task name, dateTime]
                String[] dateTimeString = tokens[1].trim().split(" "); 
                LocalDate date = LocalDate.parse(dateTimeString[0], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                LocalTime time = LocalTime.parse(dateTimeString[1], DateTimeFormatter.ofPattern("HHmm"));
                LocalDateTime dateTime = LocalDateTime.of(date, time);
                String taskTypeForStorage = "E";
                if (taskType.equals("event")) {
                    task = new Event(taskName, dateTime);
                } else { // deadline
                    task = new Deadline(taskName, dateTime);
                    taskTypeForStorage = "D";
                }
                message = this.taskList.add(task);
                if (this.storage != null) {
                    this.storage.append(taskTypeForStorage, taskName, "F",
                            dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }
            }
            return message;
        } else { // invalid command
            throw new DukeException.InvalidCommandException("Invalid command!");
        }
    }
}
