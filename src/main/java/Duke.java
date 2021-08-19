import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Duke {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String line = "    ____________________________________________________________";
        String spacing = "     ";
        System.out.println(line);
        System.out.println(spacing + "Hello! I'm Duke");
        System.out.println(spacing + "What can I do for you?");
        System.out.println(line);
        String command = myObj.nextLine();  // Read user input
        while (!command.toLowerCase().equals("bye")) {
            System.out.println(line);
            if (command.toLowerCase().equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format(spacing + "%d. %s", (i + 1), list.get(i)));
                }
            } else {
                System.out.println(spacing + command);
                list.add(command);
            }
            System.out.println(line);
            command = myObj.nextLine();
        }

         if (command.toLowerCase().equals("bye")) {
             System.out.println(line);
             System.out.println(spacing + "Bye. Hope to see you again soon!");
             System.out.println(line);
         }
    }
}