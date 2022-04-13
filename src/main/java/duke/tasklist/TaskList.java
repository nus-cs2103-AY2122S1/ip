package duke.tasklist;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private static final DateTimeFormatter FORMAT_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter FORMAT_NO_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private enum TaskType {
        Todo, Deadline, Event
    }

    /**
     * Constructs a TaskList object.
     *
     * @param tasks ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns string of all the tasks.
     *
     * @return Returns a list of all tasks.
     */
    public String getAllTasksString() {
        String output = "";
        if (tasks.size() == 0) {
            return "List is empty.";
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                output += (i + 1) + ": " + tasks.get(i) + "\n";
                System.out.println((i + 1) + ": " + tasks.get(i));
            }
            return output;
        }
    }

    /**
     * Returns string of a task with the index specified.
     *
     * @param taskId Id of the task to print.
     * @return Task with the specified index.
     */
    public String getTaskString(int taskId) {
        String taskNumberPrefix = (taskId + 1) + ": ";
        return taskNumberPrefix + tasks.get(taskId);
    }

    private Task getTask(int taskId) {
        return tasks.get(taskId - 1);
    }

    private void setTask(int taskId, Task task) {
        tasks.set(taskId - 1, task);
    }

    /**
     * Increases the date of a task by a set number of days.
     *
     * @param taskId Id of the task to modify.
     * @param days   Number of days to snooze by.
     * @return Task snoozed and number of days.
     * @throws DukeException If a Todo task id was passed in.
     */
    public String increaseTaskDateByDays(int taskId, int days) throws DukeException {
        Task task = this.getTask(taskId);
        if (task instanceof Todo) {
            throw new DukeException("A Todo task does not have a date!");
        }
        task.increaseDateByDays(days);
        this.setTask(taskId, task);
        return "Task " + taskId + " has been snoozed by " + days + " days.";
    }

    /**
     * Marks a task as done.
     *
     * @param taskId ID of the task we are marking as done.
     * @return Response to the task being marked as done.
     */
    public String markTaskAsDone(int taskId) throws DukeException {
        if (taskId < 1 || taskId > tasks.size()) {
            throw new DukeException("Please provide a task ID that exists.");
        }
        tasks.get(taskId - 1).setIsDone(true);
        return "Nice! I've marked this task as done.\n" + "    " + tasks.get(taskId - 1);
    }

    /**
     * Adds a duke.task.Todo task.
     *
     * @param description Description of the duke.task.Todo.
     * @return Response to a Todo being added.
     */
    public String addTodo(String description) {
        tasks.add(new Todo(description, false));
        return getLatestTaskString();
    }

    /**
     * Adds a duke.task.Deadline task.
     *
     * @param fullDescription String that contains the description and deadline of the task.
     * @return Response to a deadline being added.
     */
    public String addDeadline(String fullDescription) throws DukeException {
        int sepIndex = fullDescription.indexOf("/by");
        addTask(fullDescription, sepIndex, TaskType.Deadline);
        return getLatestTaskString();
    }

    /**
     * Adds an duke.task.Event task.
     *
     * @param fullDescription String that contains the description and time of the task.
     * @return Response to an event being added.
     */
    public String addEvent(String fullDescription) throws DukeException {
        int sepIndex = fullDescription.indexOf("/at");
        addTask(fullDescription, sepIndex, TaskType.Event);
        return getLatestTaskString();
    }

    /**
     * Adds a new Event or Deadline to tasks.
     *
     * @param fullDescription Description of the task.
     * @param sepIndex        Index of the separator.
     * @param type            Type of task.
     * @throws DukeException If formatting is wrong.
     */
    public void addTask(String fullDescription, int sepIndex, TaskType type) throws DukeException {
        checkFormatting(fullDescription, sepIndex, type);
        String description = fullDescription.substring(0, sepIndex - 1);
        String dateTime = fullDescription.substring(sepIndex + 4);
        String[] dateTaskArray = dateTime.split(" ");
        if (type == TaskType.Deadline) {
            if (dateTaskArray.length == 2) {
                tasks.add(new Deadline(description, dateTime, FORMAT_TIME, true, false));
            } else {
                tasks.add(new Deadline(description, dateTime, FORMAT_NO_TIME, false, false));
            }
        } else if (type == TaskType.Event) {
            if (dateTaskArray.length == 2) {
                tasks.add(new Event(description, dateTime, FORMAT_TIME, true, false));
            } else {
                tasks.add(new Event(description, dateTime, FORMAT_NO_TIME, false, false));
            }
        }
    }

    private void checkFormatting(String fullDescription, int sepIndex, TaskType type) throws DukeException {
        String taskType;
        String separator;
        if (type == TaskType.Deadline) {
            taskType = "deadline";
            separator = "/by";
        } else {
            // TaskType is Event
            taskType = "event";
            separator = "/at";
        }
        boolean hasSeparator = sepIndex != -1;
        if (!hasSeparator) {
            throw new DukeException("Please input with the correct format e.g. " + taskType + " read books" +
                    " " + separator + " 2021-09-08 09:30 (yyyy-mm-dd hh:mm, where time is optional)");
        }
        boolean hasSpaceBefore = fullDescription.charAt(sepIndex + 3) != ' ';
        boolean hasSpaceAfter = fullDescription.charAt(sepIndex - 1) != ' ';
        if (hasSpaceBefore || hasSpaceAfter) {
            throw new DukeException("Please input with the correct format e.g. " + taskType + " read books" +
                    " " + separator + " 2021-09-08 09:30 (yyyy-mm-dd hh:mm, where time is optional)");
        }
    }


    /**
     * Prints information about the latest task that was just added.
     *
     * @return String representing the latest task that was added.
     */
    public String getLatestTaskString() {
        String result = "Got it. I've added this task:\n";
        result += "   " + tasks.get(tasks.size() - 1) + "\n";
        result += "Now you have " + tasks.size() + " task(s) in the list.";
        return result;
    }

    /**
     * Deletes a specific task.
     *
     * @param taskId ID of the task to be deleted.
     * @return Response to the task being deleted.
     */
    public String deleteTask(int taskId) throws DukeException {
        if (taskId < 1 || taskId > tasks.size()) {
            throw new DukeException("Please provide a task ID that exists.");
        }
        String output = "Noted. I have removed this task:\n";
        output += "   " + tasks.get((taskId - 1)) + "\n";
        tasks.remove(taskId - 1);
        output += "Now you have " + tasks.size() + " task(s) in the list.";
        return output;
    }

    /**
     * Finds tasks that match a certain keyword.
     *
     * @param keyword Keyword supplied by the user.
     * @return String of tasks that match the keyword.
     */
    public String findTask(String keyword) {
        String output = "";
        ArrayList<Integer> taskIndexes = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            String taskDescription = tasks.get(i).getDescription().toLowerCase(Locale.ROOT);
            if (taskDescription.contains(keyword.toLowerCase(Locale.ROOT))) {
                taskIndexes.add(i);
            }
        }
        if (taskIndexes.isEmpty()) {
            output += "No matches found!";
        } else {
            output += "Here are the matching items in your list:";
            for (int i : taskIndexes) {
                output += "\n" + getTaskString(i);
            }
        }
        return output;
    }
}