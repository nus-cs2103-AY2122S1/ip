package myjournal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class to create chatBot called MyJournal.
 *
 * @author Felissa Faustine
 */
public class MyJournal {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public MyJournal(String filepath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        String input;
        Scanner reader = new Scanner(System.in);
        ui.welcomeMessage();
        while (true) {
            input = reader.nextLine();
            Scanner currLine = new Scanner(input);
            if (input.equals("bye")) {
                break;
            } else {
                parser.parse(currLine, tasks);
            }
            storage.saveFile(tasks.toString());
        }
        ui.goodByeMessage();
    }

    /**
     * The main method of the MyJournal class.
     *
     * @param args An input of an array of strings.
     */
    public static void main(String[] args) {
        try {
            new MyJournal("./tasks.txt").run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}