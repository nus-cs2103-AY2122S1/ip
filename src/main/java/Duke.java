import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        List<Task> tasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?\n");

        while(true) {
            System.out.print("You: ");
            command = sc.nextLine();
            System.out.print("Duke: ");
            if (command.equals("bye")) {
                System.out.println("Nice talking to you, goodbye!");
                break;
            } else if (command.equals("list")) {
                System.out.println("Here's your todo list!");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).toString());
                }
                System.out.println();
            } else if (command.contains("done")) {
                tasks.get(command.charAt(command.length() - 1) - '0' - 1).doTask();
                System.out.println("Here's your todo list!");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).toString());
                }
                System.out.println();
            } else {
                tasks.add(new Task(command));
                System.out.println("\""+ command + "\" has been added to your todo list.\n");
            }
        }
    }
}
