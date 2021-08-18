import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm \n" + logo + "\nHow can I help?");

        TaskList l = new TaskList();
        int length = 0;

        start(l);

        printOutput("Bye. Hope to see you again soon!");
    }

    public static void start(TaskList l) {
        String input = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            input = scanner.nextLine();
            String[] commands = input.split(" ");
            String first = commands[0];
            String rest = "";
            if (commands.length > 1) {
                rest = input.substring(first.length() + 1);
            }
            if (first.equals("bye")){
                break;
            }
            switch (first) {
                case "list": {
                    printOutput(l.toString());
                    break;
                }
                case "done": {
                    if (commands.length > 1) {
                        Task item = l.getItem(Integer.parseInt(rest) - 1);
                        if (item != null) {
                            printOutput(item.completeItem());
                        }
                    }
                    break;
                }
                case "deadline": {
                    String[] details = rest.split("/by ");
                    Deadline deadline = new Deadline(details[0], details[1]);
                    l.addToList(deadline);
                    printOutput("Got it. I've added this task:\n" + deadline + "\nNow you have " + l.getLength()+ " tasks in the list.");
                    break;
                }
                case "todo": {
                    ToDo td = new ToDo(rest);
                    l.addToList(td);
                    printOutput("Got it. I've added this task:\n" + td + "\nNow you have " + l.getLength()+ " tasks in the list.");
                    break;
                }
                case "event": {
                    String[] details = rest.split("/at ");
                    Event event = new Event(details[0], details[1]);
                    l.addToList(event);
                    printOutput("Got it. I've added this task:\n" + event + "\nNow you have " + l.getLength()+ " tasks in the list.");
                    break;
                }
                default: {
                    l.addToList(new Task(input));
                    printOutput("added: " + input);
                }
            }
//            if (first.equals("bye")){
//                break;
//            } else if (first.equals("list")) {
//                printOutput(l.toString());
//            } else if (commands[0].equals("done") ) {
//                if (commands.length > 1) {
//                    Task item = l.getItem(Integer.parseInt(commands[1]) - 1);
//                    if (item != null) {
//                        item.completeItem();
//                    }
//                }
//            } else {
//
//            }
        }
    }

    public static void printOutput(String input) {
        String line = "-------------------------------------";
        System.out.println(line + "\n" + input + "\n" + line + "\n");
    }

}

