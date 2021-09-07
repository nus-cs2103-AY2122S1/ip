package lania;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import lania.command.Command;
import lania.exception.LaniaException;
import lania.task.TaskList;

/**
 * Represents the chatbot Lania that helps manage your tasks.
 */
public class Lania {

    /** Contains the task list */
    private TaskList tasks;
    /**  Deals with interactions with the user */
    private Ui ui;
    /** Deals with loading tasks from the file and saving tasks in the file */
    private Storage storage;
    /** Deals with making sense of the user command */
    private Parser parser;
    /** Deals with keeping track of previous commands */
    private Log log;

    /**
     * Constructor for the chat bot that is invoked by the GUI.
     */
    public Lania() {
        ui = new Ui();
        storage = new Storage("data/lania.txt");
        parser = new Parser();
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showError();
            e.printStackTrace();
        }
    }

    /**
     * Constructor for the Lania object.
     *
     * @param filePath The location of the file in which data is stored
     */
    public Lania(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        log = new Log();
    }

    /**
     * Main part of the program. First, it tries to load the file
     * if it exists and display its contents. It then copies the
     * contents over into a TaskList.
     *
     * After greeting the user, the
     * program continuously waits for input unless the command 'bye'
     * is given which closes exits the program.
     *
     */
    public void run() {
        try {
            // tasks loaded from the storage should not be null
            assert storage.load() != null;
            tasks = storage.load();
        } catch (IOException e) {
            ui.showError();
            e.printStackTrace();
        }
        ui.showListMessage(tasks);
        ui.showGreetingMessage();
        boolean isExit = false;
        Scanner s = new Scanner(System.in);
        while (!isExit) {
            try {
                String input = s.nextLine();
                Command c = parser.parse(input);
                c.execute(tasks, storage, ui, log);
                isExit = c.isExit();
            } catch (LaniaException e) {
                ui.showLaniaException(e);
            } catch (DateTimeParseException e) {
                ui.showDateTimeException();
            }
        }
        s.close();
    }

    /**
     * Lania object is created and starts running.
     *
     * @param args The command line arguments. Not required here.
     **/
    public static void main(String[] args) {
        Lania lania = new Lania("data/lania.txt");
        lania.run();
    }
}
