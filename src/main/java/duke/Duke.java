package duke;

import duke.Tasks.TDLTask;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {

    /** Default line separator designs. */
    private static String sepLine = "===========================================";
    private static String sepLineOpen = "///<<<============ Duke Says: ===========>>>\\\\\\";
    private static String sepLineClose = "\\\\\\<<<===================================>>>///";

    /** Tracks whether the exit command was used. */
    private boolean isExited;


    /** Storage and Management of Duke Objects. */
    private static Duke dukeInstance;

    private DukeCommandParser currDukeCmdParser;
    private DukeStorageManager currStorageMgr;
    private DukeUI currUiCtrl;
    private TDList currTDL;

    private Duke() {
        this.currTDL = new TDList();
        this.currUiCtrl = new DukeUI();
        this.currDukeCmdParser = new DukeCommandParser();
        this.isExited = false;

        // Processes the path to use to access storage file
        try {
            Path xmlPath = Paths.get("../", "dukeDocs", "listSave1.xml");
            //System.out.println(xmlPath.getFileName());
            this.currStorageMgr = new DukeStorageManager(xmlPath);
        } catch (InvalidPathException e) {
            // If there was an error loading the save file, then create a new empty one.
            this.currStorageMgr = new DukeStorageManager();
        }


    }

    /**
     * Use to get the current instance of Duke that is running.
     * @return the Duke object that is running.
     */
    public static Duke getCurrDuke() {
        if (dukeInstance == null) {
            dukeInstance = new Duke();
        }
        return dukeInstance;
    }







    public static void main(String[] args) {
        Duke currDuke = getCurrDuke();

        // Show Welcome Message

        // Start accepting input
        currDuke.runInputLoopMain();
    }
        //Initialize variables
//        currTDL = new TDList();
//        isExited = false;
//
//
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        System.out.println("Give me something to do!");
//
//        System.out.println(sepLine);
//
//        runInputLoopMain();
//    }
//
    /**
     * The main loop used when detecting keyboard input.
     * Stops when "bye" is detected.
     */
    private void runInputLoopMain() {
        /* Create scanner for detecting input. */
        Scanner currScanner = new Scanner(System.in);

        /* Stores last input by user. */
        String lastInput = null;

        while (!this.isExited) {
            System.out.println("");
            lastInput = currScanner.nextLine();

            try {
                this.processCmdInput(lastInput);
            } catch (DukeExceptionBase dukeE) {
                dukeE.dukeSayErrorMsg();
            }
        }


    }

    /**
     * Runs to process the command input using the command parser.
     *
     * @param lastInput   The command to process.
     */
    private void processCmdInput(String lastInput) throws DukeExceptionBase {
        // Get the type of command that this input represents
        DukeCommandParser.CommandType cmdType = this.currDukeCmdParser.parse(lastInput);

        TDLTask.TaskType currTaskType = TDLTask.checkTaskType(lastInput);


        if (cmdType == DukeCommandParser.CommandType.BYE) {
            this.dukeExiter();
        } else if (cmdType == DukeCommandParser.CommandType.LIST) {
            this.listOutTDL();
        } else if (cmdType == DukeCommandParser.CommandType.MARK_TASK_DONE) {
            this.markItemDoneInTDL(lastInput);
        } else if (cmdType == DukeCommandParser.CommandType.DEL_TASK) {
            this.deleteTaskInTDL(lastInput);
        } else if (cmdType == DukeCommandParser.CommandType.ADD_TASK) {
            this.addToTDL(lastInput, currTaskType);

        } else {
            unknownCommandEntered();
        }
    }

    /**
     * Runs when program is going to exit.
     */
    private void dukeExiter() {
        dukeSays("Bye. Hope to see you soon!");
        this.isExited = true;
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

    private void unknownCommandEntered() throws DukeExceptionBase {
        throw new DukeExceptionBase("Please enter something valid!");
    }

    private void addToTDL(String str, TDLTask.TaskType currTaskType) throws DukeExceptionBase {
        this.currTDL.tdlAdd(str, currTaskType);
    }


    private void listOutTDL() {
        this.currTDL.printOutTDL();
    }

    private void markItemDoneInTDL(String command) throws DukeExceptionBase {
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
        String dukeOutput = this.currTDL.markTaskAsDone(taskNo);
        dukeSays(dukeOutput);
    }

    private void deleteTaskInTDL(String command) throws DukeExceptionBase {
        if (command.length() < 8) {
            throw new DukeExceptionBase("You need to specify a task to delete.");
        }

        String taskNumberStr = command.substring(7);
        int taskNo = -1;
        try {
            taskNo = Integer.parseInt(taskNumberStr);
        } catch (NumberFormatException e) {
            throw new DukeExceptionBase("Please enter an integer.");

        }
        String dukeOutput = this.currTDL.deleteTask(taskNo);
        dukeSays(dukeOutput);
    }


}
