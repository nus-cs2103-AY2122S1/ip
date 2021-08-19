import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\  ___ | | _____ \n"
                + "| | | |/ _ \\| |/ / _ \\\n"
                + "| |_| | |_| |   < __/\n"
                + "|____/ \\___/|_|\\_\\___|\n";
        System.out.println("----------------------------------------");
        System.out.println(logo);
        System.out.println("Hello! I'm Doke\nWhat do you want??");
        System.out.println("----------------------------------------");
        readMessage();
    }

    /**
     * Reads messages from input. Perform operations based on the input given.
     */
    public static void readMessage() {
        Scanner messageReader = new Scanner(System.in);
        List list  = new List();
        boolean end = false;
        while (!end) {
            // Get input and split by space to get the first word (the command)
            String message = messageReader.nextLine();
            String[] splitMessage = message.split(" ", 2);
            String command = splitMessage[0];

            // Output a response based on command
            System.out.println("----------------------------------------");
            String description;
            String dateTime;
            switch(command) {
                case("bye"): // Print exit message, exit the program
                    System.out.println("    Bye. Hope to see you again!");
                    end = true;
                    break;
                case("list"): // List the current tasks and their status
                    list.listItems();
                    break;
                case("done"): // Mark a task as done and display the task
                    try {
                        int taskNumber = Integer.parseInt(message.split(" ")[1]);
                        String taskMessage = list.changeTaskStatus(taskNumber);
                        System.out.println("Alright, I have marked the task as done:\n" + taskMessage);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("You forgot to indicate which task!!!");
                    } catch (NumberFormatException n) {
                        System.out.println("Enter done followed by an integer representing the task...");
                    } catch (DukeException d) {
                        System.out.println(d.getMessage());
                    }
                    break;
                case("todo"): // Create a ToDo task and display the task
                    try {
                        description = splitMessage[1];
                        list.addTask(description);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("The description of ToDo cannot be empty!\n Please try again :-(");
                    }
                    break;
                case("deadline"): // Create a Deadline task and display the task
                    try {
                        description = splitMessage[1].split("/by")[0];
                        dateTime = splitMessage[1].split("/by")[1];
                        list.addTask(description, dateTime, command);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Follow the format: \ndeadline %description% /by %date/time%");
                    }
                    break;
                case("event"): // Create an Event task and display the task
                    try {
                        description = splitMessage[1].split("/at")[0];
                        dateTime = splitMessage[1].split("/at")[1];
                        list.addTask(description, dateTime, command);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Follow the format: \nevent %description% /at %date/time%");
                    }
                    break;
                default: // If input does not have a recognised command
                    System.out.println("Command not recognised!!");

            }


            System.out.println("----------------------------------------");
        }

    }
}
