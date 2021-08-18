import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Duke {
    // constants for special commands
    private static final String CMD_BYE = "bye";
    private static final String CMD_LIST = "list";

    // static fields
    private static List<Task> tasks = new ArrayList<>();

    private static void displayGreetings() {
        System.out.println("\tHello this is Yiyang-bot :D");
        System.out.println("\tWhat can I do for you?");
    }

    private static void displayList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("\t%d. %s",
                    i+1, tasks.get(i)));
        }
    }


    private static void displayBye() {
        System.out.println("\tBye. Hope to see you again.");
    }

    public static void main(String[] args) {
        displayGreetings();

        Scanner sc = new Scanner(System.in);
        String currCommand = sc.nextLine();

        while (!currCommand.equals(CMD_BYE)) {
            if (currCommand.equals(CMD_LIST)) {
                displayList();
            } else {
                tasks.add(new Task(currCommand));
                System.out.println("\t added: " + currCommand);
            }

            currCommand = sc.nextLine();
        }

        displayBye();
    }
}