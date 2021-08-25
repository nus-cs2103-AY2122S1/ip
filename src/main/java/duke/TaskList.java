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

    public void listAllTasks() {
        if (listOfTasks.isEmpty()) {
            System.out.println("You currently have no tasks! Add one now ☻");
        } else {
            System.out.println("Here are the tasks in your list:");
            int size = listOfTasks.size();
            for (int i = 0; i < size; i++) {
                Task t = listOfTasks.get(i);
                System.out.println((i + 1) + "." + t.toString());
            }
        }
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

    public void markTaskAsDone(int index) {
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
}
