package duke;
import duke.Task.DeadlineTask;
import duke.Task.EventTask;
import duke.Task.ToDoTask;
import java.time.format.DateTimeParseException;

public class Command {
    public enum Commands {
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

    private final Commands typeOfCommand;
    private final String taskDetails;

    public Command(Commands type, String taskDetails) {
        if (type != null) {
            switch (type) {
            case BYE:
                this.typeOfCommand = Commands.BYE;
                break;
            case DEADLINE:
                this.typeOfCommand = Commands.DEADLINE;
                break;
            case DELETE:
                this.typeOfCommand = Commands.DELETE;
                break;
            case DONE:
                this.typeOfCommand = Commands.DONE;
                break;
            case EVENT:
                this.typeOfCommand = Commands.EVENT;
                break;
            case FIND:
                this.typeOfCommand = Commands.FIND;
                break;
            case LIST:
                this.typeOfCommand = Commands.LIST;
                break;
            case TODO:
                this.typeOfCommand = Commands.TODO;
                break;
            case UNKNOWN:
                this.typeOfCommand = Commands.UNKNOWN;
                break;
            default:
                this.typeOfCommand = Commands.UNKNOWN;
            }
        } else {
            this.typeOfCommand = Commands.UNKNOWN;
        }
        this.taskDetails = taskDetails;
    }

    private void goodbye(String taskDetails, UI ui) throws DukeException {
        if ((taskDetails != null) && (taskDetails.equals(""))) {
            ui.display("Bye. Hope to see you again soon! \\_(\"v\")_/");
        } else {
           throw new DukeException("OOPS! I'm sorry, but I don't know that command");
        }
    }

    private void addDeadline(String taskDetails, UI ui, TaskList tasks) throws DukeException {
        if ((taskDetails == null) || !(taskDetails.contains(" /by "))) {
            throw new DukeException("Incorrect Format of the Deadline Command!!, Correct Format --> " +
                    "deadline <Description> /by <dd/MM/yyyy HHmm>");
        } else {
            String[] values = taskDetails.split(" /by ", 2);
            try {
                DeadlineTask deadline = new DeadlineTask(values[0], values[1]);
                tasks.addTask(deadline);
                ui.display("Got it. I've added this task:");
                ui.display("  " + deadline);
                ui.display("Now you have " + tasks.getTaskListLength() + " tasks in the list.");
            } catch (DateTimeParseException e) {
                throw new DukeException("Incorrect Format of the Deadline Command!!, Correct Format --> " +
                        "deadline <Description> /by <dd/MM/yyyy HHmm>");
            }
        }
    }

    private void deleteTask(String taskDetails, UI ui, TaskList tasks) throws DukeException {
        try {
            int index = Integer.parseInt(taskDetails) - 1;
            if ((index >= 0) && (index < tasks.getTaskListLength())) {
                ui.display("Noted. I've removed this task:");
                ui.display("  " + tasks.deleteTaskAtIndex(index).toString());
            } else {
                int numberOfTasks = tasks.getTaskListLength();
                if (numberOfTasks == 0) {
                    throw new DukeException("Incorrect Index!! There are no Tasks in the Task List");
                } else {
                    throw new DukeException("Incorrect Index!! There are " + numberOfTasks + " tasks in the Task List");
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Incorrect Format of the Delete Command!!, Correct Format --> delete <index>");
        }
    }

    private void markTaskAsCompleted(String taskDetails, UI ui, TaskList tasks) throws DukeException {
        try {
            int index = Integer.parseInt(taskDetails) - 1;
            if ((index >= 0) && (index < tasks.getTaskListLength())) {
                tasks.completeTask(index);
                ui.display("Nice! I've marked this task as done:");
                ui.display("  " + tasks.getTask(index));
            } else {
                int numberOfTasks = tasks.getTaskListLength();
                if (numberOfTasks == 0) {
                    throw new DukeException("Incorrect Index!! There are no Tasks in the Task List");
                } else {
                    throw new DukeException("Incorrect Index!! There are " + numberOfTasks + " tasks in the Task List");
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Incorrect Format of the Done Command!!, Correct Format --> done <index>");
        }
    }

    private void addEvent(String taskDetails, UI ui, TaskList tasks) throws DukeException {
        if ((taskDetails == null) || !(taskDetails.contains(" /at "))) {
            throw new DukeException("Incorrect Format of the Event Command!!, " +
                    "Correct Format --> event <Description> /at <dd/MM/yyyy HHmm>");
        } else {
            String[] values = taskDetails.split(" /at ", 2);
            try {
                EventTask event = new EventTask(values[0], values[1]);
                tasks.addTask(event);
                ui.display("Got it. I've added this task:");
                ui.display("  " + event);
                ui.display("Now you have " + tasks.getTaskListLength() + " tasks in the list.");
            } catch (DateTimeParseException e) {
                throw new DukeException("Incorrect Format of the Event Command!!, " +
                        "Correct Format --> event <Description> /at <dd/MM/yyyy HHmm>");
            }
        }
    }

    private void searchAndDisplayTaskList(String taskDetails, UI ui, TaskList tasks) throws DukeException {
        if (taskDetails != null && !taskDetails.equals("")) {
            ui.printTaskList(tasks.searchTaskList(taskDetails));
        } else {
            throw new DukeException("OOPS! I'm sorry, but I don't know that command");
        }
    }

    private void displayTaskList(String taskDetails, UI ui, TaskList tasks) throws DukeException {
        if (taskDetails != null && taskDetails.equals("")) {
            ui.printTaskList(tasks);
        } else {
            throw new DukeException("OOPS! I'm sorry, but I don't know that command");
        }
    }

    private void addTodo(String taskDetails, UI ui, TaskList tasks) throws DukeException {
        if ((taskDetails == null) || (taskDetails.equals(""))) {
            throw new DukeException("Incorrect Format of the ToDo Command!!, Correct Format --> todo <Description>");
        } else {
            ToDoTask todo = new ToDoTask(taskDetails);
            tasks.addTask(todo);
            ui.display("Got it. I've added this task:");
            ui.display("  " + todo);
            ui.display("Now you have " + tasks.getTaskListLength() + " tasks in the list.");
        }
    }

    public void execute(UI ui, TaskList tasks, Storage storage) throws DukeException {
        switch (typeOfCommand) {
        case BYE:
            goodbye(taskDetails, ui);
            storage.saveTaskList(tasks);
            break;
        case DEADLINE:
            addDeadline(taskDetails, ui, tasks);
            break;

        case DELETE:
            deleteTask(taskDetails, ui, tasks);
            break;
        case DONE:
            markTaskAsCompleted(taskDetails, ui, tasks);
            break;
        case EVENT:
            addEvent(taskDetails, ui, tasks);
            break;
        case FIND:
            searchAndDisplayTaskList(taskDetails, ui, tasks);
            break;
        case LIST:
            displayTaskList(taskDetails, ui, tasks);
            break;
        case TODO:
            addTodo(taskDetails, ui, tasks);
            break;
        case UNKNOWN:
            throw new DukeException("OOPS! I'm sorry, but I don't know that command");
        }
    }

    public boolean isExit() {
        return typeOfCommand == Commands.BYE;
    }
}