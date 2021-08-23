import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Formats an output by adding a top and bottom border and displays it to the terminal
     * @param output The terminal output to format
     */
    private static void display(String output) {
        String horizontalLine = "\t ____________________________________________________________ \n";
        System.out.println(horizontalLine + "\t " + output + "\n" + horizontalLine);
    }

    public static void main(String[] args) {
        // Greet the user
        String introduction = "Hello! I'm Duke, your personal assistant. \n" +
                "\t What can I do for you? \n";
        display(introduction);

        // Read and handle inputs from the user
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            // Separate user input into command (first word) and data (rest of string)
            String input = s.nextLine();
            String command = "";
            String data = "";
            if (input.contains(" ")) {
                String[] splitInput = input.split(" ", 2);
                command = splitInput[0];
                data = splitInput[1];
            } else {
                command = input;
            }

            // Handle user input
            if (command.equals("bye")) {
                // Exit
                display("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                // Display list of tasks
                String output = "";
                for (int i = 0; i < tasks.size(); i++) {
                    output += String.valueOf(i + 1) + ". " + tasks.get(i) + "\n\t ";
                }
                display(output);
            } else if (command.equals("done")) {
                // Mark task as done
                int index = Integer.parseInt(data);
                Task t = tasks.get(index - 1);
                t.markAsDone();
                display("Nice! I've marked this task as done: \n\t\t " + t);
            } else if (command.equals("todo")) {
                // Store task as Todo
                if (data.equals("")) {
                    display("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    Task t = new Todo(data);
                    tasks.add(t);
                    display("Got it. I've added this task: \n\t\t "
                            + t
                            + "\n\t Now you have " + tasks.size() + " tasks in the list.");
                }
            } else if (command.equals("deadline")) {
                // Store task as Deadline
                String description = data.split(" /by")[0];
                String by = data.split("/by ")[1];
                Task t = new Deadline(description, by);
                tasks.add(t);
                display("Got it. I've added this task: \n\t\t "
                        + t
                        + "\n\t Now you have " + tasks.size() + " tasks in the list.");
            } else if (command.equals("event")) {
                // Store task as Event
                String description = data.split(" /at")[0];
                String at = data.split("/at ")[1];
                Task t = new Event(description, at);
                tasks.add(t);
                display("Got it. I've added this task: \n\t\t "
                        + t
                        + "\n\t Now you have " + tasks.size() + " tasks in the list.");
            } else if (command.equals("delete")) {
                // Delete task
                int index = Integer.parseInt(data);
                Task t = tasks.get(index - 1);
                tasks.remove(index - 1);
                display("Noted. I've removed this task: \n\t\t "
                        + t
                        + "\n\t Now you have " + tasks.size() + " tasks in the list.");
            } else {
                display("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        s.close();
    }
}
