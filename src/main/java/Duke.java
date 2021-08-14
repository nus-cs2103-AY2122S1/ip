import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> itemList = new ArrayList<String>(0);

    /**
     * Does the logic for registering prompts.
     * @param command The command to be registered.
     */
    private static void EnterCommand(String command) {
        switch (command) {
            case "list":
                DisplayList();
                NextCommand();
                break;
            case "bye":
                ExitDuke();
                break;
            default:
                System.out.println("added: " + command);
                itemList.add(command);
                NextCommand();
        }
    }

    /**
     * Displays the itemList.
     */
    private static void DisplayList() {
        for (int i = 1; i < itemList.size() + 1; i++) {
            System.out.println(i + ". " + itemList.get(i - 1) + "\n");
        }
        NextCommand();
    }

    /**
     * Adds the string item to the itemList.
     * @param item The item to be added to the itemList.
     */
    private static void AddToList(String item) {

    }

    /**
     * Reads the input from the user and triggers the logic for the command.
     */
    private static void NextCommand() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        EnterCommand(input);
    }

    /**
     * Closes and exits Duke.
     */
    private static void ExitDuke() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke!\n" + "What can I do for you?");

        NextCommand();
    }
}
