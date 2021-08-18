import java.util.ArrayList;

public class Printer {
    String border;
    public Printer(String border) {
        this.border = border;
    }

    public void PrintIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Im Duke\nWhat can I do for you?");
    }

    public void PrintMessage(String message) {
        System.out.println(border);
        System.out.println(message);
        System.out.println(border);
    }

    public void PrintList(ArrayList<Task> message) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(border);
        for (int i = 1; i <= message.size(); i++) {
            Task thisTask = message.get(i-1);
            String toPrint = String.format("%d. %s", i, thisTask);
            System.out.println(toPrint);
        }
        System.out.println(border);
    }

    public void PrintSpecialTasks(String message, int total) {
        String newMsg = String.
                format("Got it, I've added this task:\n  %s\nNow you have a total of %d tasks in the list.",
                message, total);
        PrintMessage(newMsg);
    }

    public void PrintDelete(String message, int total) {
        String newMsg = String.
                format("Noted. I've removed this task:\n  %s\nNow you have a total of %d tasks in the list.",
                        message, total);
        PrintMessage(newMsg);
    }
}
