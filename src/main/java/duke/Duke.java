package duke;

/**
 * The driver class of Duke.
 */
public class Duke {
    UserInterface userInterface;
    TaskList list;
    Storage storage;

    /**
     * A constructor method that creates the driver, with the default storage file path used.
     * @throws DukeException if the file is unable to be read.
     */
    public Duke() throws DukeException {
        this.userInterface = new UserInterface();
        this.list = new TaskList();
        this.storage = Storage.createStorage();
    }

    /**
     * A constructor method that creates the driver, with the input filepath used.
     * @param filePath the filepath of the file.
     * @throws DukeException if the file is unable to be read.
     */
    public Duke(String filePath) throws DukeException {
        this.userInterface = new UserInterface();
        this.list = new TaskList();
        this.storage = Storage.createStorage(filePath);
    }

    /**
     * The main driver method.
     */
    public static void main(String[] args) {
        try {
            Duke duke = new Duke("data/duke.txt");
            duke.run();
        } catch (DukeException e) {
            System.out.println("Unable to initialize database. Using default document to start up\n");
        }
        try {
            Duke duke = new Duke();
            duke.run();
        } catch (DukeException e) {
            System.out.println("Unable to initialize database.");
            System.exit(0);
        }
    }

    /**
     * The method that runs the process until a "bye" command is input.
     */
    public void run() {
        this.userInterface.printInitialGreeting();
        try {
            this.list = this.storage.load(this.list);
        } catch(DukeException e) {
            userInterface.showError(e);
        }
        this.runLoop();
        try {
            this.storage.save(this.list);
        } catch(DukeException e) {
            userInterface.showError(e);
        }
        this.userInterface.printGoodByeGreeting();
        System.exit(0);
    }

    public void runLoop() {
        String commandLine;
        boolean isExit = false;
        while (!isExit) {
            commandLine = this.userInterface.nextCommand();
            try {
                CommandResult commandResult = new Parser(this.list).parse(commandLine);
                userInterface.showResult(commandResult);
                isExit = commandResult.isExit();
            } catch (DukeException e) {
                userInterface.showError(e);
            }
        }
    }

}
