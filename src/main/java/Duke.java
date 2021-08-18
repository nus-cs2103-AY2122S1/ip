import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Duke {
    // constants for special commands
    private static final String CMD_BYE = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_DONE = "done";

    // static fields
    private static List<Task> tasks = new ArrayList<>();

    private static void displayGreetings() {
        System.out.println("\tHello this is Yiyang-bot :D");
        System.out.println("\tWhat can I do for you?");
    }

    private static void displayList() {
        if (tasks.size() == 0) {
            System.out.println("\tYou don't have any task now.");
            return;
        }

        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("\t%d. %s",
                    i+1, tasks.get(i)));
        }
    }


    private static void displayBye() {
        System.out.println("\tBye. Hope to see you again.");
    }

    private static void markAsDone(int id) {
        tasks.get(id-1).setDone(true);

        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t  " + tasks.get(id-1));
    }

    public static void main(String[] args) {
        displayGreetings();

        Scanner sc = new Scanner(System.in);
        String currLine = sc.nextLine();
        String currCommand = currLine.split(" ")[0];

        while (!currCommand.equals(CMD_BYE)) {
            if (currCommand.equals(CMD_LIST)) {
                displayList();
            } else if (currCommand.equals(CMD_DONE)) {
                int doneId = Integer.parseInt(currLine.split(" ")[1]);
                markAsDone(doneId);
            } else {    // add
                tasks.add(new Task(currLine));
                System.out.println("\t added: " + currLine);
            }

            currLine = sc.nextLine();
            currCommand = currLine.split(" ")[0];
        }

        displayBye();
    }
}