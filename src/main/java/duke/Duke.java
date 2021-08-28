package duke;

import duke.task.TaskList;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.exception.DukeException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class that handles the behaviour of the bot in response to user inputs
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

//    private static final Scanner sc = new Scanner(System.in);
//    private static boolean stop = false;
//    private static ArrayList<Task> taskList = new ArrayList<Task>();
    // Regex to check if string is numbers
    // private static Path p = Paths.get("./Users/yinruoyan/Documents/GitHub/ip/data/src/duke.txt");
//    private static Path p = Paths.get("./Users/yinruoyan/Documents/GitHub/ip/data/duke.txt");
//    private static Path p = Paths.get("../../../data/duke.txt");
//    private static final String filePath = String.valueOf(Paths.get(System.getProperty("user.home"), "data", "duke.txt"));
//    private static final String filePath = "./src/main/data/duke.txt";
    private static final String FILE_PATH = String.valueOf(Paths.get(System.getProperty("user.home"), "data", "dukeFile.txt"));

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
//        System.out.println("going into duke.run()");
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
//                ui.showError(e.getMessage());
            System.out.println("Caught the exception");
            System.out.print("Exception occurred: " + e);
            }
//            finally {
//                ui.showLine();
//            }
        }
    }
//    public void run() {
//        ui.showWelcome();
//
//        try {
//            Parser p = new Parser();
//            boolean isStop = false;
//            while (!isStop) {
//                String input = sc.nextLine();
//                p.parse(input);
//                if (!input.equals("list")) {
//                    this.storage.updateFile();
//                }
//                isStop = c.isStop();
//            }
//        }
//        catch (DukeException e) {
//            System.out.println("Caught the exception");
//            System.out.print("Exception occurred: " + e);
//        }
//    }

    /**
     * Method to call when user wishes to see a list of all events
     */
    public static void main(String[] args) {
//        new Duke("data/tasks.txt").run();
//        new Duke(String.valueOf(Paths.get(filePath))).run();
        new Duke(FILE_PATH).run();
    }
}