import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Duke {
    // constants for special commands
    private static final String CMD_BYE = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_DONE = "done";
    private static final String CMD_TODO = "todo";
    private static final String CMD_DDL = "deadline";
    private static final String CMD_EVENT = "event";

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

    private static void displayAddedNew(Task newItem) {
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + newItem);
        System.out.println(String.format("\tNow you have %d tasks in the list.", tasks.size()));
    }

    private static void markAsDone(int id) throws IndexOutOfBoundsException {
        if (id >= tasks.size()) {
            throw new IndexOutOfBoundsException("Do not have such a task in the list.");
        }

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
            Task newItem = null;

            try {
                switch (currCommand) {
                case CMD_LIST:
                    displayList();
                    break;
                case CMD_DONE:
                    int doneId = Integer.parseInt(currLine.split(" ")[1]);
                    markAsDone(doneId);
                    break;
                case CMD_TODO:
                    if (currLine.length() <= 4 || currLine.substring(5).strip().equals("")) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }

                    newItem = new Todo(currLine.substring(5).strip());
                    break;
                case CMD_DDL:
                    newItem = Deadline.parseNewCommand(currLine);
                    break;
                case CMD_EVENT:
                    newItem = Event.parseNewCommand(currLine);
                    break;
                default:
                    throw new DukeException("Sorry, Yiyang-bot doesn't know what does that mean.");
                }

                // check if any new item added
                if (newItem != null) {
                    tasks.add(newItem);
                    displayAddedNew(newItem);
                }
            } catch (IllegalArgumentException | IndexOutOfBoundsException | DukeException e) {
                System.err.println("\tOops :( " + e);
            } finally {
                currLine = sc.nextLine();
                currCommand = currLine.split(" ")[0];
            }
        }

        displayBye();
    }
}