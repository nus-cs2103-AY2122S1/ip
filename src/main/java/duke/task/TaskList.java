package duke.task;

import duke.DukeException;
import duke.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;
    private final Ui ui = new Ui();

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int taskNo) {
        return this.tasks.get(taskNo - 1);
    }

    public String saveList() {
        StringBuilder lst = new StringBuilder();
        for (int i = 1; i <= this.getSize(); i++) {
            lst.append(i).append(". ").append(this.getTask(i).toString()).append("\n");
        }
        return lst.toString();
    }

    public void taskDone(int taskNo) {
        Task task = this.getTask(taskNo);
        task.setDone();
        ui.showTaskDone(task);
    }

    public void addTaskSuffix(Task task) {
        int taskNo = this.getSize();
        String t = taskNo == 1 ? " task " : " tasks ";
        ui.makeTaskSuffix(task, taskNo, t);
    }

    public void addTask(String input) throws DukeException {
        Task newTask;

        if (input.startsWith("todo ")) {
            newTask = new Todo(input);
        } else if (input.startsWith("deadline ") && input.contains("/by ")) {
            newTask = new Deadline(input);
        } else if (input.startsWith("event ") && input.contains("/at ")) {
            newTask = new Event(input);
        } else {
            throw new DukeException("OOPS!!! Invalid task description.");
        }

        this.getTasks().add(newTask);
        addTaskSuffix(newTask);

    }

    public void deleteTask(int taskNo) {
        Task removedTask = this.getTasks().remove(taskNo - 1);
        int tasksLeft = this.getSize();
        String t = tasksLeft == 1 ? " task " : " tasks ";
        ui.showDeleteTask(removedTask, tasksLeft, t);
    }

    public void findTasksOnDate(LocalDate date) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : this.getTasks()) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.dueDate.isEqual(date)) {
                    foundTasks.add(d);
                }
            } else if (task instanceof Event) {
                Event e = (Event) task;
                if (e.eventDate.isEqual(date)) {
                    foundTasks.add(e);
                }
            }
        }

        int num = foundTasks.size();
        ui.showTasksOnDate(date, num, foundTasks);
    }


}
