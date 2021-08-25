package duke;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> tasks) {
        this.listOfTasks = tasks;
    }

    public TaskList() {
        this.listOfTasks = new ArrayList<Task>();
    }


    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    public boolean isEmpty() {
        return this.listOfTasks.isEmpty();
    }

    public int size() {
        return this.listOfTasks.size();
    }

    public Task get(int i) {
        return this.listOfTasks.get(i);
    }

    public void addTask(Task t) {
        listOfTasks.add(t);
        System.out.println("Okay! Task added:\n  " + t.toString());
        System.out.println("You now have " + listOfTasks.size() + " task(s) in the list.");
    }

    public void deleteTask(int index) {
        int numOfTasks = listOfTasks.size();
        if (index >= numOfTasks) {
            System.out.println("No task of this number. Add new task or input a different number.");
        } else if (index < 0) {
            System.out.println("Input a task number from 1 - " + numOfTasks);
        } else {
            Task t = listOfTasks.get(index);
            listOfTasks.remove(index);
            System.out.println("Ok! I've deleted this task:\n  " + t.toString());
            System.out.println("You now have " + (numOfTasks - 1) + " task(s) in the list.");
        }
    }

    public void taskDone(int index) {
        int numOfTasks = listOfTasks.size();
        if (index >= numOfTasks) {
            System.out.println("No task of this number. Add new task or input a different number.");
        } else if (index < 0) {
            System.out.println("Input a task number from 1 - " + numOfTasks);
        } else {
            Task t = listOfTasks.get(index);
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  " + t.toString());
        }
    }

    public TaskList findMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : this.listOfTasks) {
            String name = t.getName();
            if (name.equals(keyword) || name.contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        return new TaskList(matchingTasks);
    }
}
