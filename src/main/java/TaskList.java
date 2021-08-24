import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> ls;

    TaskList(ArrayList<Task> ls) {
        this.ls = ls;
    }

    public void addTask(Task task) {
        ls.add(task);
    }

    public void removeTask(int index) {
        ls.remove(index);
    }

    public void markAsDone(int index) {
        ls.get(index).markAsDone();
    }

    public Task getTask(int index) {
        return ls.get(index);
    }

    public int getSize() {
        return ls.size();
    }

    public void displayList() {
        if (ls.size() == 0) {
            System.out.println("You currently do not have any task!");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < ls.size(); i++) {
                System.out.println((i + 1) + "." + ls.get(i).toString());
            }
        }
    }

    public void printAddTask() {
        System.out.println("Got it. I've added this task: ");
        System.out.println(ls.get(ls.size() - 1).toString());
        System.out.println("Now you have " + ls.size() + " tasks in the list.");
    }
}
