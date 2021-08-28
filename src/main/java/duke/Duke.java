package duke;

import duke.Tasks.BaseTask;


import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {

//    /** Default line separator designs. */
//    private static String sepLine = "===========================================";
//    private static String sepLineOpen = "///<<<============ Duke Says: ===========>>>\\\\\\";
//    private static String sepLineClose = "\\\\\\<<<===================================>>>///";

    /** Tracks whether the exit command was used. */
    private boolean isExited;


    /** Storage and Management of Duke Objects. */
    private static Duke dukeInstance;

    private DukeCommandParser currDukeCmdParser;
    private DukeStorageManager currStorageMgr;
    private DukeUi currUiCtrl;
    private DukeListMgr currDukeList;

    private Duke() {
        this.currDukeList = new DukeListMgr();
        this.currUiCtrl = new DukeUi();
        this.currDukeCmdParser = new DukeCommandParser();
        this.isExited = false;

        // Processes the path to use to access storage file
        try {
            Path xmlPath = Paths.get("dukeDocs", "listSave1.xml");
            //System.out.println(xmlPath.getFileName());
            this.currStorageMgr = new DukeStorageManager(xmlPath);
        } catch (InvalidPathException e) {
            // If there was an error loading the save file, then create a new empty one.
            this.currStorageMgr = new DukeStorageManager();
        } catch (DukeExceptionBase e) {
            // There is no Duke instance to print the error msg yet
            System.out.println(e);
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

    /**
     * Getter for Duke's Command Parser.
     *
     * @return the current Duke Command Parser.
     */
    public DukeCommandParser getCurrDukeCmdParser() {
        return this.currDukeCmdParser;
    }

    /**
     * Getter for Duke's Storage Manager.
     *
     * @return the current Duke Storage Manager.
     */
    public DukeStorageManager getCurrStorageMgr() {
        return this.currStorageMgr;
    }

    /**
     * Getter for Duke's current list manager.
     *
     * @return the current list manager.
     */
    public DukeListMgr getCurrListMgr() {
        return this.currDukeList;
    }






    public static void main(String[] args) {
        Duke currDuke = getCurrDuke();
        // Load Save File only after Duke is created.
        currDuke.getCurrStorageMgr().reloadSaveFromXMLDoc();

        System.out.println("Duke is running in the folder: " + System.getProperty("user.dir"));

        // Show Welcome Message
        currDuke.currUiCtrl.printWelcomeMessage();

        // Start accepting input
        currDuke.runInputLoopMain();
    }

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

        BaseTask.TaskType currTaskType = BaseTask.checkTaskType(lastInput);


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
        Duke currDuke = Duke.getCurrDuke();

        // Updated to work with new UI Controller which contains a buffer
        currDuke.currUiCtrl.addToDukeBuffer(printThis);
        currDuke.currUiCtrl.dukeBufferRelease();
    }

    /**
     * Used to add something that Duke will say later into it's UI buffer.
     *
     * @param printLater The message that Duke will say when the buffer is released.
     */
    public static void dukeLaterSay(String printLater) {
        Duke currDuke = Duke.getCurrDuke();

        currDuke.currUiCtrl.addToDukeBuffer(printLater);
    }

    private void unknownCommandEntered() throws DukeExceptionBase {
        throw new DukeExceptionBase("Please enter something valid!");
    }

    private void addToTDL(String str, BaseTask.TaskType currTaskType) throws DukeExceptionBase {
        this.currDukeList.tdlAdd(str, currTaskType);
    }


    private void listOutTDL() {
        this.currDukeList.printOutWholeList();
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
        String dukeOutput = this.currDukeList.markTaskAsDone(taskNo);
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
        String dukeOutput = this.currDukeList.deleteTask(taskNo);
        dukeSays(dukeOutput);
    }


}
