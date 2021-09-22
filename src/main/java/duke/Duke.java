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
     * Constructor for creating duke.
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
     * This method is required for running the file.
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        assert storage != null : "Storage needs to be initialised";
        assert ui != null : "Ui needs to be initialised";
        return Parser.handleCommand(input,tasks);
    }

    /**
     * This is the main method.
     *
     * @param args required for main method
     */
    public static void main(String[]args) {
        new Duke("data/duke.txt").run();
    }

}

