import memory.Storage;
import memory.Task;

import java.util.regex.Pattern;

// This class defines the logic of the chatbot
public class Response {
    private static void hLine() {
        System.out.println("\t----------------------------------------------");
    }
    private static void display(String arg) { System.out.println("\t" + arg);}
    private static void display(Task arg) { System.out.println("\t  " + arg);}

    public static void respond(String reply) {
        hLine();
        System.out.println("\t" + reply);
        hLine();
    }

    public static void respond(String[] reply) {
        hLine();
        for (int i = 0; i < reply.length - 1; i++) {
            System.out.println("\t" + reply[i]);
        }
        System.out.println("\t" + reply[reply.length - 1]);
        hLine();
    }

    public static void respond(String query, Storage storage) {
        hLine();
        String[] queryArr = ReplyParser.parse(query);
        String command = queryArr[0];
        switch (command) {
            case "list":
                display("Here are the tasks in your list:");
                Task[] toShow = storage.getStorage();
                for (int i = 0; i < toShow.length; i++) {
                    Task entry = toShow[i];
                    int num = i + 1;
                    display(num + "." + entry);
                }
                break;
            case "bye":
                display("Bye! Hope to see you again soon!");
                break;
            case "done":
                int idx = Integer.parseInt(queryArr[1]);
                Task markedTask = storage.markDone(idx);
                display("Nice! I've marked this task as done:");
                display(markedTask);
                break;
            default:
                Task addedTask = storage.push(queryArr);
                display("Got it. I've added this task: ");
                display(addedTask);
                display("\t" + "Now you have " + storage.numTasks() + " tasks in the list");
                break;
        }
        hLine();
    }
}
