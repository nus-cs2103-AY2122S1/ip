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
        ArrayList items = new ArrayList<String>();
        int counter = 0;

        String input = sc.nextLine();
        while (!input.equals("bye") && !input.equals("list")) {
            counter++;
            items.add(input);
            String newString = String.format("added: %s", input);
            printer.PrintMessage(newString);
            input = sc.nextLine();
        }
        printer.PrintMessage("Bye. Hope to see you again soon!");
    }
}
