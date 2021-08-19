import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Shivam Tiwari
 * Chat bot to echo everything we input.
 * Features: Greetings, Echo and Exit on command
 */

public class Duke {

    // instance variable to store input  task values
    static ArrayList<String> list;

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        list = new ArrayList<>();
        String input = "";
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you");
        Scanner sc = new Scanner(System.in);

        //loop to check if next input is available
        while (sc.hasNext()) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");

                //exit command for when entered exit code
                System.exit(1);
            } else if (input.equals("list")) {
                printList();
            } else {
                addToList(input);
            }
        }
    }

    /**
     * Method to add the input to the list
     *
     * @param input String input from the user
     */
    static void addToList(String input) {
        //Task task = new Task(input, false);
        list.add(input);
        System.out.println("added: " + input);
    }

    /**
     * method to echo the input back to the user
     *
     * @param input String input from the user
     */
    static void Echo(String input) {
        System.out.println(input);
    }

    /**
     * method to print task list on command
     */
    static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

}
