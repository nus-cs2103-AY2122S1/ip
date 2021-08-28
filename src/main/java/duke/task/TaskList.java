package duke.task;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

import duke.Storage;
import duke.UI;

public class TaskList {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints the task list.
     *
     * @param ui The UI object of the current Duke object
     */
    public void printTaskList(UI ui) {
        if (this.tasks.size() == 0) {
            ui.printNoTaskAvailable();
        } else {
            ui.printTaskListHeader();
            for (int i = 0; i < this.tasks.size(); i++) {
                Task task = this.tasks.get(i);
                ui.printTaskWithIndex(i + 1, task);
            }
        }
    }

    /**
     * Prints the tass which occur on the specified date
     *
     * @param dateString The date to filter tasks by
     * @param ui The UI object of the current Duke object
     */
    public void printTasksOnDate(String dateString, UI ui) {
        ui.printTasksOnDateHeader();
        LocalDate date = LocalDate.parse(dateString);
        int index = 1;

        for (Task task : this.tasks) {
            if (task instanceof Deadline) {
                LocalDate deadline = ((Deadline) task).getDeadline();
                if (deadline.equals(date)) {
                    ui.printTaskWithIndex(index, task);
                }
            } else if (task instanceof Event) {
                LocalDate time = ((Event) task).getTime();
                if (time.equals(date)) {
                    ui.printTaskWithIndex(index, task);
                }
            }
        }
    }

    /** Prints tasks with the specified keyword.
     *
     * @param keyword The keyword specified by the user.
     * @param ui The UI object of the current Duke object.
     */
    public void printTasksWithKeyword(String keyword, UI ui) {
        ui.printTasksWithKeywordHeader();
        int index = 1;
        for (Task task : this.tasks) {
            if (task.toString().contains(keyword)) {
                ui.printTaskWithIndex(index, task);
            }
        }
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber The index number of the task to be marked as done.
     * @param storage The Storage object of the current Duke object.
     * @param ui The UI object of the current Duke object.
     * @throws FileNotFoundException If the file containing the tasks is not found.
     */
    public void markTaskAsDone(int taskNumber, Storage storage, UI ui) throws FileNotFoundException {
        Task task = this.tasks.get(taskNumber - 1);
        task.markAsDone();
        storage.update(this.tasks);
        ui.printTaskMarkedDone(task);
    }

    /**
     * Deletes a task.
     *
     * @param taskNumber The index number of the task to be deleted.
     * @param storage The Storage object of the current Duke object.
     * @param ui The UI object of the current Duke object.
     * @throws FileNotFoundException If the file containing the tasks is not found.
     */
    public void deleteTask(int taskNumber, Storage storage, UI ui) throws FileNotFoundException {
        Task task = this.tasks.get(taskNumber - 1);
        this.tasks.remove(taskNumber - 1);
        storage.update(this.tasks);
        ui.printDeleteTask(task);
    }

    /**
     * Adds a Todo Task.
     *
     * @param description The description of the Todo task.
     * @param storage The Storage object of the current Duke object.
     * @param ui The UI object of the current Duke object.
     * @throws FileNotFoundException If the file containing the tasks is not found.
     */
    public void addTodo(String description, Storage storage, UI ui) throws FileNotFoundException {
        Task task = new Todo(description);
        this.tasks.add(task);
        storage.update(this.tasks);
        ui.printAddTask(task);
    }

    /**
     * Adds a Deadline Task.
     *
     * @param description The description of the Deadline task.
     * @param deadline The deadline of the task.
     * @param storage The Storage object of the current Duke object.
     * @param ui The UI object of the current Duke object.
     * @throws FileNotFoundException If the file containing the tasks is not found.
     */
    public void addDeadline(String description, String deadline, Storage storage, UI ui) throws FileNotFoundException {
        Task task = new Deadline(description, deadline);
        this.tasks.add(task);
        storage.update(this.tasks);
        ui.printAddTask(task);
    }

    /**
     * Adds an Event task.
     *
     * @param description The description of the Event task.
     * @param time The time of the event.
     * @param storage The Storage object of the current Duke object.
     * @param ui The UI object of the current Duke object.
     * @throws FileNotFoundException If the file containing the tasks is not found.
     */
    public void addEvent(String description, String time, Storage storage, UI ui) throws FileNotFoundException {
        Task task = new Event(description, time);
        this.tasks.add(task);
        storage.update(this.tasks);
        ui.printAddTask(task);
    }
}
