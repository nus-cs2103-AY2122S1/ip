import java.sql.SQLOutput;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\r\nWhat can I do for you?");
        ArrayList<String> commandList = new ArrayList<>(); // init arraylist outside infinite loop.
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine(); // Can also convert result to lower-case to handle cases.
            switch(command) {
                case "list":
                    for(int i = 0; i < commandList.size(); i++) {
                        System.out.println((i+1) + ". re" + commandList.get(i));
                    }
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                default:
                    commandList.add(command);
                    System.out.println("Added: " + command);

            }
            if(command.equals("bye")) {
                break;
            }
        }



    }
}
