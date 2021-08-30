package duke;

import java.util.Scanner;

public class Duke {
    private Scanner in = new Scanner(System.in);
    private CompilationOfFiles storage;
    private ListOfTasks tasks;
    private Ui ui;

    public Duke (String filepath) {
        ui = new Ui();
        storage = new CompilationOfFiles(filepath);
        tasks = new ListOfTasks();
        storage.loadAndSaveFile(tasks);
    }

    public void run() {
        ui.start();
        boolean isEnd = false;
        while (!isEnd) {
            String command = in.nextLine();
            isEnd = Parser.handleCommand(command,tasks);
        }
        ui.end();
    }
    public static void main (String[]args) {
        new Duke("data/duke.txt").run();
    }

}

