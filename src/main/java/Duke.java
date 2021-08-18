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
        System.out.print(user);

        Scanner scanner = new Scanner(System.in);
        String userInput;
        int nTasks = 0;
        String[] tasks = new String[100];
        for (int i = 0; i < 100; i++)
            tasks[i] = "";


        do {
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye"))
                break;

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println(duke + "\n\tTasks:");
                if (nTasks == 0)
                    System.out.println("\t\tNothing in list");
                else
                    for (int i = 0; i < nTasks; i++) {
                        System.out.printf("\t\t%d. %s\n", i + 1, tasks[i]);
                    }
            }
            else {
                tasks[nTasks++] = userInput;
                System.out.println(duke + "\n\tAdded: " + userInput);
            }

            System.out.print(user);
        } while (true);

        System.out.println(duke + "Bye. Have a nice day.");
    }
}
