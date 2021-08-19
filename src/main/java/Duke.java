import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Duke {
    public static void main(String[] args) {

        ArrayList<Task> list = new ArrayList<>();
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
                System.out.println(spacing + "Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format(spacing + "%d.%s", (i + 1), list.get(i).toString()));
                }
            } else if (command.length() > 5 && command.toLowerCase().substring(0, 5).equals("done ")) {
                Task item = list.get(Integer.parseInt(command.substring(5)) - 1);
                item.markAsDone();
                System.out.println(spacing + "Nice! I've marked this task as done:");
                System.out.println(spacing + "  " + item.toString());
                // if(item num more than list length then throw error)
            } else {
                Task t = new Task(command);
                list.add(t);
                System.out.println(spacing + "added : " + command);
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