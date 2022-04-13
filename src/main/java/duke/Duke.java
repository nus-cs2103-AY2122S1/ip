package duke;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class contains the main Duke class that runs the chat bot.
 */
public class Duke {

    /** Storage and Management of Duke Objects. */
    private static Duke dukeInstance;

    /** Tracks whether the exit command was used. */
    private boolean isExited;

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
     * Gets the current instance of Duke that is running.
     * Creates a new instance of Duke if there is none running.
     *
     * @return the Duke object that is running.
     */
    public static Duke getCurrDuke() {
        if (dukeInstance == null) {
            dukeInstance = new Duke();
        }
        return dukeInstance;
    }

    /**
     * Gets Duke's Command Parser.
     *
     * @return the current Duke Command Parser.
     */
    public DukeCommandParser getCurrDukeCmdParser() {
        return this.currDukeCmdParser;
    }

    /**
     * Gets Duke's Storage Manager.
     *
     * @return the current Duke Storage Manager.
     */
    public DukeStorageManager getCurrStorageMgr() {
        return this.currStorageMgr;
    }

    /**
     * Gets Duke's current list manager.
     *
     * @return the current list manager.
     */
    public DukeListMgr getCurrListMgr() {
        return this.currDukeList;
    }


    /**
     * Runs the main method that starts the whole program.
     *
     * @param currDuke The instance of Duke to start.
     */
    public static void dukeStarter(Duke currDuke) {
        assert (currDuke.currStorageMgr != null) : "Duke Storage not initialised";
        // Load Save File only after Duke is created.
        currDuke.getCurrStorageMgr().reloadSaveFromXmlDoc();

        System.out.println("Duke is running in the folder: " + System.getProperty("user.dir"));
    }



    /**
     * Processes command when the user enters something new in the GUI.
     *
     * @param lastInput What the user entered into the GUI.
     */
    public void acceptUserInput(String lastInput) {
        try {
            this.processCmdInput(lastInput);
        } catch (DukeExceptionBase dukeE) {
            dukeE.dukeSayErrorMsg();
        }
    }

    /**
     * Runs to process the command input using the command parser.
     *
     * @param lastInput   The command to process.
     * @throws DukeExceptionBase when an invalid command gets entered.
     */
    private void processCmdInput(String lastInput) throws DukeExceptionBase {
        // Get the type of command that this input represents
        DukeCommandParser.CommandType cmdType = this.currDukeCmdParser.parse(lastInput);

        BaseTask.TaskType currTaskType = BaseTask.checkTaskType(lastInput);


        if (cmdType == DukeCommandParser.CommandType.BYE) {
            this.dukeExiter();
        } else if (cmdType == DukeCommandParser.CommandType.LIST) {
            this.listOutTdl();
        } else if (cmdType == DukeCommandParser.CommandType.MARK_TASK_DONE) {
            this.markItemDoneInTdl(lastInput);
        } else if (cmdType == DukeCommandParser.CommandType.DEL_TASK) {
            this.deleteTaskInTdl(lastInput);
        } else if (cmdType == DukeCommandParser.CommandType.ADD_TASK) {
            this.addToTdl(lastInput, currTaskType);
        } else if (cmdType == DukeCommandParser.CommandType.FIND) {
            this.findTaskInTdl(lastInput);
        } else if (cmdType == DukeCommandParser.CommandType.HELP) {
            this.showHelpScreen();
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
     * Adds to Duke UI buffer when Duke is supposed to say something.
     *
     * @param printThis The message to print inside Duke's text bubble.
     */
    public static void dukeSays(String printThis) {
        Duke currDuke = Duke.getCurrDuke();

        assert (currDuke.currUiCtrl != null) : "Duke UI not initialised!";

        // Updated to work with new UI Controller which contains a buffer
        currDuke.currUiCtrl.addToDukeBuffer(printThis);

    }

    /**
     * Adds something that Duke will say later into it's UI buffer.
     *
     * @param printLater The message that Duke will say when the buffer is released.
     */
    public static void dukeLaterSay(String printLater) {
        Duke currDuke = Duke.getCurrDuke();

        assert (currDuke.currUiCtrl != null) : "Duke UI not initialised!";

        currDuke.currUiCtrl.addToDukeBuffer(printLater);
    }

    /**
     * Empties the text buffer containing what Duke is going to say.
     *
     * @return what Duke is going to say.
     */
    public String flushUiBuffer() {
        return this.currUiCtrl.dukeBufferRelease();
    }

    /**
     * Shows the help screen for Duke.
     */
    private void showHelpScreen() {
        this.currUiCtrl.showHelpScreen();
    }

    /**
     * Gets the Welcome Message in String form. Also prints the welcome message in the console.
     *
     * @return the Welcome Message.
     */
    public String getWelcomeMessage() {
        return this.currUiCtrl.printWelcomeMessage();
    }

    private void unknownCommandEntered() throws DukeExceptionBase {
        throw new DukeExceptionBase("Please enter something valid!");
    }

    private void addToTdl(String str, BaseTask.TaskType currTaskType) throws DukeExceptionBase {
        assert (this.currDukeList != null) : "Duke List not initialised!";

        this.currDukeList.tdlAdd(str, currTaskType);
    }


    private void listOutTdl() {
        assert (this.currDukeList != null) : "Duke List not initialised!";

        this.currDukeList.printOutWholeList();
    }

    private void markItemDoneInTdl(String command) throws DukeExceptionBase {
        assert (this.currDukeList != null) : "Duke List not initialised!";

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

    private void deleteTaskInTdl(String command) throws DukeExceptionBase {
        assert (this.currDukeList != null) : "Duke List not initialised!";

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

    private void findTaskInTdl(String command) throws DukeExceptionBase {
        assert (this.currDukeList != null) : "Duke List not initialised!";

        if (command.length() < 6) {
            throw new DukeExceptionBase("You need to specify a keyword to find.");
        }

        String keywordStr = command.substring(5);

        String dukeOutput = this.currDukeList.findMatchingTaskInList(keywordStr);
        dukeSays(dukeOutput);
    }


}
