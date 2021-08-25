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
            default:
                return this.parseNewTask(command); // default is add new duke.task
            }
        } catch (DukeException ex) {
            return ex.getMessage();
        } catch (DateTimeParseException ex) {
            return "Invalid task description: "
                    + "invalid date/time\nPlease use [command type] [task name] / [dd-mm-yyyy] [time (in 24hr " +
                    "format)]\ne.g. event lecture / 21-02-2021 1500";
        } catch (IOException ex) {
            return "Unable to log task.";
        } catch (StringIndexOutOfBoundsException ex) {
            return "Invalid task description: missing name / date & time!";
        } catch (NumberFormatException ex) {
            return "Invalid input for delete command. Please enter [delete] followed by the number of the line to " +
                    "delete\ne.g. delete 2";
        } catch (Exception ex) {
            return "Unexpected error occurred. Please check input.";
        }
    }
    
    private String parseDoneTask(String taskName) throws DukeException.NoSuchTaskException,
            DukeException.TaskAlreadyCompleteException, IOException {
        String message = this.taskList.markAsCompleted(taskName);
        if (this.storage != null) { // log
            int taskIdx = this.taskList.getTaskIdx(taskName);
            this.storage.changeTaskLogToCompleted(taskIdx);
        }
        return message;
    }
    
    private String parseDeleteTask(String taskNum) throws DukeException.InvalidTaskNumException, IOException {
        String message = this.taskList.deleteTask(Integer.parseInt(taskNum.trim()));
        if (this.storage != null) {
            this.storage.deleteTaskLogEntry(Integer.parseInt(taskNum.trim()) - 1);
        }
        return message;
    }
    
    private String parseNewTask(String command) throws DukeException.DuplicateTaskException,
            DukeException.InvalidTaskDescriptionException, DukeException.InvalidCommandException, IOException {
        String[] commandTokens = command.split(" ");
        String taskName;
        Task task;
        if (commandTokens[0].equals("todo")) {
            taskName = command.substring(5).trim();
            if (this.taskList.existingTasks.contains(taskName)) {
                throw new DukeException.DuplicateTaskException("Task already in list!");
            } else if (taskName.length() == 0) {
                throw new DukeException.InvalidTaskDescriptionException("Invalid task description: missing name / date & time!");
            } else {
                task = new ToDo(taskName);
                if (this.storage != null) {
                    this.storage.append("T", "F", taskName);
                }
                return this.taskList.add(task);
            }
        } else if (commandTokens[0].equals("event") || commandTokens[0].equals("deadline")) { //either event ot deadline
            String details = command.substring(commandTokens[0].length() + 1).trim();
            String[] detailTokens = details.split("/");
            if (detailTokens.length < 2) {
                throw new DukeException.InvalidTaskDescriptionException("Invalid task description: missing name / date & time!");
            } else if (detailTokens.length > 2) {
                throw new DukeException.InvalidTaskDescriptionException("Invalid task description: "
                        + "invalid date/time\nPlease use [command type] [task name] / [dd-mm-yyyy] [time (in 24hr " +
                        "format)" +
                        "]\ne.g. event lecture / 21-02-2021 1500");
            } else if (this.taskList.existingTasks.contains(detailTokens[0].trim())) {
                throw new DukeException.DuplicateTaskException("Task already in list!");
            } else { // valid
                taskName = detailTokens[0].trim();
                String[] dateTimeString = detailTokens[1].trim().split(" ");
                LocalDate date = LocalDate.parse(dateTimeString[0], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                LocalTime time = LocalTime.parse(dateTimeString[1], DateTimeFormatter.ofPattern("HHmm"));
                LocalDateTime dateTime = LocalDateTime.of(date, time);
                if (commandTokens[0].trim().equals("event")) {
                    task = new Event(taskName, dateTime);
                    if (storage != null) {
                        this.storage.append("E", "F", taskName,
                                dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    }
                } else { //deadline
                    task = new Deadline(taskName, dateTime);
                    if (storage != null) {
                        this.storage.append("D", "F", taskName,
                                dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    }
                }
                return this.taskList.add(task);
            }
        } else { // invalid input
            throw new DukeException.InvalidCommandException("Invalid command!");
        }
    }
}
