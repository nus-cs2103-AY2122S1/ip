import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String duke = "\nDuke: ";
        String user = "\nUser: ";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(duke + "Hi, what do you want from me?\n");

        Scanner scanner = new Scanner(System.in);
        boolean exiting = false;
        String userInput;
        ArrayList<Task> tasks = new ArrayList<>();

        do {
            System.out.print(user);
            userInput = scanner.nextLine();

            Scanner userInputScanner = new Scanner(userInput);
            String operation = userInputScanner.next();

            switch (operation.toLowerCase()) {
                case "bye":
                    exiting = true;
                    break;

                case "list":
                    System.out.println(duke + "\n\tTasks:");
                    if (tasks.size() == 0)
                        System.out.println("\t\tNothing in list");
                    else
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.printf("\t\t%d.%s\n", i + 1, tasks.get(i));
                        }
                    break;

                case "done":
                    int taskNum = userInputScanner.nextInt();
                    tasks.get(taskNum - 1).setDone(true);
                    System.out.println(duke + "\n\tMarking task as completed:");
                    System.out.printf("\t\t%s\n", tasks.get(taskNum - 1));
                    break;

                default:
                    tasks.add(new Task(userInput));
                    System.out.println(duke + "\n\tAdded: " + userInput);
                    break;
            }
        } while (!exiting);

        System.out.println(duke + "Bye. Have a nice day.");
    }
}
