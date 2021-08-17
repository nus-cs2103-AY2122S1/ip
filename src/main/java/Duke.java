import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Im Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        String border = "____________________________________________________________";
        Printer printer = new Printer(border);
        ArrayList<Task> items = new ArrayList<Task>();

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            items.add(new Task(input));
            printer.PrintMessage(String.format("added: %s", input));
            input = sc.nextLine();

            if (input.equals("list")) {
                printer.PrintList(items);
                input = sc.nextLine();
            }
        }
        printer.PrintMessage("Bye. Hope to see you again soon!");
    }
}
