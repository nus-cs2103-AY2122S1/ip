import java.util.ArrayList;

public class TaskList {

    private ArrayList<String> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public boolean addTask(String task) {
        try {
            tasks.add(task);
        } catch (Exception e) {
            System.err.println("Fail to add task: " + e.getMessage());
            return false;
        }
        return true;
    }

    public String printList() {
        StringBuilder listAsString = new StringBuilder();
        int count = 0;
        for (String task : tasks) {
            count++;
            listAsString.append(Duke.space);
            listAsString.append(count).append(". ");
            listAsString.append(task);
            listAsString.append("\n");
        }
        if (count > 0) {
            listAsString.setLength(listAsString.length() - 1);
        }
        return listAsString.toString();
    }

}
