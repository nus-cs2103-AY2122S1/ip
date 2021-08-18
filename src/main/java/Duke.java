import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /* Default line separator design. */
    private static String sepLine = "===========================================";
    private static String sepLineOpen = "///<<<============ Duke Says: ===========>>>\\\\\\";
    private static String sepLineClose = "\\\\\\<<<===================================>>>///";

    private static TDList currTDL;
    private static boolean isExited;

    public static void main(String[] args) {
        //Initialize variables
        currTDL = new TDList();
        isExited = false;


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Give me something to do!");

        System.out.println(sepLine);

        runInputLoopMain();
    }

    /**
     * The main loop used when detecting keyboard input.
     * Stops when "bye" is detected.
     */
    private static void runInputLoopMain() {
        /* Create scanner for detecting input. */
        Scanner currScanner = new Scanner(System.in);

        /* Stores last input by user. */
        String lastInput = null;

        while (!isExited) {
            System.out.println("");
            lastInput = currScanner.nextLine();

            try {
                processCmdInput(lastInput);
            } catch (DukeExceptionBase dukeE) {
                dukeE.dukeSayErrorMsg();
            }
        }


    }

    /**
     * Runs to process the command input.
     *
     * @param lastInput   The command to process.
     */
    private static void processCmdInput(String lastInput) throws DukeExceptionBase {
        TDLTask.TaskType currTaskType = TDLTask.checkTaskType(lastInput);


        if (lastInput.equals("bye")) {
            dukeExiter();
        } else if (lastInput.equals("list")) {
            listOutTDL();
        } else if (lastInput.length() >= 4 && lastInput.substring(0, 4).equals("done")) {
            markItemDoneInTDL(lastInput);
        } else if (currTaskType != TDLTask.TaskType.NONE) {
            addToTDL(lastInput, currTaskType);

        } else {
            unknownCommandEntered();
        }
    }

    /**
     * Runs when program is going to exit.
     */
    private static void dukeExiter() {
        dukeSays("Bye. Hope to see you soon!");
        isExited = true;
    }

    /**
     * Used when Duke is supposed to say something.
     * This would print the message between 2 line separators.
     *
     * @param printThis The message to print inside Duke's text bubble
     */
    public static void dukeSays(String printThis) {
        System.out.println("");
        System.out.println(sepLineOpen);
        System.out.println(printThis);
        System.out.println(sepLineClose);
    }

    private static void unknownCommandEntered() throws DukeExceptionBase {
        throw new DukeExceptionBase("Please enter something valid!");
    }

    private static void addToTDL(String str, TDLTask.TaskType currTaskType) throws DukeExceptionBase {
        currTDL.tdlAdd(str, currTaskType);
    }


    private static void listOutTDL() {
        currTDL.printOutTDL();
    }

    private static void markItemDoneInTDL(String command) throws DukeExceptionBase {
        if (command.length() < 6) {
            throw new DukeExceptionBase("You need to specify a task to set as done.");
        }

        String taskNumberStr = command.substring(5);
        int taskNo = -1;
        try {
            taskNo = Integer.parseInt(taskNumberStr);
        } catch (NumberFormatException e) {
            throw new DukeExceptionBase("Please enter an integer.");

        }
        String dukeOutput = currTDL.markTaskAsDone(taskNo);
        dukeSays(dukeOutput);
    }


}
