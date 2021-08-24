package duke;

public class Duke {
    UserInterface userInterface;
    TaskList list;
    Storage storage;

    public Duke() {
        this.userInterface = new UserInterface();
        this.list = new TaskList();
        this.storage = Storage.createStorage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        this.userInterface.printInitialGreeting();
        try {
            this.list = this.storage.load(this.list);
        } catch(DukeException e) {
            userInterface.showError(e);
        }
        this.runLoop();
        this.storage.save(this.list);
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
