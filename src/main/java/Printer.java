import java.util.ArrayList;

public class Printer {
    String border;
    public Printer(String border) {
        this.border = border;
    }

    public void PrintMessage(String message) {
        System.out.println(border);
        System.out.println(message);
        System.out.println(border);
    }

    public void PrintList(ArrayList<Task> message) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= message.size(); i++) {
            Task thisTask = message.get(i-1);
            String toPrint = String.format("%d.[%s] %s", i, thisTask.getStatusIcon(), thisTask.description);
            System.out.println(toPrint);
        }
    }
}
