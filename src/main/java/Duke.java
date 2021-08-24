import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(File file) {
        this.ui = new Ui();
        this.storage = new Storage(file);
        try {
            this.taskList = new TaskList();
        } catch (Exception e) {

        }
    }

    public void run() throws DukeException {
        try {
            File dukeFile = new File("data/duke.txt");
            PrintWriter writer  = this.storage.load(dukeFile);

            String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

            System.out.println("Hello from\n" + logo);
            System.out.println("---------------------------------------------");
            System.out.println("Hello! I'm Duke, your personal task manager!\n" + "What can I do for you?");
            System.out.println("---------------------------------------------");

            Parser.parseCommand(taskList, dukeFile, writer);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        Files.createDirectories(Paths.get("data/"));
        File dukeFile = new File("data/duke.txt");
        new Duke(dukeFile).run();

    }
}

