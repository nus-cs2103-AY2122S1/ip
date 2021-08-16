import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
        try {
            Scanner scanner = new Scanner(System.in);
            boolean run = true;
            while (run) {
                String input = scanner.nextLine();
                printLine();
                String[] inputArr = input.split(" ", 2);
                switch (inputArr[0]) {
                    case "todo":
                        addTask(new Todo(inputArr[1]));
                        break;
                    case "deadline":
                        String[] messageArr = inputArr[1].split(" /by ", 2);
                        addTask(new Deadline(messageArr[0], messageArr[1]));
                        break;
                    case "event":
                        messageArr = inputArr[1].split(" /at ", 2);
                        addTask(new Event(messageArr[0], messageArr[1]));
                        break;
                    case "done":
                        int number = Integer.parseInt(inputArr[1]);
                        System.out.println("    Nice! I've marked this task as done: ");
                        list.get(number - 1).markedAsDone();
                        System.out.println("      " + list.get(number - 1).toString());
                        break;
                    case "list":
                        listItems();
                        break;
                    case "bye":
                        System.out.println("    " + "Bye. Hope to see you again soon!");
                        run = false;
                        break;
                }
                printLine();
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void listItems() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            int num = i + 1;
            System.out.println("      " + num + "." + list.get(i).toString());
        }
    }

    public static void addTask(Task t) {
        System.out.println("     Got it. I've added this task: ");
        list.add(t);
        System.out.println("      " + t.toString());
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
}
