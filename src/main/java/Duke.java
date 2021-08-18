import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<Task>();
    private static void printList() {
        System.out.println("Here are the tasks in your list: \n");
        for(int i = 0; i < list.size(); i++) {
            int index = i + 1;
            System.out.println( index + ". [" + list.get(i).getStatus() + "] " + list.get(i));
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
            String[] commandSplit = command.split("\\s");

            if (commandSplit[0].equals("done")) {
                int index = Integer.valueOf(commandSplit[1]) - 1;
                list.get(index).setDone();
                System.out.println("I've marked this task as done: \n");
                System.out.println("[X] " + list.get(index));
                continue;
            }
            Task newTask = new Task(command);
            System.out.println("added: " + newTask);
            list.add(newTask);

        }
    }
}
