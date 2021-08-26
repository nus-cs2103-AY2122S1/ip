package duke.task;

import duke.exception.DukeException;
import duke.exception.EmptyListException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidTaskException;
import duke.exception.TaskNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represent a task list object that performs operations on tasks.
 */
public class TaskList {
    private final String MESSAGE_DONE = "Nice! (ᵔ.ᵔ) Task done:";
    private final String MESSAGE_LIST = "Here's your tasks!";
    private final String MESSAGE_ADD = "Nee added this task:";
    private final String MESSAGE_DELETE = "Nee has deleted this task:";
    private final String MESSAGE_FIND = "Nee found matching tasks!";

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task  the new task to be added.
     * @param size  size of the taskList.
     * @return Formatted task.
     */
    public String formatTask(Task task, int size) {
        String str = (tasks.size() > 1) ? " tasks in the list." : " task in the list.";
        return (MESSAGE_ADD + "\n  " + task + "\n" + "Nee has " + size + str);
    }

    /**
     * Deletes task and returns a {@code string} representation
     * of the list of tasks.
     *
     * @param command Task to be deleted.
     * @throws DukeException Invalid task number, or task has invalid description.
     * @return Updated list of tasks without the deleted task.
     */
    public String deleteTask(String command) throws DukeException {
        int taskNum = Integer.parseInt(command.substring(7));
        if (taskNum <= 0 || taskNum > this.tasks.size()) {
            throw new TaskNotFoundException();
        }
        Task task = this.tasks.get(taskNum - 1);
        this.tasks.remove(taskNum - 1);
        // Show number of tasks in list
        String str = (tasks.size() == 1) ? " task in the list." : " tasks in the list.";
        return (MESSAGE_DELETE + "\n  " + task + "\n" + "Nee has " + tasks.size() + str);
    }

    /**
     * Returns a {@code string} representation of the list of tasks.
     *
     * @throws DukeException List has no tasks.
     * @return List of tasks.
     */
    public String printTasks() throws DukeException {
        if (tasks.size() == 0) {
            throw new EmptyListException();
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.tasks.size() - 1; i++) {
            res.append((i + 1) + ".\t" + this.tasks.get(i) + "\n");
        }
        res.append(tasks.size() + ".\t" + this.tasks.get(tasks.size() - 1));
        return MESSAGE_LIST + "\n" + res;
    }

    /**
     * Returns the list of tasks.
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks a task as completed.
     *
     * @param command The user input.
     * @throws DukeException Invalid task number or task has invalid description.
     * @return Formatted task marked as completed.
     */
    public String finishTask(String command) throws DukeException {
        int taskNum = Integer.parseInt(command.substring(5));
        if (taskNum <= 0 || taskNum > this.tasks.size()) {
            throw new TaskNotFoundException();
        }
        Task task = this.tasks.get(taskNum - 1);
        task.toggleDone();
        return MESSAGE_DONE + "\n" + "  " + task;
    }

    /**
     * Adds an event to the list of tasks.
     *
     * @param command the event with a specific time.
     * @throws DukeException Task has invalid description.
     * @return Formatted event.
     */
    public String addEvent(String command) throws DukeException {
        LocalDateTime dateTime;
        String[] taskCommands = command.substring(6).split("/at");
        if (taskCommands.length < 2) {
            throw new InvalidTaskException();
        }

        taskCommands = Arrays.stream(taskCommands).map(String::trim).toArray(String[]::new);
        try {
            dateTime = LocalDateTime.parse(taskCommands[1], DateTimeFormatter.ofPattern("d/M/yy Hmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }

        Task newTask = new Event(taskCommands[0], dateTime);
        tasks.add(newTask);
        return formatTask(newTask, tasks.size());
    }

    /**
     * Adds a deadline to the list of tasks.
     *
     * @param command the deadline with a specific time.
     * @throws DukeException Task has invalid description.
     * @return Formatted deadline.
     */
    public String addDeadline(String command) throws DukeException {
        LocalDateTime dateTime;
        String[] taskCommands = command.substring(9).split("/by");
        if (taskCommands.length < 2) {
            throw new InvalidTaskException();
        }

        taskCommands = Arrays.stream(taskCommands).map(String::trim).toArray(String[]::new);
        try {
            dateTime = LocalDateTime.parse(taskCommands[1], DateTimeFormatter.ofPattern("d/M/yy Hmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }

        Task newTask = new Deadline(taskCommands[0], dateTime);
        tasks.add(newTask);
        return formatTask(newTask, tasks.size());
    }

    /**
     * Adds a todo to the list of tasks.
     *
     * @param command the todo with a specific time.
     * @return Formatted todo.
     */
    public String addTodo(String command) {
        Task newTask = new Todo(command.substring(5).trim());
        tasks.add(newTask);
        return formatTask(newTask, tasks.size());
    }

    /**
     * Returns a {@code String} of filtered tasks that match the keyword.
     *
     * @param command The user input.
     * @return Filtered tasks.
     * @throws DukeException No tasks match the search term.
     */
    public String find(String command) throws DukeException {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getTaskName().contains(command.substring(5).trim())) {
                filteredTasks.add(t);
            }
        }
        if (filteredTasks.size() <= 0) {
            throw new DukeException("No tasks found!");
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < filteredTasks.size() - 1; i++) {
            res.append((i + 1) + ".\t" + filteredTasks.get(i) + "\n");
        }
        res.append(filteredTasks.size() + ".\t" + filteredTasks.get(filteredTasks.size() - 1));
        return MESSAGE_FIND + "\n" + res;
    }

}
