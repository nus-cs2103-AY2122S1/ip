package duke.util;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.exception.InvalidIndexException;
import duke.exception.MissingArgumentException;
import duke.task.CompletionStatus;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The TaskList Class is a representation of a list of tasks that Duke is keeping track of.
 *
 * Contains functions to add/remove Tasks in the list and to display the list.
 *
 * @author Benedict Chua
 */
public class TaskList {
    /** Constants for formatting messages of Duke's response */
    private static final String TASK_INDENT = "  ";
    private static final String ADD_MESSAGE = "I've added this task but it's not like I did it for you or anything!\n";
    private static final String COMPLETE_SINGULAR_MESSAGE = "You completed a task! "
        + "Maybe you aren't so incompetent after all.\n%s\n";
    private static final String COMPLETE_MULTIPLE_MESSAGE = "You completed some tasks! "
        + "Maybe you aren't so incompetent after all.\n";
    private static final String DELETE_MESSAGE = "I've deleted this task so show me some gratitude!\n";
    private static final String NO_DUE_TASK = "Great job! You don't have any remaining tasks.\n";
    private static final String REMINDER_MESSAGE = "Reminder:\n";

    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Constructs a TaskList with the given arguments.
     * If existing task is empty, creates an empty ArrayList of task.
     * Otherwise, parses the Tasks from the saved file and adds it to the ArrayList of task.
     *
     * @param existingTasks Tasks in the formatted into a String from the saved file.
     * @param storage Storage object to use to save state of TaskList.
     */
    public TaskList(ArrayList<String> existingTasks, Storage storage) {
        this.storage = storage;
        tasks = new ArrayList<>();

        if (existingTasks != null) {

            for (String taskString : existingTasks) {
                String[] taskDetails = taskString.split(" \\| ");
                int completionNumber = parseInt(taskDetails[1]);
                CompletionStatus completionStatus = completionNumber == 1
                    ? CompletionStatus.COMPLETED
                    : CompletionStatus.INCOMPLETE;

                switch (taskDetails[0]) {
                case "T":
                    tasks.add(new ToDo(completionStatus, taskDetails[2]));
                    break;
                case "D":
                    tasks.add(new Deadline(completionStatus, taskDetails[2], taskDetails[3]));
                    break;
                case "E":
                    tasks.add(new Event(completionStatus, taskDetails[2], taskDetails[3]));
                    break;
                default:
                    // Assertion for control-flow invariant
                    throw new AssertionError(String.format("Letter representing task is invalid: %s", taskDetails[0]));
                }
            }
        }
    }

    /**
     * Converts and formats current TaskList into a String suitable to write into saved file.
     *
     * @return Formatted TaskList in the form of a String.
     */
    protected String convertListToString() {
        return tasks.stream().map(task -> String.format("%s\n", task.convertToString())).collect(Collectors.joining());
    }

    /**
     * Gets the task information of the task that is being operated on and returns it as a String.
     *
     * @param task Task being operated on.
     * @return Formatted String of task information.
     */
    private String getTaskInfo(Task task) {
        return String.format("%s%s\n", TASK_INDENT, task);
    }

    /**
     * Gets number of tasks remaining in the TaskList and returns it in a formatted message.
     *
     * @return Formatted String of remaining tasks in the task list.
     */
    private String getNumberOfTasksRemaining() {
        return String.format("Now you have %d %s in the list. Do your best doing them okay?",
            tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }

    /**
     * Adds a given task to the tasks list.
     * Task can be in the from of ToDo, Deadline or Event.
     *
     * @param task the task to add to the list.
     * @return String representation of messages stating that the task has been added
     */
    public String addToList(String task, String typeOfTask) throws MissingArgumentException {
        switch (typeOfTask) {
        case "ToDo":
            tasks.add(new ToDo(task));
            break;
        case "Deadline":
            String[] deadlineDetails = Parser.parseDeadlineDate(task);
            tasks.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
            break;
        case "Event":
            String[] eventDetails = Parser.parseEventDate(task);
            tasks.add(new Event(eventDetails[0], eventDetails[1]));
            break;
        default:
            // Assertion for control-flow invariant
            throw new AssertionError(String.format("Type of task is invalid: %s", typeOfTask));
        }

        storage.writeToFile(convertListToString());

        return ADD_MESSAGE + getTaskInfo(tasks.get(tasks.size() - 1)) + getNumberOfTasksRemaining();
    }

    /**
     * Prints the tasks in the list with indexing starting from 1.
     * If dateString is not null, prints tasks that are due on that date.
     * Otherwise, prints all tasks.
     *
     * @param filterType the type of filtering to be done when displaying list
     * @param filterCondition String containing the respective filtering condition (expects date or keyword).
     */
    public String listTasks(String filterType, String filterCondition) {
        String tasksString = "";
        switch (filterType) {
        case "all":
            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                tasksString = tasksString + String.format("%d:%s\n", i + 1, currTask);
            }
            break;
        case "date":
            int dateIndex = 1;

            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                if (currTask.isOnDate(filterCondition)) {
                    tasksString = tasksString + String.format("%d:%s\n", dateIndex, currTask);
                    dateIndex++;
                }
            }
            break;
        case "keyword":
            int keywordIndex = 1;

            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                if (currTask.containsKeyword(filterCondition)) {
                    tasksString = tasksString + String.format("%d:%s\n", keywordIndex, currTask);
                    keywordIndex++;
                }
            }
            break;
        case "reminder":
            int reminderIndex = 1;
            int numOfDays = parseInt(filterCondition);
            LocalDate remindDate = LocalDate.now().plusDays(numOfDays);
            tasksString = REMINDER_MESSAGE;

            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.get(i);
                boolean isBeforeRemindDate = currTask.checkDueBeforeDate(remindDate);
                boolean isNotDone = !currTask.checkIsCompleted();
                if (isBeforeRemindDate && isNotDone) {
                    tasksString = tasksString + String.format("%d:%s\n", reminderIndex, currTask);
                    reminderIndex++;
                }
            }
            break;
        default:
            // Assertion for control-flow invariant
            throw new AssertionError(String.format("Filter type is invalid: %s", filterType));
        }

        return tasksString.isEmpty()
            ? "You have no tasks currently."
            : tasksString;
    }

    /**
     * Gets the next most urgent task (that is not done) in the task list.
     *
     * @return String of the result containing the next most urgent task.
     */
    public String getNextDueTask() {
        Task mostUrgentTask = tasks.get(0);

        for (int i = 0; i < tasks.size(); i++) {
            Task compareTask = tasks.get(i);
            if (mostUrgentTask.compareTo(compareTask) > 0) {
                mostUrgentTask = compareTask;
            }
        }

        if (mostUrgentTask.checkIsCompleted()) {
            return NO_DUE_TASK;
        }

        return REMINDER_MESSAGE + TASK_INDENT + mostUrgentTask.toString();
    }

    /**
     * Marks tasks based on index as done if it exists.
     *
     * @param indexes index(es) given by the User for Task in the TaskList starting from 1.
     * @return message of the completion of the Task.
     * @throws InvalidIndexException if index given does not exist in the TaskList.
     */
    public String markTaskAsDone(int... indexes) throws InvalidIndexException {
        String message;
        if (indexes.length == 1) {
            if (indexes[0] <= 0 || indexes[0] > tasks.size()) {
                throw new InvalidIndexException(tasks.size());
            }
            message = String.format(COMPLETE_SINGULAR_MESSAGE, tasks.get(indexes[0] - 1).markTaskAsDone());

        } else {
            message = COMPLETE_MULTIPLE_MESSAGE;
            // Assertion for internal invariant
            assert indexes.length > 0 : "No indexes have been specified";

            for (int index : indexes) {
                if (index <= 0 || index > tasks.size()) {
                    throw new InvalidIndexException(tasks.size());
                }

                message = message + String.format("%s\n", tasks.get(index - 1).markTaskAsDone());
            }
        }

        storage.writeToFile(convertListToString());

        return message;

    }

    /**
     * Deletes a task based on index if it exists.
     *
     * @param index index given by the User for Task in the TaskList starting from 1.
     * @return message of the deletion of the Task.
     * @throws InvalidIndexException if index given does not exist in the TaskList.
     */
    public String deleteTask(int index) throws InvalidIndexException {
        if (index <= 0 || index > tasks.size()) {
            throw new InvalidIndexException(tasks.size());
        }

        Task deletedTask = tasks.remove(index - 1);
        String message = DELETE_MESSAGE + getTaskInfo(deletedTask) + getNumberOfTasksRemaining();

        storage.writeToFile(convertListToString());

        return message;

    }
}
