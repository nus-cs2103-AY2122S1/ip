package duke;

import java.util.Scanner;


/**
 * Class includes methods required for running this project.
 */
public class Duke {
    private Scanner in = new Scanner(System.in);
    private CompilationOfFiles storage;
    private ListOfTasks tasks;
    private Ui ui;

    /**
     * Creates an instance of duke.
     *
     * @param filepath refers to path of the file
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new CompilationOfFiles(filepath);
        tasks = new ListOfTasks();
        storage.loadAndSaveFile(tasks);
    }
    /**
     * Runs the file.
     */
    public void run() {
        ui.start();
        boolean isEnd = false;
        while (!isEnd) {
            String command = in.nextLine();
            Parser.handleCommand(command, tasks);
            isEnd = Parser.isValid();
        }
        ui.end();
    }

    /**
     * Obtains response from parser.
     *
     * @param input refers to input typed by user
     * @return response from Duke
     *
     */
    public String getResponse(String input) {
        assert storage != null : "Storage needs to be initialised";
        assert ui != null : "Ui needs to be initialised";
        return Parser.handleCommand(input, tasks);
    }

}

