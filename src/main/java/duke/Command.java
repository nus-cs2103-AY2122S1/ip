package duke;

import java.time.format.DateTimeParseException;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.ToDoTask;

/**
 * Class to abstract the type and execution of a command
 */
public class Command {
    /**
     * Enum to encapsulate the type of CommandTypes
     */
    public enum CommandTypes {
        BYE,
        DEADLINE,
        DELETE,
        DONE,
        EVENT,
        FIND,
        LIST,
        TODO,
        UNKNOWN
    }

    private final CommandTypes typeOfCommand;
    private final String taskDetails;

    /**
     * Constructor for the Class Command
     *
     * @param type The type of Command
     * @param taskDetails The String describing the various attributes for the command
     */
    public Command(CommandTypes type, String taskDetails) {
        if (type != null) {
            switch (type) {
            case BYE:
                this.typeOfCommand = CommandTypes.BYE;
                break;
            case DEADLINE:
                this.typeOfCommand = CommandTypes.DEADLINE;
                break;
            case DELETE:
                this.typeOfCommand = CommandTypes.DELETE;
                break;
            case DONE:
                this.typeOfCommand = CommandTypes.DONE;
                break;
            case EVENT:
                this.typeOfCommand = CommandTypes.EVENT;
                break;
            case FIND:
                this.typeOfCommand = CommandTypes.FIND;
                break;
            case LIST:
                this.typeOfCommand = CommandTypes.LIST;
                break;
            case TODO:
                this.typeOfCommand = CommandTypes.TODO;
                break;
            default:
                this.typeOfCommand = CommandTypes.UNKNOWN;
            }
        } else {
            this.typeOfCommand = CommandTypes.UNKNOWN;
        }
        this.taskDetails = taskDetails;
    }

    /**
     * Method to check for the Bye Command and return the message to be displayed
     *
     * @param taskDetails The String describing the various attributes for the command
     * @return The String Message for the Bye Command
     * @throws DukeException An Exception class to be thrown if the command taskDetails is not valid
     */
    private String goodbye(String taskDetails) throws DukeException {
        String output = "";
        if ((taskDetails != null) && (taskDetails.equals(""))) {
            output += "Bye. Hope to see you again soon! \\_(\"v\")_/";
        } else {
            throw new DukeException("OOPS! I'm sorry, but I don't know that command");
        }
        return output;
    }

    /**
     * Method to check for the Deadline Command and return the message to be displayed
     *
     * @param taskDetails The String describing the various attributes for the command
     * @param tasks The Object to contain the List of the Tasks
     * @return The Message to be displayed for the Deadline Command
     * @throws DukeException An Exception class to be thrown if the command taskDetails is not valid
     */
    private String addDeadline(String taskDetails, TaskList tasks) throws DukeException {
        String output = "";
        if ((taskDetails == null) || !(taskDetails.contains(" /by "))) {
            throw new DukeException("Incorrect Format of the Deadline Command!!, \nCorrect Format --> "
                    + "deadline <Description> /by <dd/MM/yyyy HHmm>");
        } else {
            String[] values = taskDetails.split(" /by ", 2);
            try {
                DeadlineTask deadline = new DeadlineTask(values[0], values[1]);
                tasks.addTask(deadline);
                output += "Got it. I've added this task:\n";
                output += "  " + deadline + "\n";
                output += "Now you have " + tasks.getTaskListLength() + " tasks in the list.";
            } catch (DateTimeParseException e) {
                throw new DukeException("Incorrect Format of the Deadline Command!!, \nCorrect Format --> "
                        + "deadline <Description> /by <dd/MM/yyyy HHmm>");
            }
        }
        return output;
    }

    /**
     * Method to check for the Delete Command and render the UI
     *
     * @param taskDetails The String describing the various attributes for the command
     * @param tasks The Object to contain the List of the Tasks
     * @return The Message to be displayed for the Delete Command
     * @throws DukeException An Exception class to be thrown if the command taskDetails is not valid
     */
    private String deleteTask(String taskDetails, TaskList tasks) throws DukeException {
        String output = "";
        try {
            int index = Integer.parseInt(taskDetails) - 1;
            if ((index >= 0) && (index < tasks.getTaskListLength())) {
                output += "Noted. I've removed this task:\n";
                output += "  " + tasks.deleteTaskAtIndex(index).toString();
            } else {
                int numberOfTasks = tasks.getTaskListLength();
                if (numberOfTasks == 0) {
                    throw new DukeException("Incorrect Index!! There are no Tasks in the Task List");
                } else {
                    throw new DukeException("Incorrect Index!! There are " + numberOfTasks + " tasks in the Task List");
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Incorrect Format of the Delete Command!!, \nCorrect Format --> delete <index>");
        }
        return output;
    }

    /**
     * Method to check for the Done Command and render the UI
     *
     * @param taskDetails The String describing the various attributes for the command
     * @param tasks The Object to contain the List of the Tasks
     * @return The Message to be displayed for the Done Command
     * @throws DukeException An Exception class to be thrown if the command taskDetails is not valid
     */
    private String markTaskAsCompleted(String taskDetails, TaskList tasks) throws DukeException {
        String output = "";
        try {
            int index = Integer.parseInt(taskDetails) - 1;
            if ((index >= 0) && (index < tasks.getTaskListLength())) {
                tasks.completeTask(index);
                output += "Nice! I've marked this task as done:\n";
                output += "  " + tasks.getTask(index);
            } else {
                int numberOfTasks = tasks.getTaskListLength();
                if (numberOfTasks == 0) {
                    throw new DukeException("Incorrect Index!! There are no Tasks in the Task List");
                } else {
                    throw new DukeException("Incorrect Index!! There are " + numberOfTasks + " tasks in the Task List");
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Incorrect Format of the Done Command!!, \nCorrect Format --> done <index>");
        }
        return output;
    }

    /**
     * Method to check for the Event Command and render the UI
     *
     * @param taskDetails The String describing the various attributes for the command
     * @param tasks The Object to contain the List of the Tasks
     * @throws DukeException An Exception class to be thrown if the command taskDetails is not valid
     */
    private String addEvent(String taskDetails, TaskList tasks) throws DukeException {
        String output = "";
        if ((taskDetails == null) || !(taskDetails.contains(" /at "))) {
            throw new DukeException("Incorrect Format of the Event Command!!, \n"
                    + "Correct Format --> event <Description> /at <dd/MM/yyyy HHmm>");
        } else {
            String[] values = taskDetails.split(" /at ", 2);
            try {
                EventTask event = new EventTask(values[0], values[1]);
                tasks.addTask(event);
                output += "Got it. I've added this task:\n";
                output += "  " + event + "\n";
                output += "Now you have " + tasks.getTaskListLength() + " tasks in the list.";
            } catch (DateTimeParseException e) {
                throw new DukeException("Incorrect Format of the Event Command!!, \n"
                        + "Correct Format --> event <Description> /at <dd/MM/yyyy HHmm>");
            }
        }
        return output;
    }

    /**
     * Method to check for the Task with the given String in the Task List
     *
     * @param searchDetails The String to be Searched in the Task List
     * @param tasks The Object to contain the List of the Tasks
     * @return The String displaying all the Tasks with the given input string
     * @throws DukeException An Exception class to be thrown if the command taskDetails is not valid
     */
    private String searchAndDisplayTaskList(String searchDetails, TaskList tasks) throws DukeException {
        String output = "";
        UI ui = new UI();
        if (searchDetails != null && !searchDetails.equals("")) {
            output += ui.printTaskList(tasks.searchTaskList(searchDetails));
        } else {
            throw new DukeException("Incorrect Format of the Find Command!!,\nCorrect Format --> find <search_string>");
        }
        return output;
    }
    /**
     * Method to check for the List Command and render the UI
     *
     * @param taskDetails The String describing the various attributes for the command
     * @param tasks The Object to contain the List of the Tasks
     * @return The Message to be displayed for the List Command
     * @throws DukeException An Exception class to be thrown if the command taskDetails is not valid
     */
    private String displayTaskList(String taskDetails, TaskList tasks) throws DukeException {
        String output = "";
        UI ui = new UI();
        if (taskDetails != null && taskDetails.equals("")) {
            output += ui.printTaskList(tasks);
        } else {
            throw new DukeException("OOPS! I'm sorry, but I don't know that command");
        }
        return output;
    }

    /**
     * Method to check for the ToDO Command and render the UI
     *
     * @param taskDetails The String describing the various attributes for the command
     * @param tasks The Object to contain the List of the Tasks
     * @return The Message to be displayed for the Todo command
     * @throws DukeException An Exception class to be thrown if the command taskDetails is not valid
     */
    private String addTodo(String taskDetails, TaskList tasks) throws DukeException {
        String output = "";
        if ((taskDetails == null) || (taskDetails.equals(""))) {
            throw new DukeException("Incorrect Format of the ToDo Command!!, \nCorrect Format --> todo <Description>");
        } else {
            ToDoTask todo = new ToDoTask(taskDetails);
            tasks.addTask(todo);
            output += "Got it. I've added this task:\n";
            output += "  " + todo + "\n";
            output += "Now you have " + tasks.getTaskListLength() + " tasks in the list.";
        }
        return output;
    }

    /**
     * Method to check the type of the Command and distribute to the specific type of Command Handler.
     *
     * @param tasks The Object to contain the List of the Tasks
     * @param storage The Object to save the List of the Tasks
     * @throws DukeException An Exception class to be thrown if the command taskDetails is not valid
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        switch (typeOfCommand) {
        case BYE:
            storage.saveTaskList(tasks);
            return goodbye(taskDetails);
        case DEADLINE:
            return addDeadline(taskDetails, tasks);
        case DELETE:
            return deleteTask(taskDetails, tasks);
        case DONE:
            return markTaskAsCompleted(taskDetails, tasks);
        case EVENT:
            return addEvent(taskDetails, tasks);
        case FIND:
            return searchAndDisplayTaskList(taskDetails, tasks);
        case LIST:
            return displayTaskList(taskDetails, tasks);
        case TODO:
            return addTodo(taskDetails, tasks);
        case UNKNOWN:
        default:
            throw new DukeException("OOPS! I'm sorry, but I don't know that command");
        }
    }
}
