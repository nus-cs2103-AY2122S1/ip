import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____   ___  _        \n"
                + "|  _ \\/  _ \\| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\___/|_|\\_\\___|\n";
        System.out.println("----------------------------------------");
        System.out.println(logo);
        System.out.println("Hello! I'm Doke\nWhat do you want??");
        System.out.println("----------------------------------------");
        readMessage();
    }

    /**
     * Reads messages from input. If input is not "bye", add the input to List.
     * If input is "bye", exit program.
     */
    public static void readMessage() {
        Scanner messageReader = new Scanner(System.in);
        List list  = new List();
        boolean end = false;
        while (!end) {
            String message = messageReader.nextLine();
            System.out.println("----------------------------------------");
            if (message.equals("bye")) {
                System.out.println("    Bye. Hope to see you again!");
                end = true;
            } else if (message.equals("list")){
                list.listItems();
            } else {
                list.addItem(message);
            }
            System.out.println("----------------------------------------");
        }

    }
}
