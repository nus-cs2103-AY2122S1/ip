package meow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task list that stores the tasks
 * entered by the user.
 */
public class TaskList {
    protected List<Task> tasksList;

    /**
     * A public constructor to initialize a meow.TaskList object which
     * stores the content from the local file.
     */
    public TaskList(List tasksList) {
        this.tasksList = tasksList;
    }

    /**
     * A public constructor to initialize an empty meow.TaskList object.
     */
    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    /**
     * Marks the specific task in the task list as done according to the
     * index input by the user. Prints the task that has been marked as
     * done and the total number of tasks in the list.
     *
     * @param index The task number that the user wants to mark as done.
     * @throws MeowException If the index is out of range.
     */
    public String completeTask(String index) throws MeowException {
        try {
            int taskNumber = Integer.parseInt(index);
            if (taskNumber <= tasksList.size() && taskNumber > 0) {
                Task completedTask = tasksList.get(taskNumber - 1);
                completedTask.markAsDone();
                return "Nice! I've marked this task as done:\n" + completedTask.toString();
            } else {
                throw new InvalidTaskIndex();
            }
        } catch (NumberFormatException e) {
            // String cannot be parsed to integer
            throw new NotSuchTaskFoundException();
        }
    }

    /**
     * Deletes the specific task in the task list according to the
     * index input by the user. Prints the task that has been deleted
     * and the total number of tasks left in the list.
     *
     * @param index The task number that the user wants to delete from the list.
     * @throws MeowException If the index is out of range.
     */
    public String deleteTask(String index) throws MeowException {
        try {
            int taskNumber = Integer.parseInt(index);
            if (taskNumber <= tasksList.size() && taskNumber > 0) {
                Task removedTask = tasksList.remove(taskNumber - 1);
                int taskListLength = tasksList.size();
                String task = taskListLength <= 1 ? " task " : " tasks ";
                return "Noted. I've removed this task:\n"
                        + removedTask.toString() +"\n"
                        + "Now you have " + taskListLength + task + "in the list.";
            } else {
                throw new InvalidTaskIndex();
            }
        } catch (NumberFormatException e) {
            // String cannot be parsed to integer
            throw new NotSuchTaskFoundException();
        }
    }

    /**
     * Adds a new meow.Todo to the meow.TaskList and returns the meow.Todo
     * object added.
     *
     * @param todo The todo input by the user.
     * @return The meow.Todo object added.
     */
    public Todo addTodo(String todo) {
        Todo newTodo = new Todo(todo);
        tasksList.add(newTodo);
        return newTodo;
    }

    /**
     * Adds a new meow.Deadline to the meow.TaskList and returns the meow.Deadline
     * object added.
     *
     * @param deadline The deadline input by the user.
     * @param date     The date of the deadline.
     * @param time     The time of the deadline.
     * @return The meow.Deadline object added.
     */
    public Deadline addDeadline(String deadline, LocalDate date, String time) {
        Deadline newDeadline = new Deadline(deadline, date, time);
        tasksList.add(newDeadline);
        return newDeadline;
    }

    /**
     * Adds a new meow.Event to the meow.TaskList and returns the meow.Event
     * object added.
     *
     * @param event The event input by the user.
     * @param at    The time of the event.
     * @return The meow.Event object added.
     */
    public Event addEvent(String event, String at) {
        Event newEvent = new Event(event, at);
        tasksList.add(newEvent);
        return newEvent;
    }

    /**
     * Returns a list of meow.Task objects stored in the meow.TaskList object.
     *
     * @return A list of meow.Task objects.
     */
    public List<Task> getTasksList() {
        return this.tasksList;
    }

    /**
     * Filter the list of meow.Task objects based on the keyword provided by the user,
     * and returns a list of meow.Task objects.
     *
     * @param keyword The search keyword input by the user.
     * @return A list of filtered meow.Task objects.
     */
    public List<Task> searchTask(String keyword) {
        List<Task> filteredTasks = new ArrayList<>();
        for (int i = 0; i < this.tasksList.size(); i++) {
            String description = this.tasksList.get(i).getDescription();
            if (description.contains(keyword)) {
                filteredTasks.add(this.tasksList.get(i));
            }
        }
        return filteredTasks;
    }
}
