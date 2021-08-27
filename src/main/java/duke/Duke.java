package duke;

/**
 * Class that is a chat bot that can store tasks.
 */
public class Duke {
    private boolean isRunning = true;
    private enum Commands {list, done, todo, event, deadline, delete, find }
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class Constructor based on where the storage file path.
     *
     * @param filePath where the storage file is at.
     */
    public Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.parseFile());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Driver function that runs the chat bot.
     */
    public void run() {
        ui.greet();
        while(isRunning) {
            String instruction = ui.getInstruction();
            if(checkBye(instruction)) {
                break;
            }
            try {
                Parser.parse(instruction,this.ui, this.tasks);
            } catch (DukeException e) {
                ui.printErrorMessage(e);
            }
        }
        ui.sayFarewell();
    }

    /**
     * Function that checks whether the user wants to exit the program.
     *
     * @param instruction instruction given by the user
     * @return a boolean on whether the program should terminate
     */
    private boolean checkBye(String instruction){
        if(instruction.equalsIgnoreCase("bye")) {
            isRunning = false;
            storage.fileClear();
            for(int i = 0; i < tasks.getSize(); i++) {
                storage.writeToFile(tasks.get(i).toHistory());
            }
            return true;
        }
        return false;
    }



    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}