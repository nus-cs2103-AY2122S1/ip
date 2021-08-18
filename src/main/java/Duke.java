import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<Task>();
    private static void printList() {
        for(int i = 0; i < list.size(); i++) {
            int index = i + 1;
            System.out.println( index + ": " + list.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n" + logo);
        while(true) {
            System.out.println("____________________________________________________________");
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (command.equals("list")) {
                printList();
                continue;
            }
            Task newTask = new Task(command);
            System.out.println("added: " + newTask);
            list.add(newTask);

        }
    }
}
