package Duke;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> markDone(int index) {

        tasks.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(index).toString());
            return tasks;
    }

    public ArrayList<Task> addTodo(String details) {
        Todo task = new Todo(details);

        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");

        return tasks;
    }

    public ArrayList<Task> addDeadline(String details, LocalDate date) {
        Deadline task = new Deadline(details, date);

        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");

        return tasks;
    }

    public ArrayList<Task> addEvent(String details, String at) {
        Event task = new Event(details, at);
        tasks.add(task);


        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");

        return tasks;
    }

    public ArrayList<Task> deleteTask(int index) {
        String temp = tasks.get(index).toString();

        tasks.remove(index);

        System.out.println("Noted. I've removed this task: ");
        System.out.println(temp);

        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");

        return tasks;
    }

    public void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("There is no task for now :)");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < tasks.size() + 1; i++) {
                System.out.println(i + "." + tasks.get(i - 1).toString());
            }
        }
    }

    public int arrSize() {
        return tasks.size();
    }

    public ArrayList<Task> inArrayList() {
        return tasks;
    }


}
