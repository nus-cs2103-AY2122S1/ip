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
    private static final String INVALID_DONE_INPUT = "Please enter a number starting from 1! :~)";
    private static final String BLANK_INPUT_MESSAGE = "Please enter something! ANYTHING!";

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
            try {
                if (inputUpperCase.isEmpty()) {
                    throw new DukeException(BLANK_INPUT_MESSAGE);
                }
                // Get the first word of the command
                String commandWord = input.split("\\s+")[0];
                String detailsExtracted = input.replace(commandWord, "").trim();
                command = Command.evaluateInput(commandWord);
                switch (command) {
                    case LIST:
                        printReply(taskHandler.toString());
                        break;
                    case DONE:
                        try {
                            int taskIndex = Integer.parseInt(detailsExtracted);
                            printReply(taskHandler.markTaskAsDone(taskIndex));
                        } catch (NumberFormatException e) {
                            throw new DukeException(INVALID_DONE_INPUT);
                        }
                        break;
                    case BYE:
                        printReply(EXIT_MESSAGE);
                        sc.close();
                        return;
                    default:
                        taskHandler.toString();
                        printReply(taskHandler.addTask(new Task(input)));
                        break;
                }
            } catch (DukeException e) {
                printReply(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

}