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

        addToList();
    }

    /**
     * Method for duke to add things to a list and display them when called for by the user.
     * Inputs are taken by a scanner from the user's keyboard.
     */
    public static void addToList() {
        ArrayList<String> taskList = new ArrayList<>();
        System.out.println("-----------------------------------------");
        System.out.println(" Hello! I am Duke");
        System.out.println(" What can I do for you?");
        System.out.println("-----------------------------------------");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("    -----------------------------------------");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    -----------------------------------------");
                break;
            } else if (command.equals("list")) {
                System.out.println("    -----------------------------------------");
                for (int i = 0; i < taskList.size(); i++) {
                    int currNum = i + 1;
                    System.out.println("     " + currNum + ". " + taskList.get(i));
                }
                System.out.println("    -----------------------------------------");
                System.out.println();
            } else {
                System.out.println("    -----------------------------------------");
                System.out.println("     added: " + command);
                System.out.println("    -----------------------------------------");
                System.out.println();
                taskList.add(command);
            }
        }
        sc.close();
    }
}
