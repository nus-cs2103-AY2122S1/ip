import java.util.*;

public class Duke {
    public static void main(String[] args) {
        List<String> taskList = new ArrayList<>();

        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello from\n" + logo);
        System.out.println("\tHello! I'm Duke" + "\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            switch (command) {
                case "list":
                    System.out.println("\t____________________________________________________________");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("\t" + (i + 1) + ". " + taskList.get(i));
                    }
                    System.out.println("\t____________________________________________________________");
                    break;
                default:
                    taskList.add(command);
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t added: " + command);
                    System.out.println("\t____________________________________________________________");
                    break;
            }
            command = sc.nextLine();
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
