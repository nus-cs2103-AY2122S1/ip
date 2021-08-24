import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Duke {
    public static void main(String[] args) {

        DukeStorage storage = new DukeStorage();
        ArrayList<Task> list = storage.loadFile();
        TaskHandler taskHandler = new TaskHandler(list, storage);
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
            try {
                taskHandler.execute(command);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(line);
            command = myObj.nextLine();
        }

         if (command.toLowerCase().equals("bye")) {
             System.out.println(line);
             System.out.println(spacing + "Bye. Hope to see you again soon!");
             System.out.print(line);
         }
    }
}