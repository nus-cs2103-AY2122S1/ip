package dino;

import dino.command.Command;
import dino.exception.*;
import dino.task.TaskList;
import dino.util.Parser;
import dino.util.Ui;
import dino.util.Storage;

/**
 * Represents a Personal Assistant Chatbot name Dino
 * It helps the user to keep track of various things
 */
public class Dino {
    private TaskList taskList;
    private final Storage storage;
    private Ui ui;
    private boolean isExited = false;

    /**
     * Constructs a Dino object
     * Loads storage file from the path specified, if available
     *
     * @param filePath path to the storage file
     */
    public Dino(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.loadStorage());
        this.ui = new Ui();
    }

    /**
     * Runs the program
     * Exits only if the user enters "bye"
     */
    public void run() {
        ui.greeting();
        while(!this.isExited) {
            String input = ui.readNextLine();
            if (input.equals("bye")) isExited = true;
            else {
                try {
                    Command cmd = Parser.parse(input);
                    cmd.execute(this.storage, this.taskList);
                } catch (DinoException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        ui.processExit(this.storage, this.taskList);
    }

    /**
     * Drives the start of the program
     */
    public static void main(String[] args) {
        new Dino("data/dino.txt").run();
    }
}
