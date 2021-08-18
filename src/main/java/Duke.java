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
            String message = messageReader.nextLine();
            String command = message.split(" ")[0];
            System.out.println("----------------------------------------");
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
                    System.out.println("Alright, I have marked the task as done:\n" +
                            taskMessage);
                    break;
                default:
                    list.addItem(message);

            }
            System.out.println("----------------------------------------");
        }

    }
}
