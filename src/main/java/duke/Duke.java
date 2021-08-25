package duke;

import java.util.Scanner;

import java.lang.String;

import java.io.File;

public class Duke {
    private static Ui ui = new Ui();
    private static Storage storage = new Storage();
    private static TaskList taskList = new TaskList();
    public static File file = new File("data/list.txt");

    public static void main(String[] args) {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        storage.checkExistence();

        ui.showFileLocation(file.getAbsolutePath());

        taskList.initialise(file, storage);

        Parser.parse();
    }


}
