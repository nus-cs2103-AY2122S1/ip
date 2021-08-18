import java.util.ArrayList;

public class TaskManager {
    ArrayList<Task> taskArrayList = new ArrayList<>();

    public void handle(String command) {
        String[] arr = command.split(" ", 2);
        String firstWord = arr[0];
        if (firstWord.equals("done")) {
            int id = Integer.parseInt(arr[1]);
            setDone(id);
            System.out.println("Your task has been marked as done.");
            list();
        } else {
            add(new Task(command));
            System.out.println("Added: " + command + "\n");
        }
    }

    public void add(Task task) {
        taskArrayList.add(task);
    }

    public void list() {
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task t = taskArrayList.get(i);
            String isDone = t.isDone() ? "x" : " ";
            System.out.println(String.format("%d.[%s] %s", i + 1, isDone, t));
        }
        System.out.println();
    }

    public void setDone(int id) {
        taskArrayList.get(id - 1).setDone(true);
    }
}
