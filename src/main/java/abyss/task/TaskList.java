package abyss.task;

import abyss.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a task list.
     */
    public TaskList() {}

    /**
     * Adds a new to-do to the task list.
     *
     * @param description Description for the new to-do.
     * @return Added to-do.
     */
    public Task addToDo(String description) {
        Task newTask = new ToDo(description);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds a new deadline to the task list.
     *
     * @param description Description for the new deadline.
     * @param by Date of the new deadline.
     * @return Added deadline.
     * @throws DateTimeParseException If format of input date is wrong.
     */
    public Task addDeadline(String description, String by) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(by);
        Task newTask = new Deadline(description, date);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds a new event to the task list.
     *
     * @param description Description for the new event.
     * @param at Date of the new event.
     * @return Added event.
     * @throws DateTimeParseException If format of input date is wrong.
     */
    public Task addEvent(String description, String at) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(at);
        Task newTask = new Event(description, date);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param i Index of the task list at which the task is to be deleted.
     */
    public void delete(int i) {
        String removedMsg = "Task piece is swallowed by the Abyss.";
        String tasksLeftMsg = "The Abyss now contains " + (getNumberOfTasks() - 1) + " task piece(s).";
        String task = tasks.get(i - 1).toString();
        tasks.remove(i - 1);
        Ui.reply(removedMsg, task, tasksLeftMsg);
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param i Index of the task list at which the task is to be marked as done.
     */
    public void markAsDone(int i) {
        Task task = tasks.get(i - 1);
        task.markAsDone();
        String markedTask = "  " + task.toString();
        Ui.reply("Task piece is lit up in the Abyss.", markedTask);
    }

    /**
     * Filters tasks by description according to a keyword and lists the tasks.
     *
     * @param keyword Keyword to filter the task list by.
     */
    public void find(String keyword) {
        keyword = keyword.trim();
        String regex = "[ -~]*" + keyword + "[ -~]*";
        TaskList filteredTasks = new TaskList();
        for (int i = 0; i < this.getNumberOfTasks(); i++) {
            Task task = this.get(i);
            if (!task.description.matches(regex)) {
                continue;
            }
            filteredTasks.tasks.add(task);
        }
        filteredTasks.list();
    }

    /**
     * Lists the current tasks.
     */
    public void list() {
        System.out.println(Ui.formatListReply(this));
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }
}
