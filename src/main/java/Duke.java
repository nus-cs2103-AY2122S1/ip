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
            // Split into commands that add to the list and those that do not.
            if (command.equals("todo")) {
                String description = splitMessage[1];
                list.addTask(description);
            } else if (command.equals("deadline") || command.equals("event")) {
                String description = splitMessage[1].split("/")[0];
                String dateTime = splitMessage[1].split("/")[1].split(" ", 2)[1];
                list.addTask(description, dateTime, command);
            } else { // For commands that require only outputting information
                switch(command) {
                    case("bye"):
                        System.out.println("    Bye. Hope to see you again!");
                        end = true;
                        break;
                    case("list"):
                        list.listItems();
                        break;
                    case("done"):
                        int taskNumber = Integer.parseInt(message.split(" ")[1]);
                        String taskMessage = list.changeTaskStatus(taskNumber);
                        System.out.println("Alright, I have marked the task as done:\n" + taskMessage);
                        break;
                    default:
                        System.out.println("Command not recognised!!");

                }
            }

            System.out.println("----------------------------------------");
        }

    }
}
