package duke.task;

import duke.Storage;
import duke.UI;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void printTaskList(UI ui) {
        if (this.tasks.size() == 0) {
            ui.printNoTaskAvailable();
        } else {
            ui.printTaskList(this.tasks);
        }
    }

    public void printTasksOnDate(String dateString, UI ui) {
        LocalDate date = LocalDate.parse(dateString);
        ui.printTaskOnDate(this.tasks, date);
    }

    public void markTaskAsDone(int taskNumber, Storage storage, UI ui) throws FileNotFoundException {
        Task task = this.tasks.get(taskNumber - 1);
        task.markAsDone();
        storage.update(this.tasks);
        ui.printTaskMarkedDone(task);
    }

    public void deleteTask(int taskNumber, Storage storage, UI ui) throws FileNotFoundException {
        Task task = this.tasks.get(taskNumber - 1);
        this.tasks.remove(taskNumber - 1);
        storage.update(this.tasks);
        ui.printDeleteTask(task);
    }

    public void addTodo(String description, Storage storage, UI ui) throws FileNotFoundException {
        Task task = new Todo(description);
        this.tasks.add(task);
        storage.update(this.tasks);
        ui.printAddTask(task);
    }

    public void addDeadline(String description, String deadline, Storage storage, UI ui) throws FileNotFoundException {
        Task task = new Deadline(description, deadline);
        this.tasks.add(task);
        storage.update(this.tasks);
        ui.printAddTask(task);
    }

    public void addEvent(String description, String time, Storage storage, UI ui) throws FileNotFoundException {
        Task task = new Event(description, time);
        this.tasks.add(task);
        storage.update(this.tasks);
        ui.printAddTask(task);
    }
}
