import java.util.ArrayList;

public class TaskManager {
    ArrayList<Task> taskArrayList = new ArrayList<>();

    public void handle(String command) {
        String[] arr = command.split(" ");
        String firstWord = arr[0];
        switch (firstWord) {
            case "done":
                int id = Integer.parseInt(arr[1]);
                taskArrayList.get(id - 1).setDone(true);
                System.out.println("Your task has been marked as done.");
                list();
                break;
            case "todo":
                String remaining = command.substring(5);
                add(new ToDo(remaining));
                System.out.println("Your new todo has been added.");
                break;
            case "deadline":
                int byIndex = command.indexOf("/by");
                if (byIndex == -1) {
                    System.out.println("Sorry, invalid command.");
                }
                String deadlineName = command.substring(9, byIndex - 1);
                String deadlineBy = command.substring(byIndex + 4);
                add(new Deadline(deadlineName, deadlineBy));
                System.out.println("Your new deadline has been added.");
                break;
            case "event":
                int atIndex = command.indexOf("/at");
                if (atIndex == -1) {
                    System.out.println("Sorry, invalid command.");
                }
                String eventName = command.substring(6, atIndex - 1);
                String eventAt = command.substring(atIndex + 4);
                add(new Event(eventName, eventAt));
                System.out.println("Your new event has been added.");
                break;
            default:
                System.out.println("Sorry, invalid command.");
        }
    }

    public void add(Task task) {
        taskArrayList.add(task);
    }

    public void list() {
        for (int i = 0; i < taskArrayList.size(); i++) {
            System.out.println(i+1 + "." + taskArrayList.get(i).toString());
        }
        System.out.println();
    }
}
