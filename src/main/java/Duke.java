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
        ArrayList<Task> tasks = new ArrayList<Task>();
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] splitInput = input.split(" ");
            if (splitInput[0].equals("done")) {
                int index = Integer.parseInt(splitInput[1]) - 1;
                String returnString = tasks.get(index).markDone();
                printer.PrintMessage(returnString);
            } else if (input.equals("list")) {
                printer.PrintList(tasks);
            } else {
                tasks.add(new Task(input));
                printer.PrintMessage(String.format("added: %s", input));
            }
            input = sc.nextLine();
        }
        printer.PrintMessage("Bye. Hope to see you again soon!");
    }
}
