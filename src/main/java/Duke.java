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
            if (commands[0].equals("bye")){
                break;
            } else if (commands[0].equals("list")) {
                printOutput(l.toString());
            } else if (commands[0].equals("done") ) {
                if (commands.length > 1) {
                    Task item = l.getItem(Integer.parseInt(commands[1]) - 1);
                    if (item != null) {
                        item.completeItem();
                    }
                }
            } else {
                l.addToList(input);
                printOutput("added: " + input);
            }
        }
    }

    public static void printOutput(String input) {
        String line = "-------------------------------------";
        System.out.println(line + "\n" + input + "\n" + line + "\n");
    }

}

