package abyss.task;

import abyss.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public Task addToDo(String description) {
        Task newTask = new ToDo(description);
        tasks.add(newTask);
        return newTask;
    }

    public Task addDeadline(String description, String by) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(by);
        Task newTask = new Deadline(description, date);
        tasks.add(newTask);
        return newTask;
    }

    public Task addEvent(String description, String at) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(at);
        Task newTask = new Event(description, date);
        tasks.add(newTask);
        return newTask;
    }

    public void delete(int i) {
        String removedMsg = "Task piece is swallowed by the Abyss.";
        String tasksLeftMsg = "The Abyss now contains " + (getNumberOfTasks() - 1) + " task piece(s).";
        String task = tasks.get(i - 1).toString();
        tasks.remove(i - 1);
        Ui.reply(removedMsg, task, tasksLeftMsg);
    }

    public void markAsDone(int i) {
        Task task = tasks.get(i - 1);
        task.markAsDone();
        String markedTask = "  " + task.toString();
        Ui.reply("Task piece is lit up in the Abyss.", markedTask);
    }

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
