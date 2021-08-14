import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>(100);

    private static void chat(String content) {
        System.out.println(
                "____________________________________________________________\n"
                + content
                + "\n____________________________________________________________\n"
        );
    }

    private static void addItem(String item) {
        list.add(new Task(item));
    }

    private static void displayList() {
        StringBuilder listString = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            listString.append(String.valueOf(i + 1) + ".[" + task.getStatusIcon() + "] " + task.description);
            if (i != list.size() - 1) {
                listString.append("\n");
            }
        }
        chat(listString.toString());
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        chat("Hello I'm\n" + logo + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] arguments = input.split(" ");
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                displayList();
            } else if (arguments[0].equals("done")) {
                Task task = list.get(Integer.valueOf(arguments[1]) - 1);
                task.markDone();
                chat("Nice! I've marked this task as done: \n"
                        + "  ["
                        + task.getStatusIcon()
                        + "] "
                        + task.description);
            } else {
                addItem(input);
                chat("added: " + input);
            }
        }
        chat("Bye. Hope to see you again soon!");
    }


}
