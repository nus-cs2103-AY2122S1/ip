import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Presentation pst = new Presentation();
        List<String> taskList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        pst.respond("Hello! I'm Duke. \nWhat can I do for you?");

        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        pst.enterCommand();
        String command = sc.next();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                pst.printTaskList(taskList);
            } else {
                pst.respond("Added: " + command);
                taskList.add(command);
            }
            pst.enterCommand();
            command = sc.next();
        }

        sc.close();
        pst.addSpace();
        System.out.println("Program exiting... \nBye. Hope to see you again soon!\n");
    }

}
