import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> storage = new ArrayList<Task>();

    public static void main(String[] args) {
        print("Hello! My name is Alexa \nHow can I help you today?");
        run();
    }

    public static void print(String text) {
        System.out.println("=======================================");
        text.lines().map(x -> "    " + x).forEach(x -> System.out.println(x));
        System.out.println("=======================================");
    }

    public static void run() {
        while(true) {
            Scanner newInput = new Scanner(System.in);
            String input = newInput.nextLine();
            Task currentTask = new Task(input);
            if (input.equals("bye")) {
                print("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                list();
            } else if (input.length() >= 5 && input.substring(0, 5).equals("done ")) {
                done(input);
            } else {
                    storage.add(currentTask);
                    print("added: " + input);
            }
        }
    }

    public static void list() {
        int len = storage.size();
        String sentence = "";
        for (int i = 1; i < len + 1; i++) {
            sentence = sentence + i + "." + "[" + storage.get(i - 1).getStatusIcon() + "] "
                    + storage.get(i - 1).getDescription() + "\n";
        }
        print(sentence);
    }

    public static void done(String doneEntry) {
        int taskNumber = Integer.parseInt(doneEntry.substring(5,6));
        Task doneTask = storage.get(taskNumber - 1);
        doneTask.markAsDone();
        print("Congratulations on finishing this task!\n [X] " + doneTask.getDescription());
    }
}
