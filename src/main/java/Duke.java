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
            try {
                if (command.toLowerCase().equals("list")) {
                    System.out.println(spacing + "Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(String.format(spacing + "%d.%s", (i + 1), list.get(i).toString()));
                    }
                } else if (command.length() > 3 && command.toLowerCase().substring(0, 4).equals("done")) {
                    if (command.length() == 4) {
                        throw new DukeException("     ☹ OOPS!!! There must be something to be completed");
                    }
                    Task item = list.get(Integer.parseInt(command.substring(5)) - 1);
                    item.markAsDone();
                    System.out.println(spacing + "Nice! I've marked this task as done:");
                    System.out.println(spacing + "  " + item.toString());
                    // if(item num more than list length then throw error)
                } else if (command.length() > 3 && command.toLowerCase().substring(0, 4).equals("todo")) {
                    if (command.length() == 4) {
                        throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Todo t = new Todo(command.substring(5));
                    list.add(t);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println(spacing + "  " + t.toString());
                    System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
                } else if (command.length() > 7 && command.toLowerCase().substring(0, 8).equals("deadline")) {
                    if (command.length() == 8) {
                        throw new DukeException("     ☹ OOPS!!! The description and timing of a deadline cannot be empty.");
                    }
                    String[] infoArray = command.substring(9).split("/by ", 2);
                    Deadline d = new Deadline(infoArray[0], infoArray[1]);
                    list.add(d);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println(spacing + "  " + d.toString());
                    System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
                } else if (command.length() > 4 && command.toLowerCase().substring(0, 5).equals("event")) {
                    if (command.length() == 5) {
                        throw new DukeException("     ☹ OOPS!!! The description and timing of a event cannot be empty.");
                    }
                    String[] infoArray = command.substring(6).split("/at ", 2);
                    Event e = new Event(infoArray[0], infoArray[1]);
                    list.add(e);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println(spacing + "  " + e.toString());
                    System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
                } else if (command.length() > 5 && command.toLowerCase().substring(0, 6).equals("delete")) {
                    if (command.length() == 6) {
                        throw new DukeException("     ☹ OOPS!!! There must be something to be deleted");
                    }
                    Task item = list.get(Integer.parseInt(command.substring(7)) - 1);
                    System.out.println("     Noted. I've removed this task:");
                    System.out.println(spacing + "  " + item.toString());
                    list.remove(Integer.parseInt(command.substring(7)) - 1);
                    System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
                } else {
                    throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
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