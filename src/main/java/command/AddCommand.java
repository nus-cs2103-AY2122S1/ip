package command;

import exception.InvalidDateFormat;
import exception.NullDescription;
import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import task.Todo;
import task.Event;
import ui.Ui;

public class AddCommand extends Command {

    private TaskType taskType;
    private String taskInfo;
    private enum TaskType {
        TODO, DEADLINE, EVENT
    }

    private void checkDescription(String description, TaskType type) throws NullDescription {
        switch (type) {
        case TODO:
            if (description.length() <= 4 || description.split(" ").length < 2) {
                throw new NullDescription("todo");
            }
            break;
        case DEADLINE:
            if (description.length() <= 8 || description.split(" ").length < 2) {
                throw new NullDescription("deadline");
            }
            break;
        case EVENT:
            if (description.length() <= 5 || description.split(" ").length < 2) {
                throw new NullDescription("event");
            }
            break;
        default:
            break;
        }
    }

    /**
     * Abstracts out the critical information from the user's input and insert task as the taskType.
     * Initialises taskInfo and taskType with respective information.
     *
     * @param input The complete string input by users
     * @throws NullDescription If the description of task is empty or contains only spaces.
     */
    public AddCommand(String input) throws NullDescription {
        switch (input.split(" ")[0]) {
        case "todo":
            this.taskType = TaskType.TODO;
            checkDescription(input, TaskType.TODO);
            this.taskInfo = padTaskInfo(input.substring(5));
            break;
        case "deadline":
            this.taskType = TaskType.DEADLINE;
            checkDescription(input, TaskType.DEADLINE);
            this.taskInfo = padTaskInfo(input.substring(9));
            break;
        case "event":
            this.taskType = TaskType.EVENT;
            checkDescription(input, TaskType.EVENT);
            this.taskInfo = padTaskInfo(input.substring(6));
            break;
        default:
            break;
        }
    }

    private String padTaskInfo(String taskInfo) {
        if (!taskInfo.contains(" --")) {
            return taskInfo + " -- ";
        } else {
            return taskInfo;
        }
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateFormat {
        Task newTask = null;

        switch(taskType) {
        case TODO:
            newTask = createTask(TaskType.TODO);
            break;
        case DEADLINE:
            newTask = createTask(TaskType.DEADLINE);
            break;
        case EVENT:
            newTask = createTask(TaskType.EVENT);
            break;
        default:
            break;
        }

        assert newTask != null;

        tasks.addTask(newTask);
        storage.write(tasks.getTaskList());
        return String.format("Got it. I've added this task:\n"
                        + "%s\n"
                        + "Now you have %d tasks in the list.\n", newTask, tasks.size());
    }

    private Task createTask(TaskType type) throws InvalidDateFormat {
        Task outputTask = null;
        switch (type) {
        case TODO:
            String todoDescription = taskInfo.split(" --")[0];
            String todoNotes = taskInfo.split(" --")[1];
            outputTask = new Todo(todoDescription, todoNotes, false);
            break;
        case DEADLINE:
            String deadlineDescription = taskInfo.split(" /by ")[0];
            String deadlineDateAndNotes = taskInfo.split(" /by ")[1];
            String deadlineDate = deadlineDateAndNotes.split(" --")[0];
            String deadlineNotes = deadlineDateAndNotes.split(" --")[1];
            outputTask = new Deadline(deadlineDescription, deadlineDate, deadlineNotes, false);
            break;
        case EVENT:
            String eventDescription = taskInfo.split(" /at ")[0];
            String eventDateAndNotes = taskInfo.split(" /at ")[1];
            String eventDate = eventDateAndNotes.split(" --")[0];
            String eventNotes = eventDateAndNotes.split(" --")[1];
            outputTask = new Event(eventDescription, eventDate, eventNotes, false);
            break;
        default:
            break;
        }
        assert outputTask != null : "Task type should have matched";
        return outputTask;
    }
}
