package abyss.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import abyss.Ui;
import abyss.exception.InvalidCommandException;

/**
 * A task manager contains a task list and task actions.
 */
public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a task list.
     */
    public TaskManager() {}

    /**
     * Adds a new to-do to the task list.
     *
     * @param description Description for the new to-do.
     * @return Added to-do.
     */
    public Task addToDo(String description) {
        assert !description.equals("") : "description of a todo cannot be empty";

        Task task = new ToDo(description);
        tasks.add(task);
        return task;
    }

    /**
     * Adds a new deadline to the task list.
     *
     * @param description Description for the new deadline.
     * @param date Date of the new deadline.
     * @return Added deadline.
     * @throws DateTimeParseException If format of input date is wrong.
     */
    public Task addDeadline(String description, String date) throws DateTimeParseException {
        assert !description.equals("") : "description of a deadline cannot be empty";
        assert !date.equals("") : "date of a deadline cannot be empty";

        LocalDate parsedDate = LocalDate.parse(date);
        Task task = new Deadline(description, parsedDate);
        tasks.add(task);
        return task;
    }

    /**
     * Adds a new event to the task list.
     *
     * @param description Description for the new event.
     * @param date Date of the new event.
     * @return Added event.
     * @throws DateTimeParseException If format of input date is wrong.
     */
    public Task addEvent(String description, String date) throws DateTimeParseException {
        assert !description.equals("") : "description of an event cannot be empty";
        assert !date.equals("") : "date of an event cannot be empty";

        LocalDate parsedDate = LocalDate.parse(date);
        Task task = new Event(description, parsedDate);
        tasks.add(task);
        return task;
    }

    /**
     * Edits the description of an existing task.
     *
     * @param i Index of the task to be edited.
     * @param description New description of task.
     */
    public Task editDescription(int i, String description) {
        assert !description.equals("") : "description of a task cannot be empty";

        Task task = tasks.get(i);
        task.setDescription(description);
        return task;
    }

    /**
     * Edits the date of an existing task.
     *
     * @param i Index of the task to be edited.
     * @param date New date of task.
     */
    public Task editDate(int i, String date) throws InvalidCommandException, DateTimeParseException {
        assert !date.equals("") : "date of a deadline or event cannot be empty";

        LocalDate parsedDate = LocalDate.parse(date);
        Task task = tasks.get(i);
        task.setDate(parsedDate);
        return task;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param i Index of the task to be deleted.
     */
    public Task delete(int i) {
        Task task = tasks.get(i);
        tasks.remove(i);
        return task;
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param i Index of the task to be marked as done.
     */
    public Task markAsDone(int i) {
        Task task = tasks.get(i);
        task.markAsDone();
        return task;
    }

    /**
     * Filters tasks by description according to a keyword and lists the tasks.
     *
     * @param keyword Keyword to filter the task list by.
     */
    public String find(String keyword) {
        keyword = keyword.trim().toLowerCase();
        String regex = "[ -~]*" + keyword + "[ -~]*";
        TaskManager filteredTasks = new TaskManager();
        for (int i = 0; i < this.getNumberOfTasks(); i++) {
            Task task = this.get(i);
            if (!task.description.toLowerCase().matches(regex)) {
                continue;
            }
            filteredTasks.tasks.add(task);
        }
        return filteredTasks.list();
    }

    /**
     * Lists the current tasks.
     */
    public String list() {
        return Ui.formatListReply(this);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }
}
