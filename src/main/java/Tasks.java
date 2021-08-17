import java.util.ArrayList;

public class Tasks {
    private ArrayList<String> tasks = new ArrayList<>();
    //for division
    private static String ind = "    ";
    //for sentences
    private static String ind2 = "     ";
    private static String div = ind + "____________________________________________________________";

    public void addTask(String t) {
        this.tasks.add(t);
        noteAdded(t);
    }

    public ArrayList<String> getTasks() {
        return this.tasks;
    }

    private void noteAdded(String t) {
        System.out.println(div + "\n" + ind2 + "added: " + t + "\n" + div);
    }

    public void printTasks() {
        System.out.println(div);
        int i = 1;
        for (String task: tasks) {
            System.out.println( ind2+i + ". "+task);
            i++;
        }
        System.out.println(div);
    }
}
