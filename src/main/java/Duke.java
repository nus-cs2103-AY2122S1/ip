import java.util.Scanner;

public class Duke {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String INTRO_MESSAGE = "Hello~ I'm Duke ʕ•ᴥ•ʔ \n" + logo + "\nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Goodbyeeee! Hope to see you again soon! ʕっ• ᴥ •ʔっ";
    private static final String SEPARATOR = "------------------------------------";

    private static void printReply(String str) {
        System.out.println("\n" + "DUKE:\n" + str + "\n" + SEPARATOR + "\n ");
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        TaskHandler taskHandler = new TaskHandler();
        printReply(INTRO_MESSAGE);
        Command command;

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String inputUpperCase = input.trim().toUpperCase();
            command = Command.evaluateInput(inputUpperCase);
            switch (command) {
                case LIST:
                    printReply(taskHandler.printList());
                    break;
                case BYE:
                    printReply(EXIT_MESSAGE);
                    sc.close();
                    return;
                default:
                    taskHandler.printList();
                    printReply(taskHandler.addTask(new Task(input)));
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

}