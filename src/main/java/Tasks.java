import java.util.ArrayList;

public class Tasks {
    private ArrayList<Task> tasks = new ArrayList<>();
    //for division
    private static String ind = "    ";
    //for sentences
    private static String ind2 = "     ";
    private static String div = ind + "____________________________________________________________";

    public void addTask(String t) {
        this.tasks.add(new Task(t));
        noteAdded(t);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    private void noteAdded(String t) {
        System.out.println(div + "\n" + ind2 + "added: " + t + "\n" + div);
    }

    public void printTasks() {
        System.out.println(div);
        System.out.println(ind2 + "Here are the tasks in your list:");
        int i = 1;
        for (Task task: tasks) {
            System.out.println( ind2+ i + ". "+
                    "[" + task.getStatusIcon() + "] " + task);
            i++;
        }
        System.out.println(div);
    }

    public void complete(int pos) {
        if (this.tasks.size()>pos-1 && pos != 0) {
            String p = this.tasks.get(pos-1).finished();
            System.out.println(div + "\n" + ind2 + "Nice! I've marked this task as done: " + "\n" +
                    ind2 + ind2 + p + "\n" + div);
        }
    }
}
